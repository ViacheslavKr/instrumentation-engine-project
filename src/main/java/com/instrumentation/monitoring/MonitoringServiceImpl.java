package com.instrumentation.monitoring;


import com.instrumentation.engine.InstrumentationEngine;
import com.instrumentation.model.InstrumentationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.instrumentation.monitoring.MetricType.*;


@Service
public class MonitoringServiceImpl implements MonitoringService, MetricsFunctions {

	private InstrumentationEngine instrumentationEngine;

	@Autowired
	public MonitoringServiceImpl(InstrumentationEngine instrumentationEngine) {
		this.instrumentationEngine = instrumentationEngine;
	}

	@Override
	public Set<Metric> getMetrics() {
		Set<Metric> metrics = new HashSet<>();
		Collection<InstrumentationRecord> data = instrumentationEngine.getInstrumentationData();

		metrics.add(new Metric(MIN_REQUEST_PROCESSING_TIME.getLabel(), minResponseTime(data).toString()));
		metrics.add(new Metric(MAX_REQUEST_PROCESSING_TIME.getLabel(), maxResponseTime(data).toString()));
		metrics.add(new Metric(AVERAGE_REQUEST_PROCESSING_TIME.getLabel(), averageResponseTime(data).toString()));
		metrics.add(new Metric(MIN_RESPONSE_SIZE.getLabel(), minResponseSize(data).toString()));
		metrics.add(new Metric(MAX_RESPONSE_SIZE.getLabel(), maxResponseSize(data).toString()));
		metrics.add(new Metric(AVERAGE_RESPONSE_SIZE.getLabel(), averageResponseSize(data).toString()));


		return metrics;
	}
}
