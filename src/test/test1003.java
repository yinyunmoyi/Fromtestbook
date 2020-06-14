package test;

import basicS.BinaryNode;
import basicS.MyArrays;

public class test1003 {

	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
	 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
	 * 则重建二叉树并返回。
	 * 
	 * 根据后序遍历和中序遍历结果返回二叉树的根节点
	 * //例如输入后序遍历序列{4,7,2,5,8,6,3,1}和中序遍历序列{4,7,2,1,5,3,8,6}，
	 * 
	 * (47min)(12min)
	 */
	public static void main(String[] args) {
		//BinaryNode<Integer> tree = getTreeIn2(new int[]{1,2,4,7,3,5,6,8}, new int[]{4,7,2,1,5,3,8,6});
		BinaryNode<Integer> tree = getTreeEnd(new int[]{7,4,2,5,8,6,3,1}, new int[]{4,7,2,1,5,3,8,6});
		MyArrays.printTree(tree);
	}
	
	public static BinaryNode<Integer> getTree(int[] arr1, int[] arr2){
		if(arr1 == null || arr2 == null || arr1.length == 0 || arr1.length != arr2.length){
			return null;
		}
		if(arr1.length == 1){
			return new BinaryNode<Integer>(arr1[0]);
		}
		int mid = findIndex(arr2, arr1[0]), j = 1, i;
		BinaryNode<Integer> root = new BinaryNode<Integer>(arr1[0]);
		BinaryNode<Integer> currentNode = root;
		int k = mid - 1;
		while(k >= 0){
			i = findIndex(arr1, arr2[k]);
			if(i == j){
				currentNode.left = new BinaryNode<Integer>(arr1[i]);
				currentNode = currentNode.left;
				j++;
			}else{
				currentNode.left = new BinaryNode<Integer>(arr1[j]);
				currentNode = currentNode.left;
				j++;
				while(j != (i + 1)){
					currentNode.right = new BinaryNode<Integer>(arr1[j]);
					currentNode = currentNode.right;
					j++;
				}
			}
			k = mid - j;
		}
		currentNode = root;
		//System.out.println("in");
		k = mid + 1;
		while(k < arr2.length){
			i = findIndex(arr1, arr2[k]);
			if(i == j){
				//System.out.println("in");
				currentNode.right = new BinaryNode<Integer>(arr1[i]);
				currentNode = currentNode.right;
				j++;
			}else{
				//System.out.println("in");
				currentNode.right = new BinaryNode<Integer>(arr1[j]);
				currentNode = currentNode.right;
				j++;
				while(j != (i + 1)){
					currentNode.left = new BinaryNode<Integer>(arr1[j]);
					currentNode = currentNode.left;
					j++;
				}
			}
			k = j;
		}
		return root;
	}
	
	private static int findIndex(int[] arr, int num){
		for(int i = 0 ; i < arr.length; i++){
			if(num == arr[i]){
				return i;
			}
		}
		return -1;
	}
	
	public static BinaryNode<Integer> getTreeIn2(int[] arr1, int[] arr2){
		return getTreeIn2(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
	}
	
	private static BinaryNode<Integer> getTreeIn2(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2){
		if(start1 > end1 || start2 > end2 || start1 < 0 || start2 < 0 || end1 >= arr1.length || end2 >= arr2.length){
			return null;
		}
		BinaryNode<Integer> node = new BinaryNode<Integer>(arr1[start1]);
		for(int j = start2; j <= end2; j++){
			if(arr2[j] == arr1[start1]){
				node.left = getTreeIn2(arr1, start1 + 1, start1 + j - start2, arr2, start2, j - 1);
				node.right = getTreeIn2(arr1, start1 + j + 1 - start2, end1, arr2, j + 1, end2);
				break;
			}
		}
		return node;
	}
	
	public static BinaryNode<Integer> getTreeEnd(int[] arr1, int[] arr2){
		return getTreeEnd(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
	}
	
	private static BinaryNode<Integer> getTreeEnd(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2){
		if(start1 > end1 || start2 > end2){
			return null;
		}
		BinaryNode<Integer> node = new BinaryNode<Integer>(arr1[end1]);
		for(int i = start2; i <= end2; i++){
			if(arr2[i] == arr1[end1]){
				node.left = getTreeEnd(arr1, start1, start1 + i - start2 - 1, arr2, start2, i - 1);
				node.right = getTreeEnd(arr1, start1 + i - start2, end1 - 1, arr2, i + 1, end2);
			}
		}
		return node;
	}
}
