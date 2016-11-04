/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soicaupro.test;

import com.utils.DateTime;
import inet.util.DateUtil;
import inet.util.EncryptManager;
import inet.util.Encrypter;
import inet.util.Md5;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author conglt
 */
public class Test {

    public static void main(String a[]) {
        System.out.println(Encrypter.encrypt("123qweasd@qwe"));
    }
}
