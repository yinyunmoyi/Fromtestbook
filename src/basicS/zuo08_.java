package basicS;

//import java.util.Arrays;
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Stack;
import java.util.*;

public class zuo08_ {

	/**
	*	KMP算法：给两个字符串a和b，如果a是b的子串返回起始位置，如果不是返回-1，要求时间复杂度M+N
	*
	*	假设有字符串abcabc，求构造一个更大的字符串，只能在该串末尾添加字符，使生成的字符串前缀和后缀都是abcabc
	*	要求生成的该串最短
	*	这个问题的答案应该是abcabc，其实abcabcabcabc同样满足条件，但是太长不是最短
	*
	*	输入两棵二叉树，判断一个树是否为另一颗树的子树
	*
	*	manacher算法：求一个字符串中最大回文子串长度，要求时间复杂度On
	*
	*	BFPRT算法：在一个数组中找到第k个小的数，要求时间复杂度ON
	*/
	public static void main(String[] args) {
		//int[] arr = {52, 78, 34, 23, 40, 84, 72, 10, 17};
		/*String str1 = MyArrays.createString(12, 0, 2);
		String str2 = MyArrays.createString(2, 0, 1);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(KMP(str2, str1));*/
		String str = MyArrays.createString(15, 0, 2);
		//String str = "bababbaaaaabbab";
		System.out.println(str);
		System.out.println(manacher(str));;
		//System.out.println(9 | 6);
		//int[] arr = MyArrays.creatArray(7, 0, 100);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(BFPRT(arr , 2));
		Scanner in = new Scanner(System.in);
	    while (in.hasNextLine()) {//注意while处理多个case              int a = in.nextInt();
	       // String str = in.nextLine();
	        manacher(str);
	    }
	}
	
	//KMP算法：给两个字符串a和b，如果a是b的子串返回起始位置，如果不是返回-1
	
	//得到辅助数组开始遍历，设置i和j分别指向字符数组a和b，向前推进的过程中如果对应位置字符相同就前进
	//如果不同就将i刷新为辅助数组对应i号位的位置继续前进，此时j不变
	//还需要注意如果在辅助数组的首位值是-1，跳转的值-1是非法的，故此时直接将j增1
	//此时一定是i和j处于0位置
	//最后一定i与j会有一个越界，如果j越界就说明检查完成，如果i越界就说明检查失败
	public static int KMP(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() < 1 || str2.length() < str1.length()){
			return -1;
		}
		char[] a = str1.toCharArray();
		char[] b = str2.toCharArray();
		int[] numArray = getIntArray(a);
		int i = 0;
		int j = 0;
		while(i <= b.length - 1 && j <= a.length - 1){
			if(a[j] == b[i]){
				i++;
				j++;
			}else if(a[j] != b[i] && numArray[j] < 0){
				i++;
			}else{
				j = numArray[j];
			}
		}
		return (j == a.length ? (i - a.length) : -1);
	}
	
	//获得算法的辅助数组，这个辅助数组大小与传入数组相同，该int数组与char数组一一对应
	//加入char数组是这样的abcdefgabck,计算k对应位置的int数组就是3，为前缀相等区abc和后缀相等区abc的长度
	//这个长度就是3，且该长度为可能的最大长度（对应上面这个例子不能是10，不能两个串都包含所有前缀），
	//该int数组的0号位人为规定为-1，1号位人为规定为0（因为1号位前不存在前缀后缀，因为该前缀后缀在同一位置）
	private static int[] getIntArray(char[] b){
		if(b.length <= 0){
			return null;
		}else if(b.length == 1){
			return new int[]{-1};
		}
		int[] arr = new int[b.length];
		arr[0] = -1;
		arr[1] = 0;
		int key = 0, i = 2;
		while(i < b.length){
			//如果相等说明数组的值就是前一位的值加1
			if(b[key] == b[i - 1]){
				arr[i++] = ++key;
			//key大于0说明还有优化的空间，继续向前跳
			}else if(key > 0){
				key = arr[key];
			}else{
				//失去了匹配的可能，赋值为0
				arr[i++] = 0;
			}
		}
		return arr;
	}
	
	//假设有字符串abcabc，求构造一个更大的字符串，只能在该串末尾添加字符，使生成的字符串前缀和后缀都是abcabc
	//*	要求生成的该串最短
	//*	这个问题的答案应该是abcabc，其实abcabcabcabc同样满足条件，但是太长不是最短
	
	//要生成的字符串一定不超过原来的两倍，先创建这样的数组
	//生成如图的辅助数组，在原来的范围内操作相同
	//不同之处在于一旦满足条件要将当前位置赋值为匹配位置后一个字符
	//而每次辅助数组归0时要将当前位置赋值为第一个字符
	//一旦辅助数组到达两倍字符串长度就跳出循环结束算法
	public static String largeString(String str){
		char[] cha = str.toCharArray();
		char[] charArray = new char[str.length() * 2];
		for(int i = 0; i < cha.length; i++){
			charArray[i] = cha[i];
		}
		int[] arr = new int[str.length() * 2];
		int i = 2, k = 0;
		arr[0] = -1;
		arr[1] = 0;
		while(true){
			if(charArray[i - 1] == charArray[arr[i - 1]]){
				arr[i] = ++k;
				//一旦辅助数组到达两倍字符串长度就跳出循环结束算法
				if(arr[i] == str.length()){
					break;
				}
				//不同之处在于一旦满足条件要将当前位置赋值为匹配位置后一个字符
				if(i >= str.length()){
					charArray[i] = charArray[arr[i - 1] + 1];
				}
				i++;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i] = 0;
				//而每次辅助数组归0时要将当前位置赋值为第一个字符
				if(i >= str.length()){
					charArray[i] = charArray[0];
				}
				i++;
			}
		}
		
		return new String(charArray, 0, i);
	}
	
	//输入两棵二叉树，判断一个树是否为另一颗树的子树
	public static boolean isZi(BinaryNode<Integer> node1, BinaryNode<Integer> node2){
		if(node1 == null || node2 == null){
			return false;
		}
		String str1 = Xubefore(node1);
		System.out.println(str1);
		String str2 = Xubefore(node2);
		System.out.println(str2);
		return isZiStr(str1, str2) >= 0;
	}
	
	private static String Xubefore(BinaryNode<Integer> node){
		String str = "";
		if(node != null){
			Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
			stack.add(node);
			while(!stack.isEmpty()){
				BinaryNode<Integer> currentNode = stack.pop();
				if(currentNode == null){
					str += "#!";
				}else{
					str += currentNode.element + "!";
					stack.push(currentNode.right);
					stack.push(currentNode.left);
				}
			}
			return str;
		}
		return str + "!";
	}
	
	private static int isZiStr(String str1, String str2){
		int[] flag = getFlag(str2);
		char[] charArr1 = str1.toCharArray();
		char[] charArr2 = str2.toCharArray();
		int i = 0, j = 0;
		while(i < charArr1.length && j < charArr2.length){
			if(j >= 0 && charArr1[i] != charArr2[j]){
				j = flag[j];
			}else if(j == -1){
				j = 0;
				i++;
			}else{
				i++;
				j++;
			}
		}
		System.out.println(Arrays.toString(flag));
		return i < charArr1.length ? i - charArr2.length : -1;
	}
	
	private static int[] getFlag(String str){
		if(str == null || str.length() == 0){
			return null;
		}else if(str.length() == 1){
			return new int[]{-1};
		}
		char[] charArray = str.toCharArray();
		int[] arr = new int[charArray.length];
		int i = 2, k = 0;
		arr[0] = -1;
		arr[1] = 0;
		while(i < str.length()){
			if(charArray[i - 1] == charArray[arr[i - 1]]){
				arr[i++] = ++k;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i++] = 0;
			}
		}
		return arr;
	}
	//manacher算法：求一个字符串中最大回文子串长度，要求时间复杂度On
	
	//首先用transfer方法先加工字符串，因为回文子串在延伸时存在偶串难以延伸的困难，所以要把每个字符之间加入一个‘#’
	//如aaabbbccc加工结束之后变为#a#a#a#b#b#b#c#c#c#
	//这样处理过的串一定是奇数串，延伸检查回文方便
	//定义一些变量，dia回转直径数组，nowPos目前最大右边界，core最大右边界对应的最左中心
	//基本计数i、目前最大回转直径maxDia、目前最大回转直径对应中心maxCore
	
	//开始遍历，遍历结束应该是最大右边界抵达数组末尾，此时如果有的位置直径还没有确定也不会出现更大的直径了
	//此时最大直径已经计算出来了
	//如果i > nowPos，说明当前位置在最大右边界外，此时扩展寻找最大回文
	//如果i <= nowPos 且dia[2 * core - i] - 1)/2 + i ) > nowPos
	//说明当前i位置相对core位置的对称点的直径扩展到了当前直径之外，此时i点的半径就是从i到nowPos
	//如果i <= nowPos 且dia[2 * core - i] - 1)/2 + i ) < nowPos
	//说明当前i位置相对core的对称点直径还在当前直径以内，此时对称 点的直径就是i点的直径
	//如果如果i <= nowPos 且dia[2 * core - i] - 1)/2 + i ) = nowPos
	//此时i点的回文直径至少是2 * (nowPos - i) + 1，还需向外延伸继续确定真正的直径
	public static String manacher(String str){
		String str1 = transfer(str);
		char[] charArray = str1.toCharArray();
		int[] dia = new int[str1.length()];
		int nowPos = -1, core = -1, i = 0, k, maxDia = Integer.MIN_VALUE, maxCore = Integer.MIN_VALUE;
		while(nowPos < dia.length - 1){
			if(i > nowPos || (i <= nowPos && ((dia[2 * core - i] - 1)/2 + i ) == nowPos)){
				k = i > nowPos ? 1 : nowPos - i;
				while((i + k) < charArray.length && (i - k) >= 0 && charArray[i + k] == charArray[i - k]){
					k++;
				}
				//如果最右边界没有更新core的位置就不更新
				core = nowPos < i + k - 1 ? i : core;
				//nowPos = Math.max(nowPos, i + k - 1);
				nowPos = i + k - 1;
				dia[i] = 2 * k - 1;
			}else if(((dia[2 * core - i] - 1)/2 + i ) > nowPos){
				dia[i] = 2 * (nowPos - i) + 1;
			}else if(((dia[2 * core - i] - 1)/2 + i ) < nowPos){
				dia[i] = dia[2 * core - i];
			}
			maxCore = maxDia < dia[i] ? core : maxCore;
			//每次记录下当前最大直径
			maxDia = Math.max(maxDia, dia[i++]);
		}
		maxCore = (maxCore - maxDia/2 + 1)/2;
		//当前最大直径除以2就是原字符串的直径（因为原来的字符串是处理过的）
		return str.substring(maxCore, maxCore + maxDia/2);
	}
	
	private static String transfer(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		char[] charArray = str.toCharArray();
		char[] newCharArr = new char[charArray.length * 2 + 1];
		int k = 0;
		for(int i = 0; i < charArray.length; i++){
			newCharArr[k++] = '#';
			newCharArr[k++] = charArray[i];
		}
		newCharArr[k] = '#';
		return new String(newCharArr);
	}
	
	//BFPRT算法：在一个数组中找到第k个小的数，要求时间复杂度ON
	
	//任意选择一个数作为key，进行荷兰国旗分数组，如果k正好落在区间内就说明找到了该数，否则递归左边或者右边
	
	//也可以先用getNum方法先求key，这个方法中将建立一个是原数组五分之一大小的新数组，这个数组每个数都是5个数的中位数组成
	//最后又递归调用BFPRT求该数组的中位数，需要注意的是此时BFPRT一定要给出start = end 时的条件
	//否则会无限递归
	public static int BFPRT(int[] arr, int k){
		if(arr == null || arr.length < k){
			throw new RuntimeException("数组长度不合法");
		}
		return BFPRT(arr, k, 0, arr.length - 1);
	}
	
	private static int BFPRT(int[] arr, int k, int start, int end){
		//swap(arr, start, start + (int)(Math.random() * (end - start)));
		if(start == end){
			return arr[start];
		}
		int num = getNum(arr, start, end);
		int[] flag = heLan(arr, num, start, end);
		//这里不用检查start和end的关系，在调用函数入参时就已经保证了，
		//如果后两个return越界，那么前面的条件也应该满足，应该走的是第一个return
		//所以不会出现越界的情况
		if(flag[0] + 1 <= k && flag[1] + 1 >= k){
			return arr[flag[0]];
		}else if(k < flag[0] + 1){
			return BFPRT(arr, k, start, flag[0] - 1);
		}else{
			return BFPRT(arr, k, flag[1] + 1, end);
		}
	}
	
	private static int[] heLan(int[] arr, int key, int start, int end){
		int small = start - 1, k = start;
		int big = end + 1;
		while(k < big){
			if(arr[k] > key){
				swap(arr, k, big - 1);
				big--;
			}else if(arr[k] < key){
				swap(arr, small + 1, k);
				k++;
				small++;
			}else{
				k++;
			}
		}
		//这里的small+1和big-1一定能唯一的确定至少一个数的区间，因为key就在该数组中，相当于等于区至少有一个数
		return new int[]{small + 1, big - 1};
	}
	
	private static void swap(int[] arr, int start, int end){
		int k = arr[start];
		arr[start] = arr[end];
		arr[end] = k;
	}
	
	private static int getNum(int[] arr, int start, int end){
		int length = end - start + 1;
		int offset = length % 5 == 0 ? 0 : 1;
		int[] numArray = new int[length / 5 + offset];
		for(int i = 0; i < numArray.length; i++){
			int j = start + i * 5;
			int k = start + (i + 1) * 5;
			numArray[i] = getMid(arr, j, Math.min(k, end));
		}
		return BFPRT(numArray, numArray.length/2);
	}
	
	private static int getMid(int[] arr, int start, int end){
		easySort(arr, start, end);
		int length = end - start + 1;
		int mid = start + (length % 2 == 0 ? (length/2 - 1) : (length/2));
		return arr[mid];
	}
	
	private static void easySort(int[] arr, int start, int end){
		for(int i = start; i <= end; i++){
			for(int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--){
				swap(arr, j + 1, j);
			}
		}
	}
}
