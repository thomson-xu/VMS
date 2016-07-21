package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.entity.ConsulateEntity;
import com.visa.entity.CountryEntity;
import com.visa.service.ConsulateService;
import com.visa.service.CountryService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Test-Lab on 2016/6/29.
 */
@Named
@Scope("request")
public class Consulate {
    @Resource
    private ConsulateService consulateService;
    @Resource
    private CountryService countryService;

    private String result;
    private static CountryEntity countryEntity;
    private ConsulateEntity consulateEntity;
    private UISelectOne selectone;
    public Consulate(){
        consulateEntity = new ConsulateEntity();

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


    public void addConsulate(ActionEvent evt) {
        consulateEntity.setId(consulateService.getKeyValue());
        //countryEntity.setName(getCountryName());
        consulateEntity.setCountryEntity(countryEntity);
        //consulateEntity.setInterContinental(getInterContinental());
        try {

            consulateService.addCountry(consulateEntity);

            result = "Create country successfully";
        } catch (Exception e) {
            e.printStackTrace();
            result = "Create country unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DataModel getListConsulate() {
        return new ListDataModel(consulateService.findAllConsulate());
    }



    public void delCountry(){
        consulateService.deleteConsulateById(consulateEntity.getId());
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
       /* CountryEntity entity= consulateService.findCountryId(country.getId());
        entity.setName(country.getCountryName());
        entity.setNationalFlag(country.getNationalFlag());*/
        //countryEntity.setInterContinental(getInterContinental());
        try {
            consulateService.updateCountry(consulateEntity);

            result = "Create country successfully";
        } catch (Exception e) {
            e.printStackTrace();
            result = "Create country unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    private int selectIndex = 1;

    public void selectChange(ValueChangeEvent event) {
        selectIndex = Integer.parseInt((String) selectone.getValue());
    }

    boolean editable;

    public boolean isEditable() {
        return editable;
    }
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String editAction(CountryEntity countryEntity) {
        Consulate country= new Consulate();
        country.setEditable(true);

        return null;
    }

    public String saveAction() {

        //get all existing value but set "editable" to false
    /*    for (Country country : listCountry){
            country.setEditable(false);
        }*/

        //return to current page
        return null;

    }

    public ConsulateEntity getConsulateEntity() {
        return consulateEntity;
    }

    public ConsulateEntity getEntity(){

        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        if( requestParams.containsKey("Id")){
            String id = (String) requestParams.get("Id");
            consulateEntity = consulateService.findConsulateId(new Long(id));
        }
        else if (requestParams.containsKey("countryId")){

            String countryid = (String) requestParams.get("countryId");
            countryEntity=countryService.findCountryId(Long.valueOf(countryid));
            consulateEntity.setCountryEntity(countryEntity);
        }
        else {
            consulateEntity = new ConsulateEntity();
        }
        return consulateEntity;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setConsulateEntity(ConsulateEntity consulateEntity) {
        this.consulateEntity = consulateEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }


}
