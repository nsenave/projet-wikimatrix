package fr.univrennes1.istic.wikipediamatrix.checkers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class TableauCSVChecker {
	
	static Logger logger = Logger.getLogger("TableauCSVWriter");
	
	private String filePath;
	private File file;
	private CSVParser parser;
	private Charset charset = Charset.defaultCharset();
	private CSVFormat format = CSVFormat.DEFAULT;
	
	public TableauCSVChecker(String filePath) throws IOException {
		this.filePath = filePath;
		file = new File(filePath);
		parser = CSVParser.parse(file, charset, format);
	}
	
	public long getLineNumber() {
		long lineNumber = parser.getCurrentLineNumber();
		if(lineNumber != parser.getRecordNumber()) {
			logger.warning("Unregular number of records per line, this CSV file may be not proprely formatted.");
		}
		return lineNumber;
	}
	
	public long getColumnNumber() {
		return parser.getHeaderMap().size();
	}
	
	@Override
	protected void finalize() throws Throwable {
	    // TODO Auto-generated method stub
	    // do some crazy stuff here
		logger.warning("CSVFile '"+filePath+"' connexion automatically closed");
		parser.close();
	    super.finalize();
	}
}
