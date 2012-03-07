import java.util.ArrayList;


public class TreeNode {

	public String nodeName;
	public ArrayList<TreeNode> childList = new ArrayList<TreeNode>();
	
	public TreeNode(String nodeName){
		this.nodeName = nodeName;
	}
	public void addChild(TreeNode child)
	{
		childList.add(child);
	}
}
