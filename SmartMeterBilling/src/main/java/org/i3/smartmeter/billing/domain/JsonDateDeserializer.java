package org.i3.smartmeter.billing.domain;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.i3.smartmeter.billing.test.DateFormatter;
import org.springframework.stereotype.Component;

@Component
public class JsonDateDeserializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		String formattedDate = DateFormatter.formatter.format(date);
		gen.writeString(formattedDate);
	}

}
