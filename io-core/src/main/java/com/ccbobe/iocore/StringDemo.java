package com.ccbobe.iocore;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Test;

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
        String publicKey = "" +
                "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAs0ylLp01EKsFJv/IGdhk" +
                "5AEUefS0oOXVBaYo7wMks6UuDc7umCif46dtW62OgQxkOE/MerKmPurn1uJdJyy2" +
                "LdCwB8QPaGvNaGiuG6/hPIVrlpL4YJSZl9fTcDNHs29F5JfoGtSnvDoToqpGmzVQ" +
                "v19CHy3F7Mt3Bk/NabmUuOOgx08GYb7suyz/f6hySiFvCCHpOqU++wI3Psji//I3" +
                "2tTI4eBrxUwmaFbWPLdcycH/LcEJwlv6XubcnV2A2U7+lK3wAvuQcRGlBdTz5YOr" +
                "4nL9vlGbBpJO1M5JZ1TcMARBYy3pgiSi7knJ+UfbmWsVvDGboUhFi8oO6531N5Vd" +
                "FoJLdzLqT6rMXGru1K9OGq3hfmO4o15hMXPLt+GrKBKcP3YtXMK1B6mXvhhoqjyo" +
                "SwgW4BLAps3xNGbmBpYhath0JkUb7rqiUI+j+b8uTb5UqH8PMfTz1wb1sQphBfB5" +
                "E8uTZl62h9FGgaErgO+pnsA640Q4JL7E2uIqcL38PcrKgJkrExJIGCAeYMXMeQEy" +
                "xt0tYFyVcSw9QQ2U8UyPrnY50MxjNeekXxJ90eZR8C9VaepBEOs4gzswgQCEriyZ" +
                "Rl1MeowzZTpuIfRgFETkTwRUPdR94qbfq8MdV1kwqbFLyA9Gk2mFS1rBsvF9rNMN" +
                "smxs7BA0zLbGsy4MA1eaChkCAwEAAQ==" +
                "";
        String privateKey = "MIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQCzTKUunTUQqwUm" +
                "/8gZ2GTkARR59LSg5dUFpijvAySzpS4Nzu6YKJ/jp21brY6BDGQ4T8x6sqY+6ufW" +
                "4l0nLLYt0LAHxA9oa81oaK4br+E8hWuWkvhglJmX19NwM0ezb0Xkl+ga1Ke8OhOi" +
                "qkabNVC/X0IfLcXsy3cGT81puZS446DHTwZhvuy7LP9/qHJKIW8IIek6pT77Ajc+" +
                "yOL/8jfa1Mjh4GvFTCZoVtY8t1zJwf8twQnCW/pe5tydXYDZTv6UrfAC+5BxEaUF" +
                "1PPlg6vicv2+UZsGkk7UzklnVNwwBEFjLemCJKLuScn5R9uZaxW8MZuhSEWLyg7r" +
                "nfU3lV0Wgkt3MupPqsxcau7Ur04areF+Y7ijXmExc8u34asoEpw/di1cwrUHqZe+" +
                "GGiqPKhLCBbgEsCmzfE0ZuYGliFq2HQmRRvuuqJQj6P5vy5NvlSofw8x9PPXBvWx" +
                "CmEF8HkTy5NmXraH0UaBoSuA76mewDrjRDgkvsTa4ipwvfw9ysqAmSsTEkgYIB5g" +
                "xcx5ATLG3S1gXJVxLD1BDZTxTI+udjnQzGM156RfEn3R5lHwL1Vp6kEQ6ziDOzCB" +
                "AISuLJlGXUx6jDNlOm4h9GAURORPBFQ91H3ipt+rwx1XWTCpsUvID0aTaYVLWsGy" +
                "8X2s0w2ybGzsEDTMtsazLgwDV5oKGQIDAQABAoICAEB3snzMMaRImZWoKj6Au+Xv" +
                "LXmcLsYbXTimwdkOfEfLgH+eg9NZ6ANm078+7BoiZnaI2rDTQ0skEzAEPBI11Vsr" +
                "yd9A83lMKZL88H0sBARKxKBjP8MaKw+ZnoCdL46o16yVofLju1vGVgEK0FBGHz5v" +
                "9cpYHu5BSg6O6wC0pXDFB64iD91dVKkISOWRn2ZgFxgJeV6Iw9hkt5Bvko8MfGDG" +
                "WWQ92QIbm0Ow1V8HXul8Q1AkNGoZwDAXOgOq9wUrjgXPdJjd8tV0zq0GIJvJeCf3" +
                "yJyBowf92zJQ6bupm3H4Vbpfa7JLkfmztldXKvJCupHsFFUtMzRL9olpRh4yKkGv" +
                "hzPK19QLSPwmlnaWarPCzSHSYefAYYJmlu0I68KCW/KIR6Ox/l7DksNVvO6N4F8u" +
                "hj5X81EUXB7Rlvqu+XqRPLal2LvVgrHME96Hd9/8aqb4I6f1n/zJP7IG4HIzwc+o" +
                "k7HXpn264nv+ZKw4AHohGAA5aVxy4m/6jvjhz62sz3t/cGLYYS60u4QINBEjJLF7" +
                "zJgEbsCCGKPNR+7q68NhaSDoUgc3GGrwCJOJEeREaaQSmpSdNZCGe7YT2LVma2H9" +
                "rTWinLGr/0ts7DrnJIdaqNKY5gG7FmQt1hnZSwo3lVz/eF1LnZ6oJ3GWStDCoOxf" +
                "H1mGrjYP5fJGEZ53nWDBAoIBAQDaZ3RdpFsM+ltAnKU5bieQ9p3sQ+F/3mU5L6hC" +
                "E9m3ugYnQ3BencQt0BIWFbBDDvbSE/Z+LFhKvv+5x0m/IE4PGOX5h3MWmG957Mao" +
                "o6mQY9FDtLvE0JvJheZMyqwWBlOw/oZApYuNp+FnZyspcUUYofp05q9mPj/pzN2n" +
                "RnMTroKBH3Rub/bRyWhj6IWHWG+3ur+Ox5w/dPnjDY1nx6b1SFZv4nVzHHwJM5J8" +
                "xbS6lzGFIWuAiKq/qGmpz7ROKOoJM8chU5LI3hmDK7+J4NSxcZwj83yKTomPCTUR" +
                "HisfmcMRMy/zo8DeCKO3PfHP8cBColBhfBJWHNrPVW2p2ufFAoIBAQDSKfB47uGt" +
                "405kODq0veZdLew/njD2LknySMJ9jmdc2yF96OF3O4qOfjMQtIhDZitq7OoI2ZCG" +
                "nbmiJkvKDXsOi+E6mGtSAmmM5cy2bxiYYS52HvgboH08hNUkYKvEEXVgd0yMLjdk" +
                "mbo/D7P61oHgwy2KxUrMsjRFxbOV20zRTQH5JjvE3uAwGxQRJdwErODllpF/CA1+" +
                "3ozkb+DXrATA0XgV/Dc1ZJ8yRkEh9cXLl1JhDanwP1GjP3hLSonZNzYeevZtwrQP" +
                "bZ87ijPBD2awv6LB34A3giYy7Ix+r9q2B33Km+A5JCJvGSpeZmqwhjT/jhw0In3+" +
                "fvlPPndVOmpFAoIBABef0y2B5rtrpU1C5Hhm2u7KTmDNIupC8y4RYqDrJsTB4YKD" +
                "v/bsF9lgxWQQKoXGa5pjwGj7gP1s1ZqR3P9IjkkBx3aLWT6bdIt6ui5praDJu0wY" +
                "k6sBaaiNzFCIJ78mJg1iKsF5ZrQG8dHOjhOohfHZK49TmKt1llJC78L1UuNRIBq5" +
                "fU2o3MjqgVk1QKRYQ5VbJPdbziHrFFCkGaSdKwkjLv5hP5UZAq24H2NRu2/nhNrC" +
                "t68qmVWr8OoGZpmBwXcHW8XWFW2W4KmYpNpUMI2VHW+iGdmYDzciptPYMI/1gO0u" +
                "XUR8dRNPWe4rTCnTG5nZltpEonMgIecFdZxG6oUCggEAOvxea/LQEJgNtn85quTp" +
                "tRlgh3KE13OieYgGXmsLg7pVKhukvGxPtTKibnvdCt2wLfCUtyspLz8xjNsmxm0G" +
                "Lsz/yAxjgr7+lPcif8mqo4oTGkAS7t4aiFPX5r/jNcKYqRyStX17OpcS+QQVRb/S" +
                "8ByXua0qE02o8jRM9TB53Py6Iw2iFN1hGt9ewHlZyU4kSQv7hiXic8tOzAbCmoXD" +
                "R8MC61jAvhGhxOCHA1sRytSL4IWPPsjmxuTXhHpFOlwOqntkkuXk17eyy2k8oWEL" +
                "N2xx5+ws9mdnBT/zJtOAlTqBCc66Hg+QQpJTm+8u3qCsW1WtgMx1hGpq2tzzbU5Z" +
                "6QKCAQB0V1IpzyErEN1RBLuMhfwl0VZBTkCIxTAlc6LvU9Opg4m0p1S19ZLSJYbo" +
                "t1gGHe4GAnWjxu7j5FPxnbO9ulbsZdQ8780BBXmC+FE1REK/lQld/5TqW+rvGP3e" +
                "iNIi7I59akAypz58XWb7Iu35OFOTvL1qyp3GdxPeOfWK0znc9cXHIRdSpLhrt/eF" +
                "pSX88RzKQItehGQJ2F5A4cLkluWXyGeWbfndHs1q7A1BoTI74yR8CPzwV7ll9sUN" +
                "nDN4UB/M/CS2GKm3x5C5e6sxQLHfOpstGsYnG3Zww0+VaBdZ/JRzqyP6bGxvM5ad" +
                "e3oUht1NSZOkd76BU8ckGUpDCBG4";

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

}
