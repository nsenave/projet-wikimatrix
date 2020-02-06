package fr.univrennes1.istic.wikipediamatrix;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser1 {
	
	public String parseHtmlFromUrl(String url) throws IOException{  
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        System.out.println(Jsoup.parse(url));
        String title = doc.title();  
        System.out.println("title is: " + title);
        return "";
	}  
	
	public void parseHtmlFromUrl2(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
        Elements cellulesVrac = doc.select("td");
        Elements lignesVrac = doc.select("tr");
        Elements tableaux = doc.select("table");
        
        print("Nombre de tableaux : " + tableaux.size());
        print("Nombre de lignes : " + lignesVrac.size());
        print("Nombre de cellules : " + cellulesVrac.size());
        
        for (Element table : tableaux) {
        	String titre = retrieveTableTitle(table);
        	print("Titre du tableau : " + titre);
        	Elements lignes = doc.select("tr");
        	print("Nombre de lignes : " + lignes.size());
        	for(Element tr : lignes) {
        		Elements cellules = tr.select("td");
        		print("Nombre de cellules dans cette ligne : " + cellules.size());
        		for (Element td : cellules) {
        			// TODO: récupérer seulement le contenu
                    print(td.html());
                }
        	}
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
