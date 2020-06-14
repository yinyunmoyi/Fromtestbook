package test;

public class test1002 {

	/**
	 * 请，将一个字符串中的每个空格替换成“%20”。 例如，当字符串为We Are
	 * Happy.则经过替换之后的字符串为We%20Are%20Happy。(5min)
	 */
	public static void main(String[] args) {

	}
	
	public static String getNewString(String str){
		char[] charArr = str.toCharArray();
		int num = 0, k = 0;
		for(int i = 0; i < charArr.length; i++){
			num = (charArr[i] == ' ' ? (num + 1): num);
		}
		char[] newArr = new char[str.length() + num * 2];
		for(int i = 0; i < charArr.length; i++){
			if(charArr[i] == ' '){
				newArr[k++] = '%';
				newArr[k++] = '2';
				newArr[k++] = '0';
			}else{
				newArr[k++] = charArr[i];
			}
		}
		return new String(newArr);
	}

}
