package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.entity.CountryEntity;
import com.visa.service.CountryService;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Test-Lab on 2016/6/29.
 */
@Named
@Scope("request")
public class Country {

    @Resource
    private CountryService countryService;

    private String countryName;
    private String nationalFlag;
    private int interContinental;

    private UISelectOne selectone;
    private UploadedFile file;
    private String result;

    public String getCountryName() {

        return countryName;
    }

    public void setCountryName(String countryName) {
        if (countryName == null) {
            throw new RuntimeException("请输入数据");
        }
        this.countryName = countryName;
    }

    public int getInterContinental() {
        return selectIndex;
    }

    public void setInterContinental(int interContinental) {
        this.interContinental = interContinental;
    }

    public String getNationalFlag() {
        if (file != null) {
            return FilenameUtils.getName(file.getName());
        } else {
            throw new RuntimeException("请选择相应国旗图片");
        }
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setNationalFlag(String nationalFlag) {
        this.nationalFlag = nationalFlag;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * @return the selectone
     */
    public UISelectOne getSelectone() {
        return selectone;
    }

    /**
     * @param selectone the selectone to set
     */
    public void setSelectone(UISelectOne selectone) {
        this.selectone = selectone;
    }

    public void addCountry(ActionEvent evt) {
        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setName(countryName);
        countryEntity.setNationalFlag(getNationalFlag());
        countryEntity.setInterContinental(getInterContinental());
        try {
            if (submit()) {
                countryService.addCountry(countryEntity);
            }
            result = "Create country successfully";
        } catch (IOException e) {
            e.printStackTrace();
            result = "Create country unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SelectItem> getSelectItemList() {
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        for (int index = 1; index < 8; index++) {
            ContinentsEnum.getName(index);
            selectItemList.add(new SelectItem(index, ContinentsEnum.getName(index)));
        }
        return selectItemList;
    }

    private int selectIndex = 0;

    public void selectChange(ValueChangeEvent event) {
        selectIndex = Integer.parseInt((String) selectone.getValue());
    }

    private Boolean submit() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        String path = sc.getRealPath("\\resources\\images");
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
            OutputStream bos = new FileOutputStream(path + "\\" + file.getName());
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((bytesRead = stream.read(buffer, 0, 1024 * 1024)) != -1) {
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
}
