package com.example.hibernate.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernate.domain.Produkt;
import com.example.hibernate.domain.Producent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional	
public class HurtowniaManagerTest {
	
	@Autowired
	HurtowniaManager hurtowniaManager;

	private Produkt produkt1, produkt2, produkt3, produkt4;
	private Producent producent1, producent2, producent3, producent4;

	@Before
	public void init() {
		produkt1 = new Produkt("Bateria", 15.99, 0.2);
		produkt2 = new Produkt("Umywalka", 19.99, 0.05);
		produkt3 = new Produkt("Wanna", 8.99, 0.03);
		produkt4 = new Produkt("Wanna", 8.29, 0);

		producent1 = new Producent("Sanitex", "Gdansk", "999-999-999", 9999999);
		producent2 = new Producent("Wannex", "Gdansk", "666-666-666", 1234567);
		producent3 = new Producent("Sanitozord", "Warszawa", "333-333-333", 9876543);
		producent4 = new Producent("SaniTor", "Krakow", "434-343-555", 983859);
	}

	@Test
	public void AlwaysPassing(){} 

	// Produkt
	@Test
	public void addProduktCheck() {
		int retrievedSize = hurtowniaManager.getAllProdukts().size();

		hurtowniaManager.addProdukt(produkt1);

		assertEquals(retrievedSize + 1, hurtowniaManager.getAllProdukts().size());
	}

	@Test
	public void getProduktByIdCheck() {
		hurtowniaManager.addProdukt(produkt1);

		Produkt retrieved = hurtowniaManager.getProduktById(produkt1.getId());

		assertEquals("Bateria", retrieved.getNazwa());
	}

	@Test
	public void getProduktsByNazwaCheck() {
		int retrievedSize = hurtowniaManager.getProduktsByNazwa("Wanna").size();

		hurtowniaManager.addProdukt(produkt3);
		hurtowniaManager.addProdukt(produkt4);

		assertEquals(retrievedSize + 2, hurtowniaManager.getProduktsByNazwa("Wanna").size());
	}

	@Test
	public void deleteProduktCheck() {
		hurtowniaManager.addProdukt(produkt1);

		int retrievedSize = hurtowniaManager.getAllProdukts().size();

		Long retrievedId = produkt1.getId();

		assertEquals(retrievedId, hurtowniaManager.getProduktById(retrievedId).getId());

		hurtowniaManager.deleteProdukt(produkt1);

		assertNull(hurtowniaManager.getProduktById(retrievedId)); // sprawdzenie czy usunelismy napewno ten sam rekord
		assertEquals(retrievedSize - 1, hurtowniaManager.getAllProdukts().size()); // sprawdzenie czy napewno usunelismy tylko 1 rekord
	}

	@Test 
	public void updateProduktCheck() {
		hurtowniaManager.addProdukt(produkt3);

		assertEquals(8.99, produkt3.getCena(), 0);

		produkt3.setCena(7.99);

		assertEquals(7.99, produkt3.getCena(), 0);

		hurtowniaManager.updateProdukt(produkt3);

		assertEquals(7.99, produkt3.getCena(), 0);

		Produkt retrievedProdukt = hurtowniaManager.getProduktById(produkt3.getId());

		assertEquals(7.99, retrievedProdukt.getCena(), 0);
	}
	
	// Producent
	@Test
	public void addProducentCheck() {
		int retrievedSize = hurtowniaManager.getAllProducents().size();

		hurtowniaManager.addProducent(producent4);

		assertEquals(retrievedSize + 1, hurtowniaManager.getAllProducents().size());
	}
	
	@Test
	public void getProducentByIdCheck() {
		hurtowniaManager.addProducent(producent2);
		hurtowniaManager.addProducent(producent1);

		assertNotNull(hurtowniaManager.getProducentById(producent2.getId()));
		assertNotNull(hurtowniaManager.getProducentById(producent1.getId()));

		Producent retrieved1 = hurtowniaManager.getProducentById(producent2.getId());
		Producent retrieved2 = hurtowniaManager.getProducentById(producent1.getId());

		assertEquals(1234567, retrieved1.getNip());
		assertEquals(9999999, retrieved2.getNip());
	}

	@Test
	public void getProducentsByNazwaCheck() {
		int retrievedSize = hurtowniaManager.getProducentsByNazwa("Sani").size();

		hurtowniaManager.addProducent(producent1);
		hurtowniaManager.addProducent(producent2);
		hurtowniaManager.addProducent(producent3);
		hurtowniaManager.addProducent(producent4);

		assertEquals(retrievedSize + 3, hurtowniaManager.getProducentsByNazwa("Sani").size());
	}

	@Test
	public void getProducentsByNrTelCheck() {
		int retrievedSize = hurtowniaManager.getProducentsByNrTel("999-999-999").size();

		assertEquals(retrievedSize, hurtowniaManager.getProducentsByNrTel("999-999-999").size());

		producent1.setNrTel("999-999-999");

		hurtowniaManager.addProducent(producent1);

		assertEquals(retrievedSize + 1, hurtowniaManager.getProducentsByNrTel("999-999-999").size());

		hurtowniaManager.addProducent(producent2);

		assertEquals(retrievedSize + 1, hurtowniaManager.getProducentsByNrTel("999-999-999").size());

		producent3.setNrTel("999-999-999");

		hurtowniaManager.addProducent(producent3);

		assertEquals(retrievedSize + 2, hurtowniaManager.getProducentsByNrTel("999-999-999").size());
	}
	@Test
	public void deleteProducentCheck() {
		hurtowniaManager.addProducent(producent1);

		assertNotNull(hurtowniaManager.getProducentById(producent1.getId()));
		int retrievedSize = hurtowniaManager.getAllProducents().size();

		hurtowniaManager.deleteProducent(producent1);

		assertNull(hurtowniaManager.getProducentById(producent1.getId())); // sprawdzenie czy usunelismy napewno ten rekord
		assertEquals(retrievedSize - 1, hurtowniaManager.getAllProducents().size()); // sprawdzenie czy napewno usunelismy tylko 1 rekord
	}
	
	@Test
	public void updateProducentCheck() {
		assertEquals("Sanitex", producent1.getNazwa());

		hurtowniaManager.addProducent(producent1);

		assertEquals("Sanitex", producent1.getNazwa());

		producent1.setNazwa("BlaBla");

		hurtowniaManager.updateProducent(producent1);

		assertEquals("BlaBla", producent1.getNazwa());
		assertEquals("BlaBla", hurtowniaManager.getProducentById(producent1.getId()).getNazwa());
	}

	// /Relacje
	@Test
	public void updateProducentsProduktsCheck() {
		hurtowniaManager.addProdukt(produkt1);
		hurtowniaManager.addProdukt(produkt2);

		List<Produkt> produkts = new ArrayList<Produkt>();
		produkts.add(produkt1);
		produkts.add(produkt2);

		producent1.setProdukts(produkts);

		hurtowniaManager.addProducent(producent1);

		assertEquals(2, hurtowniaManager.getProducentById(producent1.getId()).getProdukts().size());

		produkts.remove(produkt2);

		producent1.setProdukts(produkts);

		hurtowniaManager.updateProducent(producent1);

		assertEquals(1, hurtowniaManager.getProducentById(producent1.getId()).getProdukts().size());
		assertNotNull(hurtowniaManager.getProduktById(produkt2.getId()));
		// nie usuwa towaru, nie powinien
	}

	@Test // X bedace w Y
	public void getProduktsOfProducentCheck() {
		hurtowniaManager.addProdukt(produkt1);
		hurtowniaManager.addProdukt(produkt2);

		List<Produkt> produkts = new ArrayList<Produkt>();
		produkts.add(produkt1);
		produkts.add(produkt2);

		producent1.setProdukts(produkts);

		hurtowniaManager.addProducent(producent1);

		Producent retrieved = hurtowniaManager.getProducentById(producent1.getId());

		List<Produkt> retrievedTowars = retrieved.getProdukts();

		assertEquals("Bateria", retrievedTowars.get(0).getNazwa());
		assertEquals("Umywalka", retrievedTowars.get(1).getNazwa());	
	}
}
