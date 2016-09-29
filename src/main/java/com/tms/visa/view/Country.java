package com.tms.visa.view;

import com.tms.visa.bean.ContinentsEnum;
import com.tms.visa.entity.CountryEntity;
import com.tms.visa.service.CountryService;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.annotation.Scope;


import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Test-Lab on 2016/6/29.
 */
@Named
@Scope("request")
public class Country {
    @Resource
    private CountryService countryService;

    private UISelectOne selectone;
    private UploadedFile file;
    private String result;
    private CountryEntity countryEntity;
    public Country(){

        countryService = new CountryService();
        countryEntity = new CountryEntity();
    }

    public int getInterContinental() {
        return selectIndex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
        countryEntity.setId(countryService.getKeyValue());
        countryEntity.setNationalFlag(FilenameUtils.getName(file.getName()));
        countryEntity.setInterContinental(getInterContinental());
        try {
            if (submit()) {
                countryService.addCountry(countryEntity);
            }
            result = "Create successfully";
        } catch (IOException e) {
            e.printStackTrace();
            result = "Create unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DataModel getListCountry() {
        return new ListDataModel(countryService.findAllCountry());
    }

    private boolean validationField() {
        boolean validateResult = false;
        boolean countryNameValidatedResult = true;
        boolean fileValidatedResult = true;
        /*if (this.countryName != null && StringUtils.trim(countryName).equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "国家名称不能为空，请输入国家名称", "errormessage");
            // Add the message into context for a specific component
            FacesContext.getCurrentInstance().addMessage("countryName", message);
            countryNameValidatedResult = false;
        }*/
        if (file == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "未选图片，请选择相应国旗图片", "errormessage");
            // Add the message into context for a specific component
            FacesContext.getCurrentInstance().addMessage("uploadfile", message);
            fileValidatedResult = false;
        }
        return validateResult = (countryNameValidatedResult && fileValidatedResult);
    }

    public void delCountry(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        if( requestParams.containsKey("Id")){
            String id = (String) requestParams.get("Id");
            countryEntity = countryService.findCountryId(new Long(id));
        }
        countryService.deleteCountryById(countryEntity.getId());
    }

    public List<SelectItem> getSelectItemList() {
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        for (int index = 1; index < 8; index++) {
            ContinentsEnum.getName(index);
            selectItemList.add(new SelectItem(index, ContinentsEnum.getName(index)));
        }
        return selectItemList;
    }

    public String getContinentValue(int index) {
        return ContinentsEnum.getName(index);

    }

    public void updateCountry(){
        countryEntity.setInterContinental(getInterContinental());
        countryEntity.setNationalFlag(FilenameUtils.getName(file.getName()));
        try {
            if (submit()) {
              countryService.updateCountry(countryEntity);
            }
            result = "Update successfully";
        } catch (IOException e) {
            e.printStackTrace();
            result = "Updte unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    private int selectIndex = 1;

    public void selectChange(ValueChangeEvent event) {
        selectIndex = Integer.parseInt((String) selectone.getValue());
    }

    private Boolean submit() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        String path = sc.getRealPath("\\resources\\images");
        String fileName = FilenameUtils.getName(file.getName());
        String contentType = file.getContentType();
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
    boolean editable;

    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String editAction(CountryEntity countryEntity) {
        Country country= new Country();
        country.setEditable(true);

        return null;
    }

    public String saveAction() {

        return null;

    }

    public CountryEntity getCountryEntity() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
       if( requestParams.containsKey("Id")){
           String id = (String) requestParams.get("Id");
           countryEntity = countryService.findCountryId(new Long(id));
       }
        else if (requestParams.containsKey("countryId")){
            String id = (String) requestParams.get("countryId");
            countryEntity = countryService.findCountryId(new Long(id));
        }
      else {
            countryEntity = new CountryEntity();
        }
        return countryEntity;
    }

    public CountryEntity getEntity() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        if( requestParams.containsKey("Id")){
            String id = (String) requestParams.get("Id");
            countryEntity = countryService.findCountryId(new Long(id));
        }
        else if (requestParams.containsKey("countryId")){
            String id = (String) requestParams.get("countryId");
            countryEntity = countryService.findCountryId(new Long(id));
        }
        else {
            countryEntity = new CountryEntity();
        }
        return countryEntity;
    }


    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }
}
