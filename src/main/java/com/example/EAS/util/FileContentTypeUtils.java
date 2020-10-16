package com.example.EAS.util;

public class FileContentTypeUtils {

    public static String contentType(String FilenameExtension) {
        if (FilenameExtension.contains(".BMP") || FilenameExtension.contains(".bmp")
                || FilenameExtension.toUpperCase().contains(".BMP")) {
            return "image/bmp";
        }
        if (FilenameExtension.contains(".GIF") || FilenameExtension.contains(".gif")
                || FilenameExtension.toUpperCase().contains(".GIF")) {
            return "image/gif";
        }
        if (FilenameExtension.contains(".JPEG") || FilenameExtension.contains(".jpeg") || FilenameExtension.contains(".JPG")
                || FilenameExtension.contains(".jpg") || FilenameExtension.contains(".PNG")
                || FilenameExtension.contains(".png") || FilenameExtension.toUpperCase().contains(".JPEG")
                || FilenameExtension.toUpperCase().contains(".JPG") || FilenameExtension.toUpperCase().contains(".PNG")) {
            return "image/jpeg";
        }
        if (FilenameExtension.contains(".HTML") || FilenameExtension.contains(".html")) {
            return "text/html";
        }
        if (FilenameExtension.contains(".TXT") || FilenameExtension.contains(".txt")
                || FilenameExtension.toUpperCase().contains(".TXT")) {
            return "text/plain";
        }
        if (FilenameExtension.contains(".VSD") || FilenameExtension.contains(".vsd")
                || FilenameExtension.toUpperCase().contains(".VSD")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.contains(".PPTX") || FilenameExtension.contains(".pptx") || FilenameExtension.contains(".PPT")
                || FilenameExtension.contains(".ppt") || FilenameExtension.toUpperCase().contains(".PPTX")
                || FilenameExtension.toUpperCase().contains(".PPT")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.contains(".DOCX") || FilenameExtension.contains(".docx") || FilenameExtension.contains(".DOC")
                || FilenameExtension.contains(".doc") || FilenameExtension.toUpperCase().contains(".DOCX")
                || FilenameExtension.toUpperCase().contains(".DOC")) {
            return "application/msword";
        }
        if (FilenameExtension.contains(".XML") || FilenameExtension.contains(".xml")
                || FilenameExtension.toUpperCase().contains(".XML")) {
            return "text/xml";
        }
        if (FilenameExtension.contains(".pdf") || FilenameExtension.contains(".PDF")
                || FilenameExtension.toUpperCase().contains(".PDF")) {
            return "application/pdf";
        }
        if (FilenameExtension.contains(".xls") || FilenameExtension.contains(".XLS")
                || FilenameExtension.toUpperCase().contains(".XLS")) {
            return "application/vnd.ms-excel";
        }
//        '' => 'application/vnd.ms-excel',
        return null;
    }
}
