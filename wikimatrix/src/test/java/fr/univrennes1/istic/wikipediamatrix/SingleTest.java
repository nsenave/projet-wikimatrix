package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SingleTest {
	
	// String urlTest = "Comparison_of_Canon_EOS_digital_cameras";
	
	/*
	 * Let's try first with a single page
	 */
	@Test
	public void testExtractors() throws Exception {
		
		// URL
		String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
		String urlTest = "Comparison_of_Canon_EOS_digital_cameras";
		
		// directory where CSV files are exported (HTML extractor) 
		String outputDirHtml = "output" + File.separator + "html" + File.separator;
		assertTrue(new File(outputDirHtml).isDirectory());
		// directory where CSV files are exported (Wikitext extractor) 
		String outputDirWikitext = "output" + File.separator + "wikitext" + File.separator;
		assertTrue(new File(outputDirWikitext).isDirectory());

		String wurl = BASE_WIKIPEDIA_URL + urlTest; 
		System.out.println("Wikipedia url: " + wurl);
		// TODO: do something with the Wikipedia URL 
		// (ie extract relevant tables for correct URL, with the two extractors)
		
		// for exporting to CSV files, we will use mkCSVFileName
		// the *first* extracted table will be exported to a CSV file called 
		// "Comparison_of_Canon_EOS_digital_cameras-test-1.csv"
		String csvFileName = mkCSVFileName(urlTest, 1);
		System.out.println("CSV file name: " + csvFileName);
		// the *second* (if any) will be exported to a CSV file called
		// "Comparison_of_Canon_EOS_digital_cameras-test-2.csv"
		
		// TODO: the HTML extractor should save CSV files into output/HTML
		// see outputDirHtml 
		// NICO: todo dans une classe séparée
		// TODO: the Wikitext extractor should save CSV files into output/wikitext
		// see outputDirWikitext
		// NICO: (idem)


	}
	
	private String mkCSVFileName(String url, int n) {
		return url.trim() + "-test-" + n + ".csv";
	}
}
