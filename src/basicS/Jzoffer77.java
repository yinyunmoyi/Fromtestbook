package basicS;

public class Jzoffer77 {

	/**
	 * 输入两个链表，找出它们的第一个公共结点。
	 */
	public static void main(String[] args) {

	}

	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
       int length1 = 0;
       ListNode currentNode = pHead1;
       while(currentNode != null){
           currentNode = currentNode.next;
           length1++;
       }
       int length2 = 0;
       currentNode = pHead2;
       while(currentNode != null){
           currentNode = currentNode.next;
           length2++;
       }
       currentNode = length2 > length1 ? pHead2 : pHead1;
       for(int i = 0; i < Math.abs(length2 - length1); i++){
           currentNode = currentNode.next;
       }
       ListNode slowNode = length2 > length1 ? pHead1 : pHead2;
       while(slowNode != null && currentNode != null && slowNode != currentNode){
           slowNode = slowNode.next;
           currentNode = currentNode.next;
       }
       return (slowNode == null || currentNode == null) ? null : slowNode;
   }
}
