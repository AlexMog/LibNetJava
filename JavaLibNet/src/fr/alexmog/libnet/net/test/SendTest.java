package fr.alexmog.libnet.net.test;

import java.util.ArrayList;
import java.util.List;

import fr.alexmog.libnet.net.TcpSocket;

public class SendTest {

	public static void main(String[] args) {
		System.out.println("Connecting...");
		try {
			@SuppressWarnings("resource")
			TcpSocket socket = new TcpSocket("localhost", 4242);
			List<Byte> datas = new ArrayList<Byte>();
			for (int i = 0; i < 5; ++i)
				datas.add((byte) 'c');
			datas.add((byte) '\0');
			System.out.println("Sending datas...");
			socket.send(datas);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
