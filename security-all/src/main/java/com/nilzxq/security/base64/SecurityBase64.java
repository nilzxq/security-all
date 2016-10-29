package main.java.com.nilzxq.security.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author nilzxq java三种实现base64的加解密方法，不推荐使用jdk
 * 
 */
public class SecurityBase64 {
	private static String src = "nilzxq security base64";

	public static void main(String[] args) {
		// jdkBase64();
		// commonsCodesBase64();
		bouncyCastleBase64();
	}

	/**
	 * jdk
	 */
	public static void jdkBase64() {
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(src.getBytes());
		System.out.println("encode:" + encode);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// new String()
			System.out.println("decode:"
					+ new String(decoder.decodeBuffer(encode)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * cc
	 */
	public static void commonsCodesBase64() {
		byte[] encodeByte = Base64.encodeBase64(src.getBytes());
		System.out.println("encode:" + new String(encodeByte));
		byte[] decodeByte = Base64.decodeBase64(src.getBytes());
		System.out.println("decode" + new String(decodeByte));
	}

	/**
	 * bb
	 */
	public static void bouncyCastleBase64() {
		byte[] encodeByte = org.bouncycastle.util.encoders.Base64.encode(src
				.getBytes());
		System.out.println("encode:" + new String(encodeByte));
		byte[] decodeByte = org.bouncycastle.util.encoders.Base64
				.decode(encodeByte);
		System.out.println("decode:" + new String(decodeByte));
	}
}
