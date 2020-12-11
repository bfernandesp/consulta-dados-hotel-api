package br.com.cvc.consulta.dados.hotel.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import br.com.cvc.consulta.dados.hotel.vo.City;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HotelControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@SuppressWarnings("unchecked")
	@Test
	public void buscarHotelPorCidade() throws Exception {
		//http://localhost:8080/hotel/hotels-city?dataFim=13%2F12%2F2020&dataInicio=08%2F12%2F2020&idCity=1032&qtdAdulto=2&qtdCrianca=1
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/hotel/hotels-city")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.queryParam("dataInicio", "08/12/2020")
				.queryParam("dataFim", "13/12/2020")
				.queryParam("idCity", "1032")
				.queryParam("qtdAdulto", "2")
				.queryParam("qtdCrianca", "1"));
		
		
		List<City> list = new Gson().fromJson(result.andReturn().getResponse().getContentAsString(), List.class);
		
		for (Object obj : list) {
			
			City h = new Gson().fromJson(new Gson().toJson(obj), City.class);
			assertTrue(h != null && h.getCityName().equals("Porto Seguro"));
			break;
		}
		
	}
	
	
}
