package br.com.cvc.consulta.dados.hotel.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PriceDetail {

	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChild;
}
