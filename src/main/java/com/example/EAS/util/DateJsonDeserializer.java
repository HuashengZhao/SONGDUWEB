package com.example.EAS.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateJsonDeserializer extends JsonDeserializer<LocalDateTime>
{
    
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, com.fasterxml.jackson.core.JsonProcessingException {
 
        try
        {
            if(jsonParser!=null&&StringUtils.isNotEmpty(jsonParser.getText())){
                String timeStr=jsonParser.getText();
                if(jsonParser.getText().length() < 14) {
                    timeStr=timeStr+" 00:00:00";
                }
                return LocalDateTime.parse(timeStr, formatter);
            }else {
                return null;
            }
 
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

