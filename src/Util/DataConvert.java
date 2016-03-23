package Util;

public class DataConvert {

	public static String bytesToStringUTFCustom(byte[] bytes) {
		
		 char[] buffer = new char[bytes.length >> 1];
		
		 for(int i = 0; i < buffer.length; i++) {
		
		  int bpos = i << 1;
		
		  char c = (char)(((bytes[bpos]&0x00FF)<<8) + (bytes[bpos+1]&0x00FF));
		
		  buffer[i] = c;
		
		 }
		
		 return new String(buffer);
		
		}

}
