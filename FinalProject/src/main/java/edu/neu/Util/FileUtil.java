package edu.neu.Util;

import edu.neu.Pojo.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class FileUtil {

//    save to server
    public String createResumeJobFolder(String FolderName , User user ) throws IOException {

        File resumeFolder = new File("D:\\FinalProjectPDFJOB" +FolderName + "\\" + user.getUname());
        resumeFolder.mkdirs();
        return resumeFolder.getAbsolutePath();
    }

    private String generateFileName(MultipartFile multiPartFile) {

        return new Date().getTime()+"-" + multiPartFile.getOriginalFilename().replace(" ","_");


    }
    public void download(String UserName , String type ,String Url) {
        System.out.println("download exe");
        String dirName = "D:\\Download";
        String extension =  FilenameUtils.getExtension(Url);
        System.out.println("Extension for file -- " +Url+ "---" +extension);

        String fileName = dirName + "\\"  +UserName + "." +type+ "." +extension ;

        String newUrl = Url.replace("\\", "/");
        String newUrl1= "file:///" +newUrl;
        System.out.println("Url after Changes -- " +newUrl1);
        downloadFile(newUrl1,fileName );


    }
    public boolean downloadFile(String url, String localname) {
        System.out.println("正在下载");

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream OutputStream = new FileOutputStream(localname)) {

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataBuffer, 0, 1024)) != -1) {
                OutputStream.write(dataBuffer, 0, bytesRead);
            }
            OutputStream.close();
            OutputStream.flush();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
