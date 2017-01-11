package com.example.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;

@Entity
@NamedQueries({
	@NamedQuery(name = "produkt.all", query = "Select t from Produkt t"),
	@NamedQuery(name = "produkt.byNazwa", query = "Select t from Produkt t where t.nazwa = :nazwa")
})
public class Produkt {
	private Long id;
	private String nazwa;
	private double cena;
	private double znizka;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}

	@Column(nullable = false)
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public double getZnizka() {
		return znizka;
	}
	public void setZnizka(double znizka) {
		this.znizka = znizka;
	}

	public Produkt() {
	}

	public Produkt(String nazwa, double cena, double znizka) {
		this.nazwa = nazwa;
		this.cena = cena;
		this.znizka = znizka;
	}
}
