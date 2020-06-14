package basicS;

//在一个排序的链表中，存在重复的结点，
//请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
//例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
public class Jzoffer99 {

	//"",".*"    "","c*"
	public class Solution {
		//初始 19k 9500ms
	    public ListNode deleteDuplication(ListNode pHead)
	    {
	        if(pHead == null){
	            return null;
	        }
	        ListNode currentNode;
	        while(pHead.next != null && pHead.next.val == pHead.val){
	            currentNode = pHead;
	            while(currentNode != null && currentNode.next != null && currentNode.next.val == currentNode.val){
	                currentNode = currentNode.next;
	            }
	            if(currentNode == null || currentNode.next == null) {
	                return null;
	            }else{
	                pHead = currentNode.next;
	            }
	        }
	        if(pHead.next == null){
	            return pHead;
	        }else{
	            currentNode = pHead;
	        }
	        while(currentNode.next != null){
	            ListNode nextNode = currentNode.next;
	            if(nextNode.next != null && nextNode.next.val != nextNode.val){
	                currentNode = currentNode.next;
	            }else{
	                if(nextNode.next == null){
	                    return pHead;
	                }else{
	                    while(nextNode.next != null && nextNode.next.val == nextNode.val){
	                        nextNode = nextNode.next;
	                    }
	                    if(nextNode.next == null){
	                        currentNode.next = null;
	                        return pHead;
	                    }else{
	                        ListNode keyNode = nextNode.next ;
	                        nextNode.next = null;
	                        currentNode.next = keyNode;
	                    }
	                }
	            }
	        }
	        return pHead;
	    }
	    
	  //初始 18k 9500ms 做了一个额外的头结点
	    public ListNode deleteDuplication1(ListNode pHead)
	    {
	    	if(pHead == null){
	            return null;
	        }
	        ListNode firstNode = new ListNode(-1);
	        firstNode.next = pHead;
	        ListNode currentNode = firstNode;
	        while(currentNode.next != null){
	            ListNode nextNode = currentNode.next;
	            if(nextNode.next != null && nextNode.next.val != nextNode.val){
	                currentNode = currentNode.next;
	            }else{
	                if(nextNode.next == null){
	                    return firstNode.next;
	                }else{
	                    while(nextNode.next != null && nextNode.next.val == nextNode.val){
	                        nextNode = nextNode.next;
	                    }
	                    if(nextNode.next == null){
	                        currentNode.next = null;
	                        return firstNode.next;
	                    }else{
	                        ListNode keyNode = nextNode.next ;
	                        nextNode.next = null;
	                        currentNode.next = keyNode;
	                    }
	                }
	            }
	            
	        }
	        return pHead;
	    }
	    
	    //虚拟一个头指针，不再只定一个指针，而是双指针前进
	    public ListNode deleteDuplication3(ListNode pHead)
	    {
	        ListNode firstNode = new ListNode(-1);
	        firstNode.next = pHead;
	        ListNode currentNode = pHead;
	        ListNode p = firstNode;
	        while(currentNode != null && currentNode.next != null){
	           if(currentNode.val == currentNode.next.val){
	               int val = currentNode.val;
	               while(currentNode != null && val == currentNode.val){
	                   currentNode = currentNode.next;
	               }
	               p.next = currentNode;
	           }else{
	               p = currentNode;
	               currentNode = currentNode.next;
	           }
	        }
	        return firstNode.next;
	    }
	}
}
