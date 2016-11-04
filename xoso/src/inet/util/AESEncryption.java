package inet.util;
import java.io.*;
import java.net.URLEncoder;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class AESEncryption {
        //THO 13/11/2013  
	//http://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html
	//  "algorithm/mode/padding" or  "algorithm"
	//PHP & Test: http://www.coderelic.com/examples/AES_Encryption_Example.php 
	
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";
    public static String KEY_XS="PX3DPB763WHRZK5G";
    
    public static  byte[] decrypt(byte[] cipherText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }
 
    public static byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {	
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }
 
    private static byte[] getKeyBytes(String key) throws UnsupportedEncodingException{
        byte[] keyBytes= new byte[16];
        byte[] parameterKeyBytes= key.getBytes(characterEncoding);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }
 
    /// <summary>
    /// Encrypts plaintext using AES 128bit key and a Chain Block Cipher and returns a base64 encoded string
    /// </summary>
    /// <param name="plainText">Plain text to encrypt</param>
    /// <param name="key">Secret key</param>
    /// <returns>Base64 encoded string</returns>
    public static String encrypt(String plainText, String key) {
    	String sEncrypted=null;
       try {
    	   byte[] plainTextbytes = plainText.getBytes(characterEncoding);
           byte[] keyBytes  = getKeyBytes(key);
           byte[] iniVector = keyBytes;
           sEncrypted= Base64.encodeToString(encrypt(plainTextbytes, keyBytes, iniVector), Base64.DEFAULT);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return sEncrypted;
    }
    
    public static String encryptURL(String plainText, String key) {
    	String sEncrypted=null;
       try {
    	   byte[] plainTextbytes = plainText.getBytes(characterEncoding);
           byte[] keyBytes  = getKeyBytes(key);
           byte[] iniVector = keyBytes;
           sEncrypted= Base64.encodeToString(encrypt(plainTextbytes, keyBytes, iniVector), Base64.DEFAULT);
           sEncrypted = URLEncoder.encode(sEncrypted, "utf-8");
       } catch (Exception e) {
			// TODO: handle exception
		}
    	return sEncrypted;
    }
 
    /// <summary>
    /// Decrypts a base64 encoded string using the given key (AES 128bit key and a Chain Block Cipher)
    /// </summary>
    /// <param name="encryptedText">Base64 Encoded String</param>
    /// <param name="key">Secret Key</param>
    /// <returns>Decrypted String</returns>
    public static String decrypt(String encryptedText, String key){
    	String sDecrypted=null;
    	 try {
	        byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
	        byte[] keyBytes = getKeyBytes(key);
	        byte[] iniVector = keyBytes;
	        sDecrypted= new String(decrypt(cipheredBytes, keyBytes, iniVector), characterEncoding);
    	 } catch (Exception e) {
 			// TODO: handle exception
 		}
    	 return sDecrypted;
    }
    
    public static void main(String args[]) {
    	try {
    		//KEY must be either 16, 24, or 32 bytes in length for 128, 192, and 256 bit encryption respectively
    		//String key="57444ITQJK4Q5PVV";
    		
    		//String url1 = "?src=http://lichvansu.wap.vn/login.jsp&token="+AESEncryption.encryptURL("http://lichvansu.wap.vn/login.jsp", "QXC5UDS3YWNYW857")+"";
    		//System.out.println(url1);
    		//String s = AESEncryption.encrypt("http://inet.edu.vn", AESEncryption.KEY_AES);
    		
    		//System.out.println(s);
    		
    		//String s2 = AESEncryption.decrypt("GB9puLokfRb1d6NjTzYxDA==",AESEncryption.KEY_AES);
    		//System.out.println("pass encrypt:"+s2);
    		
    		//String key = "2b9ff1e6b89947dfa9e84f3cf763fce2";
    		//String url = "http://forum.sms.vn";
    		
    	//	System.out.println(AESEncryption.decrypt("uTdLuQwWQ3SaZTUL8DLm9wLjledQj6L+EAa5ZbaNHNE2QiH+IpPLTkAu54/OgGRb", key));
    		//System.out.println(AESEncryption.encryptURL("http://xosobamien.vn/test.jsp", key));                
                //System.out.println(URLEncoder.encode("http://xosobamien.vn", "utf-8"));
                //System.out.println("u9EPB12QmESFnaoRo95O9QfD12x/r72NM8BICYnKepo=***********"+EncryptManager.decryptAES("u9EPB12QmESFnaoRo95O9QfD12xr72NM8BICYnKepo=", key));
                //System.out.println("msisdn "+URLEncoder.encode(EncryptManager.encryptAES("mobile=841696803096", key),"utf-8"));
                //System.out.println("dkajkjdk "+URLEncoder.encode("mobile=841696803096", "utf-8"));
    		//String encrypt = AESEncryption.encrypt("https://backorder.inet.vn/index/testlogin", key);
    		
    		//System.out.println(encrypt);    		
    		//System.out.println(AESEncryption.encrypt("https://backorder.inet.vn/index/", key));
    		//System.out.println(AESEncryption.encrypt("https://backorder.inet.vn", key));
    		//System.out.println(AESEncryption.decrypt(encrypt, key));
    		
    		//System.out.println(AESEncryption.decrypt(AESEncryption.encryptURL("https://backorder.inet.vn/index/login", "CHEA60TNZUOA0P8E"), "CHEA60TNZUOA0P8E"));
    		//System.out.println(AESEncryption.encryptURL("https://backorder.inet.vn/index/login", "CHEA60TNZUOA0P8E"));
    		//System.out.println("domain=lichvansu.wap.vn&id="+AESEncryption.encryptURL("107", "QXC5UDS3YWNYW857"));
    		System.out.println("***"+Encrypter.encrypt("cms_app"));
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
