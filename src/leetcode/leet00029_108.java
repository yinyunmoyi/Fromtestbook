package leetcode;

public class leet00029_108 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//生成平衡二叉树的关键在于每次都在已经排序的数组中选出中间的数字然后生成node
    //生成数字之后排除已经生成的数字，分成左右继续向下递归
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null ||nums.length == 0) {
        	return null;
        }
        TreeNode head = sortedArrayToBST(nums, 0, nums.length - 1);
        return head;
    }
    
    private TreeNode sortedArrayToBST(int[] arr, int start, int end) {
		if(start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.left = sortedArrayToBST(arr, start, mid - 1);
		node.right = sortedArrayToBST(arr, mid + 1, end);
		return node;
	}
}
