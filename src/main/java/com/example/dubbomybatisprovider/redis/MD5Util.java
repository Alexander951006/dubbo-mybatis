package com.example.dubbomybatisprovider.redis;


import org.apache.commons.codec.digest.DigestUtils;


public class MD5Util {
    private static final String salt = "1a2b3c";

    public static String MD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String inputPassToFormPass(String input) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + input + salt.charAt(5) + salt.charAt(4);
        return MD5(str);
    }

    public static String formPassToDbPass(String formPass, String saltDb) {
        String str = "" + saltDb.charAt(0) + saltDb.charAt(2) + formPass + saltDb.charAt(5) + saltDb.charAt(4);
        return MD5(str);
    }

    public static String inputFormToDbPass(String input, String saltDb) {
        String formPass = inputPassToFormPass(input);
        String Dbpass = formPassToDbPass(formPass, saltDb);
        return Dbpass;
    }

    public static void main(String[] args) {
        System.out.println(inputFormToDbPass("123456", "1a2b3c"));
    }
    //b7797cce01b4b131b433b6acf4add449
}

