/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author hanhlm
 */
public class LotoUtils {

    private static final List<String> LOTO_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                LOTO_LIST.add("0" + i);
            } else {
                LOTO_LIST.add("" + i);
            }
        }
    }

    public static boolean isLoto(String loto) {
        return loto != null && LOTO_LIST.contains(loto);
    }

    public static String getAllLoto(String seperator) {
        String result = "";
        if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
            int size = LOTO_LIST.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    result += LOTO_LIST.get(i) + seperator;
                } else {
                    result += LOTO_LIST.get(i);
                }
            }
        }
        return result;
    }

    public static boolean isTongLe(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        int sum = first + second;
        return (sum % 2 != 0);
    }

    public static boolean isTongChan(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        int sum = first + second;
        return (sum % 2 == 0);
    }

    public static boolean isBoChanChan(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first % 2 == 0) && (second % 2 == 0);
    }

    public static boolean isBoLeLe(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first % 2 != 0) && (second % 2 != 0);
    }
    
    public static boolean isBoChanLe(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first % 2 == 0) && (second % 2 != 0);
    }
    
    public static boolean isBoLeChan(String loto) {
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first % 2 != 0) && (second % 2 == 0);
    }

    public static boolean isBoKep(String loto){
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return first == second;
    }
    
    public static boolean isBoSatKep(String loto){
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first + 1 == second) || (first - 1 == second);
    }
    
    public static boolean isTong(String loto, int tong){
        int first = Integer.parseInt(String.valueOf(loto.charAt(0)));
        int second = Integer.parseInt(String.valueOf(loto.charAt(1)));
        return (first + second == tong) || (first + second - 10 == tong);
    }
    
    public static String getAllLoto(String type, String seperator) {
        String result = "";
        if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
            int size = LOTO_LIST.size();
            String loto = "";
            for (int i = 0; i < size; i++) {
                loto = LOTO_LIST.get(i);
                if ("TONGLE".equalsIgnoreCase(type) && isTongLe(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("TONGCHAN".equalsIgnoreCase(type) && isTongChan(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOCHANCHAN".equalsIgnoreCase(type) && isBoChanChan(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOLELE".equalsIgnoreCase(type) && isBoLeLe(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOCHANLE".equalsIgnoreCase(type) && isBoChanLe(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOLECHAN".equalsIgnoreCase(type) && isBoLeChan(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOKEP".equalsIgnoreCase(type) && isBoKep(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                } else if ("BOSATKEP".equalsIgnoreCase(type) && isBoSatKep(loto)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                }
            }
        }
        return result;
    }

    public static String getAllLotoByTong(int tong, String seperator){
        String result = "";
        if (LOTO_LIST != null && LOTO_LIST.size() > 0) {
            int size = LOTO_LIST.size();
            String loto = "";
            for (int i = 0; i < size; i++) {
                loto = LOTO_LIST.get(i);
                if (isTong(loto, tong)) {
                    result += loto;
                    if (i < (size - 1)) {
                        result += seperator;
                    }
                }
            }
        }
        return result;
    }
    
    public static boolean findNotLotoResult(String lotoResult, String loto, boolean dao) {
        int dem = 0;
        String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            if (dao) {
                if (token.equals(loto) || token.equals(lotoDao)) {
                    dem++;
                }
            } else {
                if (token.equals(loto)) {
                    dem++;
                }
            }

        }
        return dem == 0;
    }

    public static boolean findLotoResult(String lotoResult, String loto, int solan, boolean dao) {
        int dem = 0;
        String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            if (dao) {
                if (token.equals(loto) || token.equals(lotoDao)) {
                    dem++;
                }
            } else {
                if (token.equals(loto)) {
                    dem++;
                }
            }

        }
        return dem >= solan;
    }

    public static boolean findLotoResultDacbiet(String lotoResult, String loto, boolean cham) {

        if (cham) {
            String dau = loto.substring(0, 1);
            String duoi = loto.substring(1, 2);
            if (lotoResult.startsWith(dau) || lotoResult.endsWith(dau) || lotoResult.startsWith(duoi) || lotoResult.endsWith(duoi)) {
                return true;
            }
        } else {
            if (lotoResult.equals(loto)) {
                return true;
            }
        }

        return false;
    }

    public static int countLotoResultDacBiet(String lotoResult, String loto, int opt) {
        //opt=0 la dau
        //opt=1 la duoi
        //opt=2 la tong
        if (lotoResult == null || "".equals(lotoResult)) {
            return 0;
        }

        lotoResult = lotoResult.trim();

        if (opt == 0) {
            if (lotoResult.startsWith(loto)) {
                return 1;
            }
        } else if (opt == 1) {
            if (lotoResult.endsWith(loto)) {
                return 1;
            }
        } else if (opt == 2) {
            if (lotoResult.length() > 1) {
                try {
                    int so1 = Integer.parseInt(lotoResult.substring(0, 1));
                    int so2 = Integer.parseInt(lotoResult.substring(1, 2));
                    int tong = so1 + so2;
                    if (tong > 10) {
                        tong = tong - 10;
                    }
                    if (tong == Integer.parseInt(loto)) {
                        return 1;
                    }
                } catch (Exception e) {
                    System.out.println("excep>>>>>>>>>" + lotoResult);
                }

            }
        } else if (opt == 3) {
            if (lotoResult.equals(loto)) {
                return 1;
            }
        }

        return 0;
    }

    public static int countLotoResult(String lotoResult, String loto, boolean dao) {
        int dem = 0;
        String lotoDao = loto.substring(1, 2) + loto.substring(0, 1);
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            if (dao) {
                if (token.equals(loto) || token.equals(lotoDao)) {
                    dem++;
                }
            } else {
                if (token.equals(loto)) {
                    dem++;
                }
            }

        }

        return dem;
    }

    public static int countLotoResult(String lotoResult, String loto) {
        //int dem = 0;
        int result = 0;
        for (StringTokenizer stringLoto = new StringTokenizer(loto, "-"); stringLoto.hasMoreTokens();) {
            String tokenLoto = stringLoto.nextToken();
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                if (tokenLoto.equals(token)) {
                    result++;
                }
            }
        }

        return result;
    }

    public static int countDauDuoi(String lotoResult, String dauduoi, boolean isDauDuoi) {
        int result = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(lotoResult, "-"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            if (isDauDuoi) {
                if (token.startsWith(dauduoi)) {
                    result++;
                }
            } else {
                if (token.endsWith(dauduoi)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static List<String> ghepXien(String input, int opt) {
        List<String> result = null;
        String[] arr = input.split(",");

        if (opt == 2) {
            if (arr.length < 2) {
                return null;
            }
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(arr[i] + "," + arr[j]);
                }
            }
        }

        if (opt == 3) {
            if (arr.length < 3) {
                return null;
            }
            for (int i = 0; i < arr.length - 2; i++) {
                for (int j = i + 1; j < arr.length - 1; j++) {
                    for (int k = j + 1; k < arr.length; k++) {
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(arr[i] + "," + arr[j] + "," + arr[k]);
                    }
                }
            }
        }

        if (opt == 4) {
            if (arr.length < 4) {
                return null;
            }
            for (int i = 0; i < arr.length - 3; i++) {
                for (int j = i + 1; j < arr.length - 2; j++) {
                    for (int k = j + 1; k < arr.length - 1; k++) {
                        for (int n = k + 1; n < arr.length; n++) {
                            if (result == null) {
                                result = new ArrayList<>();
                            }
                            result.add(arr[i] + "," + arr[j] + "," + arr[k] + "," + arr[n]);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> list = LotoUtils.ghepXien("02,03,04,05,06", 4);
        for (String string : list) {
            System.out.println(string);
        }
    }
}
