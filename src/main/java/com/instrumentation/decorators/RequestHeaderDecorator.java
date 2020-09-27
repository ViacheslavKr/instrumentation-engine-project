package com.instrumentation.decorators;

import com.instrumentation.engine.InstrumentationEngineConstants;
import com.instrumentation.model.InstrumentationRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHeaderDecorator implements InstrumentationEntityDecorator {
	@Override
	public void onPreHandle(HttpServletRequest httpServletRequest, Object handler, InstrumentationRecord instrumentationRecord) {

	}

	@Override
	public void onAfterCompletion(HttpServletRequest request, HttpServletResponse response,
								  Object handler, Exception ex, InstrumentationRecord instrumentationRecord) {
		response.setHeader(InstrumentationEngineConstants.RESPONSE_ID_HEADER_NAME, instrumentationRecord.getInstrumentationId());
	}




}
