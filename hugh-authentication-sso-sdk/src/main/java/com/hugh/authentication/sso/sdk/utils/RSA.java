package com.hugh.authentication.sso.sdk.utils;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

/**
 * @Auther: luoyulin1
 * @Date: 2018/12/13 14:51
 * @Description:
 */
public class RSA {


    // 这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
    public static final int KEY_SIZE = 1024;

    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    /**
     * * 生成密钥对 *
     * @return KeyPair *
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", DEFAULT_PROVIDER);
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        }
        catch (Exception e) {
            // throw new Exception(e.getMessage());
            throw e;
        }
    }

    public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) throws Exception {
        if (StringUtils.isEmpty(hexModulus) || StringUtils.isEmpty(hexPrivateExponent)) {
            return null;
        }
        byte[] modulus = null;
        byte[] privateExponent = null;
        try {
            modulus = hexStr2Bytes(hexModulus);
            privateExponent = hexStr2Bytes(hexPrivateExponent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (modulus != null && privateExponent != null) {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }


    /**
     * * 生成私钥 *
     * @param modulus *
     * @param privateExponent *
     * @return RSAPrivateKey *
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", DEFAULT_PROVIDER);
        }
        catch (NoSuchAlgorithmException ex) {
            throw ex;
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        }
        catch (InvalidKeySpecException ex) {
            throw ex;
        }
    }

    /**
     * * 解密 *
     * @param pk
     *            解密的密钥 *
     * @param raw
     *            已经加密的数据 *
     * @return 解密后的明文 *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", DEFAULT_PROVIDER);
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        }
        catch (Exception e) {
            // throw new Exception(e.getMessage());
            throw e;
        }
    }

    /**
     * 解密字符串
     * @param pk
     * @param str
     * @return
     * @throws Exception
     */
    public static String decryptString(PrivateKey pk, String str) throws Exception {
        byte[] en_str = Hex.decodeHex(str.toCharArray());
        byte[] de_str = decrypt(pk, en_str);
        String strTemp = new String(de_str,CharEncoding.UTF_8);
        return strTemp;
    }

    /**
     * 利用私钥解密
     * **/
    public static String decryptString(String privateModulus, String privateExponent, String str) throws Exception {
        PrivateKey privateKey = RSA.getRSAPrivateKey(privateModulus, privateExponent);
        return decryptString(privateKey, str);
    }


    /**
     * String转16位字节
     * @param src
     * @return
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] retVal = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2;
            n = m + 2;
            retVal[i] = (byte) (Integer.parseInt(src.substring(m, n), 16));
        }
        return retVal;
    }

}
