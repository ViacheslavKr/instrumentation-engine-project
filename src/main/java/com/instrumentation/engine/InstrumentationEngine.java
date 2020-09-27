package com.instrumentation.engine;


import com.instrumentation.model.InstrumentationRecord;

import java.util.Collection;

public interface InstrumentationEngine {
	void logInstrumentationE(InstrumentationRecord instrumentationRecord);
	InstrumentationEngineImpl.InstrumentationRecordBuilder buildInstrumentation();
	Collection<InstrumentationRecord> getInstrumentationData();

}
