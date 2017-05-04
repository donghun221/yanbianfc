package com.ffyc.site.common.image.jackson;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class CustomJacksonStringDeserializer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jsonparser,
			DeserializationContext deserializationcontext) throws IOException,
			JsonProcessingException {

		String content = jsonparser.getText();
		if (content != null) {
			return content.replaceAll("\r\n", "<br/>");
		}
		return content;

	}

}