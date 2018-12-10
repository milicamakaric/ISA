package com.example.ISA_AMA_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ISA_AMA_projekat.model.RentacarServis;
import com.example.ISA_AMA_projekat.repository.RentacarRepository;

@Service
public class RentacarService {
	
	@Autowired
	RentacarRepository rentRepository;
	
	public RentacarServis findByNaziv(String naziv) {
		return rentRepository.findOneByNaziv(naziv);
	}

	public List<RentacarServis> findAll() {
		return rentRepository.findAll();
	}
	
	public Optional<RentacarServis> findById(Long id){
		return rentRepository.findById(id);
	}
	
	public RentacarServis save(RentacarServis rent){
		return rentRepository.save(rent);
	}

}
