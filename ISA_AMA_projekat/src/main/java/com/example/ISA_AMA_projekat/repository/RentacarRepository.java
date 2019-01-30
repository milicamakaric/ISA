package com.example.ISA_AMA_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.ISA_AMA_projekat.model.RentacarServis;

public interface RentacarRepository extends JpaRepository<RentacarServis, Integer>{

		RentacarServis findOneByNaziv(String naziv);
		
		@Modifying
		@Transactional
		@Query("select rentacar_servis from RentacarServis rentacar_servis where rentacar_servis.adresa.grad.naziv like ?1 or rentacar_servis.adresa.ulica like ?1 or rentacar_servis.adresa.broj like ?1 or rentacar_servis.naziv like ?1")
		List<RentacarServis> search(String name_location);
		
		
		@Modifying
		@Transactional
		@Query("update RentacarServis rs set rs.naziv = ?1 , rs.adresa.id = ?2 , rs.promotivni_opis = ?3 where rs.id = ?4")
		public void updateServis(String naziv, Integer adresa_id, String promotivni_opis,  Integer id);
}
