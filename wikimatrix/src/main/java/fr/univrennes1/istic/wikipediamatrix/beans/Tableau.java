package fr.univrennes1.istic.wikipediamatrix.beans;

import java.util.ArrayList;
import java.util.List;

public class Tableau {
	
	private String[] header;
	private List<String[]> lignes = new ArrayList<>();
	
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}

	public List<String[]> getLignes() {
		return lignes;
	}
	public String[] getLigne(int index) {
		return lignes.get(index);
	}
	public void ajouterLigne(String[] ligne) {
		lignes.add(ligne);
	}

}
