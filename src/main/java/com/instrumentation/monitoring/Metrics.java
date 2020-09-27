package com.instrumentation.monitoring;

public class Metrics {

	private Long minRequestTime;
	private Long maxRequestTime;
	private Long averageRequestTime;

	private Long minResponseSize;
	private Long maxResponseSize;
	private Long averageResponseSize;


	public Long getMinRequestTime() {
		return minRequestTime;
	}

	public void setMinRequestTime(Long minRequestTime) {
		this.minRequestTime = minRequestTime;
	}

	public Long getMaxRequestTime() {
		return maxRequestTime;
	}

	public void setMaxRequestTime(Long maxRequestTime) {
		this.maxRequestTime = maxRequestTime;
	}

	public Long getAverageRequestTime() {
		return averageRequestTime;
	}

	public void setAverageRequestTime(Long averageRequestTime) {
		this.averageRequestTime = averageRequestTime;
	}

	public Long getMinResponseSize() {
		return minResponseSize;
	}

	public void setMinResponseSize(Long minResponseSize) {
		this.minResponseSize = minResponseSize;
	}

	public Long getMaxResponseSize() {
		return maxResponseSize;
	}

	public void setMaxResponseSize(Long maxResponseSize) {
		this.maxResponseSize = maxResponseSize;
	}

	public Long getAverageResponseSize() {
		return averageResponseSize;
	}

	public void setAverageResponseSize(Long averageResponseSize) {
		this.averageResponseSize = averageResponseSize;
	}
}
