package basicS;

public class Jzoffer93 {

	//操作给定的二叉树，将其变换为源二叉树的镜像。
	
	//在先序遍历中交换左右子树即可
	public void Mirror(TreeNode root) {
        if(root != null){
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = right;
            root.right = left;
            Mirror(root.left);
            Mirror(root.right);
        }
    }
}
