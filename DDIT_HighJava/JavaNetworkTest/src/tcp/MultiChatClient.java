package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	Scanner scan = new Scanner(System.in);
	private String name; // 대화명
	
	// 시작 메서드
	public void clientStart() {
		// 대화명 입력받기
		System.out.println("대화명 >> ");
		name = scan.next();
		
		Socket socket = null;
		
		try {
			String serverIp = "192.168.36.2";
			socket = new Socket(serverIp, 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 스레드 생성
			ClientSender sender = new ClientSender(socket, name);
			sender.start();
			
			//수신용 스레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	class ClientSender extends Thread {
		private DataOutputStream dos;
		private String name;
		private Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.name = name;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				// 시작하자 마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				// 키보드로 입력받은 메시지를 서버로 전송
				while(dos != null) {
					dos.writeUTF(scan.nextLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class ClientReceiver extends Thread {
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis!= null) {
				// 서버로부터 수신한 메시지 출력하기
				try {
					System.out.println(dis.readUTF());
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
