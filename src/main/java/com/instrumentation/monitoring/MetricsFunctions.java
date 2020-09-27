package com.instrumentation.monitoring;


import com.instrumentation.model.InstrumentationRecord;

import java.util.Collection;

public interface MetricsFunctions {
	 default Long maxResponseTime(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0L;
		}
		return data.stream().mapToLong(InstrumentationRecord::getProcessingTime).max().orElse(0);
	}

	default Long minResponseTime(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0L;
		}
		return data.stream().mapToLong(InstrumentationRecord::getProcessingTime).min().orElse(0);
	}

	default Double averageResponseTime(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0.;
		}
		return data.stream().mapToLong(InstrumentationRecord::getProcessingTime).average().orElse(0);
	}


	default Long maxResponseSize(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0L;
		}
		return data.stream().mapToLong(InstrumentationRecord::getResponseSize).max().orElse(0);
	}

	default Long minResponseSize(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0L;
		}
		return data.stream().mapToLong(InstrumentationRecord::getResponseSize).min().orElse(0);
	}

	default Double averageResponseSize(Collection<InstrumentationRecord> data) {
		if (data == null) {
			return 0.;
		}
		return data.stream().mapToLong(InstrumentationRecord::getResponseSize).average().orElse(0);
	}
}
