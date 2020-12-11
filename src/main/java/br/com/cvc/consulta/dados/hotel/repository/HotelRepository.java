package br.com.cvc.consulta.dados.hotel.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cvc.consulta.dados.hotel.vo.Hotel;

@org.springframework.stereotype.Repository
public class HotelRepository extends Repository {
	
	@Value("${uri.api.parceiro.hoteis}")
	private String uriApiParceiroHoteis;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Hotel buscarHotel(Integer idHotel) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("id", idHotel);
		
		ResponseEntity<List> response = requestApi(params, uriApiParceiroHoteis.concat(String.valueOf(idHotel)), HttpMethod.GET, List.class);
		
		if (response != null && response.getBody() != null) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<Hotel> hoteisResponse = mapper.convertValue(response.getBody(),
		    		new TypeReference<List<Hotel>>() {});
			
			return hoteisResponse.get(0);
			
		}
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Hotel> buscarHotelPorCidade(Integer idCity) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("cityCode", idCity);
		
		ResponseEntity<List> response = requestApi(params, uriApiParceiroHoteis.concat("avail/").concat(String.valueOf(idCity)), HttpMethod.GET, List.class);
		
		if (response != null && response.getBody() != null) {
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<Hotel> hoteisResponse = mapper.convertValue(response.getBody(),
		    		new TypeReference<List<Hotel>>() {});
			
			return hoteisResponse;
			
		}
		
		return null;
	}

}
