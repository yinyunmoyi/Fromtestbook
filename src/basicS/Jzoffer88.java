package basicS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Jzoffer88 {

	/**
	 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
	 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
	 * (注意: 在返回值的list中，数组长度大的数组靠前)
	 */
	public static void main(String[] args) {

	}

	//用先序遍历的方式遍历整棵树，用一个双端队列进各结点的值，每次遇到根节点且累加值达到要求时
	//就将双端队列出队到空，导入list中并加入大的list，然后再把list的值倒回双端队列
	//为了保证一个分支不受另一个分支的影响，最后一定要把队列最后一个值导出继续递归
	//最后大list装配完成后再用工具类把它按照要求排序
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if(root == null){
            return list;
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        FindPath(root, 0, target, queue, list);
        Collections.sort(list, new Comparator<ArrayList<Integer>>(){
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2){
                return list2.size() - list1.size();
            }
        });
        return list;
    }
    
    public void FindPath(TreeNode root,int nowSum, int target, LinkedList<Integer> queue, ArrayList<ArrayList<Integer>> list){
        queue.offerFirst(root.val);
        nowSum += root.val;
        if(root.left == null && root.right == null && nowSum == target){
            ArrayList<Integer> smlist = new ArrayList<Integer>();
            while(!queue.isEmpty()){
                smlist.add(queue.pollLast());
            }
            list.add(smlist);
            for(Integer i : smlist){
                queue.offerFirst(i);
            }
        }else{
            if(root.left != null){
                FindPath(root.left, nowSum, target, queue, list);
            }
            if(root.right != null){
                 FindPath(root.right, nowSum, target, queue, list);
            }
        }
        queue.pollFirst();
    }
}
