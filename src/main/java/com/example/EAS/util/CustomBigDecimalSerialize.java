package com.example.EAS.util;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomBigDecimalSerialize extends JsonSerializer<BigDecimal>{

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value == null) {
            gen.writeString("0.00");
        }else {
            gen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        }
        
    }

}
