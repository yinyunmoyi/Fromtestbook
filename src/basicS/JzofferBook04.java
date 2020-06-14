package basicS;

public class JzofferBook04 {

	/**
	 * 给链表头结点和任意一个结点的索引，要求在O1的时间内删掉这个结点
	 */
	public static void main(String[] args) {
	}
	
	//把本节点的值改成下个节点的值，然后跳过下个节点
	//如果要删的节点在链表末尾只能遍历到末尾再把指针调整正确
	public static void delNode(Node head, Node node){
		if(head == node && node.next == null){
			head = null;
		}else if(node.next != null){
			int val = node.next.val;
			node.val = val;
			Node nextNode = node.next.next;
			node.next.next = null;
			node.next = nextNode;
		}else{
			Node currentNode = head;
			while(currentNode.next != node){
				currentNode = currentNode.next;
			}
			currentNode.next = null;
		}
	}

}
