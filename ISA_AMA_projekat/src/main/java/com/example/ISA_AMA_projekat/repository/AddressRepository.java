package com.example.ISA_AMA_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.ISA_AMA_projekat.model.Adresa;

public interface AddressRepository extends JpaRepository<Adresa, Integer> {
	
	@Modifying
	@Transactional
	@Query("select adresa from Adresa adresa where adresa.grad.id = ?1 and adresa.ulica = ?2 and adresa.broj = ?3")
	List<Adresa> checkAddress(Integer city_id, String street, String number);
}