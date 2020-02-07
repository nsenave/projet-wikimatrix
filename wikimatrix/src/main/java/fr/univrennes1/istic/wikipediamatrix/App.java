package fr.univrennes1.istic.wikipediamatrix;
import java.io.IOException;

import fr.univrennes1.istic.wikipediamatrix.Parser1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Zizi" );
        
        //temp
        String urlTest = "https://en.wikipedia.org/wiki/Comparison_of_Canon_EOS_digital_cameras";
        Parser1 monParser = new Parser1();
	    monParser.parseHtmlFromUrl2(urlTest);
    }
}
