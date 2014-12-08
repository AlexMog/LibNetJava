package fr.alexmog.libnet.datas.test;

import fr.alexmog.libnet.datas.Packet;

public class TestPacket {

	public static void main(String[] args) {
		Packet packet = new Packet();
		byte[] array = {1, 2, 3};
		
		packet.addData((int)2);
		packet.addData(true);
		packet.addData((byte)1);
		packet.addData(array);
		packet.addData('c');
		packet.addData((double) 2);
		packet.addData((float)1.2);
		packet.addData((long)42);
		packet.addData((short)32);
		packet.addData("I like trains!");
		System.out.println("Int:" + packet.getInt());
		System.out.println("Boolean: " + packet.getBool());
		System.out.println("Byte: " + packet.getByte());
		System.out.print("Byte[]: ");
		for (byte b : packet.getByteArray())
			System.out.print(b);
		System.out.println();
		System.out.println("Char: " + packet.getChar());
		System.out.println("Double: " + packet.getDouble());
		System.out.println("Float: " + packet.getFloat());
		System.out.println("Long: " + packet.getLong());
		System.out.println("Short: " + packet.getShort());
		System.out.println("String: " + packet.getString());
		System.out.println("End.");
	}

}
