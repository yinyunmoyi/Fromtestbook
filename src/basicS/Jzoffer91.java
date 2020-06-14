package basicS;

import java.util.ArrayList;
import java.util.Stack;

public class Jzoffer91 {

	//请按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
	//第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
	
	//用了两个栈来实现这个功能，因为在遍历上一层时如果在同一个栈中添加信息
	//同一层中的其他值还没有打印就先处理栈顶的值了，所以每一层要用不同的栈
	//所以建立了一个栈的数组，每次now栈为空时就互换now和next
	//交替使用两个栈，不同的栈在加入左右结点的先后顺序上有差别这是为了保证之字形打印
	 public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
	        ArrayList<ArrayList<Integer> > list = new ArrayList<ArrayList<Integer> >();
	        if(pRoot == null){
	            return list;
	        }
	        Stack[] stack = new Stack[2];
	        stack[0] = new Stack<>();
	        stack[1] = new Stack<>();
	        int now = 0, next = 1;
	        stack[now].push(pRoot);
	        ArrayList<Integer> smList = new ArrayList<Integer>();
	        while(!stack[0].isEmpty() || !stack[1].isEmpty()){
	            TreeNode nowNode = (TreeNode)stack[now].pop();
	            smList.add(nowNode.val);
	            if(now == 0){
	                if(nowNode.left != null){
	                    stack[next].add(nowNode.left);
	                }
	                if(nowNode.right != null){
	                    stack[next].add(nowNode.right);
	                }
	            }else{
	                if(nowNode.right != null){
	                    stack[next].add(nowNode.right);
	                }
	                if(nowNode.left != null){
	                    stack[next].add(nowNode.left);
	                }
	            }
	            if(stack[now].isEmpty()){
	                list.add(smList);
	                smList = new ArrayList<Integer>();
	                int m = now;
	                now = next;
	                next = m;
	            }
	        }
	        return list;
	    }
}
