package org.hbs.core.beans;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.hbs.core.util.IConstProperty.EDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TimestampSerializer extends JsonSerializer<Timestamp>
{

	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern(EDate.YYYY_MM_DD_HH_MM_SS_SSS_24.get());

	@Override
	public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException
	{
		// get the timestmap in the default timezone
		ZonedDateTime z = value.toInstant().atZone(ZoneId.systemDefault());
		String str = fmt.format(z);

		gen.writeString(str);
	}
}