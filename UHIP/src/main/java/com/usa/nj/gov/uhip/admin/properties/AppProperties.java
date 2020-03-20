package com.usa.nj.gov.uhip.admin.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
/**
 * this class use to read yml file.(prefix will be uhip which is place above uhipprops of yml). 
 * to read yml file we want to require one properties file.
 * @author Dell User
 *
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="uhip")
@Data
public class AppProperties {
	//uhipprops name must be same as uhipprops which is contains yml file 
	private Map<String ,String> uhipprops=new HashMap<>();

}
