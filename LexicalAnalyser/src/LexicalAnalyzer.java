import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LexicalAnalyzer {
	public ArrayList<Terminal> lexList = new ArrayList<Terminal>();
	public int row = 1;
	public int col = 0;
	public LexicalAnalyzer() throws Exception{
	
		char currentChar;
		BufferedReader in = new BufferedReader(new FileReader("D:\\source.rng"));
		
		do 
		{
			currentChar =(char)in.read();
			col++;
			
			if (Character.isLetter(currentChar))
				variable(in,currentChar);
			else if (Character.isDigit(currentChar))
				intConstant(in,currentChar);
			else if (currentChar == '<' || currentChar == '>')
				compOperator(in,currentChar);
			else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
				arithOperator(in,currentChar);
			else if (currentChar == '(')
				leftParanthesis(in,currentChar);
			else if (currentChar == ')')
				rightParanthesis(in,currentChar);
			else if (currentChar == ';')
				endOfLine(in,currentChar);
			else if (currentChar == '{')
				comment(in,currentChar);
			else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t'))
				throw new Exception("Invalid token." + "'"+currentChar+"'");
				
		

		}while(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
	}
	private void comment(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub

		do
		{
			if (!in.ready())
				throw new Exception("Non-terminated comment.");
				
			currentChar=(char)in.read();
			col++;
			if (currentChar == '{')
				throw new Exception("Invalid comment token.");
			
		}while(currentChar != '}');
		
		
		do
		{
			currentChar=(char)in.read();
			col++;
			
			colRowControl(currentChar);
			
		}while(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}
	
	private void rightParanthesis(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub
		
		String output = "";
		Terminal terminal = new Terminal(output, "rightParanthesis", row, col,  0);
		
		
		do
		{
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				output=output+currentChar;
			
			currentChar = (char)in.read();
			col++;

			colRowControl(currentChar);
			
		}while (currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
		
		terminal.terminal = output;
		lexList.add(terminal);
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}

	private void leftParanthesis(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub
		
		String output = "";
		Terminal terminal = new Terminal(output, "leftParanthesis", row, col,  0);
		
		do
		{
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				output=output+currentChar;
			
			currentChar = (char)in.read();
			col++;
			
			colRowControl(currentChar);

		}while (currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
		terminal.terminal = output;
		lexList.add(terminal);
		
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar) || currentChar == '-')
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}
	
	private void endOfLine(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub
		Terminal terminal = new Terminal(";", "endOfLine", row, col,  0);
		
		do
		{
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				lexList.add(terminal);
		
			currentChar = (char)in.read();
			col++;
			
			colRowControl(currentChar);

		}while(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
	
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}

	private void arithOperator(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub
		
		String output = "";
		Terminal terminal = new Terminal(output, "arithOperator", row, col,  0);
		do
		{
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				output=output+currentChar;
			
			currentChar = (char)in.read();
			col++;
			
			colRowControl(currentChar);

		}while (currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
		terminal.terminal = output;
		lexList.add(terminal);
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar =='\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
			
	}

	private void intConstant(BufferedReader in, char currentChar) throws Exception {
		String output = "";
		Terminal terminal = new Terminal(output, "intConstant", row, col,  0);
		
		do
		{
		
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				output=output+currentChar;
			
			currentChar=(char)in.read();
			col++;
		}while (Character.isDigit(currentChar));
		
		while (currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t')
		{			
			colRowControl(currentChar);
			
			currentChar = (char)in.read();
			col++;
			
		}
		
		terminal.terminal = output;
		
		lexList.add(terminal);
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}
	
	private void compOperator(BufferedReader in, char currentChar) throws Exception {
		// TODO Auto-generated method stub
		String output = "";
		Terminal terminal = new Terminal(output, "compOperator", row, col,  0);
		
			do
			{
				if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
					output = output+currentChar;
			
				currentChar = (char)in.read();
				col++;
				
				if (currentChar == '=' && (!output.equals("<=") && !output.equals(">=")))
				{	
			
					if (currentChar != ' ')
						output = output+currentChar;
			
					currentChar=(char)in.read();
					col++;
				}
				colRowControl(currentChar);
			}while(currentChar == ' '  || currentChar == '\r' || currentChar == '\n' || currentChar == '\t');
		
		
		terminal.terminal = output;
		lexList.add(terminal);
		
		
		if (Character.isLetter(currentChar))
			variable(in,currentChar);
		else if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}
	
	private void variable(BufferedReader in, char currentChar) throws Exception {
		
		String output = "";
		Terminal terminal = new Terminal(output, "variable", row, col,  0);
		

		while (Character.isLetter(currentChar))
		{
		
			if (currentChar != ' ' && currentChar != '\r' && currentChar != '\n' && currentChar != '\t')
				output=output+currentChar;
			
			currentChar=(char)in.read();
			col++;
		}

			
		while(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t')
		{
			
			colRowControl(currentChar);
			currentChar = (char)in.read();
			col++;
		}
		
		terminal.terminal = output;
		lexList.add(terminal);
		

		if(Character.isLetter(currentChar))
			variable(in, currentChar);
	    if (Character.isDigit(currentChar))
			intConstant(in,currentChar);
		else if (currentChar == '<' || currentChar == '>')
			compOperator(in,currentChar);
		else if (currentChar == '+' || currentChar == '*' || currentChar == '-')
			arithOperator(in,currentChar);
		else if (currentChar == '(')
			leftParanthesis(in,currentChar);
		else if (currentChar == ')')
			rightParanthesis(in,currentChar);
		else if (currentChar == ';')
			endOfLine(in,currentChar);
		else if (currentChar == '{')
			comment(in,currentChar);
		else if (!(currentChar == ' ' || currentChar == '\r' || currentChar == '\n' || currentChar == '\t') && in.ready())
			throw new Exception("Invalid token." + "'"+currentChar+"'");
		else if (!in.ready())
			return;
	}
	public void yazdir ()
	{
		Iterator<Terminal> itr = lexList.iterator();
		
		while (itr.hasNext())
		{
			Terminal nextLex = itr.next();
			
			System.out.print(nextLex.type+"("+nextLex.terminal+"), ");
			//System.out.print("Row: "+nextLex.row+" Column: "+nextLex.col);
		}
	}
	private void colRowControl(char currentChar)
	{
		if (currentChar == '\n')
		{
			row++;
			col=0;
		}
		else if (currentChar == '\t')
		{
			col+=7;
		}
		else if (currentChar == '\r')
			col--;
		
	}
	}
