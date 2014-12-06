package fr.alexmog.libnet.net.test;

import java.util.ArrayList;
import java.util.List;

import fr.alexmog.libnet.net.TcpSocket;

public class SendTest {

	public static void main(String[] args) {
		System.out.println("Connecting...");
		try {
			TcpSocket socket = new TcpSocket("localhost", 4242);
			List<Byte> datas = new ArrayList<Byte>();
			for (int i = 0; i < 5; ++i)
				datas.add((byte) 'c');
			System.out.println("Sending datas...");
			socket.send(datas);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
