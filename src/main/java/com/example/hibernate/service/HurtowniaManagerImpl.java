package com.example.hibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernate.domain.Produkt;
import com.example.hibernate.domain.Producent;


@Component
@Transactional
public class HurtowniaManagerImpl implements HurtowniaManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory _sessionFactory){
		this.sessionFactory = _sessionFactory;
	}

	// Produkt
	@Override
	public void addProdukt(Produkt produkt) {
		produkt.setId(null);
		sessionFactory.getCurrentSession().persist(produkt);	
	}

	@Override
	public List<Produkt> getAllProdukts() {
		return sessionFactory.getCurrentSession().getNamedQuery("produkt.all").list();
	}

	@Override
	public Produkt getProduktById(Long id) {
		return (Produkt) sessionFactory.getCurrentSession().get(Produkt.class, id);
	}

	@Override
	public List<Produkt> getProduktsByNazwa(String nazwa) {
		return sessionFactory.getCurrentSession().getNamedQuery("produkt.byNazwa").setString("nazwa", nazwa).list();
	}

	@Override
	public void deleteProdukt(Produkt produkt) {
		sessionFactory.getCurrentSession().delete(produkt);
	}

	@Override
	public void updateProdukt(Produkt produkt) {
		sessionFactory.getCurrentSession().update(produkt);
	}

	// Producent
	@Override
	public void addProducent(Producent producent) {
		producent.setId(null);
		sessionFactory.getCurrentSession().persist(producent);
	}

	@Override
	public List<Producent> getAllProducents() {
		return sessionFactory.getCurrentSession().getNamedQuery("producent.all").list();
	}

	@Override
	public Producent getProducentById(Long id) {
		return (Producent) sessionFactory.getCurrentSession().get(Producent.class, id);
	}

	@Override
	public List<Producent> getProducentsByNazwa(String nazwaFirmy) {
		return sessionFactory.getCurrentSession()
			.getNamedQuery("producent.byNazwa").setString("nazwa", "%"+nazwaFirmy+"%").list();
	}

	@Override
	public List<Producent> getProducentsByNrTel(String nrTel) {
		return sessionFactory.getCurrentSession()
			.getNamedQuery("producent.byNrTel").setString("nrTel", nrTel).list();
	}

	@Override
	public void deleteProducent(Producent producent) {
		sessionFactory.getCurrentSession().delete(producent);
	}

	@Override
	public void updateProducent(Producent producent) {
		sessionFactory.getCurrentSession().update(producent);
	}
}