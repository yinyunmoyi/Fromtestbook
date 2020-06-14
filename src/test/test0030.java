package test;

import java.util.HashMap;

public class test0030 {

	/**
	 * 实现一颗trie树，
	 * 实现insert方法添加字符串，实现search方法查某个字符串添加过多少次
	 * 实现delete方法删除一个字符串，实现prefixNumber方法查看多少字符串以输入值为前缀
	 * 要求所有方法的复杂度为On(34min)￥、(19min)
	 */
	public static void main(String[] args) {
		trie.trie2 tr = new trie().new trie2();
		tr.insert("abc");
		tr.insert("bbc");
		tr.insert("abc");
		tr.insert("cdf");
		tr.insert("aac");
		tr.insert("abg");
		tr.insert("abt");
		tr.insert("ttg");
		System.out.println(tr.search("abc"));
		System.out.println(tr.prefixNumber("ab"));
		tr.delete("abc");
		System.out.println(tr.prefixNumber("ab"));
	}

}













//注意要让同样的字母共享一个结点，所以insert时判断结点为空才新建，不为空就直接用，这才是trie的精髓
class trie{
	
	Node headNode;
	class Node{
		Node[] arr;
		int sum;
		int pre;
		Node(){
			arr = new Node[26];
		}
	}
	
	trie(){
		headNode = new Node();
	}
	
	void insert(String str){
		char[] charArray = str.toCharArray();
		Node currentNode = headNode;
		for (char c : charArray) {
			if(currentNode.arr[(c - 'a')] == null){
				currentNode.arr[(c - 'a')] = new Node();
			}
			currentNode = currentNode.arr[(c - 'a')];
			currentNode.pre++;
		}
		currentNode.sum++;
	}
	
	int times(String str){
		char[] charArray = str.toCharArray();
		Node currentNode = headNode;
		for (char c : charArray) {
			currentNode = currentNode.arr[(c - 'a')];
			if(currentNode == null){
				return 0;
			}
		}
		return currentNode.sum;
	}
	
	void delete(String str){
		char[] charArray = str.toCharArray();
		Node currentNode = headNode;
		for (char c : charArray) {
			currentNode = currentNode.arr[(c - 'a')];
			if(currentNode == null){
				return;
			}
			currentNode.pre--;
		}
		currentNode.sum--;
	}
	
	int prefixNumber(String str){
		char[] charArray = str.toCharArray();
		Node currentNode = headNode;
		for (char c : charArray) {
			currentNode = currentNode.arr[(c - 'a')];
			if(currentNode == null){
				return 0;
			}
		}
		return currentNode.pre;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//实现一颗trie树，
	// * 实现insert方法添加字符串，实现search方法查某个字符串添加过多少次
	// * 实现delete方法删除一个字符串，实现prefixNumber方法查看多少字符串以输入值为前缀
	// * 要求所有方法的复杂度为On(34min)￥
	class trie2{
		Node root;
		
		class Node{
			int pre;
			int end;
			HashMap<Character, Node> map;
			Node(){
				pre = 0;
				end = 0; 
				this.map = new HashMap<>();
			}
		}
		
		trie2(){
			root = new Node();
		}
		
		void insert(String str){
			char[] charArray = str.toCharArray();
			Node currentNode = root;
			for(int i = 0; i < charArray.length; i++){
				if(!currentNode.map.containsKey(charArray[i])){
					currentNode.map.put(charArray[i], new Node());
				}
				currentNode = currentNode.map.get(charArray[i]);
				currentNode.pre++;
			}
			currentNode.end++;
		}
		
		int search(String str){
			char[] charArray = str.toCharArray();
			Node currentNode = root;
			for(int i = 0; i < str.length(); i++){
				if(!currentNode.map.containsKey(charArray[i])){
					return 0;
				}
				currentNode = currentNode.map.get(charArray[i]);
			}
			return currentNode.end;
		}
		
		void delete(String str){
			char[] charArray = str.toCharArray();
			Node currentNode = root;
			for(int i = 0; i < str.length(); i++){
				if(!currentNode.map.containsKey(charArray[i])){
					return;
				}
				currentNode = currentNode.map.get(charArray[i]);
				currentNode.pre--;
			}
			currentNode.end--;
		}
		
		int prefixNumber(String str){
			char[] charArray = str.toCharArray();
			Node currentNode = root;
			for(int i = 0; i < str.length(); i++){
				if(!currentNode.map.containsKey(charArray[i])){
					return 0;
				}
				currentNode = currentNode.map.get(charArray[i]);
			}
			return currentNode.pre;
		}
	}
}
