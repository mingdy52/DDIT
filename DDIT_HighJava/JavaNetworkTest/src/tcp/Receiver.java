package tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 소켓에서 메시지를 받아서 화면에 출력하기 위한 스레드 클래스
 * @author 306-16
 *
 */
public class Receiver extends Thread {
	private DataInputStream dis;
	
	public Receiver(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
