package com.instrumentation.monitoring;

public enum MetricType {

	MAX_REQUEST_PROCESSING_TIME ("Max request processing time"),
	MIN_REQUEST_PROCESSING_TIME ("Min request processing time"),
	AVERAGE_REQUEST_PROCESSING_TIME ("Average request processing time"),
	MAX_RESPONSE_SIZE ("Max response size"),
	MIN_RESPONSE_SIZE ("Min response size"),
	AVERAGE_RESPONSE_SIZE ("Average response size");



	private String label;

	MetricType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
