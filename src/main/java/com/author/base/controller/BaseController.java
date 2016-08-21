package com.author.base.controller;

/**
 * Created by Administrator on 2016/8/8.
 */

import com.author.base.common.util.MessageManager;
import com.author.base.common.web.controller.ControllerTools;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
public class BaseController {
    protected Log logger = LogFactory.getLog(this.getClass());

    public BaseController() {
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn("异常信息", ex);
        }

        boolean isAjaxRequest = this.isAjaxRequest(request);
        Message msg = MessageManager.exception(ex);
        if (isAjaxRequest) {
            PrintWriter map1 = ControllerTools.getWriter(response);
            JSONObject data = JSONFormat.toJson(msg, false);
            map1.print(data.toString());
            return null;
        } else {
            HashMap map = new HashMap();
            map.put("message", msg.getMessage());
            map.put("isError", Boolean.valueOf(true));
            map.put("exceptionName", msg.getExName());
            return new ModelAndView("error/exception", map);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return requestType != null;
    }
}