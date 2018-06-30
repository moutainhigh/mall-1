package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class IsoDateJsonSerializer extends JsonSerializer<Date> {

    private static  DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public void serialize(Date date, JsonGenerator json, SerializerProvider provider) throws IOException {
        // The client side will handle presentation, we just want it accurate
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String out = df.format(date);
        json.writeString(out);
    }
}