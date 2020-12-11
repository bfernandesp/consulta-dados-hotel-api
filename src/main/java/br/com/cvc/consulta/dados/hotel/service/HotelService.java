package br.com.cvc.consulta.dados.hotel.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.cvc.consulta.dados.hotel.repository.HotelRepository;
import br.com.cvc.consulta.dados.hotel.util.Utils;
import br.com.cvc.consulta.dados.hotel.vo.City;
import br.com.cvc.consulta.dados.hotel.vo.Hotel;
import br.com.cvc.consulta.dados.hotel.vo.PriceDetail;
import br.com.cvc.consulta.dados.hotel.vo.Room;
import br.com.cvc.consulta.dados.hotel.vo.RoomDetail;

@Service
public class HotelService {
	
	@Value("${consulta.dados.api.comissao}")
	private BigDecimal comissao;
	
	public HotelRepository hotelRepository;
	
	@Autowired
	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
	
	public Hotel obterHotel(int idHotel) {
		return hotelRepository.buscarHotel(idHotel);
	}
	
	private List<Hotel> buscarHotelPorCidade(int idCity) {
		return hotelRepository.buscarHotelPorCidade(idCity);
	}
	
	/**
	 * Calcula preco total hospedagem
	 * @param idCity
	 * @param dataInicio
	 * @param dataFim
	 * @param qtdAdulto
	 * @param qtdCrianca
	 * @return
	 * @throws Exception
	 */
	public List<City> totalViagemHoteisCidade(int idCity, String dataInicio, String dataFim, int qtdAdulto, int qtdCrianca) throws Exception{
		System.out.println(comissao);
		List<City> citys = new ArrayList<>();
		int qtdDiarias = Utils.diferencaEmDias(dataInicio, dataFim);
		
		if (qtdDiarias < 1) {
			throw new Exception("Período início e fim inválido");
		}
		
		List<Hotel> hoteis = buscarHotelPorCidade(idCity);
		
		hoteis.stream().forEach((Hotel h) -> {
			City c = new City();
			c.setId(h.getId());
			c.setCityName(h.getCityName());
			c.setRooms(new ArrayList<>());
			
			h.getRooms().stream().forEach((Room r) -> {
				RoomDetail rd = new RoomDetail();
				rd.setRoomID(r.getRoomID());
				rd.setCategoryName(r.getCategoryName());
				
				PriceDetail pd = new PriceDetail();
				pd.setPricePerDayAdult(r.getPrice().getAdult().divide(comissao,MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN));
				pd.setPricePerDayChild(r.getPrice().getChild().divide(comissao,MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN));
				rd.setPriceDetail(pd);
				
				BigDecimal totalAdultos = pd.getPricePerDayAdult().multiply(BigDecimal.valueOf(qtdAdulto)).multiply(BigDecimal.valueOf(qtdDiarias));
				BigDecimal totalCriancas = pd.getPricePerDayChild().multiply(BigDecimal.valueOf(qtdCrianca)).multiply(BigDecimal.valueOf(qtdDiarias));
				
				rd.setTotalPrice(totalAdultos.add(totalCriancas));
				
				c.getRooms().add(rd);
			});
			
			citys.add(c);
		});
		
		
		return citys;
	}

}
