package com.ssn.fedral.gov.property;

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
public class SsnProperty {
	private Map<String, String> ssaprops = new HashMap<String, String>();

}
