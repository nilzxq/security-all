package main.java.com.nilzxq.security.aes;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SecurityAES {

	private static String src="nilzxq security base64";
	public static void main(String[] args){
		jdkAES();
		//bcAES();
	}
	public static void jdkAES(){
		//����key
		try {
			KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
			//keyGenerator.init(256);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey=keyGenerator.generateKey();
			byte[] keyBytes=secretKey.getEncoded();
			
			//key��ת��
			Key key=new SecretKeySpec(keyBytes, "AES");
			
			//����
			// �ӽ��ܵ��㷨/������ʽ/��䷽ʽ
			Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(cipher.ENCRYPT_MODE,key);
			byte[] result=cipher.doFinal(src.getBytes());
			System.out.println("jdk aes encrpt:"+Base64.encodeBase64String(result));
			
			//����
			cipher.init(cipher.DECRYPT_MODE,key);
			result=cipher.doFinal(result);
			System.out.println("jdk aes decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**public static void bcAES(){
		//����key
				try {
					Security.addProvider(new BouncyCastleProvider());
					
					KeyGenerator keyGenerator=KeyGenerator.getInstance("AES","BC");
					keyGenerator.getProvider();
					//keyGenerator.init(256);
					keyGenerator.init(new SecureRandom());
					SecretKey secretKey=keyGenerator.generateKey();
					byte[] keyBytes=secretKey.getEncoded();
					
					//key��ת��
					Key key=new SecretKeySpec(keyBytes, "AES");

					//����
					// �ӽ��ܵ��㷨/������ʽ/��䷽ʽ
					Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
					cipher.init(cipher.ENCRYPT_MODE,key);
					byte[] result=cipher.doFinal(src.getBytes());
					System.out.println("bc aes encrpt:"+Base64.encodeBase64String(result));
					
					//����
					cipher.init(cipher.DECRYPT_MODE,key);
					result=cipher.doFinal(result);
					System.out.println("bc aes decrpt:"+new String(result));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
	}	**/
}
