/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author TUYENTT
 */
public class FileTool {
    
//    private static  String fileName = FileTool.class.getResource("mkadmin.properties").getFile();
//    
//    public synchronized static String readFile() {
//        String responseText = "";
//        BufferedReader bf;
//        try {
//            bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
//            String str;
//            Vector<String> vValue = null;
//            while ((str = bf.readLine()) != null) {
//                if (str == null) {
//                    continue;
//                }
//                if (str.charAt(0) == '#') {
//                    continue;
//                }
//                responseText = str.trim();
//            }
//            bf.close();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return responseText;
//    }
//
//    public synchronized static void writeFile(String text) {
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(fileName, false);
//            fos.write(text.trim().getBytes("UTF-8"));
//            //fos.write("\n".getBytes("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (Exception e) {
//            }
//        }
//    }
    
    public static void saveToFile(String text, String fileName)
    {
      FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName, false);
            fos.write(text.getBytes("UTF-8"));
            //fos.write("\n".getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("passs="+ Md5.Hash("admin"));
    }
}
