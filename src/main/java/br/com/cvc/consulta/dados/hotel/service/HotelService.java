package br.com.cvc.consulta.dados.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cvc.consulta.dados.hotel.repository.HotelRepository;
import br.com.cvc.consulta.dados.hotel.vo.Hotel;

@Service
public class HotelService {
	
	public HotelRepository hotelRepository;
	
	@Autowired
	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository=hotelRepository;
	}
	
	public Hotel obterHotel(int idHotel) {
		return hotelRepository.buscarHotel(idHotel);
	}

}
