package br.com.cvc.consulta.dados.hotel.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	/**
	 * Informando dataInicio e dataFim no formado DD/MM/AAAA metodo retorna diferenca entre as datas em dias
	 * @param dataInicio ("dd/MM/yyyy")
	 * @param dataFim ("dd/MM/yyyy")
	 * @return int
	 */
	public static int diferencaEmDias(String dataInicio, String dataFim) {
		int dias = -1;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data1 = Calendar.getInstance();
			Calendar data2 = Calendar.getInstance();
			try {
				data1.setTime(sdf.parse(dataInicio));
				data2.setTime(sdf.parse(dataFim));
			} catch (java.text.ParseException e ) {}
			dias = data2.get(Calendar.DAY_OF_YEAR) -
					data1.get(Calendar.DAY_OF_YEAR);
		}catch (Exception e) {
			log.error("Erro ao obter a diferenca entre as datas > Inicio: " + dataInicio + " / Fim: " + dataFim);
			e.printStackTrace();
		}
		return dias;
	}
	

}
