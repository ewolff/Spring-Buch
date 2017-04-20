package de.spring_buch.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"marshaller.xml");

		RestTemplate restTemplate = new RestTemplate();
		Marshaller marshaller = applicationContext.getBean("marshaller",
				Marshaller.class);
		List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<HttpMessageConverter<?>>();
		httpMessageConverters.add(new MarshallingHttpMessageConverter(
				marshaller));
		restTemplate.setMessageConverters(httpMessageConverters);
		List result = restTemplate
				.getForObject(
						"http://localhost:8080/beispielanwendung/springbuchweb/kunden.xml",
						List.class);
		System.out.println(result);
	}
}
