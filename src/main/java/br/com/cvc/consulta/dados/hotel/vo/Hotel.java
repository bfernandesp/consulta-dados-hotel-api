package br.com.cvc.consulta.dados.hotel.vo;

import java.util.List;

import lombok.Data;

@Data
public class Hotel {
	
	private Integer id;
	private String name;
	private Integer cityCode;
	private String cityName;
	private List<Room> rooms;
}
