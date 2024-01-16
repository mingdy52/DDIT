package tcp; 
/*
 * 연결지향
 *  : 클라이언트와 서버가 연결된 상태에서 데이터를 주고받는 프로토콜.
 *    클라이언트가 연결 요청을 하고, 서버가 연결을 수락하면 통신 선로 고정됨.
 *  바인딩 포트 :  클라이언트가 서버에 접속할 포트
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {
	public static void main(String[] args) throws IOException {
//		소켓이란? 두 호스트 간 통신을 하기 위한 양 끝단(endpoint)를 말한다.
//		TCP 소켓 통신을 하기 위해 ServerSocket객체 생성
		
		ServerSocket server = new ServerSocket(7777);
//		ServerSocket server = new ServerSocket();
//		server.bind(new InetSocketAddress(7777));
		// ServerSocket: 클라이언트의 연결 요청을 기다리면서 요청이 오면 요청 수락 
		System.out.println("서버가 접속을 기다립니다.");
		
//		accept()메서드는 Client에서 연결 요청이 올 때까지 계속 기다린다.
//		연결 요청이 오면 Socket객체를 생성해서 Client의 Socket과 연결한다.
		Socket socket = server.accept();
		// Socket: 연결된 클라이언트와 통신
		// 클라이언트가 연결 요청을 하면 accept()는 클라이언트와 통신할 Socket을 만들고 리턴
		
//		----------------------------------------------------
//		이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.err.println("접속한 클라이언트 정보");
		System.err.println("주소 : " + socket.getInetAddress());
		
//		Client에 메시지 보내기
//		접속한 Socket의 getOutputStream()을 이용하여 구한다.
		
		OutputStream out = socket.getOutputStream();
		
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeUTF("바봉"); //메시지 보내기
		System.out.println("메시지를 보냈습니다.");
		
		oos.close();
		
		server.close();
	}
}
