package basicS;

public class Jzoffer04 {

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
	 * 则重建二叉树并返回。
	 */
	public static void main(String[] args) {

	}

	//Definition for binary tree
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { 
	    	  val = x; 
	      }
	}
	
	//270 ms	22440K
	//传入递归方法中去
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    
	
    public TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn){
        
    	//如果传入的数组最前下标大于最后下标就返回null
    	//说明树已经达到最末端
    	if(startPre > endPre || startIn > endIn){
            return null;
        }
    	//将pre数组的第一个值作为根节点，递归传入后每个被分割的pre数组的第一个值都是根节点
        TreeNode root = new TreeNode(pre[startPre]);
        
        //对比两个数组，如果中序遍历哪个值根前序遍历的第一个值相同，那么就以此为分界分割两个数组
        //因为前序遍历的第一个值总是根节点，在中序遍历中根节点的左右两端一定是左右子树
        for(int i = startIn; i <= endIn; i++){
            if(in[i] == pre[startPre]){
            	//左右子树的构建，考虑分割两个数组
                root.left = reConstructBinaryTree(pre, startPre+1, startPre - startIn + i, in, startIn, i-1);
                root.right = reConstructBinaryTree(pre, startPre - startIn + i + 1, endPre, in, i+1, endIn);
                break;
            }
        }
        
        //最后返回根节点
        return root;
        
    }
    
    //给出后序遍历和中序遍历的结果建立二叉树
    //例如输入后序遍历序列{4,7,2,5,8,6,3,1}和中序遍历序列{4,7,2,1,5,3,8,6}，
    public TreeNode a_reConstructBinaryTree(int [] pos,int [] in) {
        return a_reConstructBinaryTree(pos, 0, pos.length - 1, in, 0, in.length - 1);
    }
    
    public TreeNode a_reConstructBinaryTree(int[] pos, int startPos, int endPos, int[] in, int startIn, int endIn){
    	
    	if(startPos > endPos || startIn > endIn){
            return null;
        }
    	
    	TreeNode root = new TreeNode(pos[endPos]);
    	
    	for(int i = startIn; i <= endIn; i++){
    		if(in[i] == pos[endPos]){
    			root.left = a_reConstructBinaryTree(pos, startPos, startPos-startIn+i-1, in, startIn, i-1);
    			root.right = a_reConstructBinaryTree(pos, startPos-startIn+i, endPos-1, in, i+1, endIn);
    		}
    	}
    	return root;
    }
}
