package com.example.EAS.util;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Vector;

@Slf4j
@Component
public class EasFileDownLoadUtil {

    private Connection conn = null;

    public Connection getConnection(){
        return conn;
    }
    public  boolean login(String ip, int port, String name, String pwd){
        //创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
        //new Connection(ip, port)创建对象
        conn = new Connection(ip,port);
        try {
            //连接远程服务器
            conn.connect();
            //使用用户名和密码登录
            return conn.authenticateWithPassword(name, pwd);
        } catch (IOException e) {
            System.err.printf("用户%s密码%s登录服务器%s失败！", name, pwd, ip);
            e.printStackTrace();
        }
        return false;
    }

    public  boolean logout(){

        if(conn != null){
            conn.close();
        }

        return true;
    }


    /**
     * 流式输出，用于浏览器下载
     * @param conn
     * @param fileName
     * @param outputStream
     */
    public void copyFile(Connection conn, String fileName, ServletOutputStream outputStream){
        SCPClient sc = new SCPClient(conn);
        try {
            sc.get(fileName, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn != null ){
                conn.close();
            }
        }
    }


    /**
     * 在远程LINUX服务器上，在指定目录下，获取文件各个属性
     * @param[in] conn Conncetion对象
     * @param[in] remotePath 远程主机的指定目录
     */
    public void getFileProperties(Connection conn, String remotePath){
        try {
            SFTPv3Client sft = new SFTPv3Client(conn);

            Vector<?> v = sft.ls(remotePath);

            for(int i=0;i<v.size();i++){
                SFTPv3DirectoryEntry s = new SFTPv3DirectoryEntry();
                s = (SFTPv3DirectoryEntry) v.get(i);
                //文件名
                String filename = s.filename;
                System.out.println(filename);
                //文件的大小
                Long fileSize = s.attributes.size;
            }
            sft.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //RemoteInvokeShell("49.232.131.207",22,"root","Apple1995",ShellConstant.REMOVE_USER_CLOUD_FILE+"c1/cc/c1.out "+ShellConstant.REMOVE_USER_CLOUD_FILE+"c1/cc/测试.out");
        EasFileDownLoadUtil ftpUtils=new EasFileDownLoadUtil();
        ftpUtils.login("172.17.16.31", 22, "admin", "sdjt1234@#");
        //ftpUtils.getFileProperties(ftpUtils.conn,"/mnt/ftp/dvr_data/2020/01/04/");
        SCPClient sc = new SCPClient(ftpUtils.getConnection());
        try {
            sc.get("/mnt/ftp/dvr_data/2020/01/04/live000/live/company_1_staff_8.flv.tmp", "D:/publicfile");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
