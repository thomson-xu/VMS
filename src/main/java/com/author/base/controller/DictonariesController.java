package com.author.base.controller;

/**
 * Created by Administrator on 2016/8/8.
 */

import com.author.base.common.web.DictonariesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping({"/dict"})
public class DictonariesController {
    public DictonariesController() {
    }

    @ResponseBody
    @RequestMapping({"/getbyname/{name}"})
    public List getByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) throws Exception {
        List list = DictonariesUtils.getByName(name);
        
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getbyid/{id}"})
    public List getById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        List list = DictonariesUtils.getById(id);
       
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getspbyid/{id}"})
    public List getSpecifiedById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        List list = DictonariesUtils.getSpecifiedById(id);
        
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getspbyname/{name}"})
    public List getSpecifiedByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) throws Exception {
        List list = DictonariesUtils.getSpecifiedByName(name);
       
        return list;
    }

    @ResponseBody
    @RequestMapping({"/getsp"})
    public List getSpecified(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List msg = null;
        String value = request.getParameter("id");
        if(value != null && !"".equals(value)) {
            msg = this.getSpecifiedById(request, response, value);
        } else {
            value = request.getParameter("name");
            msg = this.getSpecifiedByName(request, response, value);
        }

        return msg;
    }

    @ResponseBody
    @RequestMapping({"/getall"})
    public List getAll() throws Exception {
        List all = DictonariesUtils.getAll();

        return all;
    }

    @ResponseBody
    @RequestMapping({"/refresh"})
    public List refresh() throws Exception {
        DictonariesUtils.refresh();

        return list;
    }
}
