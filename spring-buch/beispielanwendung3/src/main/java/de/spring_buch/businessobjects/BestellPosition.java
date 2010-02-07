package de.spring_buch.businessobjects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import de.spring_buch.framework.BusinessObject;

public class BestellPosition extends BusinessObject {

	private int id;

	@NotNull
	private Ware ware;

	@Min(1)
	private int anzahl;

	public BestellPosition() {
		super();
	}

	public BestellPosition(Ware ware, int anzahl) {
		super();
		this.ware = ware;
		this.anzahl = anzahl;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public Ware getWare() {
		return ware;
	}

	public void setWare(Ware ware) {
		this.ware = ware;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double determineBetrag() {
		return getAnzahl() * getWare().getPreis();
	}

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof BestellPosition))
			return false;
		BestellPosition otherDetail = (BestellPosition) other;
		if (ware.getId() != otherDetail.ware.getId()) {
			return false;
		}
		return this.anzahl == otherDetail.anzahl;
	}

	public int hashCode() {
		return anzahl;
	}

}
