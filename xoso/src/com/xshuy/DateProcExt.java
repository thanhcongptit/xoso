/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xshuy;

import com.soicaupro.thongkebacnho.CommonUtil;
import com.utils.DateProc;
import java.util.StringTokenizer;

/**
 *
 * @author hanhlm
 */
public class DateProcExt extends DateProc {

    public static String convertYMDtoMDY(String input) {
        String result = "";
        
        if (!CommonUtil.isEmptyString(input)) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(input, "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                if ("".equals(result)) {
                    result = token;
                } else {
                    result = token + "/" + result;
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateProcExt.convertYMDtoMDY("2016-01-12"));
    }
}
