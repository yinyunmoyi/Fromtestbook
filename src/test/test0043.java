package test;

import java.util.Arrays;
import java.util.Stack;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test0043 {

	/**
	 * 输入两棵二叉树，判断一个树是否为另一颗树的子树
	 */
	public static void main(String[] args) {
		BinaryNode<Integer> node1; 
		BinaryNode<Integer> node2;  
		boolean flag;
		do{
			node1 = MyArrays.createFullTree(3, 0, 5);
			node2 = MyArrays.createFullTree(2, 0, 5);
			flag = isZi(node1, node2);
		}while(!flag);
		MyArrays.printTree(node1);
		MyArrays.printTree(node2);
		
		/*BinaryNode<Integer> node1 = MyArrays.createFullTree(3, 0, 0);
		BinaryNode<Integer> node2 = MyArrays.createFullTree(2, 0, 0);
		MyArrays.printTree(node1);
		MyArrays.printTree(node2);
		System.out.println(isZi(node1, node2));*/
		
		
		//System.out.println(isZiStr("0!0!0!#!#!0!#!#!0!0!#!#!0!#!#!", "0!0!#!#!0!#!#!"));
		
	}
	
	public static boolean isZi(BinaryNode<Integer> node1, BinaryNode<Integer> node2){
		if(node1 == null || node2 == null){
			return false;
		}
		String str1 = Xubefore(node1);
		//System.out.println(str1);
		String str2 = Xubefore(node2);
		//System.out.println(str2);
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

}
