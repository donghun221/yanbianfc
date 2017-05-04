package com.ffyc.site.common.image.jackson;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class CustomJacksonTimestampDeserializer extends JsonDeserializer<Timestamp>
{
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Timestamp deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException, JsonProcessingException
    {

        String date = jsonparser.getText();
        try
        {
            return (Timestamp)format.parse(date);
        }
        catch(ParseException e)
        {
            throw new RuntimeException(e);
        }

    }

}