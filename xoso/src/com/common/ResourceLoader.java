/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common;

import java.util.ResourceBundle;

/**
 *
 * @author Binh
 */
public class ResourceLoader {
    public static String getString(String key) {
        ResourceBundle rb=ResourceBundle.getBundle("conf.resources");
        try {
            return rb.getString(key);
        } catch (Exception e) {
            return "";
        }
    }
}
