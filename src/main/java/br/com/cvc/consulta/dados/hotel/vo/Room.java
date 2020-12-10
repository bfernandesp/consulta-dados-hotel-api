package br.com.cvc.consulta.dados.hotel.vo;

import lombok.Data;

@Data
public class Room {

	private Integer roomID;
	private String categoryName;
	private Price price;
}
