package leetcode;
import java.util.*;

public class leet00049_116 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//初始方案，层序遍历的改进
    //层序遍历的时候记录上一个节点的值，然后将next连接到本节点
    //在适当的时机将next连接到null
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        int times = 0, k = 0;
        Node before = root;
        if(root.left != null){
            queue.offer(root.left);
            queue.offer(root.right);
        }
        while(!queue.isEmpty()){
            Node node = queue.poll();
            k++;
            if(k == (int)Math.pow(2, times)){
                before.next = null;
                times++;
                k = 0;
            }else{
                before.next = node;
            }
            before = node;
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }
    
  //采用了一种特殊的遍历方式，这种遍历是基于next指针的遍历，在遍历到下一层之前
    //将下一次层的next指针布置完毕，然后从最左边开始进行遍历
    //相当于之后的遍历都利用了之前的成果
    //每个节点不仅可以连接自己左到右的next，还可以连接自己右到自己next左的指针
    public Node connect1(Node root) {
        if(root == null){
            return root;
        }
        Node node = root;
        while(node != null){
            Node levelNode = node;
            while(levelNode != null){
                if(levelNode.left != null){
                    levelNode.left.next = levelNode.right;
                }
                if(levelNode.right != null && levelNode.next != null){
                    levelNode.right.next = levelNode.next.left;
                }
                levelNode = levelNode.next;
            }
            node = node.left;
        }
        return root;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
