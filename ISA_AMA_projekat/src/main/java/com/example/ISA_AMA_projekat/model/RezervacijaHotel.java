package com.example.ISA_AMA_projekat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class RezervacijaHotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private Date datum_rezervacije;
	
	@Column
	private Date datum_dolaska;

	@Column
	private int broj_nocenja;

	@Column
	private int broj_osoba;

	@Column
	private int broj_soba;

	@Column
	private double ukupna_cena;

	@Column
	private boolean brza;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel hotel;
	
	@ManyToMany(mappedBy="rezervacije")
	private Set<Soba> sobe = new HashSet<Soba>();

	public RezervacijaHotel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum_rezervacije() {
		return datum_rezervacije;
	}

	public void setDatum_rezervacije(Date datum_rezervacije) {
		this.datum_rezervacije = datum_rezervacije;
	}

	public Date getDatum_dolaska() {
		return datum_dolaska;
	}

	public void setDatum_dolaska(Date datum_dolaska) {
		this.datum_dolaska = datum_dolaska;
	}

	public int getBroj_nocenja() {
		return broj_nocenja;
	}

	public void setBroj_nocenja(int broj_nocenja) {
		this.broj_nocenja = broj_nocenja;
	}

	public int getBroj_osoba() {
		return broj_osoba;
	}

	public void setBroj_osoba(int broj_osoba) {
		this.broj_osoba = broj_osoba;
	}

	public int getBroj_soba() {
		return broj_soba;
	}

	public void setBroj_soba(int broj_soba) {
		this.broj_soba = broj_soba;
	}

	public double getUkupna_cena() {
		return ukupna_cena;
	}

	public void setUkupna_cena(double ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}

	public boolean isBrza() {
		return brza;
	}

	public void setBrza(boolean brza) {
		this.brza = brza;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Set<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(Set<Soba> sobe) {
		this.sobe = sobe;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RezervacijaHotel rh = (RezervacijaHotel) o;
        if(rh.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rh.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "RezervacijaHotel [id=" + id + ", broj_nocenja=" + broj_nocenja + ", broj_soba="
				+ broj_soba + ", ukupna_cena=" + ukupna_cena + "]";
	}
}
