package com.srlluvia.pruebabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srlluvia.pruebabackend.model.Country;

/*
 * Allows to comunicate with the database
 * JpaRepository takes the class it expects to manage and the Id class
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String>{

}
