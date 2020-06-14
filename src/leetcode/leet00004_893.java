package leetcode;

import java.util.HashSet;

public class leet00004_893 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(numSpecialEquivGroups1(new String[] {"abc","acb","bac","bca","cab","cba"}));
		
	}
	
	//again
	
	
	
	//You are given an array A of strings.

	//Two strings S and T are special-equivalent if after any number of moves, S == T.

	//A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].

	//Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent with any string in S.

	//Return the number of groups of special-equivalent strings from A.
	
	//常规解法：根据每个字符串生成一个签名，签名就是奇数位出现的字母种类和个数和偶数位出现的字母种类和个数
	//的整合，中间加了一个特殊字符*以分开，然后把签名加入set中，set的size就是最后的结果
	
	//还可以将排序后的字符串作为签名，当字符串不长时这样做可以减少空间的损耗
	public static int numSpecialEquivGroups(String[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        HashSet<String> set = new HashSet<String>();
        for(int i = 0; i < A.length; i++){
            int[] odd = new int[26];
            int[] even = new int[26];
            for(int j = 0; j < A[i].length(); j++){
                if(j % 2 == 0){
                    even[A[i].charAt(j) - 'a']++;
                }else{
                    odd[A[i].charAt(j) - 'a']++;
                }
            }
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < 26; j++){
                sb.append("*");
                sb.append(even[j]);
                sb.append("*");
                sb.append(odd[j]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
	
	//一种极其高级的解法，这种解法把签名从字符串变成了一个int数，使得计算过程非常快
	//用一个26大小的质数数组为基础，对字符串的每一位进行乘积，这样乘积结果不同，质数组合肯定不同
	//但是这种算法有大小限制，可能会溢出
	private final static int[] RECORD = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public static int numSpecialEquivGroups1(String[] A) {
        HashSet<Integer> res = new HashSet<>();
        for (String s : A) {
            res.add(hash(s));
        }
        return res.size();
    }

    private static int hash(String str) {
        int res = 1;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i++) - 97;
            res *= RECORD[idx];
        }
        res+=2017;
        for (int i = 1; i < str.length(); i++) {
            int idx = str.charAt(i++) - 97;
            res *= RECORD[idx];
        }
        
        return res;
    }

}
