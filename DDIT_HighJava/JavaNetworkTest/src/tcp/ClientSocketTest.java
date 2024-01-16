package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String serverIp = "192.168.36.37";
		// 자기 자신 컴퓨터(로컬호스트)를 나타내는 방법
		// IP : 127.0.0.1
		// 호스트 이름 : localhost
		
		System.out.println(serverIp + "서버에 접속 중입니다.");
		// Socket 객체를 생성함고 동시에 연결 요청을 하려면 
		// 생성자의 매개값으로 서버의 IP주소와 바인딩 포트 변호를 제공한다.
		// 외부 서버에 접속하려면 localhost대신에 정확한 IP주소를 입력하면 된다.
		
		// 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 연결이 되면 이후의 명령이 실행된다. 
		System.out.println("연결되었습니다.");
		
		// 서버에서 보내온 메시지 받기
		// 메시지를 받기 위해 InputStream객체를 생성한다.
		// Socket의 getInputStream() 메서드 이용
		InputStream is  = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		
		// 서버로부터 받은 메시지 출력하기
		System.out.println("받은 메시지 : " + ois.readUTF());
		System.out.println("연결 종료");
		
		ois.close();
		
	}
	
}
