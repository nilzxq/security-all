package main.java.com.nilzxq.security.md;

import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SecurityMD {
	private static String src = "nilzxq security base64";
	public static void main(String[] args) {
		
		jdkMD5();
		jdkMD2();
		bcMD4();
		bcMD5();
		ccMD5();
		ccMD2();
	}
	
	public static void jdkMD5(){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] md5Bytes=md.digest(src.getBytes());
			System.out.println("JDK MD5:"+Hex.encodeHexString(md5Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void jdkMD2(){
		try {
			MessageDigest md=MessageDigest.getInstance("MD2");
			byte[] md5Bytes=md.digest(src.getBytes());
			System.out.println("JDK MD2:"+Hex.encodeHexString(md5Bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bcMD5(){
		Digest digest=new MD5Digest();
		//要被处理的内容/从哪开始/长度
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] md5Bytes=new byte[digest.getDigestSize()];
		//进行摘要输出的内容/偏移量
		digest.doFinal(md5Bytes,0);
		System.out.println("BC MD5:"+org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
	}
	
	public static void bcMD4(){
		try {
		Security.addProvider(new BouncyCastleProvider());
			MessageDigest md=MessageDigest.getInstance("MD4");
			byte[] md4Bytes=md.digest(src.getBytes());
			System.out.println("BC MD4:"+Hex.encodeHexString(md4Bytes));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Digest digest=new MD4Digest();
//		//要被处理的内容/从哪开始/长度
//		digest.update(src.getBytes(),0,src.getBytes().length);
//		byte[] md4Bytes=new byte[digest.getDigestSize()];
//		//进行摘要输出的内容/偏移量
//		digest.doFinal(md4Bytes,0);
//		System.out.println("BC MD4:"+org.bouncycastle.util.encoders.Hex.toHexString( md4Bytes));
	}
	
	public static void ccMD5(){
		System.out.println("CC MD5:"+DigestUtils.md5Hex(src.getBytes()));
	}
	public static void ccMD2(){
		System.out.println("CC MD2:"+DigestUtils.md2Hex(src.getBytes()));
	}
}
