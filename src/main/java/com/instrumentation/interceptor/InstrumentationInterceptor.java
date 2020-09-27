package com.instrumentation.interceptor;



import com.instrumentation.decorators.InstrumentationEntityDecorator;
import com.instrumentation.decorators.RequestHeaderDecorator;
import com.instrumentation.engine.InstrumentationEngine;
import com.instrumentation.engine.InstrumentationEngineConstants;
import com.instrumentation.model.InstrumentationRecord;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class InstrumentationInterceptor extends HandlerInterceptorAdapter {

	private final InstrumentationEngine instrumentationEngine;
	private final List<InstrumentationEntityDecorator> instrumentationDecorators;


	public InstrumentationInterceptor(InstrumentationEngine instrumentationEngine, List<InstrumentationEntityDecorator> instrumentationDecorators) {
		this.instrumentationEngine = instrumentationEngine;
		this.instrumentationDecorators = instrumentationDecorators;
	}

	public InstrumentationInterceptor(InstrumentationEngine instrumentationEngine) {
		this(instrumentationEngine, Collections.singletonList(new RequestHeaderDecorator()));

	}


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


		if (isInstrumentable(request)) {
			InstrumentationRecord instrumentationRecord = (InstrumentationRecord) request.getAttribute(InstrumentationEngineConstants.
				INSTR_RECORD_ATTR_NAME);
			for (InstrumentationEntityDecorator decorator : instrumentationDecorators) {
				decorator.onPreHandle(request, handler, instrumentationRecord);
			}
		}
		return true;
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
								Object handler, Exception ex) {
		if (isInstrumentable(request)) {
			InstrumentationRecord instrumentationRecord = (InstrumentationRecord) request.getAttribute(InstrumentationEngineConstants.
				INSTR_RECORD_ATTR_NAME);

			for (InstrumentationEntityDecorator decorator : instrumentationDecorators) {
				decorator.onAfterCompletion(request, response, handler, ex, instrumentationRecord);
			}


			instrumentationRecord.setResponseSize(((ContentCachingResponseWrapper) response).getContentSize());
			instrumentationRecord.stop();
			instrumentationEngine.logInstrumentationE(instrumentationRecord);

		}

	}

	private static boolean isInstrumentable(HttpServletRequest request) {
		return request.getAttribute(InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME) instanceof InstrumentationRecord;
	}



}
