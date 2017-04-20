package conversionservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	public Date convert(String source) {
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot convert String ["
					+ source + "] to  java.util.Date", e);
		}
	}

}
