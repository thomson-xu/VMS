package com.tms.base;

/**
 * Created by Administrator on 2016/8/8.
 */

import com.tms.base.common.config.ReadConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WebApplicationInitServlet extends HttpServlet {
    private static final Log logger = LogFactory.getLog(WebApplicationInitServlet.class);
    private static final long serialVersionUID = 1L;

    public WebApplicationInitServlet() {
    }

    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        ReadConfig reader = new ReadConfig();

        try {
            SystemConfig e = (SystemConfig)reader.configToBean(SystemConfig.class);
            application.setAttribute("SystemConfigLoad", "success");
            application.setAttribute("sysConfig", e);
            logger.info("initialization web application infomation completed!");
        } catch (Exception var4) {
            var4.printStackTrace();
            application.setAttribute("SystemConfigLoad", "failed");
        }

        super.init();
    }
}