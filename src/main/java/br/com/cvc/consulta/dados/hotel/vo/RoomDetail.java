package br.com.cvc.consulta.dados.hotel.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RoomDetail {
	
	private Integer roomID;
	private String categoryName;
	private BigDecimal totalPrice;
	private PriceDetail priceDetail;

}
