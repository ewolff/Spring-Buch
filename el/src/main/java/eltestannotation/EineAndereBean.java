package eltestannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EineAndereBean {
	
	private String country;

	@Value("#{systemProperties.country}")
	public void setCountry( String country) {
		this.country=country;
	}

	public String getCountry() {
		return country;
	}

}
