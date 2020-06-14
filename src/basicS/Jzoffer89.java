package basicS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Jzoffer89 {

	//从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
	
	//用两个变量去指示什么时候换行这个过程
	//nextLinesum、thisLineNum代表下一行的元素数和这行的元素数
	//每打印一个值，这一行的元素数都减一，每入队一次下一行的元素数就加一，
	//如果thisLineNum变为0说明本行结束，放入list，并让list指向新的list
	ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > list = new ArrayList<ArrayList<Integer> >();
        if(pRoot == null){
			return list;
		}
		int nextLinesum = 0, thisLineNum = 1;
        ArrayList<Integer> smList = new ArrayList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(pRoot);
		while(!queue.isEmpty()){
			TreeNode currentNode = queue.poll();
			thisLineNum--;
			smList.add(currentNode.val);
			if(currentNode.left != null){
				queue.offer(currentNode.left);
				nextLinesum++;
			}
			if(currentNode.right != null){
				queue.offer(currentNode.right);
				nextLinesum++;
			}
			if(thisLineNum == 0){
				thisLineNum = nextLinesum;
				nextLinesum = 0;
				list.add(smList);
                smList = new ArrayList<Integer>();
			}
		}
         return list;
    }
}
