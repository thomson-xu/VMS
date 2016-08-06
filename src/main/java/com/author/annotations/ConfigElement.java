package com.author.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.author.common.config.DefaultFormatter;
import com.author.common.config.DefaultReader;
import com.author.common.config.Formatter;
import com.author.common.config.Reader;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigElement {
	
	public String tag() default ""; 
	
	public String type() default "String";
	
	public String property() default "";
	
	public Class<? extends Reader> reader() default DefaultReader.class;
	
	public Class<? extends Formatter> fomatter() default DefaultFormatter.class;

}
