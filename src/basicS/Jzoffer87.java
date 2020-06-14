package basicS;

import java.util.ArrayList;

public class Jzoffer87 {

	//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
	//要求不能创建任何新的结点，只能调整树中结点指针的指向。
	
	//从左右结点拿到两个值分别是左子树的最大结点和右子树的最小结点
	//然后只需要将本节点和这两个结点建立相互连接即可
	public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list1 = (ArrayList<Integer>)list.clone();
        return change(pRootOfTree).min;
    }
    
    public stu change(TreeNode node){
        if(node.left == null && node.right == null){
            return new stu(node, node);
        }
        TreeNode max = node;
        TreeNode min = node;
        if(node.left != null){
            stu leftStu = change(node.left);
            min = leftStu.min;
            node.left = leftStu.max;
            leftStu.max.right = node;
        }
        if(node.right != null){
            stu rightStu = change(node.right);
            max = rightStu.max;
            node.right = rightStu.min;
            rightStu.min.left = node;
        }
        return new stu(min, max);
    }
}

class stu{
    TreeNode min;
    TreeNode max;
    public stu(TreeNode min, TreeNode max){
        this.min = min;
        this.max = max;
    }
}
