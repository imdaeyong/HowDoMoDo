import tensorflow as tf
import torch

from transformers import BertTokenizer
from transformers import BertForSequenceClassification, AdamW, BertConfig
from torch.utils.data import TensorDataset, DataLoader, RandomSampler, SequentialSampler
from keras.preprocessing.sequence import pad_sequences
from sklearn.model_selection import train_test_split

import pandas as pd
import numpy as np
import random
import time
import datetime

class npmodel():

    instance = None
    model = None
    tokenizer = None
    device = None

    def __new__(cls):
        if not cls.instance:
            cls.instance = object.__new__(cls)
            print("instance 생성!")
        return cls.instance

    @classmethod
    def init(cls):
        # GPU 디바이스 이름 구함
        #device_name = tf.test.gpu_device_name()

        # GPU 디바이스 이름 검사
        #if device_name == '/device:GPU:0':
        #    print('Found GPU at: {}'.format(device_name))
        #else:
        #    raise SystemError('GPU device not found')

        if torch.cuda.is_available():
            cls.device = torch.device("cuda")
            print('There are %d GPU(s) available.' % torch.cuda.device_count())
        #    print('We will use the GPU:', torch.cuda.get_device_name(0))
        else:
            cls.device = torch.device("cpu")
            print('No GPU available, using the CPU instead.')

        cls.model = BertForSequenceClassification.from_pretrained('/home/ubuntu/app/s03p23a305/backend_api/bigdata/model/')
        #cls.model.cuda()

        cls.tokenizer = BertTokenizer.from_pretrained('bert-base-multilingual-cased', do_lower_case=False)

    @classmethod
    def convert_input_data(cls, sentences):

        # BERT의 토크나이저로 문장을 토큰으로 분리
        tokenized_texts = [cls.tokenizer.tokenize(sent) for sent in sentences]

        # 입력 토큰의 최대 시퀀스 길이
        MAX_LEN = 128

        # 토큰을 숫자 인덱스로 변환
        input_ids = [cls.tokenizer.convert_tokens_to_ids(x) for x in tokenized_texts]

        # 문장을 MAX_LEN 길이에 맞게 자르고, 모자란 부분을 패딩 0으로 채움
        input_ids = pad_sequences(input_ids, maxlen=MAX_LEN, dtype="long", truncating="post", padding="post")

        # 어텐션 마스크 초기화
        attention_masks = []

        # 어텐션 마스크를 패딩이 아니면 1, 패딩이면 0으로 설정
        # 패딩 부분은 BERT 모델에서 어텐션을 수행하지 않아 속도 향상
        for seq in input_ids:
            seq_mask = [float(i > 0) for i in seq]
            attention_masks.append(seq_mask)

        # 데이터를 파이토치의 텐서로 변환
        inputs = torch.tensor(input_ids)
        masks = torch.tensor(attention_masks)

        return inputs, masks

    # 문장 테스트
    @classmethod
    def test_sentences(cls, sentences):

        # 평가모드로 변경
        cls.model.eval()

        # 문장을 입력 데이터로 변환
        inputs, masks = cls.convert_input_data(sentences)

        # 데이터를 GPU에 넣음
        b_input_ids = inputs.to(cls.device)
        b_input_mask = masks.to(cls.device)

        # 그래디언트 계산 안함
        with torch.no_grad():
            # Forward 수행
            outputs = cls.model(b_input_ids, token_type_ids=None, attention_mask=b_input_mask)

        # 로스 구함
        logits = outputs[0]

        # CPU로 데이터 이동
        logits = logits.detach().cpu().numpy()

        return np.argmax(logits)
