package basicS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class zuo04 {

	/**
	 * 回字打印矩阵
	 * 
	 * 将正方形矩阵顺时针旋转
	 * 
	 * 之字形打印一个矩阵，例如
	 * [1, 2, 3, 4]
	 * [5, 6, 7, 8]
	 * [9, 10, 11, 12]
	 * 打印结果为1,2,5,9,6,3,4,7,10,11,8,12
	 * 
	 * 双向链表的反转(待验证)
	 * 
	 * 判断一个链表是否为回文结构，3种方法，最后一种没有空间复杂度
	 * 回文结构：1-》2-》3-》2-》1
	 * （链表类问题时间复杂度一般都为n，优化解一般是从空间复杂度入手，一般空间复杂度为1的最优）
	 * 
	 * 将单向链表按某值划分成左边小，中间相等，右边大的情形
	 * 两种方法，一种利用On的辅助空间，一种不用，后者还可以满足稳定性
	 * 
	 * 判断一个链表是否有环，用辅助空间/不用,如果有返回第一个相较节点，如果没有返回null
	 * 
	 * 判断两个链表是否相交，相交返回交点，不相交返回null
	 */
	public static void main(String[] args) {
		/*int[][] arr = MyArrays.creatMatrix(3, 4, 0, 50);
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
		System.out.println(Arrays.toString(arr[2]));
		System.out.println("------------------");
		printMatrix(arr);*/
		
		/*int[][] arr = MyArrays.creatMatrix(3, 3, 0, 50);
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
		System.out.println(Arrays.toString(arr[2]));
		System.out.println("------------------");
		clockWisePrint(arr);
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
		System.out.println(Arrays.toString(arr[2]));*/
		
		/*int[][] arr = MyArrays.creatMatrix(3, 4, 0, 50);
		System.out.println(Arrays.toString(arr[0]));
		System.out.println(Arrays.toString(arr[1]));
		System.out.println(Arrays.toString(arr[2]));
		zhiPrint(arr);*/
		
		
		/*boolean flag ;
		Node head;
		do{
		int[] arr = MyArrays.creatArray(7, 1, 10);
		head = MyArrays.ArrayToNode(arr);
		flag = isHuiChain_c(head);
		System.out.println("正在尝试！");
		}
		while(!flag);
		MyArrays.printChain(head);*/
		
		/*int[] arr = MyArrays.creatArray(1, 1, 10);
		Node head = MyArrays.ArrayToNode(arr);
		MyArrays.printChain(head);
		System.out.println();
		Node newHead = dutchFlagChain_a(head, 5);
		MyArrays.printChain(newHead);*/
		
		Node createLoopChain = MyArrays.createLoopChain(10, 8, 0, 100);
		MyArrays.printChain(createLoopChain);
		System.out.println(haveLoopChain_a(createLoopChain).val);
	}

	//回字形打印矩阵，这里用的方法是拆成子问题，然后逐个解决子问题，这种思路写代码的速度最快
	//在第一个方法中，定义了四个值分别作为每轮打印的左上角坐标和右下角坐标
	//每轮打印一圈，然后各坐标变化后继续打印
	
	//在第二个方法中，先处理两个坐标重合的情况，这种情况下只打印一个数
	//再处理头尾结点坐标行相同或列相同的情况
	//最后处理四个值各不相同的情况
	public static void printMatrix(int a[][]){
		int column = a.length - 1;
		int row = a[0].length - 1;
		int n = 0;
		int m = 0;
		while(n <= column && m <= row){
			printMatrix(a, n++, m++, column--, row--);
		}
	}
	
	private static void printMatrix(int[][] a, int n, int m, int column, int row){
		if(n == column && m == row){
			System.out.println(a[n][m]);
		}else if(m == row){
			while(n <= column){
				System.out.println(a[n++][m]);
			}
		}else if(n == column){
			while(m <= row){
				System.out.println(a[n][m++]);
			}
		}else{
			int beginColumn = n, beginRow = m;
			while(m < row){
				System.out.println(a[n][m++]);
			}
			while(n < column){
				System.out.println(a[n++][row]);
			}
			while(beginRow < row){
				System.out.println(a[column][row--]);
			}
			while(beginColumn < column){
				System.out.println(a[column--][beginRow]);
			}
		}
	}
	
	//顺时针旋转正方形矩阵
	//解决问题的思路还是分解成小问题，一圈一圈解决
	//定义了一圈的左上角点坐标和右下角坐标
	//对于特定的一圈而言，如果只有一个数那么就无需调整位置
	//对于普通情况，只需制造一个循环，每次循环调整4个数的位置，先写好调整四个角的，然后调整即可
	public static void clockWisePrint(int[][] arr){
		int n = 0, m = 0;
		int column = arr.length - 1;
		int row = arr[0].length - 1;
		while(n <= column && m <= row){
			clockWisePrint(arr, n++, m++, column--, row--);
		}
	}
	
	private static void clockWisePrint(int[][] arr, int n, int m, int column, int row){
		if(n == column){
			return;
		}else{
			int t , i = 0;
			while(i < (column - n)){
				t = arr[n][m + i];
				arr[n][m + i] = arr[column - i][m];
				arr[column - i][m] = arr[column][row - i];
				arr[column][row - i] = arr[n + i][row];
				arr[n + i][row] = t;
				i++;
			}
		}
	}
	
	//之字形打印一个矩阵
	//依然采用划分问题的思路，其实它的子问题就是已知一条斜打印线的起点和终点，如何打印这条斜线
	//这个问题通过改变下标很容易解决，而打印线的起点和终点是通过每次坐标沿着边界移动实现的
	//每次是斜向上打印还是向下打印是交替进行的，用一个布尔变量来指示这一点，每次循环完成都更新这个布尔变量
	public static void zhiPrint(int[][] arr){
		int i = 0, j = 0;
		int m = 0, n = 0;
		boolean flag = true;
		while(m <= arr.length - 1 && n <= arr[0].length - 1){
			zhiPrint(arr, flag, i, j, m, n);
			flag = !flag;
			if(j == arr[0].length - 1){
				i++;
			}else{
				j++;
			}
			
			if(m == arr.length - 1){
				n++;
			}else{
				m++;
			}
		}
	}
	
	private static void zhiPrint(int[][] arr, boolean flag, int i, int j, int m, int n){
		if(flag){
			while(i <= m && n <= j){
				System.out.println(arr[m--][n++]);
			}
		}else{
			while(i <= m && n <= j){
				System.out.println(arr[i++][j--]);
			}
		}
	}
	
	
	//双向链表的反转(待验证)
	public static mutNode reverseMutNode(mutNode head){
		mutNode currentNode = head;
		mutNode preNode = null;
		mutNode nextNode = null;
		while(currentNode != null){
			nextNode = currentNode.next;
			currentNode.pre = nextNode;
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = nextNode;
		}
		
		return preNode;
	}
	
	//判断一个链表是否为回文结构
	
	//借助一个栈来完成这个功能，首先遍历链表将所有元素装入栈中
	//再遍历一遍与栈中的数一一比对，如果不相等就是非回文链表
	//如果进行到最后比对都正常那么就是回文链表
	//这种方法利用的就是回文链表的顺序和逆序是一样
	public static boolean isHuiChain_a(Node head){
		Stack<Integer> stack =  new Stack<Integer>();
		Node currentNode = head;
		while(currentNode != null){
			stack.push(currentNode.val);
			currentNode = currentNode.next;
		}
		currentNode = head;
		while(currentNode != null){
			if(currentNode.val != stack.pop()){
				return false;
			}
			currentNode = currentNode.next;
		}
		
		return true;
		
	}
	
	//借助一个栈来完成功能，和上一个方法相比这个算法需要的栈的大小仅仅是链表长度的一半
	//设置两个指针，一个每次走两步，一个每次走一步
	//当走两步那个指针走完时，另一个指针正好指在中间，继续遍历的过程中，将剩余元素装入栈中
	//重新遍历链表，对比剩余元素，不对的话直接返回false，如果至栈为空都能对上则返回true
	public static boolean isHuiChain_b(Node head){
		Stack<Integer> stack =  new Stack<Integer>();
		Node currentNode = head;
		Node keyNode = head;
		//走两步的指针keyNode必须保证不为空且它的next也不能为空，否则会出现空指针异常
		//链表长度为奇数则currentNode停在正中央，如果为偶数则currentNode停在中间两个结点的后一个上
		//无论是哪种情况都可以将所有信息装入栈中验证
		while(currentNode != null && keyNode != null && keyNode.next != null){
			currentNode = currentNode.next;
			keyNode = keyNode.next.next;
		}
		
		while(currentNode != null){
			stack.push(currentNode.val);
			currentNode = currentNode.next;
		}
		currentNode = head;
		while(!stack.isEmpty()){
			if(currentNode.val != stack.pop()){
				return false;
			}
			currentNode = currentNode.next;
		}
		
		return true;
	}
	
	
	//不借助辅助空间来实现功能，首先设置两个指针，一个指针一次走一步，一个一次走两步
	//最后走完时，另一个指针停在链表的正中间位置或中间两个位置的后一个
	//该指针从该位置开始讲指针反转，反转结束后：1-》2-》3《-2《-1
	//此时从两头遍历，遍历结束后还没有出现不相等情况则是回文结构
	//最后，恢复链表结构把值返回
	public static boolean isHuiChain_c(Node head){
		
		if(head.next == null){
			return true;
		}
		
		boolean flag = true;
		
		//设置两个指针，一个指针一次走一步，一个一次走两步
		Node currentNode = head;
		Node keyNode = head;
		while(currentNode != null && keyNode != null && keyNode.next != null){
			currentNode = currentNode.next;
			keyNode = keyNode.next.next;
		}
		
		//该指针从该位置开始讲指针反转，反转结束后：1-》2-》3《-2《-1
		Node preNode = null;
		Node midNode = null;
		
		while(currentNode != null){
			midNode = currentNode.next;
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = midNode;
		}
		
		//此时从两头遍历，遍历结束后还没有出现不相等情况则是回文结构
		Node endPositionNode = preNode;
		keyNode = head;
		while(keyNode!= null && preNode != null){
			if(keyNode.val != preNode.val){
				flag = false;
				break;
			}
			keyNode = keyNode.next;
			preNode = preNode.next;
		}
		
		//最后，恢复链表结构
		midNode = null;
		preNode = null;
		while(endPositionNode != null){
			midNode = endPositionNode.next;
			endPositionNode.next = preNode;
			preNode = endPositionNode;
			endPositionNode = midNode;
		}
		
		return flag;
		
	}
	
	
	//将单向链表按某值划分成左边小，中间相等，右边大的情形
	
	//这个算法需要一个与链表等大的数组作为辅助空间，且不保证稳定性
	//首先将链表的每一个结点装入数组中，然后堆对数组进行荷兰国旗问题排序
	//最后按照调整后数组的顺序重新调整链表指针即可
	public static Node dutchFlagChain(Node head, int key){
		Node currentNode = head;
		
		//计算链表长度并准备等大数组
		int i = 0;
		while(currentNode != null){
			i++;
			currentNode = currentNode.next;
		}
		
		Node[] nodeArray = new Node[i];
		
		//将链表结点装入数组中
		i = 0;
		currentNode = head;
		while(currentNode != null){
			nodeArray[i++] = currentNode;
			currentNode = currentNode.next;
		}
		
		//用荷兰国旗问题算法对数组进行排序
		i = 0;
		int pre = -1;
		int end = nodeArray.length - 1;
		while(i < end){
			if(nodeArray[i].val > key){
				nodeSwap(nodeArray, i, end--);
			}else if(nodeArray[i].val < key){
				nodeSwap(nodeArray, pre + 1, i);
				pre++;
				i++;
			}else{
				i++;
			}
		}
		
		//重新调整链表的次序
		for(i = 0; i < nodeArray.length - 1; i++){
			nodeArray[i].next = nodeArray[i + 1];
		}
		nodeArray[nodeArray.length - 1].next = null;
		return nodeArray[0];
	}
	
	private static void nodeSwap(Node[] nodeArray, int i, int j){
		Node node = nodeArray[i];
		nodeArray[i] = nodeArray[j];
		nodeArray[j] = node;
	}
	
	//将单向链表按某值划分成左边小，中间相等，右边大的情形
	
	//这种算法无需辅助空间，且划分后除了相等部分，其余两边各自内部的相对次序不变
	//设置三个区域，遍历链表时将结点分配到小中大三个区域，每个区域用一个链表记载结点，只需要头和尾指针即可代表一个区域
	//最后将三个区域头尾相连即可
	//注意要处理特别情况，三个区域某个为空时
	public static Node dutchFlagChain_a(Node head, int key){
		
		//定义当前结点和三个区域的六个指针，准备遍历
		Node currentNode = head;
		
		Node smallNodeStart = null;
		Node smallNodeEnd = null;
		Node midNodeStart = null;
		Node midNodeEnd = null;
		Node bigNodeStart = null;
		Node bigNodeEnd = null;
		
		//遍历链表，将结点分到三个区域，如果指针是null那就将两个指针一同初始化
		while(currentNode != null){
			if(currentNode.val < key){
				if(smallNodeStart == null){
					smallNodeStart = smallNodeEnd = currentNode;
				}else{
					smallNodeEnd.next = currentNode;
					smallNodeEnd = currentNode;
				}
			}else if(currentNode.val > key){
				if(bigNodeStart == null){
					bigNodeStart = bigNodeEnd = currentNode;
				}else{
					bigNodeEnd.next = currentNode;
					bigNodeEnd = currentNode;
				}
			}else{
				if(midNodeStart == null){
					midNodeStart = midNodeEnd = currentNode;
				}else{
					midNodeEnd.next = currentNode;
					midNodeEnd = currentNode;
				}
			}
			
			currentNode = currentNode.next;
		}
		
		//将三个区域的尾全指向空，因为特殊情况的存在，这三个指针都有可能作为新链表的尾结点，故需要全部置空
		//这样后面将无需置空的部分修改掉即可
		
		//还有一种解决方法本算法没有采用：可以直接在创建各个区域结点时
		//每次循环直接记录下一个结点的值，立即将本节点指向空，在将指针移动到记录好的位置：
		//next = head.next;
		//head.next = null;
		//处理本节点数值分组
		//head = next;
		//这样就可以保证每个节点的初始指向为空，然后用结果去调整每个指向
		//这实际上在遍历的过程中彻底打乱了链表
		
		if(smallNodeEnd != null){smallNodeEnd.next = null;}
		if(midNodeEnd != null){midNodeEnd.next = null;}
		if(bigNodeEnd != null){bigNodeEnd.next = null;}
		
		//如果中间区域不为空就将它与大于区域连接起来
		//如果为空那么就把大于区域赋值给中间区域
		//大于区域没有必要判断空指针，因为指向空指针是可以的
		if(midNodeStart != null){
			midNodeEnd.next = bigNodeStart;
		}else{
			midNodeStart = bigNodeStart;
		}
		
		//如果小于区域不为空就将他和中间区域连接，否则继续赋值
		if(smallNodeStart != null){
			smallNodeEnd.next = midNodeStart;
		}else{
			smallNodeStart = midNodeStart;
		}
		
		//最后，小于区域的头部必然是新链表的头结点
		return smallNodeStart;
		
	}
	
	//判断一个链表是否有环，用辅助空间/不用
	
	//这种算法借助了一个集合，遍历时把节点装入集合内，每次检查新节点有没有装入过
	//如果有装入过就说明是环链表，如果没有就说明不是
	public static Node haveLoopChain(Node head){
		HashSet<Node> set = new HashSet<Node>();
		Node currentNode = head;
		while(currentNode != null && (!set.contains(currentNode))){
			set.add(currentNode);
			currentNode = currentNode.next;
		}
		if(currentNode != null){
			return currentNode;
		}else{
			return null;
		}
	}
	
	//判断一个链表是否有环，用辅助空间/不用
	
	//这种算法不用辅助空间，设置两个指针，一个快指针一个慢指针，快指针一次走两步，慢指针一次走一步
	//直到两个指针重合，此时把快指针重新指向头结点，快慢指针一起一次走一步前进
	//再次重合的结点就是相交节点
	public static Node haveLoopChain_a(Node head){
		Node fastNode = head;
		Node slowNode = head;
		//这里可以用dowhile也可以把判断相等的条件放入循环的后半部，结合break去实现
		do{
			if(fastNode.next == null){
				return null;
			}
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
		}while(!(fastNode == slowNode || slowNode == null || fastNode == null));
		
		if(slowNode == null || fastNode == null){
			return null;
		}
		
		fastNode = head;
		while(fastNode != slowNode){
			fastNode = fastNode.next;
			slowNode = slowNode.next;
		}
		
		return fastNode;
	}
	
	//判断两个链表是否相交，相交返回交点，不相交返回null
	
	//根据两个链表是否为环链表可以分多钟情况处理
	//如果两个链表都是无环链表，那么就用无环链表的算法寻找
	//如果是有环则用对应的算法
	//如果一个无环一个有环肯定不会相交，因为无环链表的末结点是确定的，而有环链表必定结尾是个环
	//此时直接返回null
	public static Node returnIntersect(Node head1, Node head2){
		Node loop1 = haveLoopChain_a(head1);
		Node loop2 = haveLoopChain_a(head2);
		if(loop1 == null && loop2 == null){
			return noLoopIntersect(head1, head2);
			//return noLoopIntersect_a(head1, head2);
		}else if(loop1 != null && loop2 != null){
			return loopIntersect(head1, loop1, head2, loop2);
		}else{
			return null;
		}
	}
	
	//两个无环链表求相交结点方法1
	
	//        \          /
	//         \        /
	//          \      /
	//           \    /
	//            \  /
	//             \/
    //              |
	//              |
	
	//这种算法需要一个set，遍历链表1将所有节点装入其中
	//遍历链表2，遇到与链表1重合的结点返回该节点
	//如果至链表尾没有找到，则也返回该节点（此时为null）
	private static Node noLoopIntersect(Node head1, Node head2){
		HashSet<Node> set = new HashSet<Node>();
		Node currentNode = head1;
		while(currentNode != null){
			set.add(currentNode);
			currentNode = currentNode.next;
		}
		
		currentNode = head2;
		while(currentNode != null && (!set.contains(currentNode))){
			currentNode = currentNode.next;
		}
		
		return currentNode;
	}
	
	//两个无环链表求相交结点方法2
	
	//这种算法不需要额外空间复杂度，遍历两个链表得到链表的长度和最后一个结点
	//如果两个链表的最后一个结点不同，那么两个一定不相交
	//如果相同说明相交，此时让较长的链表先走两个链表的差值步
	//然后两个指针再一同前进，直到找到相同的结点，即为首个相交结点
	private static Node noLoopIntersect_a(Node head1, Node head2){
		Node currentNode1 = head1;
		Node currentNode2 = head2;
		int length1 = 0;
		int length2 = 0;
		while(currentNode1.next != null){
			length1++;
			currentNode1 = currentNode1.next;
		}
		
		while(currentNode2.next != null){
			length2++;
			currentNode2 = currentNode2.next;
		}
		
		if(currentNode1 != currentNode2){
			return null;
		}
		
		currentNode1 = length1 > length2 ? head1 : head2;
		currentNode2 = currentNode1 == head1 ? head2 : head1;
		int num = length1 > length2 ? (length1 - length2):(length2 - length1);
		
		for(int i = 0; i < num; i++){
			currentNode1 = currentNode1.next;
		}
		
		while(currentNode1 != currentNode2){
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		
		return currentNode1;
	}
	
	
	//求两个有环链表第一个相交结点
	
	//情形1：没有交点
	
	//情形2：
	//           \    /
	//            \  /
	//             \/
    //              |
	//              |
	//             / \
	//            /   \
	//            \   /
	//             \ /
	
	//情形3：
	//        \               /
	//         \             /
	//          \           /
	//           \         /
	//            \       /
	//             \     /
	//              -----
	//             /     \
	//             \     /
	//              -----
	
	//如果两个loop相等那么必为情形2
    //如果不相等，那么就遍历链表1寻找loop2，如果找到就是情形3，随便返回一个loop即可，否则就是情形1直接返回null
	private static Node loopIntersect(Node head1, Node loop1, Node head2, Node loop2){
		if(loop1 == loop2){
			return findLoopIntersect(head1, head2, loop1);
		}else{
			Node currentCode = head1;
			while(currentCode != null){
				//-----------------------------------------------
				//-------------错误-------------------------------
				//-----------------------------------------------
				if(currentCode == loop2){
					return loop2;
				}
				currentCode = currentCode.next;
			}
			
			return null;
		}
	}
	
	//对于情形2，就是无环链表找交点算法的翻版，只不过原来两个链表遍历在null处停止，现在在loop处停止
	private static Node findLoopIntersect(Node head1, Node head2, Node loop){
		Node currentNode1 = head1;
		Node currentNode2 = head2;
		int length1 = 0;
		int length2 = 0;
		while(currentNode1.next != loop){
			length1++;
			currentNode1 = currentNode1.next;
		}
		
		while(currentNode2.next != loop){
			length2++;
			currentNode2 = currentNode2.next;
		}
		
		currentNode1 = length1 > length2 ? head1 : head2;
		currentNode2 = currentNode1 == head1 ? head2 : head1;
		int num = length1 > length2 ? (length1 - length2):(length2 - length1);
		
		for(int i = 0; i < num; i++){
			currentNode1 = currentNode1.next;
		}
		
		while(currentNode1 != currentNode2){
			currentNode1 = currentNode1.next;
			currentNode2 = currentNode2.next;
		}
		
		return currentNode1;
	}
}

class mutNode{
	public mutNode next;
	public mutNode pre;
	public int val;
	public mutNode(int val){
		this.val = val;
	}
}
