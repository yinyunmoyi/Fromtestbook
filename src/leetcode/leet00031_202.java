package leetcode;

public class leet00031_202 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//这道题每个数字会有唯一确定的下一个数字，这实际上是一个抽象的链表
    //返回true意味着这个链表的循环节点是1，false则不是1
    //用双指针法来判别是否是循环链表，如果最后是1退出循环那么所有循环肯定只有一个数1
    //否则就不是
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
        	slow = getNext(slow);
        	fast = getNext(fast);
        	fast = getNext(fast);
        }while(slow != fast);
        return slow == 1;
    }
    
    private int getNext(int num) {
		int n;
		int sum = 0;
		while(num != 0) {
			n = num % 10;
			sum += (n * n);
			num /= 10;
		}
		return sum;
	}
}
