package br.com.cvc.consulta.dados.hotel.repository;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Repository {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity requestApi(Map<String, Object> params, String uriApi, HttpMethod httpMethod, Class classType) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uriApi);
		
		params.keySet().stream().forEach((String key) -> { 
			builder.queryParam(key, params.get(key));
		});
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity response = restTemplate.exchange(
		        builder.toUriString(), 
		        httpMethod, 
		        entity,
		        classType);
	
		return response;
	}
	

}
