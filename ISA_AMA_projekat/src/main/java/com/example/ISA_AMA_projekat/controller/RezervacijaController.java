package com.example.ISA_AMA_projekat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA_AMA_projekat.model.Aviokompanija;
import com.example.ISA_AMA_projekat.model.Korisnik;
import com.example.ISA_AMA_projekat.model.Let;
import com.example.ISA_AMA_projekat.model.OsobaIzRez;
import com.example.ISA_AMA_projekat.model.Rezervacija;
import com.example.ISA_AMA_projekat.security.TokenUtils;
import com.example.ISA_AMA_projekat.service.EmailService;
import com.example.ISA_AMA_projekat.service.KorisnikService;
import com.example.ISA_AMA_projekat.service.LetService;
import com.example.ISA_AMA_projekat.service.OsobaIzRezService;
import com.example.ISA_AMA_projekat.service.RezervacijaService;

@RestController
@RequestMapping(value="api/rezervacija")
public class RezervacijaController 
{

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private KorisnikService userDetailsService;
	
	@Autowired
	private LetService letService;
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private OsobaIzRezService osobaIzRezService;
	
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(
			value = "/create/{id}/{token}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveKorisnik(@RequestBody List<OsobaIzRez> osobe, @PathVariable("id") Integer flightID, @PathVariable("token") String token)
	{
		String email = tokenUtils.getUsernameFromToken(token);
		Korisnik korisnik = (Korisnik) this.userDetailsService.loadUserByUsername(email);
				
		//* * * BEKEND VALIDACIJA INFO O OSOBAMA U REZERVACIJI START* * * 
		Optional<Let> letOp = letService.findById(flightID);
		
		List<OsobaIzRez> new_osobe = new ArrayList<OsobaIzRez>(); 
		
		System.out.println("Size of the list: " + osobe.size());
		
		
		Let let = null;
		try
		{
			let = letOp.get();
		}
		catch(Exception e)
		{
			System.err.println("NO FLIGHT FOUND: " + flightID);
		}
		
		
		
		for(int i = 0 ; i < osobe.size() ; i ++ )
		{
			OsobaIzRez osoba = osobe.get(i);
			System.out.println("ID OSOBE:" + osoba.getId() + " " + osoba.getEmail());
			
			if( !Pattern.matches("[a-zA-Z]{1,}", osoba.getIme() ) )
			{
				System.err.println("BEKEND VALIDACIJA IZBACILA ZBOG PASOSA: " + osoba.getIme());
				return null;
			}
			if( !Pattern.matches("[a-zA-Z]{1,}", osoba.getPrezime() ) )
			{
				System.err.println("BEKEND VALIDACIJA IZBACILA ZBOG PASOSA: " + osoba.getPrezime());
				return null;
			}
			if( !Pattern.matches("[0-9]{2,}", osoba.getBrojPasosa() ) )
			{
				System.err.println("BEKEND VALIDACIJA IZBACILA ZBOG PASOSA: " + osoba.getBrojPasosa());
				return null;
			}
			if( !Pattern.matches("[a-zA-Z0-9_-[.]]{2,}@[a-zA-Z0-9_-]{2,}[.][a-zA-Z]{2,4}", osoba.getEmail() ) )
			{
				System.err.println("BEKEND VALIDACIJA IZBACILA ZBOG EMAIL-A: " + osoba.getEmail());
				return null;
			}
			

			if(osoba.getPrtljag() > 2 || osoba.getPrtljag() < 0)
			{
				System.err.println("BEKEND VALIDACIJA IZBACILA ZBOG PTRLJAGA");
				return null;
			}
			
			//provera sedista
			if(osoba.getSediste() < 1 || osoba.getSediste() > let.getMaxKapacitet())
			{
				System.err.println("Nevalidno numerisano sediste.");
				return null;
			}
			if(let.getZauzetaSedista().contains(osoba.getSediste()))
			{
				System.err.println("Pokusaj rezervacije zauzetog sedista.");
				return null;
			}
			//ubacivanje sedista
			let.getZauzetaSedista().add(osoba.getSediste());
		}	
		
		System.out.println("Korisnik: " + korisnik.getId() + " Let: " + let.getId());		
		//* * * BEKEND VALIDACIJA INFO O OSOBAMA U REZERVACIJI END* * *
		
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setLet(let);
		rezervacija.setCena(let.getCena() * osobe.size());
		rezervacija.setBrza(false);
		rezervacija.setDatumRezervacije(new Date());
		rezervacija.setKorisnik(korisnik);
		
		//settovanje ostalih rezervacija u okviru ove na null
		rezervacija.setRezervacijaVozila(null);
		rezervacija.setRezevacijaHotel(null);
		
		Rezervacija rez = rezervacijaService.save(rezervacija);
		System.out.println("Upisana rezervacija id: " + rez.getId());
		
		for(int i = 0 ; i < osobe.size() ; i ++ )
		{
			OsobaIzRez osoba = osobe.get(i);	
			osoba.setRezervacija(rez);
			
			
			if(osoba.getEmail().equals(korisnik.getEmail()))
			{
				osoba.setPotvrdjeno(true);
				osoba.setKorisnikUcesnik(korisnik);
			}
			else
			{	
				Korisnik tempKorisnik = korisnikService.findByEmail(osoba.getEmail());
				if(tempKorisnik == null) //ako ne postoji korisnik nase aplikacije sa ovim mejlom
				{
					//potvrdjeno zanci korisnik placa
					osoba.setPotvrdjeno(true);
				}
				else
				{
					if(korisnikService.areFriends(korisnik, tempKorisnik))
					{
						//ukoliko se informacije ne poklapaju
						if(!osoba.getIme().equals(tempKorisnik.getIme()))
						{
							return null;
						}
						if(!osoba.getPrezime().equals(tempKorisnik.getPrezime()))
						{
							return null;
						}
						//salje se takav mejl koji govori da prijatelj moze da prihvati svoj deo troskova 
						
						
						boolean poslatMejl= sendRezervationInfo(tempKorisnik, rez, true);
						osoba.setPotvrdjeno(false);	
						osoba.setKorisnikUcesnik(tempKorisnik);
					}
					else
					{
						//salje se mejl da je rezevisao i da su troskovi na korisnika koji je rezervisao
						boolean poslatMejl= sendRezervationInfo(tempKorisnik, rez, false);
						osoba.setPotvrdjeno(true);	
					}
				}
			}
			
			
			OsobaIzRez new_osoba = osobaIzRezService.save(osoba);
			new_osobe.add(new_osoba);
			System.out.println("UPISANA NOVA OSOBA:" + new_osoba.getId() + " " + new_osoba.getEmail());
		}
		//sacuvaj let
		Let l = letService.save(let);
				
		return rez.getId().toString();
	}
	
	
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rezervacija> getRezervacija(@PathVariable("id") Integer id)	
	{
		try
		{
			return new ResponseEntity<Rezervacija>(rezervacijaService.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("ispao");
			return null; 	
		}
	}
	
	
	public boolean sendRezervationInfo(Korisnik korisnik, Rezervacija rez, boolean friend)
	{

		//slanje emaila
		try 
		{	
			if(friend)
				emailService.sendReservationExpensesConformation(korisnik, rez);
			else
				emailService.sendReservationConformation(korisnik, rez);
		}catch( Exception e )
		{
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			return false;
		}

		return true;
	}
	
}