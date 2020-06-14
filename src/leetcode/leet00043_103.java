package leetcode;
import java.util.*;

public class leet00043_103 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	//在前序遍历中往list中放值
	//根据level的不同放入list的方式也不同，一个是往头放一个是往尾放
	//如果还没有对应位置的list就新建
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        fillList(root, list, 0);
        return list;
    }
    
    private void fillList(TreeNode node, List<List<Integer>> list, int level){
        if(node == null){
            return;
        }
        if(list.size() == level){
            LinkedList<Integer> smlist = new LinkedList<>();
            list.add(smlist);
        }
        List<Integer> smlist = list.get(level);
        if(level % 2 == 0){
            smlist.add(node.val);
        }else{
            smlist.add(0, node.val);
        }
        fillList(node.left, list, level + 1);
        fillList(node.right, list, level + 1);
    }
    
    
  //层序遍历的修改，首先，左右放置的位置和出队的顺序是不能改变的
  //因为一旦修改就有可能出现第三层：4 5 6 7
  //入队后就变成7 6 5 4的现象
  //修改的是list添加元素的方式，可以吧小list设置为链表，然后add的时候从0开始
  //这样就解决了从右向左时的放入元素的顺序
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        boolean flag = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> slist = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(flag){
                    slist.add(node.val);
                }else{
                    slist.add(0, node.val);
                }

                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            flag = !flag;
            list.add(slist);
        }
        return list;
    }

}
