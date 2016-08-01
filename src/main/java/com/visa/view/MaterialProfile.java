package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.entity.ConsulateEntity;
import com.visa.entity.CountryEntity;
import com.visa.service.ConsulateService;
import com.visa.service.CountryService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.event.ValueChangeEvent;
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
public class MaterialProfile {
    @Resource
    private CountryService countryService;

    @Resource
    private ConsulateService consulateService;

    private  static List<SelectItem>  selectContinentList = new ArrayList<SelectItem>();

    private static Map<Long, List<SelectItem>> selectConsulateMap= new HashMap<Long, List<SelectItem>>();
    private static Map<Integer, List<SelectItem>> selectCountryMap= new HashMap<Integer, List<SelectItem>>();
    private String continentName;
    private String countryName;
    private String consulateName;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getConsulateName() {
        return consulateName;
    }

    public void setConsulateName(String consulateName) {
        this.consulateName = consulateName;
    }

    public MaterialProfile() {

    }
    private static MaterialProfile instance;
    public  static synchronized MaterialProfile getInstance() {
        if(instance==null){
            instance = new MaterialProfile();
        }
        return instance;
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

    public static void setInstance(MaterialProfile instance) {
        MaterialProfile.instance = instance;
    }

    public ConsulateService getConsulateService() {
        return consulateService;
    }

    public void setConsulateService(ConsulateService consulateService) {
        this.consulateService = consulateService;
    }


    public List<SelectItem> getContinentList() throws Exception {
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

    public List<SelectItem> getCountryList(String continentName){
        if(continentName!=""){
            return getSelectCountryMap().get(Integer.valueOf(continentName));
        }
        return getSelectCountryMap().get(continentName);
    }

    public void changeCountry(ValueChangeEvent env){
       this.setCountryName(env.getNewValue().toString());
        env.getComponent().getAttributes().get("selCountry").toString();
    }

    public List<SelectItem> getConsulateList(String countryName){
        if(countryName!="") {
            return getSelectConsulateMap().get(Long.valueOf(countryName));
        }
        return getSelectConsulateMap().get(countryName);
    }
    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<SelectItem> getSelectContinentList() throws Exception {
        if(selectContinentList.size()==0){
            getContinentList();
        }
        return selectContinentList;
    }

    public void setSelectContinentList(List<SelectItem> selectContinentList) {

        this.selectContinentList = selectContinentList;
    }

}
