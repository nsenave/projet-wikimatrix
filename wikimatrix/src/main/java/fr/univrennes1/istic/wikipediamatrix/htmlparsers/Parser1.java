package fr.univrennes1.istic.wikipediamatrix.htmlparsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import fr.univrennes1.istic.wikipediamatrix.beans.Tableau;

public class Parser1 implements Parser {
	
	static Logger logger = Logger.getLogger("Parser1");
	
	public List<Tableau> parseHtmlFromUrl(String url) throws IOException {
		
		// Recuperation du html de la page
		Document doc = Jsoup.connect(url).get();
		
		// Recuperation des tableaux de la page
        Elements tables = doc.select("table");
        logger.info("Number of tables : " + tables.size());
        
        // Initialisation du resultat
     	List<Tableau> res = new ArrayList<>();
        
        // Recuperation du contenu des tableaux
        for (int t=0; t<tables.size(); t++) {
        	
        	// Recuperation du tableau
        	Element table = tables.get(t);
        	
        	// Instanciation d'un objet Tableau
        	Tableau tableau = new Tableau();

        	// Recuperation des lignes du tableau
        	Elements lignes = table.select("tr");
        	//logger.info("Nombre de lignes : " + lignes.size());
        	
        	// On recupere l'en-tete du tableau
        	Element firstTr = lignes.get(0);
        	Elements cellulesHeader = firstTr.select("th");
        	//logger.info("Nombre de colonnes : " + cellulesHeader.size());
        	List<String> header = new ArrayList<>(); 
        	for(Element td : cellulesHeader) {
        		header.add(extractTextFromNode(td));
        	}
        	tableau.setHeader(convertStringListToArray(header));
        	
        	// On recupere le contenu du tableau
        	for(int i=1; i<lignes.size(); i++) {
        		Element tr = lignes.get(i);
        		Elements cellules = tr.select("td");
        		//logger.info("Nombre de cellules dans cette ligne : " + cellules.size());
        		List<String> ligne = new ArrayList<>();
        		for (Element td : cellules) {
        			ligne.add(extractTextFromNode(td));
                }
        		tableau.ajouterLigne(convertStringListToArray(ligne));
        	}
        	
        	// On ajoute le tableau aux resultats
        	res.add(tableau);
        	
        }
        
        return res;
        
    }
	
	private String[] convertStringListToArray(List<String> liste) {
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
	
	private String extractTextFromNode(Element element) {
		// Initialisation de la chaine resultat
		String res = "";
		// On recupere le contenu des TexNodes
		List<TextNode> textNodes = element.textNodes();
		for(TextNode textNode : textNodes) {
			res += textNode.text();
		}
		// On va chercher recursivement le contenu des fils
		Elements childs = element.children();
		for(Element child : childs) {
			//logger.info("---" + child.attr("style") + "---");
			//logger.info( String.valueOf(child.attr("style") != "display:none") );
			if(! child.attr("style").contentEquals("display:none") ) {
				res += extractTextFromNode(child, res);
			}
			else {
				//logger.info("style=display:none");
			}
		}
		return res.trim();
	}
	
	private String extractTextFromNode(Element element, String res) {
		if(element.childNodeSize() == 0) {
			return res;
		}
		else {
			List<TextNode> textNodes = element.textNodes();
			for(TextNode textNode : textNodes) {
				res += textNode.text();
			}
			Elements childs = element.children();
			for(Element child : childs) {
				return extractTextFromNode(child, res);
			}
		}
		return res;
	}

}
