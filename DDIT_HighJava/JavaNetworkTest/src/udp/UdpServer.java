package udp;
/*
 * 1. 비연결지향
 * 2. 데이터 신뢰성 보장 x
 * 3. tcp에 비해 전송 속도가 빠름
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket ds;
	private DatagramPacket dp;
	private byte[] msg; // 패킷 송수신을 위한 바이트 배열 선언
	
	public UdpServer(int port) {
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성한다.
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
				// 데이터 수신 생성자: 바이트 배열의 길이만큼 저장
			
			System.out.println("패킷 수신 대기중");
			
			// 패킷을 통해 데이터를 수신(receive)한다.
			ds.receive(dp);
			// udp 데이터그램을 받아서 이미 존재하는 DatagramPacket 객체에 저장
			
			System.out.println("패킷 수신 완료");
			
			// 수신한 패킷으로 부터 client의 IP주소와 Port를 얻는다.
			InetAddress iAddr = dp.getAddress();
			int port = dp.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); // 시간문자열을 byte배열로 변환한다.
			
			// 패킷을 생성해서 client에게 전송(send)한다.
			dp = new DatagramPacket(msg, msg.length, iAddr, port);
					// 데이터 송신 생성자: address와 port로 바이트 배열의 길이만큼 저장
			
			ds.send(dp); // 전송시작
			// udp 데이터그램을 전송하는 메서드
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer(7777).start();
	}
	
}
