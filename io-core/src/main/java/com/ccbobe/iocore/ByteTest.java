package com.ccbobe.iocore;



public class ByteTest {

    public static void main(String[] args) {
        System.out.println(8>>2);
        System.out.println(8<<2);
        String s1 = String.format("%8s", Integer.toBinaryString(Integer.MAX_VALUE & 0xFF)).replace(' ', '0');
        System.out.println(s1);
        Integer.toHexString(Integer.MAX_VALUE);
        byte[] bytes = IntToByte(1000000);

        System.out.println((0)&0xff);

        System.out.println(byteArrayToInt(new byte[]{0,15,66,64}));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(IntToByte(Integer.MAX_VALUE));
    }

    /**
     * int转byte数组
     * @param num
     * @return
     */
    public static byte[]IntToByte(int num){
        byte[]bytes=new byte[4];
        bytes[0]=(byte) ((num>>24)&0xff);
        bytes[1]=(byte) ((num>>16)&0xff);
        bytes[2]=(byte) ((num>>8)&0xff);
        bytes[3]=(byte) (num&0xff);
        return bytes;
    }

    /**
     * byte[]转int
     * @param bytes 需要转换成int的数组
     * @return int值
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

}
