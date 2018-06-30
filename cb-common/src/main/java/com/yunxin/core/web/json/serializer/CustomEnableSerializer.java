package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomEnableSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        if (arg0) {
            arg1.writeString("启用");
        } else {
            arg1.writeString("禁用");
        }
    }
}
