package com.wu.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="wu")
public class ConfigBeans {

	private String token;
	private String roboturl;
	private String robotkey;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRoboturl() {
		return roboturl;
	}

	public void setRoboturl(String roboturl) {
		this.roboturl = roboturl;
	}

	public String getRobotkey() {
		return robotkey;
	}

	public void setRobotkey(String robotkey) {
		this.robotkey = robotkey;
	}
	
	
}
