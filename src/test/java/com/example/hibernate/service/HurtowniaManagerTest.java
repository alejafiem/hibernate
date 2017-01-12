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

}
