package com.author.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.author.annotations.ConfigElement;
import org.dom4j.Element;

public interface Reader {
    Object doRead(ConfigElement var1, Element var2) throws Exception;
}
