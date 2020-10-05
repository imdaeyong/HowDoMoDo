package com.ssafy.howdomodo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.ssafy.howdomodo.model.HelloMessage;
import com.ssafy.howdomodo.model.MessageVO;

@RestController
public class MessageHandler {
	@MessageMapping("/hello") // 설정한 prefix를 포함하면 /api/hello 이다.
	@SendTo("/topic/roomId") // 전달하려는 곳의 subscribe
	public MessageVO broadcasting(HelloMessage message) throws Exception {
		Thread.sleep(1000);
		return new MessageVO("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
//	@MessageMapping("/hello") // 설정한 prefix를 포함하면 /api/hello 이다.
//	@SendTo("/topic/roomId") // 전달하려는 곳의 subscribe
//	public MessageVO broadcasting(MessageVO message) throws Exception {
//		System.out.println("message: " + message.getContent());
//		return message;
//	}
	
	// 각각의 메세지 유형에 따라 Mapping을 추가해줄 수 있다.
	@MessageMapping("/out") // 설정한 prefix를 포함하면 /app/out 이다.
	@SendTo("/topic/out") // 전달하려는 곳의 subscribe
	public String outroom(String message) throws Exception {
		System.out.println("out message: " + message);
		return message;
	}
	
	@MessageMapping("/in") // 설정한 prefix를 포함하면 /app/in 이다.
	@SendTo("/topic/in") // 전달하려는 곳의 subscribe
	public String inroom(String message) throws Exception {
		System.out.println("in message: " + message);
		return message;
	}

}
