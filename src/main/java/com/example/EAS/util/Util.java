package com.example.EAS.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 共通方法的定义
 */
public class Util {
    /**
     * 订单流水号
     */
    public static String number = "0";
    /**
     * 订单流水号位数
     */
    public static int no = 6;


    /**
     * 空检查
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else {
            if (value instanceof String) {
                String strTmp = (String) value;
                if (strTmp.trim().length() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (value instanceof Collection) {
                Collection<?> datalist = (Collection<?>) value;
                if (datalist.size() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (value instanceof Map) {
                Map<?, ?> datalist = (Map<?, ?>) value;
                if (datalist.size() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 非空验证
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }


    public static boolean isEmptyList(Collection<?> list) {
        if (list == null) {
            return true;
        } else if (list.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmptyMap(Map<?, ?> list) {
        if (list == null) {
            return true;
        } else if (list.size() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private static final String REG_RXPRE_RESV[] = {"\\.", "\\^", "\\$",
            "\\[", "\\]", "\\*", "\\+", "\\?", "\\|", "\\(", "\\)"};


    /**
     * project路径取得
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 换行文字
     *
     * @return
     */
    public static String getLineSep() {
        return System.getProperty("line.separator");
    }

    /**
     * @param totalRecNum
     * @param perPageNum
     * @return 总页数
     */
    public static int getTotalPage(int totalRecNum, int perPageNum) {
        if ((totalRecNum <= 0) || (perPageNum <= 0)) {
            return 0;
        } else {
            int result = totalRecNum / perPageNum;
            if ((result * perPageNum) == totalRecNum) {
                return result;
            } else {
                return result + 1;
            }
        }
    }

    /**
     * 获取byte数
     *
     * @param value
     * @return
     */
    public static int getByteNum(String value) {
        if (value == null) {
            return 0;
        }
        return value.getBytes().length;
    }

    static final String CERT_BASE_LIST = "0123456789ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuwxyz_-";

    public static String getOcxCertifyPassword(int pwdLen) {
        StringBuffer sbCertifyPassword = new StringBuffer();
        int listLen = CERT_BASE_LIST.length();
        for (int iNum = 0; iNum < pwdLen; iNum++) {
            Random rand = new Random();
            int iPos = (int) (listLen * rand.nextDouble());
            if (iPos < 0) {
                iPos = 0;
            }
            if (iPos >= listLen) {
                iPos = listLen - 1;
            }
            sbCertifyPassword.append(CERT_BASE_LIST.charAt(iPos));
        }
        return sbCertifyPassword.toString();
    }

    /**
     * @param param
     * @param value [|]
     * @return
     */
    public static List<String> splitFullString(String value, String param) {
        List<String> result = new ArrayList<String>();
        if (isEmpty(value)) {
            return result;
        } else if (isEmpty(param)) {
            result.add(value);
            return result;
        } else {
            while (true) {
                int iPos = value.indexOf(param);
                if (iPos < 0) {
                    result.add(value);
                    break;
                } else {
                    result.add(value.substring(0, iPos));
                    value = value.substring(iPos + param.length());
                }
            }
            return result;
        }
    }

    /**
     * 换行文字替换 过期，可使用patch.css中的t_wrap标记页面上你需要\r\n换行的元素
     *
     * @param value
     * @return
     */
    @Deprecated
    public static String replaceReturnWithBR(String value) {
        if (Util.isEmpty(value)) {
            return null;
        }
        String result = value.trim();
        result = result.replaceAll("\r\n", "<br/>");
        result = result.replaceAll("\n", "<br/>");
        return result;
    }

    /**
     * CharArray
     *
     * @param array
     * @param beginIndex
     * @param endIndex
     */
    public static char[] getCharArray(char[] array, int beginIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        char[] tmp = new char[endIndex - beginIndex];
        int count = 0;
        for (int i = beginIndex; i < endIndex; i++) {
            if (i < array.length) {
                tmp[i - beginIndex] = array[i];
                count++;
            } else {
                break;
            }
        }
        char[] ret = new char[count];
        for (int i = 0; i < count; i++) {
            ret[i] = tmp[i];
        }
        return ret;
    }

    public static java.sql.Date timestampToDate(Timestamp p) {
        if (p == null) {
            return null;
        }
        return new java.sql.Date(p.getTime());
    }

    /**
     * 保存文件
     *
     * @param is       输入流
     * @param filepath 输出文件地址
     * @throws Exception
     */
    public static void saveFile(BufferedInputStream is, String filepath)
            throws Exception {
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        FileOutputStream fos = new FileOutputStream(filepath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.close();
        fos.close();
    }

    /**
     * 删除文件夹中的文件（递归删除）
     *
     * @param path
     */
    public static void clearFolder(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String str : list) {
                clearFolder(path + "/" + str);
            }
        } else {
            file.delete();
        }
    }

    /**
     * 删除文件夹中的文件（不递归删除）
     *
     * @param path
     */
    public static void clearFolderL1(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list = file.list();
            for (String str : list) {
                file = new File(path + "/" + str);
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }

    /**
     * 文件拷贝
     *
     * @param oldFilename
     * @param newFilename
     * @param bNotOverWrite
     */
    public synchronized static void copyFile(String oldFilename,
                                             String newFilename, boolean bNotOverWrite) {
        FileChannel ifc = null;
        FileChannel ofc = null;
        try {
            File fileNew = new File(newFilename);
            if ((bNotOverWrite && fileNew.exists())) {
                // 文件已存在、不覆盖模式的情况下，不处理
                return;
            }
            File file = new File(oldFilename);
            if (!file.exists()) {
                // 文件不存在
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            ifc = fis.getChannel();
            FileOutputStream fos = new FileOutputStream(fileNew);
            ofc = fos.getChannel();
            // 数据传输
            ifc.transferTo(0, ifc.size(), ofc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ifc != null) {
                try {
                    ifc.close();
                } catch (IOException e) {
                }
            }
            if (ofc != null) {
                try {
                    ofc.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 去除头和尾的字符串
     *
     * @param value         传入的字符串
     * @param replaceFirst  去除第一个字符串
     * @param subStringLast 去除的最后一个字符串
     * @return
     */
    public static String removeFirstAndLastString(String value,
                                                  String replaceFirst, String subStringLast) {
        if (!Util.isEmpty(value)) {
            String newValue = value.substring(0,
                    value.lastIndexOf(subStringLast));
            newValue = newValue.replaceFirst(replaceFirst, "");
            return newValue;
        } else {
            return null;
        }
    }

    /**
     * 视频转换
     *
     * @param pInputFile
     * @param pOutputFile
     * @param pConvToolPath
     * @return
     */
    public static boolean convVideoToFlv(String pInputFile, String pOutputFile,
                                         String pConvToolPath) {
        if (Util.isEmpty(pInputFile)) {
            return false;
        }
        if (isNotValidFile(pInputFile)) {
            return false;
        }
        List<String> command = new ArrayList<String>();
        command.add(pConvToolPath);
        command.add("-y");
        command.add("-i");
        command.add(pInputFile);
        command.add("-ab");
        command.add("5600");
        command.add("-ac");
        command.add("1");
        command.add("-ar");
        command.add("44100");
        command.add("-vcodec");
        command.add("libx264");
        command.add("-vprofile");
        command.add("baseline");
        command.add("-r");
        command.add("24");
        command.add("-level");
        command.add("30");
        // command.add("-sameq");
        // command.add("-s");
        // command.add("1280*960");
        command.add(pOutputFile);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            builder.redirectErrorStream(true);
            Process pro = builder.start();
            BufferedReader buf = null;
            String line = null;
            buf = new BufferedReader(
                    new InputStreamReader(pro.getInputStream()));
            StringBuffer sb = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                continue;
            }
            pro.waitFor();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // 检查文件是否存在
    public static boolean isNotValidFile(String value) {
        if (Util.isEmpty(value)) {
            return false;
        }
        File file = new File(value);
        if (!file.exists() || !file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否匹配正则
     *
     * @param str
     * @param regStr
     * @return
     */
    public static boolean isMatch(String str, String regStr) {
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 获取登录用户IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取随机数（-6到6）
     *
     * @return
     */
    public static int getRandomNum() {
        int max = 6;
        int min = -6;
        Random random = new Random();
        int s = random.nextInt(max - min + 1) - max;
        return s;
    }

    /**
     * @param code
     * @return
     * @description :补0，补足3位
     * @author yq
     * 2017年9月13日  下午3:55:07
     */
    public static String autoGenericCode3(String code) {
        return autoGenericCode(code, 3);
    }

    /**
     * @param code
     * @param num
     * @return
     * @description :不够位数的在前面补0，保留num的长度位数字
     * @author yq
     * 2017年9月13日  下午3:51:56
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0     
        // num 代表长度   
        // d 代表参数为正数型 
        result = String.format("%0" + num + "d", Integer.parseInt(code));
        return result;
    }

    /**
     * 相除保留小数位
     *
     * @param v1     参数1
     * @param v2     参数2
     * @param decial 小数位
     */
    public static Double div(int v1, int v2, int decial) {
        if (v2 == 0) {
            return 0d;
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, decial, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(div(340 * 100, 600, 2));
    }

    /**
     * 设置每日订单流水号和标识
     *
     * @param
     */
    public static synchronized void initNumber(Boolean bool) {
        /**如果今日订单数为0,重置订单流水号,设置首单标识*/
        if (bool) {
            number = "1";
        } else {
        }
    }

    /**
     * 应用重开时流水号赋值
     *
     * @param num 数据库日最大流水号
     */
    public static synchronized void setNumber(String num) {
        number = num;
    }

    /**
     * 生成随机6位数字
     */
    public static int getRandom() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

}
