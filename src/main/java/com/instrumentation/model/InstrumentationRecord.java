package com.instrumentation.model;

import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class InstrumentationRecord {
	private HttpMethod method;
	private String url;
	private String sessionId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private long responseSize;
	private long processingTime;
	private String instrumentationId;




	public InstrumentationRecord(HttpMethod method, String url, String sessionId) {
		this.method = method;
		this.url = url;
		this.sessionId = sessionId;

	}


	public void start() {

		startTime = LocalDateTime.now();
		instrumentationId = UUID.randomUUID().toString();
	}




	public void stop() {
		endTime = LocalDateTime.now();
		processingTime = Duration.between(startTime, endTime).toMillis();
	}

	public long getProcessingTime() {
		return processingTime;
	}

	public long getResponseSize() {
		return responseSize;
	}

	public void setResponseSize(long responseSize) {
		this.responseSize = responseSize;
	}


	public String getInstrumentationId() {
		return instrumentationId;
	}

	public void setInstrumentationId(String instrumentationId) {
		this.instrumentationId = instrumentationId;
	}
}
