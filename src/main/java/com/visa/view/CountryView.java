package com.visa.view;

import com.visa.service.CountryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Test-Lab on 2016/6/29.
 */
@Service
@Scope("session")
public class CountryView {

    @Resource
    private CountryService countryService;

    private String countryName;
    private String nationalFlag;
    private int interContinental;

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }


}
