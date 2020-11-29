package com.ccbobe.iocore;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用法：公钥加密、私钥解密、私钥签名、公钥验签。
 */
public class RSAUtil {
    /**
     * encryption algorithm RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    private final static String SIGN_TYPE = "SHA512withRSA";

    /**
     * RSA Maximum Encrypted Plaintext Size
     *  1024位/8位-11=128-11=117    1024 位
     *  4096位/8位-11 = 512-11=501  4096 位
     *
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA Maximum decrypted ciphertext size
     * 1024位/8位=128   1024
     * 4096位/8位=512   4096
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * encryption
     * @param data data
     * @param publicKey publicKey
     * @return byte
     * @throws Exception Exception
     */
    public static byte[] encrypt(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // Sectional Encryption of Data
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * Decrypt
     * @param text text
     * @param privateKey privateKey
     * @return byte
     * @throws Exception Exception
     */
    public static byte[] decrypt(byte[] text, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = text.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // Sectional Encryption of Data
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(text, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(text, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }




    /**
     * 执行签名
     *
     * @param rsaPrivateKey 私钥
     * @param src           参数内容
     * @return 签名后的内容，base64后的字符串
     * @throws InvalidKeyException      InvalidKeyException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws SignatureException       SignatureException
     */
    public static String sign(String rsaPrivateKey, String src) throws InvalidKeyException,
            NoSuchAlgorithmException, InvalidKeySpecException, SignatureException {
        // base64解码私钥
        byte[] decodePrivateKey = Base64.getDecoder().decode(rsaPrivateKey);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodePrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //生成内容摘要，再用RSA的私钥加密，进而生成数字签名
        Signature signature = Signature.getInstance(SIGN_TYPE);
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        // 生成签名
        byte[] result = signature.sign();

        // base64编码签名为字符串
        return new String(Base64.getEncoder().encode(result));
    }

    /**
     * 验证签名
     *
     * @param rsaPublicKey 公钥
     * @param sign         签名
     * @param src          参数内容
     * @return 验证结果
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeySpecException  InvalidKeySpecException
     * @throws InvalidKeyException      InvalidKeyException
     * @throws SignatureException       SignatureException
     */
    public static boolean verifySignature(String rsaPublicKey, String sign, String src) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException {
        // base64解码公钥
        byte[] decodePublicKey = java.util.Base64.getDecoder().decode(rsaPublicKey);

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodePublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGN_TYPE);
        signature.initVerify(publicKey);
        signature.update(src.getBytes());
        // base64解码签名为字节数组
        byte[] decodeSign = java.util.Base64.getDecoder().decode(sign);
        // 验证签名
        return signature.verify(decodeSign);
    }

    /**
     * 计算生成key
     * @return
     */
    public static Map<String,String> keyGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        byte[] publicBytes = pair.getPublic().getEncoded();
        byte[] privateBytes = pair.getPrivate().getEncoded();
        System.out.println("public key: " + Base64.getEncoder().encodeToString(publicBytes));
        System.out.println("private key: " + Base64.getEncoder().encodeToString(privateBytes));
        Map map = new HashMap(2);
        map.put("publicKey",Base64.getEncoder().encodeToString(publicBytes));
        map.put("privateKey",Base64.getEncoder().encodeToString(privateBytes));
        return map;
    }
}
