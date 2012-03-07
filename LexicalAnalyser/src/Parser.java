import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Stack;


public class Parser {
	
	private ArrayList<Terminal> lexList;
	ListIterator<Terminal> lexIterator;
	Terminal currentLex;
	private Stack<Character> pStack = new Stack<Character>();
	ParseTree tree;
	
	public Parser (ArrayList<Terminal> lexList)
	{
		
		this.lexList = lexList;
		this.lexIterator =  this.lexList.listIterator();
		
		
	}
	public void startParsing() throws Exception
	{
		do
		{
			ranger_line();
			//System.out.println("Code line parsed successfully.");
			tree.showTree();
		}while (lexIterator.hasNext());
	}

	private void ranger_line() throws Exception {
		
		
		tree = new ParseTree(new TreeNode("ranger_line"));
		
		if (!lexIterator.hasNext())
			return;
		
		
		if (!match(currentLex=lexIterator.next(),"variable"))
			throw new Exception("Variable expected. Line: "+currentLex.row+" Col: "+currentLex.col);
		
		tree.root.addChild(new TreeNode(currentLex.terminal));
		
		
		if (!lexIterator.hasNext() || !match(currentLex=lexIterator.next(),"compOperator"))
			throw new Exception("Comparison operator expected instead of "+currentLex.terminal+" Line: "+currentLex.row+" Col: "+currentLex.col);
		
		tree.root.addChild(new TreeNode(currentLex.terminal));
		
		TreeNode newChild;
		tree.root.addChild(newChild=new TreeNode("expr"));
		expr(newChild);
		
		if (!lexIterator.hasNext() || !match(currentLex=lexIterator.next(),"endOfLine"))
		{	
			if (match(currentLex, "rightParanthesis"))
			{
				if (pStack.isEmpty() || pStack.pop() != '(')
					throw new Exception("Paranthesis mismatch. Line: "+currentLex.row+" Col: "+currentLex.col);
				else
					throw new Exception("End of line character is missed. Line: "+currentLex.row);
		
			}else if (lexIterator.hasNext() && !match(currentLex, "arithOperator"))
				throw new Exception("Arithmetic operator expected. Line: "+currentLex.row+" Col: "+currentLex.col);
			else
				throw new Exception("End of line character is missed. Line: "+currentLex.row);
		}
		
		tree.root.addChild(new TreeNode(";"));
	}

	private void expr(TreeNode node) throws Exception {
		
		
		if (!lexIterator.hasNext())
			return;
		
		//Terminal nextLex = lexIterator.next();
		currentLex = lexIterator.next();
		if (match(currentLex,"leftParanthesis"))
		{
			node.addChild(new TreeNode("("));
			pStack.push('(');
			TreeNode newNode;
			node.addChild(newNode=new TreeNode("expr"));
			expr(newNode);
			
			if (!match(currentLex=lexIterator.next(),"rightParanthesis"))
				throw new Exception("Right paranthesis expected  instead of "+currentLex.terminal+" Line: "+currentLex.row+" Col: "+currentLex.col);
			else
			{
				if (pStack.isEmpty() || pStack.pop() != '(')
					throw new Exception("Paranthesis mismatch. Line: "+currentLex.row+" Col: "+currentLex.col);
				
				node.addChild(new TreeNode(")"));
			}
			TreeNode newNodee;
			node.addChild(newNodee=new TreeNode("exprRest"));
			exprRest(newNodee);
		}
		else if (match(currentLex, "variable"))
		{
			node.addChild(new TreeNode(currentLex.terminal));
			
			TreeNode newNodee;
			node.addChild(newNodee=new TreeNode("exprRest"));
			exprRest(newNodee);
		}
		else if (match(currentLex, "intConstant"))
		{
			node.addChild(new TreeNode(currentLex.terminal));
			
			TreeNode newNodee;
			node.addChild(newNodee=new TreeNode("exprRest"));
			exprRest(newNodee);
		}
		else
			throw new Exception("Expecting '(', integer or variable instead of "+currentLex.terminal+" Line: "+currentLex.row+" Col: "+currentLex.col);
	}

	private void exprRest(TreeNode node) throws Exception {
		
		if (!lexIterator.hasNext())
			return;
		
		currentLex = lexIterator.next();
		
		if (match(currentLex, "arithOperator"))
		{
			node.addChild(new TreeNode(currentLex.terminal));
			TreeNode newNode;
			node.addChild(newNode=new TreeNode("expr"));
			expr(newNode);
			TreeNode newNodee;
			node.addChild(newNodee = new TreeNode("exprRest"));
			exprRest(newNodee);
		}
		else
		{
			lexIterator.previous();
			return;
		}
		
	}

	private boolean match(Terminal nextLex, String lexType) {
		
		
		if (nextLex.type.equals(lexType))
			return true;
		else
		{
			//System.out.println("hata");
			return false;
		}
	}
	
	
	
}
