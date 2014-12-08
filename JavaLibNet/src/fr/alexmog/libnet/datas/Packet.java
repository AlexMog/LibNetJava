package fr.alexmog.libnet.datas;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import fr.alexmog.libnet.utils.ArrayConverter;

public class Packet {
	private List<Byte> datas;
	private int reader = 0;
	private byte[] formatted = null;
	
	public Packet(List<Byte> datas) {
		this.datas = datas;
	}
	
	public Packet() {
		this.datas = new ArrayList<Byte>();
	}
	
	public void format() {
		if (formatted != null)
			return ;
		formatted = new byte[datas.size()];
		for (int i = 0; i < datas.size(); ++i) {
			formatted[i] = datas.iterator().next();
		}
	}
	
	public byte[] toArrayOfBytes() {
		byte[] array = new byte[datas.size()];
		for (int i = 0; i < datas.size() + 8; ++i)
		{
			array[i] = datas.iterator().next().byteValue();
		}
		return array;
	}
	
	
	public void clear() {
		datas.clear();
		reader = 0;
	}
	
	public void resetReader() {
		reader = 0;
	}
	
	public void addData(int data) {
		System.out.println(data);
		byte[] buff = ByteBuffer.allocate(4).putInt(data).array();
		int cleanedValue = ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getInt();
		System.out.println(cleanedValue);
		System.out.println();
		buff = ByteBuffer.allocate(4).putInt(cleanedValue).array();
		for (byte b : buff)
			System.out.println(b);
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public int getInt() {
		format();
		int value = ByteBuffer.wrap(formatted).getInt(reader);
		reader += 4;
		return value;
	}
	
	public void addData(long data) {
		byte[] buff = ByteBuffer.allocate(8).putLong(data).array();
		long cleanedValue = ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getLong();
		buff = ByteBuffer.allocate(8).putLong(cleanedValue).array();
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public long getLong() {
		format();
		long value = ByteBuffer.wrap(formatted).getLong(reader);
		reader += 8;
		return value;
	}
	
	public void addData(short data) {
		byte[] buff = ByteBuffer.allocate(2).putShort(data).array();
		short cleanedValue = ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getShort();
		buff = ByteBuffer.allocate(2).putShort(cleanedValue).array();
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public short getShort() {
		format();
		short value = ByteBuffer.wrap(formatted).getShort(reader);
		reader += 2;
		return value;
	}
	
	public void addData(char data) {
		byte[] buff = ByteBuffer.allocate(2).putChar(data).array();
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public char getChar() {
		format();
		char value = ByteBuffer.wrap(formatted).getChar(reader);
		reader += 2;
		return value;
	}
	
	public void addData(byte data) {
		datas.add(data);
	}
	
	public byte getByte() {
		format();
		byte value = formatted[reader];
		reader += 1;
		return value;
	}
	
	public void addData(boolean data) {
		datas.add((byte)(data ? 1 : 0));
	}
	
	public boolean getBool() {
		format();
		boolean value = (formatted[reader] == 1 ? true : false);
		reader += 1;
		return value;
	}
	
	public void addData(double data) {
		byte[] buff = ByteBuffer.allocate(8).putDouble(data).array();
		double cleanedValue = ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getDouble();
		buff = ByteBuffer.allocate(8).putDouble(cleanedValue).array();
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public double getDouble() {
		format();
		double value = ByteBuffer.wrap(formatted).getDouble(reader);
		reader += 8;
		return value;
	}
	
	public void addData(float data) {
		byte[] buff = ByteBuffer.allocate(4).putFloat(data).array();
		float cleanedValue = ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getFloat();
		buff = ByteBuffer.allocate(4).putFloat(cleanedValue).array();
		datas.addAll(ArrayConverter.toByteList(buff));
	}
	
	public float getFloat() {
		format();
		float value = ByteBuffer.wrap(formatted).getFloat(reader);
		reader += 4;
		return value;
	}
	
	public void addData(byte[] data) {
		this.addData((int)data.length);
		datas.addAll(ArrayConverter.toByteList(data));
	}
	
	public byte[] getByteArray() {
		format();
		int size = this.getInt();
		byte[] ret = new byte[size];
		for (int i = 0; i < size; ++i) {
			ret[i] = formatted[reader++];
		}
		return ret;
	}
	
	public void addData(List<Byte> data) {
		this.addData((int)data.size());
		datas.addAll(data);
	}
	
	public List<Byte> getByteList() {
		format();
		return ArrayConverter.toByteList(this.getByteArray());
	}
	
	public void addData(String str) {
		this.addData((int)str.length());
		datas.addAll(ArrayConverter.toByteList(str.getBytes()));
	}
	
	public String getString() {
		format();
		int size = this.getInt();
		String ret = "";
		for (int i = 0; i < size; ++i)
			ret += this.getChar();
		return ret;
	}
}
