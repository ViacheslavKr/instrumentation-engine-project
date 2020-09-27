package com.instrumentation.engine;

import com.instrumentation.model.InstrumentationRecord;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class InstrumentationEngineImpl implements InstrumentationEngine {

	private Map<String, InstrumentationRecord> instrumentationData = new HashMap<>();

	@Override
	public void logInstrumentationE(InstrumentationRecord instrumentationRecord) {
		Objects.requireNonNull(instrumentationRecord, "instrumentationRecord must be not null");
		Objects.requireNonNull(instrumentationRecord.getInstrumentationId(), "instrumentationId must be not null");

		instrumentationData.put(instrumentationRecord.getInstrumentationId(), instrumentationRecord);
	}

	@Override
	public InstrumentationRecordBuilder buildInstrumentation() {
		return new InstrumentationRecordBuilder();
	}

	@Override
	public Collection<InstrumentationRecord> getInstrumentationData() {
		return instrumentationData.values();
	}

	public static class InstrumentationRecordBuilder {
		private HttpMethod method;
		private String url;
		private String sessionId;

		public InstrumentationRecordBuilder method(HttpMethod method) {
			this.method = method;
			return this;
		}

		public InstrumentationRecordBuilder url(String url) {
			this.url = url;
			return this;
		}

		public InstrumentationRecordBuilder sessionId(String sessionId) {
			this.sessionId = sessionId;
			return this;
		}

		public InstrumentationRecord build() {
			return new InstrumentationRecord(method, url, sessionId);
		}

	}


}
