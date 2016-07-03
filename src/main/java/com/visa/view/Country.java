package com.visa.view;

import com.visa.bean.ContinentsEnum;
import com.visa.bean.UpLoadBean;
import com.visa.entity.CountryEntity;
import com.visa.service.CountryService;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
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
    private String interContinental;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getInterContinental() {
        return interContinental;
    }

    public void setInterContinental(String interContinental) {
        this.interContinental = interContinental;
    }

    public String getNationalFlag() {
        return nationalFlag;
    }

    public void setNationalFlag(){
        this.nationalFlag= getFileName();
    }

    public void addCountry(ActionEvent evt) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(getCountryName());
        countryEntity.setNationalFlag(getNationalFlag());
        countryEntity.setInterContinental(getContinents(getInterContinental()));
        countryService.addCountry(countryEntity);
    }

    private int getContinents(String continentName) {
        return ContinentsEnum.getValue(continentName);

    }

    public List<SelectItem> getSelectItemList() {
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        for (int index=1; index<8; index++){
            ContinentsEnum.getName(index);
            selectItemList.add(new SelectItem(index, ContinentsEnum.getName(index)));
        }
        return selectItemList;
    }

    private String getFileName(){
        UpLoadBean upLoad= new UpLoadBean();
       return upLoad.getFile().getFilename().toString();
    }
}
