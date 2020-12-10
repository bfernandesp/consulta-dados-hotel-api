package br.com.cvc.consulta.dados.hotel.vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Price {
	
	private BigDecimal adult;
	private BigDecimal child;

}
