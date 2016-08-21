package com.author.base.controller;

/**
 * Created by Administrator on 2016/8/8.
 */

import com.author.base.common.debug.DebugUtil;
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
    public Message getByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) throws Exception {
        List list = DictonariesUtils.getByName(name);
        Message msg = new Message(list);
        return msg;
    }

    @ResponseBody
    @RequestMapping({"/getbyid/{id}"})
    public Message getById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        List list = DictonariesUtils.getById(id);
        Message msg = new Message(list);
        return msg;
    }

    @ResponseBody
    @RequestMapping({"/getspbyid/{id}"})
    public Message getSpecifiedById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        List list = DictonariesUtils.getSpecifiedById(id);
        DebugUtil.println(id);
        Message msg = new Message(list);
        return msg;
    }

    @ResponseBody
    @RequestMapping({"/getspbyname/{name}"})
    public Message getSpecifiedByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("name") String name) throws Exception {
        List list = DictonariesUtils.getSpecifiedByName(name);
        Message msg = new Message(list);
        return msg;
    }

    @ResponseBody
    @RequestMapping({"/getsp"})
    public Message getSpecified(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Message msg = null;
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
    public Message getAll() throws Exception {
        List all = DictonariesUtils.getAll();
        Message msg = new Message(all);
        return msg;
    }

    @ResponseBody
    @RequestMapping({"/refresh"})
    public Message refresh() throws Exception {
        DictonariesUtils.refresh();
        Message msg = new Message();
        msg.setSuccess(Boolean.valueOf(true));
        msg.setMessage("字典数据已刷新！");
        return msg;
    }
}
