package basicS;

public class Jzoffer94 {

	//输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
	
	//先遍历树A，找到相同的根节点，然后调用方法isSame判断是否相同
	//如果方法isSame结果不相同或者本节点的值根本不相等，就转而寻找左子树和右子树
	//这里有一个逻辑的或关系叠加，而且这种或关系根据前面的结果进行调整，用连续的if语句修改同一个boolean值是最佳选择
	//而isSame方法的实现是，首先判断root2是不是空，如果是空返回true，此时即使1不是空也还有相等的可能
	//如果root1是空说明root1遍历到了末端，此时没有可能相同了返回false
	//如果两个都不为空，必须值相等且递归左右子树都返回true才算相同
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1 == null || root2 == null){
            return false;
        }
        boolean key = false;
        if(root1.val == root2.val){
            key = isSame(root1, root2);
        }
        if(!key){
            key = HasSubtree(root1.left, root2);
        }
        if(!key){
            key = HasSubtree(root1.right, root2);
        }
        return key;
    }
    
    public boolean isSame(TreeNode root1,TreeNode root2){
        if(root2 == null){
            return true;
        }
        if(root1 == null){
            return false;
        }
        
        return (root1.val == root2.val) && isSame(root1.left, root2.left)
                && isSame(root1.right, root2.right);
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
