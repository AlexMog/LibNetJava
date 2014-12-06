package fr.alexmog.libnet.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class TcpSocket extends Socket {
	private InputStream is = null;
	private OutputStream os = null;
	
	public TcpSocket() {
		super();
	}
	
	public TcpSocket(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
	
	// IT WORKS! Need improuvement...
	public void send(List<Byte> datas) throws IOException {
		byte[] bi = ByteBuffer.allocate(8).putLong(datas.size()).array();
		long size = ByteBuffer.wrap(bi).order(ByteOrder.LITTLE_ENDIAN).getLong();
		bi = ByteBuffer.allocate(8).putLong(size).array();
		if (os == null)
			os = this.getOutputStream();
		byte[] finalSend = new byte[datas.size() + 8];
		for (int i = 0; i < datas.size() + 8; ++i)
		{
			if (i < 8) {
				finalSend[i] = bi[i];
			} else {
				finalSend[i] = datas.iterator().next().byteValue();
			}
		}
		os.write(finalSend);
		os.flush();
	}
}
