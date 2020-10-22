package com.example.EAS.util;

import com.example.EAS.mapper.TConSupplierapplyMapper;
import com.example.EAS.vo.AttachmentsVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


/**
 * @PackageName:com.example.EAS.config
 * @ClassName:demo
 * @Description:
 * @author: $$ Watson $$ zhs19951228@163.com
 * @date:2020/9/17 9:33
 */
@Slf4j
@Component
public class FtpUtil {

    @Autowired
    private TConSupplierapplyMapper mapper;

    public static void main(String[] args) throws Exception {
        String host = "172.17.4.60";
        Integer port = 21;
        String username = "adminftp";
        String password = "sdjt2020@#";
        String basePath = "/";
        String filePath = "/ftp/2020/9/17";

//
//        File file = new File("C:\\Users\\LENOVO\\Desktop\\work\\eas\\timg (2).jpg");
//        FileInputStream isinput = new FileInputStream(file);
//        boolean b = uploadFile(filePath, "timg (2).jpg", isinput);
//
//        FTPClient ftp = new FTPClient();
//        try {
//            int reply;
//            ftp.connect("172.17.4.60", 21);// 连接FTP服务器
//            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//            ftp.login("adminftp", "sdjt2020@#");// 登录
//
//            moveFile("/ftp/2020/09/18/1600390482878","/ftp/2020/09/18/000000000");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public InputStream transFile(MultipartFile file) {
        InputStream ips = null;
        File file1 = null;
        try {
            file1 = File.createTempFile("temp", null);
            file.transferTo(file1);
            ips = new FileInputStream(file1);
            file1.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ips;
    }


    /**
     * 附件上传
     * type：inputstream
     *
     * @param filePath
     * @param filename
     * @param input
     * @return
     */
    public boolean uploadFile(String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        ftp.enterLocalPassiveMode();
        try {
            int reply;
            ftp.connect("172.17.4.60", 21);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("adminftp", "sdjt2020@#");// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory("/ftp" + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = "/";
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                        System.out.println(tempPath);
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            result = ftp.storeFile(new String(filename.getBytes("GBK"), "ISO-8859-1"), input);
            System.out.println(new String(filename.getBytes("GBK"), "ISO-8859-1"));

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                input.close();
                ftp.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 保存附件单据关联 修改ftp文件名
     */
    public void saveAttachMent(List<AttachmentsVO> attachmentsVOList, String billId) {
        for (AttachmentsVO attachmentsVO : attachmentsVOList) {

            String personName = attachmentsVO.getPersonName();
            String webUrl = attachmentsVO.getWebUrl();
            if (attachmentsVO.getCreateTime() != null) {
                attachmentsVO.setCreateTime(attachmentsVO.getCreateTime());
            }
            attachmentsVO.setEasId(billId);
            attachmentsVO.setPersonName(personName);

            //        修改ftp附件目录
            String[] split = webUrl.split("/");
            String changeUrl = split[webUrl.split("/").length - 1];
            String encode = URLEncoder.encode(billId);
            String newUrl = webUrl.replace(changeUrl, encode).replace("ftp/", "");

            //      存入对应信息到 eas database
            mapper.insertAttachMent(attachmentsVO);

        }
    }


    /**
     * 实现文件的移动，这里做的是一个文件夹下的所有内容移动到新的文件，
     * 如果要做指定文件移动，加个判断判断文件名
     * 如果不需要移动，只是需要文件重命名，可以使用ftp.rename(oleName,newName)
     *
     * @param
     * @param oldPath
     * @param newPath
     * @return
     */
    public boolean moveFile(String oldPath, String newPath) {
        boolean flag = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("172.17.4.60", 21);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("adminftp", "sdjt2020@#");// 登录
            ftp.changeWorkingDirectory(oldPath);
            ftp.enterLocalPassiveMode();
            //获取文件数组
            FTPFile[] files = ftp.listFiles();

            //新文件夹不存在则创建
            ftp.changeWorkingDirectory("/");
            if (!ftp.changeWorkingDirectory(newPath)) {

                /*该部分为逐级创建*/
                String[] split = newPath.split("/");

                ftp.changeWorkingDirectory("/");
                for (String str : split) {
                    if (StringUtils.isBlank(str)) {
                        continue;
                    }
                    if (!ftp.changeWorkingDirectory(str)) {
                        System.err.println("不存在");
                        boolean makeDirectory = ftp.makeDirectory(str);
                        boolean changeWorkingDirectory = ftp.changeWorkingDirectory(str);
                    } else {
                        System.err.println("存在");
                    }
                }

            }

            ftp.changeWorkingDirectory("/");
            //回到原有工作目录
            ftp.changeWorkingDirectory(oldPath);
            for (FTPFile file : files) {
                //转存目录
                flag = ftp.rename(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"), newPath + File.separator + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                if (flag) {
                    System.out.println(file.getName() + "移动成功");
                } else {
                    System.out.println(file.getName() + "移动失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("移动文件失败");
        }
        return flag;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public boolean downloadFile(String remotePath,
                                String fileName, String localPath) {

        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("172.17.4.60", 21);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("adminftp", "sdjt2020@#");// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            ftp.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(new String(ff.getName().getBytes("GBK"), "ISO-8859-1"), is);
                    is.close();
                }
            }
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 宋都web 附件下载
     *
     * @param request
     * @param response
     * @param remotePath
     * @param fileName
     */
    public void exportOutputStream(HttpServletRequest request
            , HttpServletResponse response, String remotePath, String fileName) {

        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("172.17.4.60", 21);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("adminftp", "sdjt2020@#");// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return;
            }
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {

//                        new String(ff.getName().getBytes(""));
                if (ff.getName().equals(fileName)) {

                    String filenameEncoder = "";
                    response.reset();
                    try {
                        filenameEncoder = java.net.URLEncoder.encode(fileName, "utf-8");
                        filenameEncoder = filenameEncoder.replace("+", " ");
                        response.addHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    response.addHeader("Access-Control-Allow-Origin", "*");
                    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                    response.addHeader("Access-Control-Max-Age", "3600");
                    response.addHeader("Access-Control-Allow-Headers", "x-requested-with");
                    response.addHeader("Content-Length", "" + ff.getSize());
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/octet-stream");
                    ftp.retrieveFile(ff.getName(), response.getOutputStream());
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }

    /**
     * 天联云ftp 附件下载
     *
     * @param request
     * @param response
     * @param remotePath
     * @param fileName
     */
    public void exportTLYOS(HttpServletRequest request
            , HttpServletResponse response, String remotePath, String fileName) {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect("172.17.16.31 ", 21);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login("admin", "sdjt1234@#");// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return;
            }
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
//                        new String(ff.getName().getBytes(""));
                if (ff.getName().equals(fileName)) {
                    String filenameEncoder = "";
                    response.reset();
                    try {
                        filenameEncoder = java.net.URLEncoder.encode(fileName, "utf-8");
                        filenameEncoder = filenameEncoder.replace("+", " ");
                        response.addHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    response.addHeader("Access-Control-Allow-Origin", "*");
                    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                    response.addHeader("Access-Control-Max-Age", "3600");
                    response.addHeader("Access-Control-Allow-Headers", "x-requested-with");
                    response.addHeader("Content-Length", "" + ff.getSize());
                    response.setCharacterEncoding("utf-8");
                    response.setContentType("application/octet-stream");
                    ftp.retrieveFile(ff.getName(), response.getOutputStream());
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
}
