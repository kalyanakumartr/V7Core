package org.hbs.core.beans;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomTimestampSerializer extends JsonSerializer<Timestamp>
{

	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");

	@Override
	public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException
	{
		ZonedDateTime z = value.toInstant().atZone(ZoneId.systemDefault());
		String str = fmt.format(z);

		gen.writeString(str);
	}
}