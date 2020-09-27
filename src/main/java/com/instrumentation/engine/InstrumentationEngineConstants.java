package com.instrumentation.engine;


import com.instrumentation.interceptor.InstrumentationInterceptor;

public class InstrumentationEngineConstants {
	public static final String INSTR_RECORD_ATTR_NAME = InstrumentationInterceptor.class.getName() + ".instrumentationRecord";
	public static final String RESPONSE_ID_HEADER_NAME = "Response ID";
}
