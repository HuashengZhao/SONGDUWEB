package com.example.EAS.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;


/**
 * Created by Watson on 2019/3/12.
 */


public class DesEncrypt {

    Key key;

    public DesEncrypt(String str) {
        setKey(str);// 生成密匙
    }

    public DesEncrypt() {
        setKey("VIP20198888*7*6*5*4**88882088VIP");
    }

    /**
     * 根据参数生成KEY
     */

    public void setKey(String strKey) {
        try {
            //对比DES
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            this.key = keyFactory.generateSecret(new DESedeKeySpec(strKey.getBytes("UTF8")));
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        }
    }


    /**
     * 加密:String明文输入,String密文输出
     */
    public String encrypt(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = this.getEncCode(byteMing);
            strMi = base64en.encode(byteMi);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    /**
     * 解密:以String密文输入,String明文输出
     *
     * @param strMi
     * @return
     */

    public String decrypt(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = this.getDesCode(byteMi);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    /**
     * 加密以byte[]明文输入,byte[]密文输出
     *
     * @param byteS
     * @return
     */
    private byte[] getEncCode(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {//对比DES
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key, SecureRandom.getInstance("SHA1PRNG"));
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 解密以byte[]密文输入,以byte[]明文输出
     *
     * @param byteD
     * @return
     */
    private byte[] getDesCode(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {//对比DES
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, key, SecureRandom.getInstance("SHA1PRNG"));
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error initializing SqlMap class. Cause: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }
//==========================================================snowflake===================================================
    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1489111610226L;

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private static DesEncrypt idWorker;

    static {
        idWorker = new DesEncrypt();
    }

// ==============================================================Methods================================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置，不同毫秒内，序列号置为0
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //时间部分
                | sequence;                             //序列号部分
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 静态工具类
     *
     * @return
     */

    public static synchronized Long generateId() {
        long id = idWorker.nextId();
        return id;
    }
//==================================================最终生成============================================================

    /**
     * 最终生成id的方法
     *
     * @param sourceType
     * @param key
     * @return
     */
    public static String createID(String sourceType, String key) {
        DesEncrypt des = new DesEncrypt();
        long id = DesEncrypt.generateId();
        String timeAndSequence = String.valueOf(id);
        StringBuffer sb = new StringBuffer();
        String strMing = sb.append(timeAndSequence).append(key).append(sourceType).toString();
        String strMi = des.encrypt(strMing);
        return strMi;

    }


//========================================================测试==========================================================

    /**
     * test
     *
     * @param args
     */

    public static void main(String args[]) {
//        String id=createID("1","10");
//        System.out.println(id);
//        DesEncrypt des = new DesEncrypt();
//
//        long id = DesEncrypt.generateId();
//
//        String s = "1022";
//
//        String str1 = String.valueOf(id);
//
//        // DES加密
//        StringBuffer sb = new StringBuffer();
//
//        String s1 = sb.append(str1).append(s).toString();
//
//        String str2 = des.encrypt("user");
//        System.out.println("密文:" + str2);
        DesEncrypt des1 = new DesEncrypt();
        String deStr=des1.decrypt("");
//        // DES解密
        System.out.println("明文:" + deStr);
//        System.out.println(str2.length());
//        System.out.println("\n2019/12/23 10:42:16 - Spoon - 正在开始任务... \n2019/12/23 10:42:16 - account_view");


    }
}

