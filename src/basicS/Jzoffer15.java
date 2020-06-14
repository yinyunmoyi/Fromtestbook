package basicS;

public class Jzoffer15 {

	/**
	 * 输入两个单调递增的链表，输出两个链表合成后的链表，
	 * 当然我们需要合成后的链表满足单调不减规则。
	 */
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode head1 = node1;
		for(int i = 3; i <= 9; i = i + 2){
			ListNode node_mid = new ListNode(i);
			node1.next = node_mid;
			node1 = node1.next;
		}
		
		ListNode node2= new ListNode(2);
		ListNode head2 = node2;
		for(int i = 4; i <= 10; i = i + 2){
			ListNode node_mid = new ListNode(i);
			node2.next = node_mid;
			node2 = node2.next;
		}
		
		ListNode node = Merge_c(head1, head2);
		System.out.println(node);
		
		
	}

	//28 ms	9744K
	//这种算法不破坏原来的结构，需要建立新节点，基于的是合并算法
	public ListNode Merge(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode head = null;
        if(node2.val < node1.val){
            head = new ListNode(node2.val);
            node2 = node2.next;
        }else{
            head = new ListNode(node1.val);
            node1 = node1.next;
        }
        ListNode newNode = head;
        ListNode mid;
        while(node1 != null && node2 != null){
            if(node2.val < node1.val){
                mid = new ListNode(node2.val);
                node2 = node2.next;
            }else{
                mid = new ListNode(node1.val);
                node1 = node1.next;
            }
            newNode.next = mid;
            newNode = newNode.next;
        }
        
        while(node1 != null){
            mid = new ListNode(node1.val);
            node1 = node1.next;
            newNode.next = mid;
            newNode = newNode.next;
        }
        
        while(node2 != null){
            mid = new ListNode(node2.val);
            node2 = node2.next;
            newNode.next = mid;
            newNode = newNode.next;
        }
        
        return head;
    }
	
	//21 ms	9512K
	//这种算法会破坏原结构，也是基于合并算法
	public ListNode Merge_a(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        ListNode node;
        if(list1.val > list2.val){
            node = list2;
            list2 = list2.next;
        }else{
            node = list1;
            list1 = list1.next;
        }
        ListNode head = node;
        
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                node.next = list2;
                list2 = list2.next;
            }else{
                node.next = list1;
                list1 = list1.next;
            }
            node = node.next;
        }
        
        while(list1 != null){
            node.next = list1;
            list1 = list1.next;
            node = node.next;
        }
        
        while(list2 != null){
            node.next = list2;
            list2 = list2.next;
            node = node.next;
        }
        
        return head;
    }
	
	//23 ms	9508K
	//与上个算法不同在于最后在收尾时不再逐个添加
	//而是利用链表的性质直接设置指针完成操作
	public ListNode Merge_b(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        ListNode node;
        if(list1.val > list2.val){
            node = list2;
            list2 = list2.next;
        }else{
            node = list1;
            list1 = list1.next;
        }
        ListNode head = node;
        
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                node.next = list2;
                list2 = list2.next;
            }else{
                node.next = list1;
                list1 = list1.next;
            }
            node = node.next;
        }
        
        if(list1 != null){
            node.next = list1;
        }else{
            node.next = list2;
        }
        
        return head;
    }
	
	//29 ms	9640K
	//这种算法不在一开始留下head结点，而是在循环过程中给head结点赋值
	//而且利用了一行两个等号的形式
	public static ListNode Merge_c(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        ListNode node = null;
        ListNode head = null;
        
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                if(head == null){
                	//而且利用了一行两个等号的形式
                    head = node = list2;
                }else{
                    node.next = list2;
                    node = node.next;
                }
                list2 = list2.next;
            }else{
                if(head == null){
                    head = node = list1;
                }else{
                    node.next = list1;
                    node = node.next;
                }
                list1 = list1.next;
            }
        }
        
        if(list1 != null){
            node.next = list1;
        }else{
            node.next = list2;
        }
        
        return head;
    }
	
	
	//23 ms	9552K
	//这种与上个算法基本类似，不同之处在于多创建一个结点，这个结点的下一个结点才是有意义的序列首项
	//返回时直接返回下一个结点作为头结点
	public ListNode Merge_d(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        ListNode head = new ListNode(0);
        ListNode node = head;
        
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                node.next = list2;
                list2 = list2.next;
            }else{
                node.next = list1;
                list1 = list1.next;
            }
            node = node.next;
        }
        
        if(list1 != null){
            node.next = list1;
        }else{
            node.next = list2;
        }
        
        return head.next;
    }
	
	//29 ms	9756K
	//递归代码
	public ListNode Merge_e(ListNode list1,ListNode list2) {
        
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }
        
        if(list1.val > list2.val){
            list2.next = Merge(list1, list2.next);
            return list2;
        }else{
            list1.next = Merge(list1.next, list2);
            return list1;
        }
        
    }
}
