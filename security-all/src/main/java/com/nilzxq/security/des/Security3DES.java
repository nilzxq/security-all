package main.java.com.nilzxq.security.des;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Security3DES {

	public static String src = "nilzxq security des";
	public static void main(String[] args) {
		jdk3DES();
		bc3DES();
	}
    private static void jdk3DES(){
    	try {
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			//keyGenerator.init(168);
			//SecureRandom()生成默认长度，根据不同的算法
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// key转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// 加密
			// 加解密的算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			// 模式/转换的key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk 3des encrpt:" + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("jdk 3des decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void bc3DES(){
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			// 生成KEY 
			//"BC"不同于jdk
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");
			keyGenerator.getProvider();
			//keyGenerator.init(56);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();

			// key转换
			DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key convertSecretKey = factory.generateSecret(desKeySpec);

			// 加密
			// 加解密的算法/工作方式/填充方式
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			// 模式/转换的key
			cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("bc 3des encrpt:" + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result=cipher.doFinal(result);
			System.out.println("bc 3des decrpt:"+new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
