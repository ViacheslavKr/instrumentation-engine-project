package com.instrumentation.interceptor;

import com.instrumentation.decorators.InstrumentationEntityDecorator;
import com.instrumentation.engine.InstrumentationEngine;
import com.instrumentation.engine.InstrumentationEngineConstants;
import com.instrumentation.model.InstrumentationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstrumentationInterceptorTest {

    @Mock
    private InstrumentationEngine instrumentationEngine;

    @Mock
    private InstrumentationEntityDecorator decorator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ContentCachingResponseWrapper response;


    private InstrumentationInterceptor interceptor;


    @BeforeEach
    public void setUp() {
        interceptor = new InstrumentationInterceptor(instrumentationEngine, Collections.singletonList(decorator));
    }

    @Test
    void preHandleDoInstrumentation() {
        InstrumentationRecord record = new InstrumentationRecord(HttpMethod.GET, "url", "session id");

        when(request.getAttribute(InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME)).
                thenReturn(record);

        interceptor.preHandle(request, response, null);
        verify(decorator, times(1)).onPreHandle(request, null, record);
    }

    @Test
    void preHandleSkipInstrumentation() {

        when(request.getAttribute(InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME)).
                thenReturn(null);

        interceptor.preHandle(request, response, null);
        verify(decorator, times(0)).onPreHandle(any(), any(), any());
    }

    @Test
    void afterCompletionSkip() {


        when(request.getAttribute(InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME)).
                thenReturn(null);

        interceptor.afterCompletion(request, response, null, null);
        verify(decorator, times(0)).onAfterCompletion(any(), any(), any(), any(), any());
    }


    @Test
    void performAfterCompletion() {
        InstrumentationRecord record = new InstrumentationRecord(HttpMethod.GET, "url", "session id");
        record.start();

        when(request.getAttribute(InstrumentationEngineConstants.INSTR_RECORD_ATTR_NAME)).thenReturn(record);
        when(response.getContentSize()).thenReturn(100);

        interceptor.afterCompletion(request, response, null, null);
        verify(decorator, times(1)).onAfterCompletion(eq(request), eq(response), any(), any(), any());
        verify(instrumentationEngine, times(1)).logInstrumentationE(eq(record));

        assertEquals(100, record.getResponseSize());
        assertTrue(record.getProcessingTime() > 0);

    }
}