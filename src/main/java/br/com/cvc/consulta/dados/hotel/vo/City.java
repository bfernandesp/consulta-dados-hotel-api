package br.com.cvc.consulta.dados.hotel.vo;

import java.util.List;

import lombok.Data;

@Data
public class City {
	
	private Integer id;
	private String cityName;
	private List<RoomDetail> rooms;

}
