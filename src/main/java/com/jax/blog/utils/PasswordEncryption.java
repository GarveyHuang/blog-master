package com.jax.blog.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @ClassName PasswordEncryption
 * @Description PBKDF2(Password Based Key Derivation Function) 加密算法
 * @Author huangjw
 * @Date 2018/09/17 22:14
 * @Version 1.0
 **/
public class PasswordEncryption {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

    /**
     * 盐的长度
     */
    private static final int SALT_BYTE_SIZE = 32 / 2;

    /**
     * 生成密文的长度
     */
    private static final int HASH_BIT_SIZE = 128 * 4;

    /**
     * 迭代次数
     */
    private static final int PBKDF2_ITERATIONS = 2000;

    public static boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return encryptedAttemptedPassword.equals(encryptedPassword);
    }

    /**
     * 生成密文
     * @param password 明文密码
     * @param salt 盐值
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String getEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(Charset.forName("UTF-8")), PBKDF2_ITERATIONS, HASH_BIT_SIZE);
        SecretKey secretKey = secretKeyFactory.generateSecret(spec);

        byte[] rawHash = secretKey.getEncoded();
        byte[] hashBase64 = Base64.getEncoder().encode(rawHash);

        return  new String(hashBase64);
    }

    /**
     * 通过提供加密的强随机数生成器 生成盐
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        char[] salt = new char[SALT_BYTE_SIZE];
        for (int i = 0; i < SALT_BYTE_SIZE; i++) {
            int t = random.nextInt(3);
            if (t == 0) {
                salt[i] = (char) (random.nextInt(10) + 48);
            } else if (t == 1) {
                salt[i] = (char) (random.nextInt(26) + 65);
            } else {
                salt[i] = (char) (random.nextInt(26) + 97);
            }
        }

        return new String(salt);
    }
}
