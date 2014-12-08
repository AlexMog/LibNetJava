package fr.alexmog.libnet.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayConverter {
	public static List<Byte> toByteList(byte[] array) {
	    if (array==null) {
	       return new ArrayList<Byte>(0);
	    } else {
	       int size = array.length;
	       List<Byte> list = new ArrayList<Byte>(size);
	       for(int i = 0; i < size; i++) {
	          list.add(array[i]);
	       }
	       return list;
	    }
	}
}
