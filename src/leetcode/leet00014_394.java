package leetcode;

import java.util.LinkedList;

public class leet00014_394 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//这道题有个非常不易察觉的错误
		//就是前面的数可能是多位数而非一位数，这样就必须来积累数字
		
		//初始版本解法，比较繁琐，用一个方法来构造这个位置直到右括号的字符串内容
		//用一个全局变量来记录位置
		public static String decodeString(String s) {
			char[] arr = s.toCharArray();
			StringBuffer res = new StringBuffer();
			while (k < arr.length) {
				res.append(decodeString(arr, k));
				
			}
			return res.toString();
		}

		static int k = 0;

		public static StringBuffer decodeString(char[] arr, int start) {
			StringBuffer res = new StringBuffer();
			while (k < arr.length && (arr[k] < '0' || arr[k] > '9')) {
				res.append(arr[k]);
				k++;
			}
			int times = 0;
			if (k < arr.length) {
				times = getTimes(arr);
			}
			k++;
			StringBuffer sb = new StringBuffer();
			while (k < arr.length && arr[k] != ']') {
				if (arr[k] >= '0' && arr[k] <= '9') {
					sb.append(decodeString(arr, k));
				} else {
					sb.append(arr[k++]);
				}
			}
			res.append(go(times, sb));
			k++;
			return res;
		}

		public static StringBuffer go(int times, StringBuffer sb) {
			StringBuffer res = new StringBuffer();
			for (int i = 0; i < times; i++) {
				res.append(sb);
			}
			return res;
		}
		
		public static int getTimes(char[] arr) {
			int num = 0;
			while(k < arr.length && (arr[k] >= '0' && arr[k] <= '9')) {
				num = num * 10 + (arr[k++] - '0');
			}
			return num;
		}
		
		//改进版本，所有操作在一次循环内完成，借助两个栈来完成这种进入进出的操作
		//用两个栈来装数字和括号内的字母，每次把之前确定的字符串装入栈中
		//并把字符串计入res归零，然后继续存入res字符
		//遇见左括号就将字符串和数字都放入其中
		//遇见右括号再把结果取出和已经积累的字符串拼接
		public static String decodeString1(String s) {
	        LinkedList<Integer> stack1 = new LinkedList<>();
	        LinkedList<String> stack2 = new LinkedList<>();
	        String res = "";
	        int n = 0;
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            if (c >= '0' && c <= '9') {
	                n = n * 10 + c - '0';
	            } else if (c == '[') {
	                stack1.push(n);
	                stack2.push(res);
	                n = 0;
	                res = "";
	            } else if (c == ']') {
	                StringBuilder sb = new StringBuilder(stack2.pop());
	                int repete = stack1.pop();
	                for (int k = 0; k < repete; k++) {
	                    sb.append(res);
	                }
	                res = sb.toString();
	            } else {
	                res += c;
	            }
	        }
	        return res;
	    }
}
