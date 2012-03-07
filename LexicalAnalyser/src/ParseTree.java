import java.util.Iterator;


public class ParseTree {
	public TreeNode root;
	private TreeNode temp;
	private int charCount=3;
	boolean end = false;
	int hasNoChild=0;
	
	public ParseTree(TreeNode root) {
	  this.root=root;
	}
	public void showTree ()
	{
		
		temp=root;
		System.out.println(temp.nodeName);
		
		while (hasChild(temp) && !end)
		{
			printChildren(temp);
		}
		System.out.println();
	}
	private boolean hasChild(TreeNode node)
	{
		if (node.childList.isEmpty())
			return false;
		else
			return true;
	}
	private void printChildren(TreeNode node)
	{
		
		Iterator<TreeNode> itr = node.childList.iterator();
		
		for (int i=0; i<charCount; i++)
			System.out.print(" ");
		
		hasNoChild=0;
		while (itr.hasNext())
		{
			TreeNode currentNode = itr.next();
			
			if (temp.equals(node))
				charCount+=currentNode.nodeName.length();
			
			System.out.print(currentNode.nodeName+" ");
			if (hasChild(currentNode))
			{
				temp=currentNode;
				

			}else
				hasNoChild++;
				
		}
		
		if (hasNoChild == node.childList.size())
			end=true;
		
		System.out.println();
		
	}
	
}
