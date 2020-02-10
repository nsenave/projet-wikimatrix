package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import fr.univrennes1.istic.wikipediamatrix.beans.Tableau;
import fr.univrennes1.istic.wikipediamatrix.htmlparsers.Parser;
import fr.univrennes1.istic.wikipediamatrix.htmlparsers.Parser1;
import fr.univrennes1.istic.wikipediamatrix.htmlparsers.Parser2;
import fr.univrennes1.istic.wikipediamatrix.writers.TableauCSVWriter;

import java.util.logging.Logger;

public class SingleTest {
	
	static Logger logger = Logger.getLogger("SingleTest");
	
	static String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
	// URL
	static String urlTest = "Comparison_of_Canon_EOS_digital_cameras";
	
	/*
	 * Let's try first with a single page
	 */
	@Test
	public void testExtractors() throws Exception {
		
		// for exporting to CSV files, we will use mkCSVFileName
		// the *first* extracted table will be exported to a CSV file called 
		// "Comparison_of_Canon_EOS_digital_cameras-test-1.csv"
		// the *second* (if any) will be exported to a CSV file called
		// "Comparison_of_Canon_EOS_digital_cameras-test-2.csv"
		
		// the HTML extractors should save CSV files into output folders
		
		// Parser number 1
		logger.info("FIRST EXTRACTOR");
		Parser1 parser1 = new Parser1();
		writeCSVs(parser1, urlTest, mkOutputDir(1));
		
		// Parser number 2
		logger.info("SECOND EXTRACTOR");
		Parser2 parser2 = new Parser2();
		writeCSVs(parser2, urlTest, mkOutputDir(2));

	}
	
	private String mkOutputDir(int i) {
		// directory where CSV files are exported (HTML extractor) 
		String outputDirHtml = "output" + i + File.separator;
		assertTrue(new File(outputDirHtml).isDirectory());
		return outputDirHtml;
		
	}
	
	private String mwWebURL(String url) {
		return BASE_WIKIPEDIA_URL + url;
	}
	
	private String mkCSVFileName(String url, int n) {
		return url.trim() + "-test-" + n + ".csv";
	}
	
	private void writeCSVs(Parser parser, String url, String outputDirHtml) throws IOException {
		
		String wurl = mwWebURL(url);
		logger.info("Wikipedia url: " + wurl);
		
		List<Tableau> tableaux = parser.parseHtmlFromUrl(wurl);
		
		for(int t=0; t<tableaux.size(); t++) {
			
        	Tableau tableau = tableaux.get(t);
        	
        	String csvFileName = mkCSVFileName(url, t);
        	logger.info("CSV file name: " + csvFileName);
        	
        	TableauCSVWriter writer = new TableauCSVWriter(outputDirHtml + csvFileName, tableau);
        	writer.writeCSVFromTableau();
        }
	}
}
