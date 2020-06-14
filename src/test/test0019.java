package test;

import basicS.BinaryNode;

public class test0019 {

	/**
	 * 在二叉树中找到一个结点的后继结点（后继结点就是在中序遍历中某个结点后面的结点就是该节点的后继结点）
	 * 假设该node有一个指向父节点的指针，根节点该指针指向null  (20min)
	 */
	public static void main(String[] args) {

	}

	public static ParentNode nextNode(ParentNode node){
		if(node == null){
			return null;
		}
		ParentNode currentNode;
		if(node.right != null){
			currentNode = node.right;
			while(currentNode.left != null){
				currentNode = currentNode.left;
			}
			return currentNode;
		}else{
			currentNode = node;
			ParentNode parent = node.parent;
			while(parent != null && parent.left != currentNode){
				currentNode = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
}

class ParentNode{
	ParentNode left;
	ParentNode right;
	ParentNode parent;
	int val;
	ParentNode(int val){
		this.val = val;
	}
}
