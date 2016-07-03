package com.visa.bean;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    }

}
