package com.ssn.fedral.gov.SSAAppproperties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ssa")
@Data
public class SAAppproperties {
	public Map<String, String> ssaProperty = new HashMap<String, String>();

}
