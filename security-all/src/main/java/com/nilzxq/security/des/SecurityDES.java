package main.java.com.nilzxq.security.des;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SecurityDES {
	public static String src = "nilzxq security des";

	public static void main(String[] args) {
		jdkDES();
        bcDES();
	}

	public static void jdkDES() {
		try {
			// ����KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// keyת��
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// ����
			// �ӽ��ܵ��㷨/������ʽ/��䷽ʽ
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// ģʽ/ת����key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk des encrpt:" + Hex.encodeHexString(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("jdk des decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void bcDES(){
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			// ����KEY 
			//"BC"��ͬ��jdk
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			keyGenerator.getProvider();
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// keyת��
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// ����
			// �ӽ��ܵ��㷨/������ʽ/��䷽ʽ
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// ģʽ/ת����key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("bc des encrpt:" + Hex.encodeHexString(result));
			
			//����
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("bc des decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
