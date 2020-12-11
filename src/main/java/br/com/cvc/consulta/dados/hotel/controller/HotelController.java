package br.com.cvc.consulta.dados.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cvc.consulta.dados.hotel.service.HotelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Obter dados Hotel", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com dados do Hotel."),
			@ApiResponse(code = 400, message = "Erro na busca dos dados do Hotel.")
	})
	@GetMapping("/dados-hotel")
	public ResponseEntity obterDadosHotel(@RequestParam(value="idHotel", required = true) Integer idHotel) {
		log.info("Buscando Hotel > " + idHotel);
		return ResponseEntity.status(HttpStatus.OK)
				.body(hotelService.obterHotel(idHotel));
		
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="Obter valores Hospedagem", response=ResponseEntity.class, responseContainer = "Set")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorno com os valores da hospedagem."),
			@ApiResponse(code = 400, message = "Erro obter valores da hospedagem.")
	})
	@GetMapping("/hotels-city")
	public ResponseEntity buscarHotelPorCidade(@RequestParam(value="idCity", required = true) Integer idCity,
			@RequestParam(value="dataInicio", required = true) String dataInicio,
			@RequestParam(value="dataFim", required = true) String dataFim,
			@RequestParam(value="qtdAdulto", required = true) Integer qtdAdulto,
			@RequestParam(value="qtdCrianca", required = true) Integer qtdCrianca) {
		log.info("Buscando Hotel por cidade > " + idCity);
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(hotelService.totalViagemHoteisCidade(idCity, dataInicio, dataFim, qtdAdulto, qtdCrianca));
		} catch (Exception e) {
			log.error("Erro ao buscar valores da hospedagem >> " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
	
}
