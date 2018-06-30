package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class IsoCalendarJsonSerializer extends JsonSerializer<Calendar> {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public void serialize(Calendar value, JsonGenerator gen, SerializerProvider sp) throws
            IOException, JsonProcessingException {

        String formattedDate = formatter.format(value.getTime());

        gen.writeString(formattedDate);
    }
}