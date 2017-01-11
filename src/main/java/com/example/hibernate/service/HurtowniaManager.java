package com.example.hibernate.service;

import java.util.List;
import com.example.hibernate.domain.Produkt;
import com.example.hibernate.domain.Producent;

public interface HurtowniaManager {

	// Produkt
	void addProdukt(Produkt produkt);
	List<Produkt> getAllProdukts();
	Produkt getProduktById(Long id);
	List<Produkt> getProduktsByNazwa(String pelnaNazwa);
	void deleteProdukt(Produkt produkt);
	void updateProdukt(Produkt produkt);

	// Producent
	void addProducent(Producent producent);
	List<Producent> getAllProducents();
	Producent getProducentById(Long id);
	List<Producent> getProducentsByNazwa(String regex);
	List<Producent> getProducentsByNrTel(String numerTelefonu);
	void deleteProducent(Producent producent);
	void updateProducent(Producent producent);
}
