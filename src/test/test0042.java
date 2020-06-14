package test;

public class test0042 {

	/**
	 * 假设有字符串abcabc，求构造一个更大的字符串，只能在该串末尾添加字符，使生成的字符串前缀和后缀都是abcabc
	*	要求生成的该串最短
	*	这个问题的答案应该是abcabc，其实abcabcabcabc同样满足条件，但是太长不是最短
	*
	*	(19min)
	 */
	public static void main(String[] args) {
		System.out.println(getLargeStr2_2("abcacabc"));
	}
	
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
				if(arr[i] == str.length()){
					break;
				}
				if(i >= str.length()){
					charArray[i] = charArray[arr[i - 1] + 1];
				}
				i++;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i] = 0;
				if(i >= str.length()){
					charArray[i] = charArray[0];
				}
				i++;
			}
		}
		
		return new String(charArray, 0, i);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//假设有字符串abcabc，求构造一个更大的字符串，只能在该串末尾添加字符，使生成的字符串前缀和后缀都是abcabc
	//*	要求生成的该串最短
	//*	这个问题的答案应该是abcabc，其实abcabcabcabc同样满足条件，但是太长不是最短
	public static String getLargeStr2_2(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		if(str.length() == 1){
			return str + str;
		}
		char[] charArr = str.toCharArray();
		char[] newArr = new char[str.length() * 2];
		int[] arr = new int[str.length() * 2];
		for(int i = 0; i < charArr.length; i++){
			newArr[i] = charArr[i];
		}
		arr[0] = -1;
		arr[1] = 0;
		int i = 2, k = 0;
		while(true){
			if(newArr[i - 1] == newArr[arr[i - 1]]){
				arr[i] = ++k;
				if(arr[i] == str.length()){
					break;
				}
				if(i >= str.length()){
					newArr[i] = newArr[arr[i]];
				}
				i++;
			}else if(k > 0){
				k = arr[k];
			}else{
				arr[i] = 0;
				if(i >= str.length()){
					newArr[i] = newArr[0];
				}
				i++;
			}
		}
		return new String(newArr, 0, i);
	}

}
