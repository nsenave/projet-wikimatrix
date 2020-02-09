package fr.univrennes1.istic.wikipediamatrix;
import java.io.IOException;

import fr.univrennes1.istic.wikipediamatrix.ParserNico;

/**
 * Hello world!
 *
 */
public class Nico 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Zizi" );
        
        //temp
        String urlTest = "https://en.wikipedia.org/wiki/Comparison_of_Canon_EOS_digital_cameras";
        new ParserNico().parseHtmlFromUrl2(urlTest);
    }
}
