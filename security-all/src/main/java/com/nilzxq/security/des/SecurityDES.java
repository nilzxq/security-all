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
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// key转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// 加密
			// 加解密的算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 模式/转换的key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk des encrpt:" + Hex.encodeHexString(result));
			
			//解密
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
			
			// 生成KEY 
			//"BC"不同于jdk
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			keyGenerator.getProvider();
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// key转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// 加密
			// 加解密的算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 模式/转换的key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("bc des encrpt:" + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("bc des decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
