package com.kyo.basic.base.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.Map;

public class CustomLogLayout extends JsonLayout {
	@Override
	protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
		map.put("plex_type", "APPLICATION");
		super.addCustomDataToJsonMap(map, event);
	}
}
