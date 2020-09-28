package com.instrumentation.filter;


import com.instrumentation.engine.InstrumentationEngine;
import com.instrumentation.model.InstrumentationRecord;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.instrumentation.engine.InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME;


public class InstrumentationFilter implements Filter {

    private final InstrumentationEngine instrumentationEngine;

    public InstrumentationFilter(InstrumentationEngine instrumentationEngine) {
        this.instrumentationEngine = instrumentationEngine;

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        InstrumentationRecord instrumentationRecord = instrumentationEngine.buildInstrumentation().
                method(HttpMethod.valueOf(httpRequest.getMethod())).
                sessionId(httpRequest.getSession().getId()).
                url(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length())).
                build();

        instrumentationRecord.start();

        servletRequest.setAttribute(INSTR_RECORD_ATTR_NAME, instrumentationRecord);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
