package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.entity.*;
import com.visa.service.*;
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
import java.util.HashMap;
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

    @Resource
    private MaterialService materialService;

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
    private static List<SelectItem>  selectContinentList = new ArrayList<SelectItem>();
    private static List<SelectItem> selectConsulateList;
    private static List<SelectItem> selectCountryList ;
    private static Map<Long, List<SelectItem>> selectConsulateMap= new HashMap<Long, List<SelectItem>>();
    private static Map<Integer, List<SelectItem>> selectCountryMap= new HashMap<Integer, List<SelectItem>>();

    public Material() {
        materialEntity = new MaterialEntity();
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public void addMaterial(ActionEvent evt) {
        materialEntity.setId(materialService.getKeyValue());
        materialEntity.setConsulateId(selectConsulateIndex);
        materialEntity.setVisatypeId(selectVisatypeIndex);
        materialEntity.setProfessionId(selectProfessionIndex);
        try {

            materialService.addMaterial(materialEntity);

            result = "Create successfully";
        } catch (Exception e) {
            e.printStackTrace();
            result = "Create unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DataModel getListMaterial() {
        return new ListDataModel(materialService.findAllMaterial());
    }


    public void delCountry() {
        materialService.deleteMaterialById(consulateEntity.getId());
    }

    public void updateCountry() {
        try {

            materialService.updateMaterial(materialEntity);

            result = "Update successfully";
        } catch (Exception e) {
            e.printStackTrace();
            result = "Update unsuccessfully";
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, null, result);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    private int selectContinentIndex = 1;

    public void selectContinentChange(ValueChangeEvent event) {
        System.out.print("====================change continent=================");
        selectContinentIndex = Integer.parseInt((String) selectContinentone.getValue());
        this.setSelectCountryList(getSelectCountryMap().get(Integer.parseInt((String) selectContinentone.getValue())));
        this.setSelectConsulateList(null);
    }

    private int selectCountryIndex = 0;

    public void selectCountryChange(ValueChangeEvent event) {
        selectCountryIndex = Integer.parseInt((String) selectCountryone.getValue());
        this.setSelectConsulateList(getSelectConsulateMap().get(Long.valueOf(selectCountryIndex)));
    }

    private int selectConsulateIndex = 0;

    public void selectConsulateChange(ValueChangeEvent event) {
        if(event.getNewValue()!=null){
            selectConsulateIndex = Integer.parseInt((String) selectConsulateone.getValue());
        }
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


    public List<SelectItem> getContinentList() {
        selectContinentList.clear();
        for (int index = 1; index < 8; index++) {
            selectContinentList.add(new SelectItem(index, ContinentsEnum.getName(index)));
        }

        List<SelectItem>  allCountryList= new ArrayList<SelectItem>();
        for(int i=0; i<selectContinentList.size(); i++){
            List<CountryEntity> countryList = countryService.findCountryByContinent(
                    new Integer(selectContinentList.get(i).getValue().toString() ).intValue());
            List<SelectItem>  selectCountryList= new ArrayList<SelectItem>();
            if (null != countryList && countryList.size() > 0) {
                for (CountryEntity contry : countryList) {
                    SelectItem item =new SelectItem(contry.getId(),contry.getName());
                    selectCountryList.add(item);
                    allCountryList.add(item);
                }
            }
            selectCountryMap.put((Integer) selectContinentList.get(i).getValue(),selectCountryList);
        }

        for(int j=0; j<allCountryList.size(); j ++){

            List<ConsulateEntity> consulateList = consulateService.findConsulateByCountry(
                    Long.valueOf(allCountryList.get(j).getValue().toString()));
            List<SelectItem> selectConsulateList= new ArrayList<SelectItem>();
            for (ConsulateEntity consulate : consulateList) {
                selectConsulateList.add(new SelectItem(consulate.getId(), consulate.getConsulateName()));
            }
            selectConsulateMap.put((Long) allCountryList.get(j).getValue(),selectConsulateList);
        }
        return selectContinentList;
    }

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
        if(selectContinentList.size()==0){
            getContinentList();
        }
        return selectContinentList;
    }

    public void setSelectContinentList(List<SelectItem> selectContinentList) {
        this.selectContinentList = selectContinentList;
    }


    public Map<Long, List<SelectItem>> getSelectConsulateMap() {
        return selectConsulateMap;
    }

    public void setSelectConsulateMap(Map<Long, List<SelectItem>> selectConsulateMap) {
        this.selectConsulateMap = selectConsulateMap;
    }

    public Map<Integer, List<SelectItem>> getSelectCountryMap() {
        return selectCountryMap;
    }

    public void setSelectCountryMap(Map<Integer, List<SelectItem>> selectCountryMap) {
        this.selectCountryMap = selectCountryMap;
    }

    public List<SelectItem> getSelectCountryList() {
        return selectCountryList;
    }

    public void setSelectCountryList(List<SelectItem> selectCountryList) {
        this.selectCountryList = selectCountryList;
    }

    public List<SelectItem> getSelectConsulateList() {
        return  selectConsulateList;
    }

    public void setSelectConsulateList(List<SelectItem> selectConsulateList) {
        this.selectConsulateList = selectConsulateList;
    }
}
