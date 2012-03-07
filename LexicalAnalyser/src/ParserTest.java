/*Umutcan Þimþek | Buket Üþenmez
 * 05-09-28 | 05-09-86
 *Programming Languages, Project - 2
 * Lexical Analyzer Application
 * Wed, 04/27, 2011
 * */


public class ParserTest {


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		LexicalAnalyzer analyzer = new LexicalAnalyzer();
		Parser parser = new Parser(analyzer.lexList);
		//analyzer.yazdir();
		parser.startParsing();
		
		
		
		
	}
	
	
}
