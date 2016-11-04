/*
 * @(#)RSAkeyObj.java	version 1.0	May 12, 2010
 *
 * Copyright 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


package inet.util;

/**
 *  Mô tả đoạn code bên dưới
 *  @author: SONHV
 *  @since: May 12, 2010
 */
public class RSAkeyObj {
private String privateKey;
private String publicKey;

   public RSAkeyObj(String privateKey, String publicKey) {
      this.privateKey = privateKey;
      this.publicKey = publicKey;
   }

   public RSAkeyObj() {
   }

   public String getPrivateKey() {
      return privateKey;
   }

   public String getPublicKey() {
      return publicKey;
   }

   public void setPrivateKey(String privateKey) {
      this.privateKey = privateKey;
   }

   public void setPublicKey(String publicKey) {
      this.publicKey = publicKey;
   }

}
