package com.example.ISA_AMA_projekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Vozilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String marka;
	
	@Column
	private String model;
	
	@Column
	private int godina_proizvodnje;
	
	@Column
	private int broj_sedista;
	
	@Column
	private String tip;
	
	@Column
	private double cena_dan;
	
	@Column
	private double prosecna_ocena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference
	private Filijala filijala;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="vozilo")
	@JsonManagedReference
	private Set<RezervacijaVozila> rezervacije = new HashSet<RezervacijaVozila>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rating> ocene = new HashSet<Rating>();
	
	@Column
	private boolean na_popustu;

	public Vozilo() {
		super();
	}

	
	

	public Set<RezervacijaVozila> getRezervacije() {
		return rezervacije;
	}




	public void setRezervacije(Set<RezervacijaVozila> rezervacije) {
		this.rezervacije = rezervacije;
	}




	public Set<Rating> getOcene() {
		return ocene;
	}




	public void setOcene(Set<Rating> ocene) {
		this.ocene = ocene;
	}




	public boolean isNa_popustu() {
		return na_popustu;
	}


	public void setNa_popustu(boolean na_popustu) {
		this.na_popustu = na_popustu;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getGodina_proizvodnje() {
		return godina_proizvodnje;
	}

	public void setGodina_proizvodnje(int godina_proizvodnje) {
		this.godina_proizvodnje = godina_proizvodnje;
	}

	public int getBroj_sedista() {
		return broj_sedista;
	}

	public void setBroj_sedista(int broj_sedista) {
		this.broj_sedista = broj_sedista;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getCena_dan() {
		return cena_dan;
	}

	public void setCena_dan(double cena_dan) {
		this.cena_dan = cena_dan;
	}

	public double getProsecna_ocena() {
		return prosecna_ocena;
	}

	public void setProsecna_ocena(double prosecna_ocena) {
		this.prosecna_ocena = prosecna_ocena;
	}

	public Filijala getFilijala() {
		return filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}
	
	
	
	

}
