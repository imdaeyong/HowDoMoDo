package com.ssafy.howdomodo.ui.mypage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ssafy.howdomodo.R
import com.ssafy.howdomodo.`object`.UserCollection
import com.ssafy.howdomodo.ui.login.LoginActivity
import com.ssafy.howdomodo.ui.mypage.privates.PrivateActivity
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MypageFragment : Fragment() {
    private val mypageViewModel: MyPageViewModel by viewModel()
    private lateinit var sharedPreferences: SharedPreferences

    lateinit var myPageAdapter: MyPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frag_my_tv_nickName.text = UserCollection.userNick
        frag_my_tv_email.text = UserCollection.userEmail
        frag_my_tv_name.text = UserCollection.userName

        frag_my_tv_update.setOnClickListener {
            val mypageJsonObject = JSONObject()
            mypageJsonObject.put("userCode", UserCollection.userCode)
            val body = JsonParser.parseString(mypageJsonObject.toString()) as JsonObject

//             정보조회 api 통신
            mypageViewModel.userInfo(UserCollection.userCode.toInt())
        }
        observe()

        myPageAdapter = MyPageAdapter(object : MyPageViewHolder.ClickListener {
            override fun itemClick(position: Int) {
                when (myPageAdapter.mineList[position]) {
                    "로그아웃" -> {
                        val intent = Intent(activity, LoginActivity::class.java)

                        sharedPreferences =
                            activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.commit()
                        startActivity(intent)
                        activity!!.finish()
                    }
                    "오픈소스 라이선스" -> {
                        val intent = Intent(activity, PrivateActivity::class.java)
                        intent.putExtra("title", myPageAdapter.mineList[position])
                        intent.putExtra("text", "준비 중입니다.")
                        startActivity(intent)
                    }
                    "이용약관" -> {
                        val intent = Intent(activity, PrivateActivity::class.java)
                        intent.putExtra("title", myPageAdapter.mineList[position])
                        intent.putExtra(
                            "text", "제1장 환영합니다!\n" +
                                    "\n" +
                                    "제1조 목적\n" +
                                    "하우두무두(이하 ‘서비스’)를 이용해 주셔서 감사합니다. 서비스는 여러분이 서비스가 제공하는 다양한 모바일 서비스에 더 가깝게 다가갈 수 있도록 통합 적용될 수 있는 카카오 통합 약관(이하 ‘본 약관’)을 마련하였습니다. 본 약관은 여러분이 서비스를 이용하는 데 필요한 권리, 의무 및 책임사항, 이용조건 및 절차 등 기본적인 사항을 규정하고 있으므로 조금만 시간을 내서 주의 깊게 읽어주시기 바랍니다.\n" +
                                    "\n" +
                                    "제2조 약관의 효력\n" +
                                    "본 약관의 내용은 서비스가 제공하는 개별 서비스 또는 서비스 초기 화면에 게시하거나 기타의 방법으로 공지합니다.\n" +
                                    "서비스는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제 1항과 같은 방법으로 공지 또는 통지함으로서 효력을 발생합니다.\n" +
                                    "\n" +
                                    "제3조 약관 이외의 준칙\n" +
                                    "이 약관에 명시되지 않은 사항이 전기 통시 기본법, 전기통신 사업법, 기타 관련 법령에 규정되어 있는 경우 그 규정에 따릅니다.\n" +
                                    "\n" +
                                    "제2장 서비스의 이용\n" +
                                    "\n" +
                                    "제4조 이용계약의 체결\n" +
                                    "회원 가입 시 이 계약이 체결된 것으로 간주합니다.\n" +
                                    "\n" +
                                    "제5조 용어의 정의\n" +
                                    "이 약관에서 사용하는 용어의 정의는 다음과 같습니다.\n" +
                                    "회원 : ‘하우두무두’와 서비스 이용에 관한 계약을 체결한 자\n" +
                                    "아이디(ID) : 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 ‘하우두무두’가 승인하는 문자와 숫자의 조합\n" +
                                    "비밀번호 : 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합\n" +
                                    "닉네임 : ‘이비’ 캐릭터의 닉네임으로, 한글 6글자의 조합\n" +
                                    "\n" +
                                    "제6조 이용신청\n" +
                                    "회원가입은 온라인으로 가입신청 양식에 기록하여 제출함으로써 이용신청을 할 수 있습니다.\n" +
                                    "가입희망 회원은 반드시 1개의 ID만 신청을 할 수 있습니다.\n" +
                                    "\n" +
                                    "제7조 회원가입의 승낙\n" +
                                    "‘하우두무두’의 회원 가입 신청 양식에 가입 희망 회원이 인터넷으로 제6조와 같이 신청하면 서비스는 바로 가입을 승인하여 서비스를 이용할 수 있습니다.\n" +
                                    "\n" +
                                    "제8조 서비스 제공의 중지\n" +
                                    "‘하우두무두’는 다음 각 호의 1에 해당하는 경우 서비스의 제공을 중지할 수 있습니다.\n" +
                                    "설비의 보수 등을 위하여 부득이한 경우\n" +
                                    "전기통신사업법에 규정된 기간통신사업자가 전기통신서비스를 중지하는 경우\n" +
                                    "기타 서비스가 서비스를 제공할 수 없는 사유가 발생한 경우\n" +
                                    "\n" +
                                    "제9조 ‘하우두무두’의 의무\n" +
                                    "계속적, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다하여야 합니다.\n" +
                                    "항상 회원의 신용정보를 포함한 개인신상정보의 보안에 대하여 관리에 만전을 기함으로서 회원의 정보보안에 최선을 다하여야 합니다.\n" +
                                    "\n" +
                                    "제10조 개인정보보호\n" +
                                    "서비스는 이용자의 정보수집시 서비스의 제공에 필요한 최소한의 정보를 수집합니다.\n" +
                                    "제공된 개인정보는 당해 이용자의 동의 없이 목적 외의 이용이나 제3자에게 제공할 수 없으며, 이에 대한 모든 책임은 서비스가 집니다.\n" +
                                    "회원은 언제든지 서비스가 가지고 있는 자신의 정보에 대해 열람 및 오류 정정을 요구할 수 있으며 서비스는 이에 대해 지체 없이 처리합니다.\n" +
                                    "\n" +
                                    "제11조 회원의 의무\n" +
                                    "회원은 관계법령, 이 약관의 규정, 이용안내 및 주의사항 등 서비스가 통지하는 사항을 준수하여햐 합니다.\n" +
                                    "회원은 승낙 없이 서비스를 이용하여 얻은 정보를 서비스의 사전 승낙 없이 복사, 복제, 변경, 번역, 출판, 방송 기타의 방법으로 사용하거나 이를 제공할 수 없습니다.\n" +
                                    "회원은 사전 승낙 없이 서비스를 이용하여 어떠한 영리 행위도 할 수 없습니다.\n" +
                                    "\n" +
                                    "제12조 게시물에 대한 권리·의무\n" +
                                    "게시물에 대한 저작권을 포함한 모든 권리 및 책임은 이를 게시한 회원에게 있습니다.\n" +
                                    "제13조 양도금지\n" +
                                    "회원이 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다."
                        )
                        startActivity(intent)
                    }
                    "개인정보처리방침" -> {
                        val intent = Intent(activity, PrivateActivity::class.java)
                        intent.putExtra("title", myPageAdapter.mineList[position])
                        intent.putExtra(
                            "text",
                            "1. 개인정보의 처리 목적 <하우두무두>(‘15.164.70.24:3456’이하 ‘하우두무두’) 은(는) 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다.\n" +
                                    "- 고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별.인증, 회원자격 유지.관리, 물품 또는 서비스 공급에 따른 금액 결제, 물품 또는 서비스의 공급.배송 등\n" +
                                    "\n" +
                                    "\n" +
                                    "2. 개인정보의 처리 및 보유 기간\n" +
                                    "\n" +
                                    "① <하우두무두>(‘15.164.70.24:3456’이하 ‘하우두무두’) 은(는) 정보주체로부터 개인정보를 수집할 때 동의 받은 개인정보 보유․이용기간 또는 법령에 따른 개인정보 보유․이용기간 내에서 개인정보를 처리․보유합니다.\n" +
                                    "\n" +
                                    "② 구체적인 개인정보 처리 및 보유 기간은 다음과 같습니다.\n" +
                                    "아래 예시를 참고하여 개인정보 처리업무와 개인정보 처리업무에 대한 보유기간 및 관련 법령, 근거 등을 기재합니다.\n" +
                                    "(예시)- 고객 가입 및 관리 : 서비스 이용계약 또는 회원가입 해지시까지, 다만 채권․채무관계 잔존시에는 해당 채권․채무관계 정산시까지\n" +
                                    "- 전자상거래에서의 계약․청약철회, 대금결제, 재화 등 공급기록 : 5년\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "3. 개인정보의 제3자 제공에 관한 사항\n" +
                                    "\n" +
                                    "① <하우두무두>('15.164.70.24:3456'이하 '하우두무두')은(는) 정보주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.\n" +
                                    "\n" +
                                    "② <하우두무두>('15.164.70.24:3456')은(는) 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.\n" +
                                    "\n" +
                                    "\n" +
                                    "1. <아로정보기술(주)>\n" +
                                    "- 개인정보를 제공받는 자 : 아로정보기술(주)\n" +
                                    "- 제공받는 자의 개인정보 이용목적 : 비밀번호, 로그인ID\n" +
                                    "- 제공받는 자의 보유.이용기간: 회원 탈퇴 시까지\n" +
                                    "\n" +
                                    "\n" +
                                    "2. <아로정보기술(주)>\n" +
                                    "- 개인정보를 제공받는 자 : 아로정보기술(주)\n" +
                                    "- 제공받는 자의 개인정보 이용목적 : 자택주소\n" +
                                    "- 제공받는 자의 보유.이용기간: 회원 탈퇴 시까지\n" +
                                    "\n" +
                                    "\n" +
                                    "3. <아로정보기술(주)>\n" +
                                    "- 개인정보를 제공받는 자 : 아로정보기술(주)\n" +
                                    "- 제공받는 자의 개인정보 이용목적 : 현재 위치\n" +
                                    "- 제공받는 자의 보유.이용기간: 현재 위치 확인 뒤 경로 안내 후 즉시 파기\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "4. 개인정보처리 위탁\n" +
                                    "\n" +
                                    "① <하우두무두>('하우두무두')은(는) 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.\n" +
                                    "\n" +
                                    "1. <>\n" +
                                    "- 위탁받는 자 (수탁자) :\n" +
                                    "- 위탁하는 업무의 내용 :\n" +
                                    "- 위탁기간 :\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "② <하우두무두>('15.164.70.24:3456'이하 '하우두무두')은(는) 위탁계약 체결시 개인정보 보호법 제25조에 따라 위탁업무 수행목적 외 개인정보 처리금지, 기술적․관리적 보호조치, 재위탁 제한, 수탁자에 대한 관리․감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.\n" +
                                    "\n" +
                                    "③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.\n" +
                                    "5. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다.\n" +
                                    "\n" +
                                    "① 정보주체는 하우두무두(‘15.164.70.24:3456’이하 ‘하우두무두) 에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.\n" +
                                    "1. 개인정보 열람요구\n" +
                                    "2. 오류 등이 있을 경우 정정 요구\n" +
                                    "3. 삭제요구\n" +
                                    "4. 처리정지 요구\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "6. 처리하는 개인정보의 항목 작성\n" +
                                    "\n" +
                                    "① <하우두무두>('15.164.70.24:3456'이하 '하우두무두')은(는) 다음의 개인정보 항목을 처리하고 있습니다.\n" +
                                    "\n" +
                                    "1<회원가입 및 관리>\n" +
                                    "- 필수항목 : 비밀번호, 로그인ID\n" +
                                    "- 선택항목 :\n" +
                                    "\n" +
                                    "\n" +
                                    "2<자주 가는 장소 등록 및 수정>\n" +
                                    "- 필수항목 : 자택주소\n" +
                                    "- 선택항목 :\n" +
                                    "\n" +
                                    "\n" +
                                    "3<현재 위치 정보 확인>\n" +
                                    "- 필수항목 : 현재 위치 정보\n" +
                                    "- 선택항목 :\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "7. 개인정보의 파기<하우두무두>('하우두무두')은(는) 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.\n" +
                                    "\n" +
                                    "-파기절차\n" +
                                    "이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.\n" +
                                    "\n" +
                                    "-파기기한\n" +
                                    "이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "8. 개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항\n" +
                                    "\n" +
                                    "① 하우두무두 은 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠기(cookie)’를 사용합니다. ② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다. 가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다. 나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다. 다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.\n" +
                                    "\n" +
                                    "\n" +
                                    "9. 개인정보 보호책임자 작성\n" +
                                    "\n" +
                                    "\n" +
                                    "① 하우두무두(‘15.164.70.24:3456’이하 ‘하우두무두) 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\n" +
                                    "\n" +
                                    "▶ 개인정보 보호책임자\n" +
                                    "성명 :최연호\n" +
                                    "직책 :Product Manager\n" +
                                    "직급 :Product Manager\n" +
                                    "연락처 :010-8645-2275, dusgh4321@naver.com,\n" +
                                    "※ 개인정보 보호 담당부서로 연결됩니다.\n" +
                                    "\n" +
                                    "▶ 개인정보 보호 담당부서\n" +
                                    "부서명 :Plan\n" +
                                    "담당자 :조윤호, 강주희\n" +
                                    "연락처 :010-5090-0129, chyn7@naver.com,\n" +
                                    "② 정보주체께서는 하우두무두(‘15.164.70.24:3456’이하 ‘하우두무두) 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 하우두무두(‘15.164.70.24:3456’이하 ‘하우두무두) 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "10. 개인정보 처리방침 변경\n" +
                                    "\n" +
                                    "①이 개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "11. 개인정보의 안전성 확보 조치 <하우두무두>('하우두무두')은(는) 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.\n" +
                                    "\n" +
                                    "1. 개인정보에 대한 접근 제한\n" +
                                    "개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여,변경,말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다."
                        )
                        startActivity(intent)
                    }
                    "회원탈퇴" -> {
                        // movieTitle을 Request 보내서 긍,부정 데이터를 받아온다.
//                        Toast.makeText(view.context,"클릭",Toast.LENGTH_SHORT).show()
                        val dialog = MyDialog(view.context)
                        dialog.setOnOKClickedListener { content ->
                            if (content == "확인") {
                                // 탈퇴
                                sharedPreferences =
                                    activity!!.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.clear()
                                editor.commit()
                                mypageViewModel.userDelete(UserCollection.userCode.toInt())
                            } else {
                                Toast.makeText(view.context, "취소", Toast.LENGTH_SHORT).show()
                            }
                        }
                        dialog.start("탈퇴하시겠습니까?")
                    }
                    "비밀번호 재설정" -> {
                        val intent = Intent(activity, UpdatePwActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })

        frag_my_rv_content.adapter = myPageAdapter
        frag_my_rv_content.layoutManager = LinearLayoutManager(view.context)

    }

    private fun observe() {
        mypageViewModel.mypageResponse.observe(this, Observer {

            val intent = Intent(activity, MypageActivity::class.java)
            intent.putExtra("info", mypageViewModel.mypageResponse.value?.data)
            startActivity(intent)
        })
        mypageViewModel.errorToast.observe(this, Observer {
            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
        })

        mypageViewModel.successMessage.observe(this, Observer {
            if (it == "회원 탈퇴 성공") {
                val intent = Intent(this.context, LoginActivity::class.java)
                Toast.makeText(this.context, "탈퇴 완료", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        })
    }
}