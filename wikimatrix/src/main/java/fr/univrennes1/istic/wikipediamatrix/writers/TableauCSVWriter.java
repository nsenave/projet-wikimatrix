package fr.univrennes1.istic.wikipediamatrix.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Logger;

import com.opencsv.CSVWriter;

import fr.univrennes1.istic.wikipediamatrix.beans.Tableau;

public class TableauCSVWriter {

	static Logger logger = Logger.getLogger("TableauCSVWriter");
	
	public String filePath;
	public Tableau tableau;
	
	public TableauCSVWriter(String filePath, Tableau tableau) {
		this.filePath = filePath;
		this.tableau = tableau;
	}
	
	public void writeCSVFromTableau() throws IOException {
		
		// Connexion avec le fichier
    	File file = new File(filePath); 
		FileWriter outputfile = new FileWriter(file); 
		CSVWriter writer = new CSVWriter(outputfile); 
		
		// Ecriture
		writer.writeNext(tableau.getHeader()); 
		for(String[] ligne : tableau.getLignes()) {
			writer.writeNext(ligne); 
		}
		
		// Fermeture de la connexion avec le fichier
		try {
        	writer.close(); 
    	}
    	catch(IOException e) {
    		logger.warning("IOException lors de la cloture de la connexion avec le fichier.");
    		//e.printStackTrace();
    	}
	}

}
