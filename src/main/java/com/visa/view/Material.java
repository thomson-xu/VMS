package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.entity.*;
import com.visa.service.ConsulateService;
import com.visa.service.CountryService;
import com.visa.service.ProfessionService;
import com.visa.service.VisaTypeService;
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
public class Material {
    @Resource
    private CountryService countryService;

    @Resource
    private ConsulateService consulateService;

    @Resource
    private VisaTypeService visaTypeService;

    @Resource
    private ProfessionService professionService;

    private String result;
    private static CountryEntity countryEntity;
    private ConsulateEntity consulateEntity;
    private VisatypeEntity visatypeEntity;
    private ProfessionEntity professionEntity;
    private MaterialEntity materialEntity;
    private UISelectOne selectContinentone;
    private UISelectOne selectCountryone;
    private UISelectOne selectConsulateone;
    private UISelectOne selectVisaTypeone;
    private UISelectOne selectProfessionone;
    private List<SelectItem>  selectContinentList = new ArrayList<SelectItem>();
    private List<SelectItem> selectConsulateList = new ArrayList<SelectItem>();
    private List<SelectItem> selectCountryList = new ArrayList<SelectItem>();

    public Material() {
        materialEntity = new MaterialEntity();

    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public void addConsulate(ActionEvent evt) {
        consulateEntity.setId(consulateService.getKeyValue());

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


    public void delCountry() {
        consulateService.deleteConsulateById(consulateEntity.getId());
    }

    public void updateCountry() {
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

    private int selectContinentIndex = 1;

    public void selectContinentChange(ValueChangeEvent event) {
        selectContinentIndex = Integer.parseInt((String) selectContinentone.getValue());
        List<CountryEntity> countryList = countryService.findCountryByContinent(selectContinentIndex);
        if (null != countryList && countryList.size() > 0) {
            for (CountryEntity contry : countryList) {
                selectCountryList.add(new SelectItem(contry.getId(), contry.getName()));
            }
        }
    }

    private int selectCountryIndex = 0;

    public void selectCountryChange(ValueChangeEvent event) {
        selectCountryIndex = Integer.parseInt((String) selectCountryone.getValue());
        List<ConsulateEntity> consulateList = consulateService.findConsulateByCountry(Long.valueOf(String.valueOf(selectCountryIndex)));
        for (ConsulateEntity consulate : consulateList) {
            selectConsulateList.add(new SelectItem(consulate.getId(), consulate.getConsulateName()));
        }
    }

    private int selectConsulateIndex = 0;

    public void selectConsulateChange(ValueChangeEvent event) {
        selectConsulateIndex = Integer.parseInt((String) selectConsulateone.getValue());
    }

    private int selectVisatypeIndex = 0;

    public void selectVisaTypeChange(ValueChangeEvent event) {
        selectVisatypeIndex = Integer.parseInt((String) selectVisaTypeone.getValue());
    }

    private int selectProfessionIndex = 0;

    public void selectProfessionChange(ValueChangeEvent event) {
        selectProfessionIndex = Integer.parseInt((String) selectProfessionone.getValue());
    }

    boolean editable;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String editAction(CountryEntity countryEntity) {
        Material country = new Material();
        country.setEditable(true);

        return null;
    }

    public String saveAction() {

        return null;

    }

    public ConsulateEntity getConsulateEntity() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        if (requestParams.containsKey("Id")) {
            String id = (String) requestParams.get("Id");
            consulateEntity = consulateService.findConsulateId(new Long(id));
        } else if (requestParams.containsKey("country")) {

            String param = (String) requestParams.get("country");
            if (param.contains("CountryEntity")) {
                String params[] = param.substring(20).split("\t");
                params[0].substring(4, params[0].length() - 1);
                countryEntity = new CountryEntity();
                countryEntity.setId(Long.valueOf(params[0].substring(4, params[0].length() - 1))); //setID
                countryEntity.setName(params[1].substring(6, params[1].length() - 1));    //setName
                countryEntity.setInterContinental(Integer.valueOf(params[2].substring(18, params[2].length() - 1)));//setIntercontinental
                countryEntity.setNationalFlag(params[3].substring(14, params[3].length() - 1)); //setNationalFlag
            }
            consulateEntity.setCountryEntity(countryEntity);
        } else {
            consulateEntity = new ConsulateEntity();
        }
        return consulateEntity;
    }

    public ConsulateEntity getEntity() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map requestParams = fc.getExternalContext().getRequestParameterMap();
        if (requestParams.containsKey("Id")) {
            String id = (String) requestParams.get("Id");
            consulateEntity = consulateService.findConsulateId(new Long(id));
        } else if (requestParams.containsKey("country")) {

            String param = (String) requestParams.get("country");
            if (param.contains("CountryEntity")) {
                String params[] = param.substring(20).split("\t");
                params[0].substring(4, params[0].length() - 1);
                countryEntity = new CountryEntity();
                countryEntity.setId(Long.valueOf(params[0].substring(4, params[0].length() - 1))); //setID
                countryEntity.setName(params[1].substring(6, params[1].length() - 1));    //setName
                countryEntity.setInterContinental(Integer.valueOf(params[2].substring(18, params[2].length() - 1)));//setIntercontinental
                countryEntity.setNationalFlag(params[3].substring(14, params[3].length() - 1)); //setNationalFlag
            }
            consulateEntity.setCountryEntity(countryEntity);
        } else {
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

    public UISelectOne getSelectContinentone() {
        return selectContinentone;
    }

    public void setSelectContinentone(UISelectOne selectContinentone) {
        this.selectContinentone = selectContinentone;
    }

    public UISelectOne getSelectCountryone() {
        return selectCountryone;
    }

    public void setSelectCountryone(UISelectOne selectCountryone) {
        this.selectCountryone = selectCountryone;
    }

    public UISelectOne getSelectConsulateone() {
        return selectConsulateone;
    }

    public void setSelectConsulateone(UISelectOne selectConsulateone) {
        this.selectConsulateone = selectConsulateone;
    }

    public UISelectOne getSelectVisaTypeone() {
        return selectVisaTypeone;
    }

    public void setSelectVisaTypeone(UISelectOne selectVisaTypeone) {
        this.selectVisaTypeone = selectVisaTypeone;
    }

    public UISelectOne getSelectProfessionone() {
        return selectProfessionone;
    }

    public void setSelectProfessionone(UISelectOne selectProfessionone) {
        this.selectProfessionone = selectProfessionone;
    }

    public int getSelectVisatypeIndex() {
        return selectVisatypeIndex;
    }

    public void setSelectVisatypeIndex(int selectVisatypeIndex) {
        this.selectVisatypeIndex = selectVisatypeIndex;
    }

    public int getSelectProfessionIndex() {
        return selectProfessionIndex;
    }

    public void setSelectProfessionIndex(int selectProfessionIndex) {
        this.selectProfessionIndex = selectProfessionIndex;
    }

    public ConsulateService getConsulateService() {
        return consulateService;
    }

    public void setConsulateService(ConsulateService consulateService) {
        this.consulateService = consulateService;
    }


    public List<SelectItem> getContinentList() throws Exception {

        for (int index = 1; index < 8; index++) {
            ContinentsEnum.getName(index);
            selectContinentList.add(new SelectItem(index, ContinentsEnum.getName(index)));
        }

        return selectContinentList;
    }
/*
    public List<SelectItem> getCountryList(int id) throws Exception {
        List<CountryEntity> countryList = countryService.findCountryByContinent(id);
        if (null != countryList && countryList.size() > 0) {
            for (CountryEntity contry : countryList) {
                selectCountryList.add(new SelectItem(contry.getId(), contry.getName()));
            }
        }else{
            throw new Exception("can not find any  visa type");
        }

        return selectCountryList;
    }*/

/*    public List<SelectItem> getConsulateList(Long id) throws Exception {
        List<SelectItem> selectConsulateList = new ArrayList<SelectItem>();
        List<ConsulateEntity> consulateList = consulateService.findConsulateByCountry(id);
         for (ConsulateEntity consulate : consulateList) {
             selectConsulateList.add(new SelectItem(consulate.getId(), consulate.getConsulateName()));
          }


        return selectConsulateList;
    }*/

    public List<SelectItem> getVisaTypeList() throws Exception {
        List<SelectItem> selectVisatypeList = new ArrayList<SelectItem>();
        List<VisatypeEntity> visatypeEntityList = visaTypeService.findAllVisatype();
        if (null != visatypeEntityList && visatypeEntityList.size() > 0) {
            for (VisatypeEntity type : visatypeEntityList) {
                selectVisatypeList.add(new SelectItem(type.getId(), type.getType()));
            }
        }else{
            throw new Exception("can not find any  visa type");
        }

        return selectVisatypeList;
    }
    public List<SelectItem> getProfessionList() throws Exception {
        List<SelectItem> selectProfessionList = new ArrayList<SelectItem>();
        List<ProfessionEntity> professionEntityList = professionService.findAllProfession();
        if (null != professionEntityList && professionEntityList.size() > 0) {
            for (ProfessionEntity type : professionEntityList) {
                selectProfessionList.add(new SelectItem(type.getId(), type.getName()));
            }
        }else{
            throw new Exception("can not find any  visa type");
        }

        return selectProfessionList;
    }

    public VisatypeEntity getVisatypeEntity() {
        return visatypeEntity;
    }

    public void setVisatypeEntity(VisatypeEntity visatypeEntity) {
        this.visatypeEntity = visatypeEntity;
    }

    public ProfessionEntity getProfessionEntity() {
        return professionEntity;
    }

    public void setProfessionEntity(ProfessionEntity professionEntity) {
        this.professionEntity = professionEntity;
    }

    public MaterialEntity getMaterialEntity() {
        return materialEntity;
    }

    public void setMaterialEntity(MaterialEntity materialEntity) {
        this.materialEntity = materialEntity;
    }

    public VisaTypeService getVisaTypeService() {
        return visaTypeService;
    }

    public void setVisaTypeService(VisaTypeService visaTypeService) {
        this.visaTypeService = visaTypeService;
    }

    public ProfessionService getProfessionService() {
        return professionService;
    }

    public void setProfessionService(ProfessionService professionService) {
        this.professionService = professionService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public int getSelectContinentIndex() {
        return selectContinentIndex;
    }

    public void setSelectContinentIndex(int selectContinentIndex) {
        this.selectContinentIndex = selectContinentIndex;
    }

    public int getSelectCountryIndex() {
        return selectCountryIndex;
    }

    public void setSelectCountryIndex(int selectCountryIndex) {
        this.selectCountryIndex = selectCountryIndex;
    }

    public int getSelectConsulateIndex() {
        return selectConsulateIndex;
    }

    public void setSelectConsulateIndex(int selectConsulateIndex) {
        this.selectConsulateIndex = selectConsulateIndex;
    }

    public List<SelectItem> getSelectContinentList() {
        return selectContinentList;
    }

    public void setSelectContinentList(List<SelectItem> selectContinentList) {
        this.selectContinentList = selectContinentList;
    }

    public List<SelectItem> getSelectCountryList() {
        return selectCountryList;
    }

    public void setSelectCountryList(List<SelectItem> selectCountryList) {
        this.selectCountryList = selectCountryList;
    }

    public List<SelectItem> getSelectConsulateList() {
        return selectConsulateList;
    }

    public void setSelectConsulateList(List<SelectItem> selectConsulateList) {
        this.selectConsulateList = selectConsulateList;
    }
}
