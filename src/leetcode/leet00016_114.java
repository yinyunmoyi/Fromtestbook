package leetcode;

public class leet00016_114 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void flatten(TreeNode root) {
        change(root);
    }
    //这题用先序遍历遍历示例的结果是 1 2 3 4 5 6，但是如果这道题用先序遍历去解会有一个问题
	//就是6指向空这一段难以操作，因为null并不是只有最后才出现，而是在遍历过程中有很多个null
	//所以这里用了先序遍历的逆形式，就是后序遍历的先右再左，设置了一个字段为pre来记录要指向的点来操作
    public TreeNode pre = null;
	public void change(TreeNode root) {
		if(root == null) {
			return;
		}
		change(root.right);
		change(root.left);
		root.left = null;
		root.right = pre;
		pre = root;
	}
}

class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
