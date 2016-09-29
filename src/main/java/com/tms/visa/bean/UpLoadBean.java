package com.tms.visa.bean;

import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.*;
import java.security.MessageDigest;

@Named
@Scope("request")
public class UpLoadBean {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UpLoadBean(UploadedFile file){
        this.file=file;
    }
    public Boolean submit() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext)context.getExternalContext().getContext();
        String path=sc.getRealPath("\\resources\\images") ;
        String fileName = FilenameUtils.getName(file.getName());
        String contentType = file.getContentType();
        // Now you can save bytes in DB (and also content type?)
       /* InputStreamReader reader = new InputStreamReader(file.getInputStream());
        OutputStreamWriter  writer = new OutputStreamWriter (new FileOutputStream(new File(path+"\\"+fileName)));
        int ch = 0;
        while((ch = reader.read())!=-1 ){
            writer.write(ch);
        }

        writer.flush();
        reader.close();
        writer.close();
        context.addMessage(null,
                new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));*/

        try {
            InputStream stream = file.getInputStream();// 把文件读入
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream bos = new FileOutputStream( path+ "\\" + file.getName());
            int bytesRead = 0;
            byte[] buffer = new byte[1024*1024];
            while ((bytesRead = stream.read(buffer, 0, 1024*1024)) != -1) {
                bos.write(buffer, 0, bytesRead);// 将文件写入服务器
            }
            bos.close();
            stream.close();
            return Boolean.TRUE;
        } catch (Exception e) {
            System.err.print(e);
            return Boolean.FALSE;
        }
    }

    public String processMyFile() {

        try {

            MessageDigest md

                    = MessageDigest.getInstance("MD5");

            InputStream in = new BufferedInputStream(

                    file.getInputStream());

            try {

                byte[] buffer = new byte[1024 * 1024];

                int count;

                while ((count = in.read(buffer)) > 0)

                    md.update(buffer, 0, count);

            } finally {

                in.close();

            }

            byte hash[] = md.digest();

            StringBuffer buf = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {

                int b = hash[i] & 0xFF;

                int c = (b >> 4) & 0xF;

                c = c < 10 ? '0' + c : 'A' + c - 10;

                buf.append((char) c);

                c = b & 0xF;

                c = c < 10 ? '0' + c : 'A' + c - 10;

                buf.append((char) c);

            }
            BufferedWriter output = new BufferedWriter(new FileWriter(new File("classpath")));
            output.write(buf.toString());
            return "OK";

        } catch (Exception x) {

            FacesMessage message = new FacesMessage(

                    FacesMessage.SEVERITY_FATAL,

                    x.getClass().getName(), x.getMessage());

            FacesContext.getCurrentInstance().addMessage(

                    null, message);

            return null;

        }

    }
/*
    public String uploadedfile() {

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            //在控制台输出上传文件的文件名，和文件大小
            FacesMessage message = new FacesMessage(
                    "Successfully uploaded file" + file.getFilename() + "("
                            + file.getLength() + "bytes)");
            context.addMessage(null, message);

            InputStream stream;
            stream = new BufferedInputStream(file.getInputStream());
            byte[] bytes = new byte[1024 * 1024];//设定文件大小
            @SuppressWarnings("unused")
            int count;
            count = stream.read(bytes);
            stream.close();//关闭流
            return "uploadedfile";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
