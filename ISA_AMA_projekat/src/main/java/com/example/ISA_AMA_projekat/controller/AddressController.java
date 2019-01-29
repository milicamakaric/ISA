package com.example.ISA_AMA_projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ISA_AMA_projekat.model.Adresa;
import com.example.ISA_AMA_projekat.model.Grad;
import com.example.ISA_AMA_projekat.service.AddressService;
import com.example.ISA_AMA_projekat.service.GradService;

@RestController
@RequestMapping(value="api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private GradService gradService;
	
	@RequestMapping(
			value = "/checkCity/{city}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grad> checkCity(@PathVariable("city") String city){
		
		Grad grad = gradService.findByNaziv(city);
		
		return new ResponseEntity<Grad>(grad, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/newCity/{city}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grad> newCity(@PathVariable("city") String city){
		
		Grad grad = new Grad(city);
		Grad result = gradService.save(grad);
		
		return new ResponseEntity<Grad>(result, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/checkAddress/{street}/{number}/{longitude}/{latitude}/{city_id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Adresa> checkAddress(@PathVariable("street") String street, @PathVariable("number") String number, @PathVariable("longitude") double longitude, @PathVariable("latitude") double latitude, @PathVariable("city_id") Integer city_id){
		
		System.out.println("[AddessController: checkAddress] grad.id: " + city_id);
		List<Adresa> adrese = addressService.checkAddress(city_id, street, number);
		
		Grad grad = gradService.findById(city_id);
		System.out.println("[AddessController: checkAddress] grad.naziv: " + grad.getNaziv());
		
		Adresa adresa = null;
		
		if(adrese.isEmpty()) {
			Adresa newAddress = new Adresa();
			newAddress.setGrad(grad);
			newAddress.setUlica(street);
			newAddress.setBroj(number);
			newAddress.setLongitude(longitude);
			newAddress.setLatitude(latitude);
			
			adresa = addressService.save(newAddress);
		}else {
			adresa = adrese.get(0);
		}
		System.out.println("[AddessController: checkAddress] adresa.id: " + adresa.getId());
		return new ResponseEntity<Adresa>(adresa, HttpStatus.OK);
	}

}