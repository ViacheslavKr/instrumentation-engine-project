package com.instrumentation.decorators;

import com.instrumentation.model.InstrumentationRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RequestHeaderDecoratorTest {

    private InstrumentationEntityDecorator decorator = new RequestHeaderDecorator();

    @Mock
    private   HttpServletResponse response ;

    @Test
    void onAfterCompletion() {

        InstrumentationRecord record = new InstrumentationRecord(HttpMethod.GET, "some url", "SESSION 1");
        record.setInstrumentationId("INSTR_ID");
        decorator.onAfterCompletion(null, response, null, null, record);
        Mockito.verify(response,Mockito.times(1)).setHeader("Response ID","INSTR_ID");
    }
}