package basicS;
import java.util.HashMap;

public class Jzoffer24 {

	/**
	 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，
	 * 一个指向下一个节点，另一个特殊指针指向任意一个节点），
	 * 返回结果为复制后复杂链表的head。
	 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
	 */
	public static void main(String[] args) {

	}

	class RandomListNode{
		int label;
	    RandomListNode next = null;
	    RandomListNode random = null;

	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
	
	//33 ms	9720K
	//复杂链表的复制分为三步
	public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return null;
        }
        
        RandomListNode currentCode = pHead;
        RandomListNode midCode;
        //将每个节点复制，并连在旧节点后面，形成一个大链表
        while(currentCode != null){
            RandomListNode cloneNode = new RandomListNode(currentCode.label);
            midCode = currentCode.next;
            currentCode.next = cloneNode;
            cloneNode.next = midCode;
            currentCode = midCode;
        }
        
        currentCode = pHead;
        //将每个新建节点的random指针关系调整正确，根据邻近旧节点的指针可以轻易获得
        while(currentCode != null){
            currentCode.next.random = currentCode.random == null? null : currentCode.random.next;
            currentCode = currentCode.next == null ? null : currentCode.next.next;
        }
        
        currentCode = pHead;
        RandomListNode cloneHead = pHead.next;
        //将旧链表和新链表区分开，划清界限，最后返回新链表
        while(currentCode != null){
            midCode = currentCode.next.next;
            currentCode.next.next = midCode == null ? null : midCode.next;
            currentCode.next = midCode;
            currentCode = midCode;
        }
        
        return cloneHead;
    }
	
	//39 ms	9576K
	//此算法用了hashmap，用了散列表之后就可以直接通过原来链表定位到新链表，这样新链表的random指针
	//就可以先定位到旧链表，然后再通过旧链表的random定位到旧链表的一节点，通过散列表再定位到新链表即可
	public RandomListNode Clone_a(RandomListNode pHead)
    {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode nowNode = pHead;
        //这里也是通过预先建立一个结点来完成简洁的循环
        RandomListNode cloneNode = new RandomListNode(-1);
        //这里是建立新链表的next链
        while(nowNode != null){
            cloneNode.next = new RandomListNode(nowNode.label);
            cloneNode = cloneNode.next;
            map.put(nowNode, cloneNode);
            nowNode = nowNode.next;
        }
        nowNode = pHead;
        //建立新链表的random链
        while(nowNode != null){
            map.get(nowNode).random =  map.get(nowNode.random);
            nowNode = nowNode.next;
        }
        return map.get(pHead);
    }
	
	
	
	
	
	
	
	
	
	
	//上面的算法都有一定的局限性，它们都是默认链表中的random指针指向已有的结点
	//假如random指针指向的结点并非链表中的一环时，算法失效，此时需要借助图算法。。。
	
	
	
	
	
	
	
	
	
	
	
	
	
}
