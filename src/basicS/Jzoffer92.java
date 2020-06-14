package basicS;

public class Jzoffer92 {

	/**
	 * 请，用来判断一颗二叉树是不是对称的。
	 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
	 */
	public static void main(String[] args) {

	}
	
	//两棵树作为参数，分别按照相反的方向遍历比较值即可
	boolean isSymmetrical(TreeNode pRoot)
    {
        return isSymmetrical(pRoot, pRoot);
    }
    
    boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2){
        if(pRoot1 != null && pRoot2 != null){
            return (pRoot1.val == pRoot2.val) && isSymmetrical(pRoot1.left, pRoot2.right) && 
                isSymmetrical(pRoot1.right, pRoot2.left);
        }else if(pRoot1 == null && pRoot2 == null){
            return true;
        }else{
            return false;
        }
    }

}
