package fr.univrennes1.istic.wikipediamatrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVWriter;

public class ParserNico {
	
	public String parseHtmlFromUrl(String url) throws IOException{  
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        System.out.println(Jsoup.parse(url));
        String title = doc.title();  
        System.out.println("title is: " + title);
        return "";
	}  
	
	public void parseHtmlFromUrl2(String url) throws IOException {
		
		//
		Document doc = Jsoup.connect(url).get();
		
		//
		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File("/home/nico/csv-test.csv"); 
		
		// create FileWriter object with file as parameter 
		FileWriter outputfile = new FileWriter(file); 

		// create CSVWriter object filewriter object as parameter 
		CSVWriter writer = new CSVWriter(outputfile); 
		

		// add data to csv 
		String[] data1 = { "Aman", "10", "620" }; 
		writer.writeNext(data1); 
		String[] data2 = { "Suraj", "10", "630" }; 
		writer.writeNext(data2); 

        Elements cellulesVrac = doc.select("td");
        Elements lignesVrac = doc.select("tr");
        Elements tableaux = doc.select("table");
        
        print("Nombre de tableaux : " + tableaux.size());
        print("Nombre de lignes : " + lignesVrac.size());
        print("Nombre de cellules : " + cellulesVrac.size());
        
        for (Element table : tableaux) {
        	String titre = retrieveTableTitle(table);
        	print("Titre du tableau : " + titre);
        	Elements lignes = table.select("tr");
        	print("Nombre de lignes : " + lignes.size());
        	
        	// On recupere l'en-tete du tableau
        	Element firstTr = lignes.get(0);
        	Elements cellulesHeader = firstTr.select("td");
        	print("Nombre de colonnes : " + cellulesHeader.size());
        	List<String> header = new ArrayList<>(); 
        	for(Element td : cellulesHeader) {
        		header.add(getCellContent(td));
        	}
        	writer.writeNext(convertStringListToArray(header)); 
        	
        	// On recupere le contenu du tableau
        	for(int i=1; i<lignes.size(); i++) { //Element tr : lignes
        		Element tr = lignes.get(i);
        		Elements cellules = tr.select("td");
        		print("Nombre de cellules dans cette ligne : " + cellules.size());
        		List<String> ligne = new ArrayList<>();
        		for (Element td : cellules) {
        			ligne.add(getCellContent(td));
                }
        		writer.writeNext(convertStringListToArray(ligne)); 
        	}
        	
        	// closing writer connection 
        	writer.close(); 
        }
        
    }
	
	public String retrieveTableTitle(Element tableau) {
		String res = "";
		Element noeudParent = tableau.parent();
		print(noeudParent.attr("class"));
		Elements noeudsFreres = noeudParent.children();
		String[] balisesTitre = {"h1","h2","h3","h4"};
		for(Element noeud : noeudsFreres) {
			String nomNoeud = noeud.nodeName();
			if( nomNoeud != null && stringContainsItemFromList(nomNoeud, balisesTitre) ){
				// ça fonctionne sur l'exemple, pas très robuste a priori :
				res = ((Element) noeud.childNode(0)).html();
			}
		}
		print("retrieveTableTitle : " + res);
		return res;
	}
	
	public String getCellContent(Element td) {
		return td.html();
	}
	
	public String[] convertStringListToArray(List<String> liste) {
		/*
		 * Peut-être qu'une librairie fait ça
		 */
		int listSize = liste.size();
		String[] res = new String[listSize];
		for(int i=0; i<listSize; i++) {
			res[i] = liste.get(i);
		}
		return res;
	}
	
	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
		/*
		 * https://stackoverflow.com/a/8995988
		 */
	    return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
	}

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /*
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    */

}
