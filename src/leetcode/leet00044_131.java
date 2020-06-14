package leetcode;
import java.util.*;

public class leet00044_131 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//常规递归解法，每个位置都向后遍历一遍，积累的字符串装入集合中
	public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        fillList(s, 0, new ArrayList<String>(), list);
        return list;
    }
    
    private void fillList(String s, int posi, ArrayList<String> slist, List<List<String>> list){
        if(posi == s.length()){
            list.add((List<String>) slist.clone());
            return;
        }
        String str = "";
        for(int i = posi; i < s.length(); i++){
            str += s.charAt(i);
            if(isHui(str)){
                slist.add(str);
                fillList(s, i + 1, slist, list);
                slist.remove(slist.size() - 1);
            }
        }
    }

    private boolean isHui(String str){
        int start = 0;
        int end = str.length() - 1;
        while(start < end){
            if(str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
  //一样的递归方案，只是判断是否是回文字符串的方法不用再写了
  //原来的递归把所有的位置字符串都判断了一遍是不是回文的
  //这里有很多重复计算，可以利用dp将这部分重复省掉
  //如果用ij来表示从i到j的字符串是否是回文的话，那么要确定ij，那么就先判断i和j两个位置的字符是否相同
  //其次再判断[i+1][j-1]是否是true即可
  //但是这个循环的顺序是不能按照常规顺序的，如果一开始就从[0][0]计算到[0][n]是不可能的
  //要从很小的范围开始算起
    public List<List<String>> partition1(String s) {
        boolean[][] flag = new boolean[s.length()][s.length()];
        for(int j = 0; j < s.length(); j++){
            for(int i = 0; i <= j; i++){
                if(s.charAt(i) == s.charAt(j) && (j - i <= 2 || flag[i + 1][j - 1])){
                    flag[i][j] = true;
                }
            }
        }
        List<List<String>> list = new ArrayList<>();
        fillList1(s, 0, new ArrayList<String>(), list, flag);
        return list;
    }
    
    private void fillList1(String s, int posi, ArrayList<String> slist, List<List<String>> list, boolean[][] flag){
        if(posi == s.length()){
            //clone方法在使用抽象类时报错
            list.add((List<String>) slist.clone());
        }else{
            for(int i = posi; i < s.length(); i++){
                if(flag[posi][i]){
                    slist.add(s.substring(posi, i + 1));
                    fillList1(s, i + 1, slist, list, flag);
                    slist.remove(slist.size() - 1);
                }
            }
        }
    }

}
