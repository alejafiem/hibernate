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
/*
	@Test 
	public void updateTowarCheck() {
		hurtowniaManager.addTowar(t3);

		assertEquals(8.99, t3.getCena(), 0);

		t3.setCena(7.99);

		assertEquals(7.99, t3.getCena(), 0);

		hurtowniaManager.updateTowar(t3);

		assertEquals(7.99, t3.getCena(), 0);

		Towar retrievedTowar = hurtowniaManager.getTowarById(t3.getId());

		assertEquals(7.99, retrievedTowar.getCena(), 0);
	}

	// Producent
	@Test
	public void addProducentCheck() {
		int retrievedSize = hurtowniaManager.getAllProducents().size();

		hurtowniaManager.addProducent(p4);

		assertEquals(retrievedSize + 1, hurtowniaManager.getAllProducents().size());
	}
	
	@Test
	public void getProducentByIdCheck() {
		hurtowniaManager.addProducent(p2);
		hurtowniaManager.addProducent(p1);

		assertNotNull(hurtowniaManager.getProducentById(p2.getId()));
		assertNotNull(hurtowniaManager.getProducentById(p1.getId()));

		Producent retrieved1 = hurtowniaManager.getProducentById(p2.getId());
		Producent retrieved2 = hurtowniaManager.getProducentById(p1.getId());

		assertEquals(2008, retrieved1.getRokZalozeniaFirmy());
		assertEquals(2002, retrieved2.getRokZalozeniaFirmy());
	}

	@Test
	public void getProducentsByNazwaFirmyCheck() {
		int retrievedSize = hurtowniaManager.getProducentsByNazwaFirmy("BUD").size();

		hurtowniaManager.addProducent(p1);
		hurtowniaManager.addProducent(p2);
		hurtowniaManager.addProducent(p3);
		hurtowniaManager.addProducent(p4);

		assertEquals(retrievedSize + 3, hurtowniaManager.getProducentsByNazwaFirmy("BUD").size());
	}

	@Test
	public void getProducentsByNumerTelefonuCheck() {
		int retrievedSize = hurtowniaManager.getProducentsByNumerTelefonu("500-600-700").size();

		assertEquals(retrievedSize, hurtowniaManager.getProducentsByNumerTelefonu("500-600-700").size());

		p1.setNumerTelefonu("500-600-700");

		hurtowniaManager.addProducent(p1);

		assertEquals(retrievedSize + 1, hurtowniaManager.getProducentsByNumerTelefonu("500-600-700").size());

		hurtowniaManager.addProducent(p2);

		assertEquals(retrievedSize + 1, hurtowniaManager.getProducentsByNumerTelefonu("500-600-700").size());

		p3.setNumerTelefonu("500-600-700");

		hurtowniaManager.addProducent(p3);

		assertEquals(retrievedSize + 2, hurtowniaManager.getProducentsByNumerTelefonu("500-600-700").size());
	}

	@Test
	public void deleteProducentCheck() {
		hurtowniaManager.addProducent(p1);

		assertNotNull(hurtowniaManager.getProducentById(p1.getId()));
		int retrievedSize = hurtowniaManager.getAllProducents().size();

		hurtowniaManager.deleteProducent(p1);

		assertNull(hurtowniaManager.getProducentById(p1.getId())); // sprawdzenie czy usunelismy napewno ten rekord
		assertEquals(retrievedSize - 1, hurtowniaManager.getAllProducents().size()); // sprawdzenie czy napewno usunelismy tylko 1 rekord
	}

	@Test
	public void updateProducentCheck() {
		assertEquals("MAT-BUD", p1.getNazwaFirmy());

		hurtowniaManager.addProducent(p1);

		assertEquals("MAT-BUD", p1.getNazwaFirmy());

		p1.setNazwaFirmy("SUPER-BUD");

		hurtowniaManager.updateProducent(p1);

		assertEquals("SUPER-BUD", p1.getNazwaFirmy());
		assertEquals("SUPER-BUD", hurtowniaManager.getProducentById(p1.getId()).getNazwaFirmy());
	}
	
	// Relacje
	@Test
	public void updateProducentsTowarsCheck() {
		hurtowniaManager.addTowar(t1);
		hurtowniaManager.addTowar(t2);

		List<Towar> towars = new ArrayList<Towar>();
		towars.add(t1);
		towars.add(t2);

		p1.setTowars(towars);

		hurtowniaManager.addProducent(p1);

		assertEquals(2, hurtowniaManager.getProducentById(p1.getId()).getTowars().size());

		towars.remove(t2);

		p1.setTowars(towars);

		hurtowniaManager.updateProducent(p1);

		assertEquals(1, hurtowniaManager.getProducentById(p1.getId()).getTowars().size());
		assertNotNull(hurtowniaManager.getTowarById(t2.getId()));
		// nie usuwa towaru, nie powinien
	}

	@Test // X bedace w Y
	public void getTowarsOfProducentCheck() {
		hurtowniaManager.addTowar(t1);
		hurtowniaManager.addTowar(t2);

		List<Towar> towars = new ArrayList<Towar>();
		towars.add(t1);
		towars.add(t2);

		p1.setTowars(towars);

		hurtowniaManager.addProducent(p1);

		Producent retrieved = hurtowniaManager.getProducentById(p1.getId());

		List<Towar> retrievedTowars = retrieved.getTowars();

		assertEquals("Cement", retrievedTowars.get(0).getPelnaNazwa());
		assertEquals("Tynk", retrievedTowars.get(1).getPelnaNazwa());	
	}
*/
}
