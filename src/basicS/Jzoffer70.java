package basicS;

public class Jzoffer70 {

	/**
	 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
	 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
	 */
	public static void main(String[] args) {

	}

	//制造一个字段，中序遍历即可
	TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null || k == 0){
            return null;
        }
        index = k;
        KthNode(pRoot);
        return res;
    }
    
    TreeNode res;
    int index;
    void KthNode(TreeNode pRoot){
        
        if(pRoot == null){
            return;
        }
        KthNode(pRoot.left);
        if(index == 1){
            res = pRoot;
        }
        index--;
        KthNode(pRoot.right);
    }
}
