package fr.univrennes1.istic.wikipediamatrix.htmlparsers;

import java.io.IOException;
import java.util.List;
import fr.univrennes1.istic.wikipediamatrix.beans.Tableau;

public interface Parser {
	
	List<Tableau> parseHtmlFromUrl(String url) throws IOException;

}
