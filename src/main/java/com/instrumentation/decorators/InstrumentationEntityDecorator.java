package com.instrumentation.decorators;

import com.instrumentation.model.InstrumentationRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InstrumentationEntityDecorator {
	void onPreHandle(HttpServletRequest httpServletRequest, Object handler, InstrumentationRecord instrumentationRecord);
	void onAfterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler,
						   Exception ex, InstrumentationRecord instrumentationRecord);
}
