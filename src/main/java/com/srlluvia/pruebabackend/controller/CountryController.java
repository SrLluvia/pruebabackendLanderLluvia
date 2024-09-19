package com.srlluvia.pruebabackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srlluvia.pruebabackend.model.Country;
import com.srlluvia.pruebabackend.repository.CountryRepository;

@RestController
public class CountryController {

	private CountryRepository countryRepository;
	
	public CountryController(CountryRepository countryRepository) {
	    this.countryRepository = countryRepository;
	}
	
	/*
	 * Calls the API restcountries and stores the information.
	 * The API allows to filter the response with ?fields={field},{field},{field}
	 * but the field name contais also many fields inside it, so its parsed to maintain only
	 * the official name
	 */
	@PostMapping("/api/v1/data/country")
	public void saveCountryInfo() {
		String apiUrl = "https://restcountries.com/v3.1/all?fields=name,population";
		
		//Needed to call the API
		RestTemplate restTemplate = new RestTemplate();
		//Needed to parse the field name
		ObjectMapper objectMapper = new ObjectMapper();
		
		String countryResponse = restTemplate.getForObject(apiUrl, String.class);
		
		//Could throw JsonMappingException
		try {
			//Parse the response to JsonNode so it can be iterated
			JsonNode countryResponseParsed = objectMapper.readTree(countryResponse);
			for(JsonNode countryNode: countryResponseParsed) {
				//Get field name.official as String
				String officialName = countryNode.path("name").path("official").asText();
				System.out.println("Official name: " + officialName);
				//Get field population as int
				int population = countryNode.path("population").asInt();
				System.out.println("Population: " + population);
				
				//Create new Country and save it
				Country country = new Country(officialName, population);
				countryRepository.save(country);
				System.out.println("Saved");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/api/v1/data/country")
	public List<Country> getAllCountrysInfo(){
		return countryRepository.findAll();
	}
	
}
