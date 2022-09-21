package kr.or.ddit.common.myIdPwSearch.service;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.myIdPwSearch.dao.IdPwSearchDAO;
import kr.or.ddit.common.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class IdPwSearchServiceImpl implements IdPwSearchService {

	@Inject
	IdPwSearchDAO idPwSearchDAO;

	@Inject
	PasswordEncoder passEncoder;

	@Override
	public MemberVO getMemberId(MemberVO member) {
		// TODO Auto-generated method stub
		return idPwSearchDAO.getMemberId(member);
	}

	@Override
	public MemberVO getMemberPass(MemberVO member) {
		// TODO Auto-generated method stub
		return idPwSearchDAO.getMemberPass(member);
	}

	@Override
	public void modifyMemberPass(MemberVO member) {
		// TODO Auto-generated method stub
		member.setNewPass(passEncoder.encode(member.getNewPass()));
		idPwSearchDAO.updateMemberPass(member);
	}

	@Override
	public void sendMail(MemberVO member) {

		String memId = member.getMemId();
		String memName = member.getMemName();
		String memMail = member.getMemMail();
		String memNewPass = member.getNewPass();
		
		log.info("**********{}", memNewPass);

		// 네이버일 경우 smtp.naver.com 을 입력합니다.
		// Google일 경우 smtp.gmail.com 을 입력합니다.
		String host = "smtp.naver.com";
		final String username = "qazz6797"; // 네이버 아이디를 입력해주세요. @nave.com은 입력하지 마시구요.
		final String password = "rhwjdgus123!@"; // 네이버 이메일 비밀번호를 입력해주세요.
		int port = 465; // 포트번호 // 메일 내용
		String recipient = memMail; // 받는 사람의 메일주소를 입력해주세요.
		String subject = "O-TION으로 부터 임시 비밀번호 메일을 받았습니다."; // 메일 제목 입력해주세요.
		String body = memName + "님의 O-TION 임시 비밀번호는 " + memNewPass + "입니다."
				+ "해당 비밀번호에 대한 변경은 로그인 후 마이페이지 - 비밀번호 변경에서 가능합니다"; // 메일 내용 입력해주세요.
		Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성 // SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host); // Session 생성
		Session session = Session.getDefaultInstance(props, new Authenticator(){
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true); // for debug
		Message mimeMessage = new MimeMessage(session); // MimeMessage 생성
		try {
			mimeMessage.setFrom(new InternetAddress("qazz6797@naver.com"));// 발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더
																			// 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요.
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); // 수신자셋팅 //.TO
																								// 외에 .CC(참조)
																								// .BCC(숨은참조) 도
																								// 있음
			mimeMessage.setSubject(subject); // 제목셋팅
			mimeMessage.setText(body); // 내용셋팅
			Transport.send(mimeMessage); // javax.mail.Transport.send() 이용
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
