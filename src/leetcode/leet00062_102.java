package leetcode;

import java.util.*;

public class leet00062_102 {

	/*
	 * 层序遍历非递归版，用一个队列来实现，难点在于如何知道哪些结点在同一层
	 * 用循环巧妙的使队列中的元素分代，层序遍历本身就是bfs的实现
	 */
	public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> smlist = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--){
                TreeNode node = queue.poll();
                smlist.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            list.add(smlist);
        }
        return list;
    }
	
	/*
	 * 层序遍历的递归版，基于先序遍历添加元素
	 * 因为先序遍历是一定在同一层从左到右遍历的
	 * 如果对应位置小的list不存在，就把元素add进去
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        fill(root, 0, list);
        return list;
    }
    
    private void fill(TreeNode node, int level, List<List<Integer>> list){
        if(node == null){
            return;
        }
        if(level + 1 > list.size()){
            ((ArrayList)list).add(new ArrayList<>());
        }
        list.get(level).add(node.val);
        fill(node.left, level + 1, list);
        fill(node.right, level + 1, list);
    }
}
