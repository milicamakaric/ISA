package com.example.ISA_AMA_projekat.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RezervacijaVozila {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "servis_id", referencedColumnName="id")
	private RentacarServis servis; //ovde bi trebalo samo id da bude u tabeli?
	
	@OneToOne(mappedBy = "rezervacija", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
	@JoinColumn(name = "vozilo_id", referencedColumnName="id")
	private Vozilo vozilo; //nisam sigurna da li je ovo dobro
	
	/*
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik_id", referencedColumnName="id")
	private Korisnik korisnik;	//ovo msm da ne treba jer je rezervacija vozila samo deo glavne rezevracije, pa tamo vec imamo korisnika
	 */
	
	@Column
	private Date datum_preuzimanja;
	
	@Column
	private Date datum_vracanja;
	
	@Column
	private String mesto_preuzimanja;
	
	@Column
	private String mesto_vracanja;
	
	@Column
	private double ukupna_cena;
	
	@Column
	private int broj_putnika;
	
	@Column
	private boolean brza;

	public RezervacijaVozila() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RentacarServis getServis() {
		return servis;
	}

	public void setServis(RentacarServis servis) {
		this.servis = servis;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public Date getDatum_preuzimanja() {
		return datum_preuzimanja;
	}

	public void setDatum_preuzimanja(Date datum_preuzimanja) {
		this.datum_preuzimanja = datum_preuzimanja;
	}

	public Date getDatum_vracanja() {
		return datum_vracanja;
	}

	public void setDatum_vracanja(Date datum_vracanja) {
		this.datum_vracanja = datum_vracanja;
	}

	public String getMesto_preuzimanja() {
		return mesto_preuzimanja;
	}

	public void setMesto_preuzimanja(String mesto_preuzimanja) {
		this.mesto_preuzimanja = mesto_preuzimanja;
	}

	public String getMesto_vracanja() {
		return mesto_vracanja;
	}

	public void setMesto_vracanja(String mesto_vracanja) {
		this.mesto_vracanja = mesto_vracanja;
	}

	public double getUkupna_cena() {
		return ukupna_cena;
	}

	public void setUkupna_cena(double ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}

	public int getBroj_putnika() {
		return broj_putnika;
	}

	public void setBroj_putnika(int broj_putnika) {
		this.broj_putnika = broj_putnika;
	}

	public boolean isBrza() {
		return brza;
	}

	public void setBrza(boolean brza) {
		this.brza = brza;
	}
	
	
	
	

}