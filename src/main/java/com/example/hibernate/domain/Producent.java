package com.example.hibernate.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "producent.all", query = "Select p from Producent p"),
	@NamedQuery(name = "producent.byNazwa", query = "Select p from Producent p where p.nazwa LIKE :nazwa"),
	@NamedQuery(name = "producent.byNrTel", query = "Select p from Producent p where p.nrTel = :nrTel")
})
public class Producent {
	private Long id;
	private String nazwa;
	private String miasto;
	private String nrTel;
	private int nip;
	private List<Produkt> produkts = new ArrayList<Produkt>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	@Column(nullable = false)
	public String getMiasto() {
		return miasto;
	}
	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}
	@Column(nullable = false)
	public String getNrTel() {
		return nrTel;
	}
	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
	}
	public int getNip() {
		return nip;
	}
	public void setNip(int nip) {
		this.nip = nip;
	}
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Produkt> getProdukts() 
	{
		return produkts;
	}
	public void setProdukts(List<Produkt> produkts) {
		this.produkts = produkts;
	}

	public Producent() {
	}

	public Producent(String nazwa, String miasto, String nrTel, int nip) {
		this.nazwa = nazwa;
		this.miasto = miasto;
		this.nrTel = nrTel;
		this.nip = nip;
	}	
}
