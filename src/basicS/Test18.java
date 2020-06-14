package basicS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//import java.awt.List;

public class Test18 {

	/**
	 * 处理长度相同，仅一个字母不同的相似单词
	 * 给定一个List里面装着很多单词string
	 * 要求得到一个Map，这个Map中的键是一个单词
	 * 值是与该单词仅一个字母之差的单词构成的List
	 * 如：line和wine、pine
	 */
	public static void main(String[] args) {

	}
	
	//原始方法1：运行时间最长
	public Map<String,List<String>> computeWords(List<String> wordsList){
		
		//新建一个map装入最后的处理结果
		Map<String,List<String>> map = new TreeMap<String, List<String>>();
		//新建一个数组，准备吧list转换成数组
		String[] words = new String[wordsList.size()];
		
		//将list转换成数组，这里转换的原因在于后面要嵌套循环
		//而嵌套循环要操作两次相邻的值，加强for处理不了
		wordsList.toArray(words);
		//嵌套循环查找仅差一个字母的单词
		for(int i = 0;i < words.length;i++){
			for(int j = i + 1;j < words.length; j++){
				if(oneCharOff(words[i] , words[j])){
					//如果两个单词仅差一个字母
					//那么就将它们两个相互加入对方的键中，存入map中
					update(map , words[i] , words[j]);
					update(map , words[j] , words[i]);
				}
			}
		}
		
		//最后返回map
		return map;
	}

	//原始方法2：运行时间较长
	//比方法1改进在对比之前就把所有的单词都按单词长度分了组
	//本来对原数组进行双层遍历运行时间为n！
	//假设分为四组，改进后运行时间为(n/4)!*4
	//运行时间大大减少
	public Map<String,List<String>> computeWords_2(List<String> wordsList){
		
		Map<String,List<String>> map = new TreeMap<>();
		Map<Integer,List<String>> mapOfNumber = new TreeMap<>();
		
		//将单词按单词长度分组装入mapOfNumber中
		//这里更新的方法用的是update_2，它比原方法修改了合适的泛型
		for (String st : wordsList) {
			update_2(mapOfNumber , st.length() , st);
		}
		
		//将分组后各组的单词list都分别进行处理
		for (List<String> list : mapOfNumber.values()) {
			
			String[] st = new String[list.size()];
			
			list.toArray(st);
			for(int i = 0;i < st.length;i++){
				for(int j = i + 1;j < st.length ; j++){
					if(oneCharOff(st[i] , st[j])){
						//如果两个单词仅差一个字母
						//那么就将它们两个相互加入对方的键中，存入map中
						update(map , st[i] , st[j]);
						update(map , st[j] , st[i]);
					}
				}
			}
		}
		
		return map;
	}
	
	//方法3：运行时间最短
	//将单词按长度分类之后，把结果再进行处理
	//把单词的所有的长度减一的子字符串都为键，单词为值存入另一个map中
	//这样操作完成之后，所有键相同的单词就是我们要的差一个单词的单词集
	//最后把单词集分别装入map结果中即可
	public Map<String,List<String>> computeWords_3(List<String> wordsList){
		
		Map<String,List<String>> map = new TreeMap<>();
		Map<Integer,List<String>> mapOfNumber = new TreeMap<>();
		
		//将单词按单词长度分组装入mapOfNumber中
		//这里更新的方法用的是update_2，它比原方法修改了合适的泛型
		for (String st : wordsList) {
			update_2(mapOfNumber , st.length() , st);
		}
		
		//拿到每个按单词长度分类的list，此时的list内都是相同长度的单词
		for(List<String> list : mapOfNumber.values()){
			
			//创建一个map装入键为子字符串，值为单词
			Map<String,List<String>> mapBaseOnPart = new TreeMap<>();
			
			//将list的每一个单词都遍历
			for(String st : list){
				//选取不同的i，也就是不同的切割单词的位置
				for(int i = 0;i < list.size(); i++){
					//键的字符串中只有原单词的第i个位置的单词没有了
					//这种方法实际上不能确定存入的键到底是去掉了哪一个位置的字母
					//但存入时不会出现错误
					update(mapBaseOnPart , st.substring(0,i) + st.substring(i + 1) , st);
				}
			}
			
			//对得到的mapBaseOnPart中的values进行遍历
			//此时的partList中装的是所有互相相似的单词
			//下面要做的就是将它们互相存入结果map中
			for (List<String> partList : mapBaseOnPart.values()) {
				
				//如果结果中元素不到两个就不必进行下去了
				//此时没有相互相似的元素
				//如果满足条件那么就互相存入map中
				if(partList.size() >= 2){
					String[] partSt = new String[partList.size()];
				
					partList.toArray(partSt);
				
					for(int i = 0; i < partSt.length; i++){
						for(int j = i + 1;j < partSt.length;j++){
							update(map , partSt[i] , partSt[j]);
							update(map , partSt[j] , partSt[i]);
						}
					}
				}
			}
		}
		return map;
	}
	
	
	
	//这是一个对比两个单词的方法，如果两个单词长度相同且仅一个字母不同就返回true
	private boolean oneCharOff(String s1 , String s2){
		
		//若长度不同直接返回false
		if(s1.length() != s2.length()){
			return false;
		}
		
		//引入一个记录变量，该变量记录不相同字母个数
		int count = 0;
		
		//遍历每一对字母查看是否相似
		//如果字母不同那么count加一并判断
		//如果不同字母个数达到两个直接返回false
		for(int i = 0;i < s1.length(); i++){
			if(s1.charAt(i) != s2.charAt(i)){
				if(++count > 1){
					return false;
				}
			}
		}
		
		//查看count是否为1并返回判断结果
		return count == 1;
	}
	
	//这是一个更新map的方法，它会把s1作为键，s2作为值存入map中
	//实际上map的值是list，s2只需加入这个list
	private void update(Map<String,List<String>> map , String s1 , String s2){
		
		//获取s1键对应的值
		List<String> list = map.get(s1);
		
		//如果list为null，证明map中还未存入与s1 键相关的内容
		if(list == null){
			//此时新建一个list，并把键值对加入到map中
			list = new ArrayList<String>();
			map.put(s1, list);
		}
		
		//最后将s2加入list中完成对map的更新
		list.add(s2);
	}
	
	//修改泛型的方法
	private void update_2(Map<Integer,List<String>> map , Integer s1 , String s2){
		
		//获取s1键对应的值
		List<String> list = map.get(s1);
		
		//如果list为null，证明map中还未存入与s1 键相关的内容
		if(list == null){
			//此时新建一个list，并把键值对加入到map中
			list = new ArrayList<String>();
			map.put(s1, list);
		}
		
		//最后将s2加入list中完成对map的更新
		list.add(s2);
	}
}
