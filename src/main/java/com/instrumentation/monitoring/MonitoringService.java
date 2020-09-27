package com.instrumentation.monitoring;

import java.util.Set;

public interface MonitoringService {
	Set<Metric> getMetrics();
}
