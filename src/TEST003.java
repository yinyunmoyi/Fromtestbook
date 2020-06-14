import java.util.HashMap;

public class TEST003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		hasAimNode(root, p, q);
		return res;
    }
	
	TreeNode res = null;
	public boolean hasAimNode(TreeNode node, TreeNode p, TreeNode q) {
		if(node == null) {
			return false;
		}
		boolean left = hasAimNode(node.left, p, q);
		boolean right = hasAimNode(node.right, p, q);
		if(left && right) {
			res = node;
			return false;
		}else if((left || right) && (node == p || node == q)) {
			res = node;
			return false;
		}else {
			return (left || right) || node == p || node == q;
		}
		
	}
}

class TreeNode {
	      int val;
	     TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }


