package basicS;
public class kao {
	public static void main(String[] args){
		Node head = MyArrays.ArrayToNode(new int[]{1,2,3,3,4,4,5});
		MyArrays.printChain(head);
		System.out.println();
		Node node = deleteDuplication(head);
		MyArrays.printChain(node);
		char[] arr = {'a','b','b'};
		System.out.println("abc".compareTo("a"));;
	}
	
    public static Node deleteDuplication(Node pHead)
    {
        if(pHead == null){
            return null;
        }
        Node currentNode = pHead;
        while(currentNode.next!= null){
            if(currentNode.next.val > currentNode.val){
                currentNode = currentNode.next;
            }else if(currentNode.next.val == currentNode.val){
                Node keyNode = currentNode.next;
                while(keyNode.next != null && keyNode.next.val == currentNode.val){
                    keyNode = keyNode.next;
                }
                if(keyNode.next == null){
                    currentNode.next = null;
                    return pHead;
                }else{
                    Node nextNode = keyNode.next;
                    keyNode.next = null;
                    currentNode.next = nextNode;
                    currentNode = currentNode.next;
                }
            }
        }
        return pHead;
    }
}


