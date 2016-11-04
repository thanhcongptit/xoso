

package com.utils;


import com.utils.sercurity.EncryptionException;
import com.utils.sercurity.StringEncrypter;

public class Encrypter
{

    public Encrypter()
    {
    }

    public static String decrypt(String encryptedText)
        throws EncryptionException
    {
        return encrypter.decrypt(encryptedText);
    }

    public static String encrypt(String clearText)
        throws EncryptionException
    {
        return encrypter.encrypt(clearText);
    }

    private static StringEncrypter encrypter;
    private static String encryptionKey;
    private static String encryptionScheme;

    static 
    {
        encryptionKey = "Le Anh Tuan: leanhtuan2002@gmail.com";
        encryptionScheme = "DES";
        encrypter = null;
        try{
        	 encrypter = new StringEncrypter(encryptionScheme, encryptionKey); 	
        }catch(EncryptionException ex){
        	ex.printStackTrace();
        }
       
//        break MISSING_BLOCK_LABEL_43;
//        EncryptionException ex;
//        ex;
//        ex.printStackTrace();
    }
    
    public static void main(String[] args) {
		try {
			// vtvmobile1122334455
			 System.out.println(Encrypter.encrypt("service2oo8"));
			//
			// vtvadmin12345
			// System.out.println(Encrypter.encrypt("vtvadmin12345"));
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
	}
}