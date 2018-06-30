/**
 *
 */
package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.yunxin.core.util.CalendarUtils;
import com.yunxin.core.util.CalendarUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author ThinkPad
 */
public class JsonTimestampNotSecondSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider arg2) throws IOException {
        String formattedDate = "";
        if (value != null) {
            formattedDate = CalendarUtils.formatDateTimeNotSecond(value);
        }
        jgen.writeString(formattedDate);
    }

}
