/**
 *
 */
package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author ThinkPad
 */
public class JsonObjectLazySerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator jgen, SerializerProvider arg2) throws IOException {
        try {
            jgen.writeObject(value);
        } catch (Exception e) {
            jgen.writeEndObject();
        }
    }

}
