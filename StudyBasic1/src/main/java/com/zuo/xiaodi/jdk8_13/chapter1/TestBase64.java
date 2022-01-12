package com.zuo.xiaodi.jdk8_13.chapter1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Java8新特性，提供了Base64工具类，比以前使用的sun.misc(以后版本会去掉此API) 和 Apache Commons Codec(需要引包)快
 */
public class TestBase64 {

    public static void main(String[] args) {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        String str = "左丽萍";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        String encodedStr = encoder.encodeToString(strBytes);
        System.out.println("编码后：" + encodedStr);
        String decodedStr = new String(decoder.decode(encodedStr));
        System.out.println("解码后：" + decodedStr);
    }

}
