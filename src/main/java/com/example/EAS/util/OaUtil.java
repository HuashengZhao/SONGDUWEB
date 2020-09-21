package com.example.EAS.util;



import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * OA审批相关Util (OA对接加密算法)
 * 
 */
public class OaUtil {
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String DEFAULT_PSWD = "Djk@%&opN!$$*";
    private static final String KEY_ALGORITHM = "AES";
    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     * @throws Exception
     */
    public static String encrypt(String content) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] byteContent = content.getBytes("utf-8");
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(DEFAULT_PSWD));
        // 加密
        byte[] result = cipher.doFinal(byteContent);
        //通过Base64转码返回
        return Base64.encodeBase64String(result);
    }

    /**
     * 生成加密秘钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKeySpec getSecretKey(final String password) throws NoSuchAlgorithmException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(password.getBytes());
        //AES 要求密钥长度为 128
        kg.init(128, secureRandom);
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }
    /**
     * 解密方法
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(String content) throws Exception {
        //实例化
      Cipher cipher =  Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE,
                getSecretKey(DEFAULT_PSWD));
        //执行操作
        byte[] result =  cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, "utf-8");
    }

}
