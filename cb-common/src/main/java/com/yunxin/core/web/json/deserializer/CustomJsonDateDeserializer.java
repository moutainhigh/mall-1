/**
 *
 */
package com.yunxin.core.web.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.yunxin.core.util.CalendarUtils;
import com.yunxin.core.util.CalendarUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author gonglei
 */
public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext arg1) throws IOException {
        String date = jsonparser.getText();
        try {
            return CalendarUtils.parseDateTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
