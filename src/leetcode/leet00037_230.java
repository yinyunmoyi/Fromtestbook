package leetcode;

public class leet00037_230 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//如果在遍历树的过程中给每个节点加一个计数字段
	//这样之后再次删除或增加节点后，再次查找第k个节点时就只需要logN的复杂度了

	//下面是常规的递归解法，复杂度为ON
	public int kthSmallest(TreeNode root, int k) {
        return findKthSmallest(root, k).val;
    }
    
    int num = 0;
    
    private TreeNode findKthSmallest(TreeNode node, int aim){
        if(node == null){
            return null;
        }
        TreeNode left = findKthSmallest(node.left, aim);
        num++;
        if(num == aim){
            return node;
        }
        TreeNode right = findKthSmallest(node.right, aim);
        return left == null ? right : left;
    }
}
