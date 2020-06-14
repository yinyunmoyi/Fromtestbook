package basicS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Jzoffer26 {

	/**
	 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
	 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串
	 * abc,acb,bac,bca,cab和cba
	 */
	public static void main(String[] args) {

	}
	
	//138 ms	12232K	--------------------------------------------------------
	//-------------------------------------------------------------------------
	//转化成字符数组进行操作，全排列算法
	//一直在与确定位置的字母互换得到不同的结果
	public ArrayList<String> Permutation(String str) {
		//创建要返回的list
        ArrayList<String> list = new ArrayList<>();
        //如果字符串为空或为空字符串，直接返回list，此时list刚刚新建被赋值为null
        if(str != null && str.length() > 0){
        	//调用递归函数Permutation，并将结果list排序，排序结果就是字典排序的结果
           Permutation(str.toCharArray(), 0, list);
            Collections.sort(list);
       }
        return list;
    }
    
	//递归函数Permutation，第一个参数是字符数组，第二个参数是处理进度i，当i=1时代表，i=1要和1以后的位置进行互换
	//对于数组【a，b，c】，最开始i=0，在这个递归函数中调用i=1的递归函数三次，每个i=1的递归函数又调用了两次i=2的递归函数
	//总共调用了6次i=2的函数，也就是将全部6个结果都加入了list中
    private void Permutation(char[] a, int i, ArrayList<String> list){
        //当i等于数组最后一位时，也就没有其他情形了，此时没有可以和i号位置互换的数了
    	//此时将对应的字符串加入list并结束函数
    	if(i == a.length - 1){
            list.add(String.valueOf(a));
        }else{
        	//若i此时还没到数组最后一位，说明还有其余可能，准备进行数组的互换
        	//创建一个set用来存已经加入过的字母，出现字母相同时不会重复放入list中
            HashSet<Character> set = new HashSet<>();
            //j从i开始，到字符数组末尾结束，j是要与i互换的那个位置，当第一次循环时就是不变
            for(int j = i; j < a.length; j++){
            	//如果是第一次循环就直接进入，将此时的数组放入list中，因为此时还没互换，一定不会出现重复的情况
            	//如果set中不包括j位置的值也可以进入，如果set中包括j的值说明此时没有必要将数组放入list了
            	//比如对于【a，b，b】来说，第一次进入时是【a，b，b】，也就是0位置与0位置互换
            	//第二次进入时是【b，a，b】，这个排序与之前不同，依然可以放入
            	//第三次进入就变成【b，a，b】，此时是第三个位置与第一个位置互换，因为这个结果之前已经有了，所以不用再进入了
            	//这种算法一直在与确定位置的字母互换得到不同的结果，所以检测到有重复结果时选择不进入
                if(i == j || !set.contains(a[j])){
                	//将j位置的数放入set中
                    set.add(a[j]);
                    //交换i与j位置的字母
                    swap(a, i, j);
                    //将交换之后的结果再给递归函数处理，此时对于【a，b，b】，就是i=1时，但1与2互换结果一样，所以仅仅加入【a，b，b】
                    //对于【b，a，b】，i=1时，会加入【b，a，b】和【b，b，a】
                    Permutation(a, i+1, list);
                    //最后还要将位置给交换回来，因为对于【b，a，b】i=1时，加入【b，a，b】（2和2互换）后还要2和3位置互换，就是【b，b，a】
                    swap(a, i, j);
                }
            }
        }
    }
    
    //一个互换的函数
    private void swap(char[] a, int i, int j){
        char b = a[i];
        a[i] = a[j];
        a[j] = b;
    }

    /*链接：https://www.nowcoder.com/questionTerminal/fe6b651b66ae47d7acce78ffdd9a96c7
    	来源：牛客网


    	     * 一个全排列可看做一个字符串，字符串可有前缀、后缀。
    	     * 生成给定全排列的下一个排列.所谓一个的下一个就是这一个与下一个之间没有其他的。
    	     * 这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
    	     *
    	     * [例]839647521是1--9的排列。1—9的排列最前面的是123456789，最后面的987654321，
    	     * 从右向左扫描若都是增的，就到了987654321，也就没有下一个了。否则找出第一次出现下降的位置。
    	     *
    	     * 【例】 如何得到346987521的下一个
    	     * 1，从尾部往前找第一个P(i-1) < P(i)的位置
    	     * 3 4 6 <- 9 <- 8 <- 7 <- 5 <- 2 <- 1
    	     * 最终找到6是第一个变小的数字，记录下6的位置i-1
    	     *
    	     * 2，从i位置往后找到最后一个大于6的数
    	     * 3 4 6 -> 9 -> 8 -> 7 5 2 1
    	     * 最终找到7的位置，记录位置为m
    	     *
    	     * 3，交换位置i-1和m的值
    	     * 3 4 7 9 8 6 5 2 1
    	     * 4，倒序i位置后的所有数据
    	     * 3 4 7 1 2 5 6 8 9
    	     * 则347125689为346987521的下一个排列
    	     链接：https://www.nowcoder.com/questionTerminal/fe6b651b66ae47d7acce78ffdd9a96c7
    	    	 来源：牛客网

    	    	 public ArrayList<String> Permutation2(String str){
    	    	         ArrayList<String> list = new ArrayList<String>();
    	    	         if(str==null || str.length()==0){
    	    	             return list;
    	    	         }
    	    	         char[] chars = str.toCharArray();
    	    	         Arrays.sort(chars);
    	    	         list.add(String.valueOf(chars));
    	    	         int len = chars.length;
    	    	         while(true){
    	    	             int lIndex = len-1;
    	    	             int rIndex;
    	    	             while(lIndex>=1 && chars[lIndex-1]>=chars[lIndex]){
    	    	                 lIndex--;
    	    	             }
    	    	             if(lIndex == 0)
    	    	                 break;
    	    	             rIndex = lIndex;
    	    	             while(rIndex<len && chars[rIndex]>chars[lIndex-1]){
    	    	                 rIndex++;
    	    	             }
    	    	             swap(chars,lIndex-1,rIndex-1);
    	    	             reverse(chars,lIndex);
    	    	  
    	    	             list.add(String.valueOf(chars));
    	    	         }
    	    	  
    	    	         return list;
    	    	     }
    	    	  
    	    	     private void reverse(char[] chars,int k){
    	    	         if(chars==null || chars.length<=k)
    	    	             return;
    	    	         int len = chars.length;
    	    	         for(int i=0;i<(len-k)/2;i++){
    	    	             int m = k+i;
    	    	             int n = len-1-i;
    	    	             if(m<=n){
    	    	                 swap(chars,m,n);
    	    	             }
    	    	         }*/
}
