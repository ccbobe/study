package com.ccbobe.iocore;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class StringDemo {
    public static void main(String[] args) {
        int x=10;
        double y =20.2;
        long z=10L;
        String str = ""+x+y*z;
        int aaaa = 2147483647;
        System.out.println(aaaa+2);
        System.out.println(str);

        int num=68;
        char c = (char)num;
        System.out.println(c);



    }



    /**
     * CRC16相关计算
     */
    static byte[] crc16_tab_h = {(byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
            (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
            (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
            (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
            (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
            (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
            (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
            (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01,
            (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
            (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1,
            (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
            (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
            (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00,
            (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0,
            (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40};

    static byte[] crc16_tab_l = {(byte) 0x00, (byte) 0xC0, (byte) 0xC1, (byte) 0x01, (byte) 0xC3, (byte) 0x03, (byte) 0x02, (byte) 0xC2,
            (byte) 0xC6, (byte) 0x06, (byte) 0x07, (byte) 0xC7, (byte) 0x05, (byte) 0xC5, (byte) 0xC4, (byte) 0x04, (byte) 0xCC, (byte) 0x0C,
            (byte) 0x0D, (byte) 0xCD, (byte) 0x0F, (byte) 0xCF, (byte) 0xCE, (byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB, (byte) 0x0B,
            (byte) 0xC9, (byte) 0x09, (byte) 0x08, (byte) 0xC8, (byte) 0xD8, (byte) 0x18, (byte) 0x19, (byte) 0xD9, (byte) 0x1B, (byte) 0xDB,
            (byte) 0xDA, (byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF, (byte) 0x1F, (byte) 0xDD, (byte) 0x1D, (byte) 0x1C, (byte) 0xDC,
            (byte) 0x14, (byte) 0xD4, (byte) 0xD5, (byte) 0x15, (byte) 0xD7, (byte) 0x17, (byte) 0x16, (byte) 0xD6, (byte) 0xD2, (byte) 0x12,
            (byte) 0x13, (byte) 0xD3, (byte) 0x11, (byte) 0xD1, (byte) 0xD0, (byte) 0x10, (byte) 0xF0, (byte) 0x30, (byte) 0x31, (byte) 0xF1,
            (byte) 0x33, (byte) 0xF3, (byte) 0xF2, (byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7, (byte) 0x37, (byte) 0xF5, (byte) 0x35,
            (byte) 0x34, (byte) 0xF4, (byte) 0x3C, (byte) 0xFC, (byte) 0xFD, (byte) 0x3D, (byte) 0xFF, (byte) 0x3F, (byte) 0x3E, (byte) 0xFE,
            (byte) 0xFA, (byte) 0x3A, (byte) 0x3B, (byte) 0xFB, (byte) 0x39, (byte) 0xF9, (byte) 0xF8, (byte) 0x38, (byte) 0x28, (byte) 0xE8,
            (byte) 0xE9, (byte) 0x29, (byte) 0xEB, (byte) 0x2B, (byte) 0x2A, (byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F, (byte) 0xEF,
            (byte) 0x2D, (byte) 0xED, (byte) 0xEC, (byte) 0x2C, (byte) 0xE4, (byte) 0x24, (byte) 0x25, (byte) 0xE5, (byte) 0x27, (byte) 0xE7,
            (byte) 0xE6, (byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3, (byte) 0x23, (byte) 0xE1, (byte) 0x21, (byte) 0x20, (byte) 0xE0,
            (byte) 0xA0, (byte) 0x60, (byte) 0x61, (byte) 0xA1, (byte) 0x63, (byte) 0xA3, (byte) 0xA2, (byte) 0x62, (byte) 0x66, (byte) 0xA6,
            (byte) 0xA7, (byte) 0x67, (byte) 0xA5, (byte) 0x65, (byte) 0x64, (byte) 0xA4, (byte) 0x6C, (byte) 0xAC, (byte) 0xAD, (byte) 0x6D,
            (byte) 0xAF, (byte) 0x6F, (byte) 0x6E, (byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B, (byte) 0xAB, (byte) 0x69, (byte) 0xA9,
            (byte) 0xA8, (byte) 0x68, (byte) 0x78, (byte) 0xB8, (byte) 0xB9, (byte) 0x79, (byte) 0xBB, (byte) 0x7B, (byte) 0x7A, (byte) 0xBA,
            (byte) 0xBE, (byte) 0x7E, (byte) 0x7F, (byte) 0xBF, (byte) 0x7D, (byte) 0xBD, (byte) 0xBC, (byte) 0x7C, (byte) 0xB4, (byte) 0x74,
            (byte) 0x75, (byte) 0xB5, (byte) 0x77, (byte) 0xB7, (byte) 0xB6, (byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3, (byte) 0x73,
            (byte) 0xB1, (byte) 0x71, (byte) 0x70, (byte) 0xB0, (byte) 0x50, (byte) 0x90, (byte) 0x91, (byte) 0x51, (byte) 0x93, (byte) 0x53,
            (byte) 0x52, (byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57, (byte) 0x97, (byte) 0x55, (byte) 0x95, (byte) 0x94, (byte) 0x54,
            (byte) 0x9C, (byte) 0x5C, (byte) 0x5D, (byte) 0x9D, (byte) 0x5F, (byte) 0x9F, (byte) 0x9E, (byte) 0x5E, (byte) 0x5A, (byte) 0x9A,
            (byte) 0x9B, (byte) 0x5B, (byte) 0x99, (byte) 0x59, (byte) 0x58, (byte) 0x98, (byte) 0x88, (byte) 0x48, (byte) 0x49, (byte) 0x89,
            (byte) 0x4B, (byte) 0x8B, (byte) 0x8A, (byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F, (byte) 0x4F, (byte) 0x8D, (byte) 0x4D,
            (byte) 0x4C, (byte) 0x8C, (byte) 0x44, (byte) 0x84, (byte) 0x85, (byte) 0x45, (byte) 0x87, (byte) 0x47, (byte) 0x46, (byte) 0x86,
            (byte) 0x82, (byte) 0x42, (byte) 0x43, (byte) 0x83, (byte) 0x41, (byte) 0x81, (byte) 0x80, (byte) 0x40};

    /**
     * 获取CRC16校验后数组
     *
     * @param crcStr 需要计算的16进制的字符串
     * @return 请求指令的16进制字节数组
     */
    public static byte[] getRequestOrder(String crcStr) {
        //转化为字节数组
        byte[] data = hexString2Bytes(crcStr);
        //获取CRC16校验值
        int i = calcCrc16(data, 0, data.length, 0xffff);
        //转变高低校验值位置,低字节在前高字节在后
        String format = String.format("%04x", i);
        String hight = format.substring(0, 2);
        String low = format.substring(2);
        String crc = low + hight;
        //拼接请求指令
        String value = crcStr + crc;
        //转变为16进制的字节数组
        return hexString2Bytes(value);
    }

    /**
     * 计算CRC16校验
     *
     * @param data   需要计算的数组
     * @param offset 起始位置
     * @param len    长度
     * @param preval 之前的校验值
     * @return CRC16校验值
     */
    public static int calcCrc16(byte[] data, int offset, int len, int preval) {
        int ucCRCHi = (preval & 0xff00) >> 8;
        int ucCRCLo = preval & 0x00ff;
        int iIndex;
        for (int i = 0; i < len; ++i) {
            iIndex = (ucCRCLo ^ data[offset + i]) & 0x00ff;
            ucCRCLo = ucCRCHi ^ crc16_tab_h[iIndex];
            ucCRCHi = crc16_tab_l[iIndex];
        }
        return ((ucCRCHi & 0x00ff) << 8) | (ucCRCLo & 0x00ff) & 0xffff;
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式
     * 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF, 0xD9}
     *
     * @param src String
     * @return byte[]
     */
    public static byte[] hexString2Bytes(String src) {
        byte[] ret = new byte[src.length() / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < src.length() / 2; i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成一个字节；
     * 如："EF"--> 0xEF
     *
     * @param src0 byte
     * @param src1 byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        b0 = (byte) (b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (b0 ^ _b1);
        return ret;
    }



    @Test
    public void execArr(){
        /**
         * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
         * 数组中某些数字是重复的，但不知道有几个数字是重复的，
         * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
         *   Input:
         *   {2, 3, 1, 0, 2, 5}
         *
         *   Output:
         *   2
         *
         */
        int arr[]={2, 5, 1, 0, 4, 5};
        Set set = new HashSet(arr.length);
        for (int i = 0; i < arr.length; i++) {
            boolean add = set.add(arr[i]);
            if (!add){
                System.out.println(arr[i]);
                break;
            }
        }
    }

    @Test
    public void test_byte_RSA_1() throws Exception {


        //  4096 位测试
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtB0S/HhpRJvrcMPsoAnnh/IQQvhVVVkzk0jatc/3M4CHmvRbOT3gTMiUWsdWl/wmL4DyeIkVYuEm7Zod2in0Hm3WqyNZpX2GEe5wd+cqjfVvjLMHkHQ6aOjN2cLIkWfcafdfYO790Lny3BqmXj0xE5SA4XbeXewyPivIKNZQKNQIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK0HRL8eGlEm+tww+ygCeeH8hBC+FVVWTOTSNq1z/czgIea9Fs5PeBMyJRax1aX/CYvgPJ4iRVi4Sbtmh3aKfQebdarI1mlfYYR7nB35yqN9W+MsweQdDpo6M3ZwsiRZ9xp919g7v3QufLcGqZePTETlIDhdt5d7DI+K8go1lAo1AgMBAAECgYEAiY7Lwr0l4vqAWWm/YnGFgHoGbGESCMQRYaEVwxQB5E0T5qLRbLeVGH23KrG6zrtRVZix2G4af4wTYh8C9uTmvEBLN9vzb/887/fwj3XoD92YJfUT4/7VTDwZP3BVghwfLIsBDRCVnpUBsL7n/mjZAsoHgKSdt3fb8yXnDue4ooECQQD4TJLca2RuxSUyvf3Usmx/duhnwoBSCrwxhXlnXDC/V+r0syIQrXmGCKXvVqUK6L0DDsUxQv6140NsqD10jRFxAkEAsmUQ7kxmJOAaWseL3WZojOeqHUNtBFQ05YqQls8EO558FhKZrpH1as7bxwFbriVjLLEpsAugKXydTwdRKFRjBQJBAIFTqP7DtOj5KFnYmZgqYta1QXGh3iJdUw5cHhLW8tEG4usXTnvvTyef44XY1FftR12LWOxCn9ZvC9RRgMjvHRECQDAiSu4xWQIsIZBK9TcY0a7rNyBMOBcJ7Xfd6mN2mgrIGW0POb47jb9y7yQD/su/NMIzTy88A8wN3pgbPk+mbU0CQB9X0UUvAB77cif89MLeU0wgFzuzENwp5PcngVa0CdMeFZktamaWyeZWLkVbKAngZYhwztQ0AUEykdwQjLqzQF8=";

        String string = "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！" +
                "hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！hello world ccbobe！！！";

        System.out.println(string.length());
        System.out.println("加密后");
        byte[] encrypt = RSAUtil.encrypt(string.getBytes(), publicKey);
        System.out.println(new String(Base64.getEncoder().encode(encrypt)));
        System.out.println("解密后");
        byte[] decrypt = new byte[0];
        try {
            decrypt = RSAUtil.decrypt(encrypt, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = new String(decrypt);
        System.out.println(s);

        String sign = RSAUtil.sign(privateKey, s);
        System.out.println(sign);
        boolean verifySignature = RSAUtil.verifySignature(publicKey, sign, s);
        System.out.println(verifySignature);

    }

    @Test
    public void test_initKeys() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();

        byte[] publicBytes = pair.getPublic().getEncoded();
        byte[] privateBytes = pair.getPrivate().getEncoded();

        System.out.println("public key: " + Base64.getEncoder().encodeToString(publicBytes));
        System.out.println("private key: " + Base64.getEncoder().encodeToString(privateBytes));
    }

}
