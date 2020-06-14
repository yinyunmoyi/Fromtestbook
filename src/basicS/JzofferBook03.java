package basicS;

public class JzofferBook03 {

	/**
	 * 输入数字n，按顺序打印从1到最大的n位十进制数
	 * 如果n为3，那么打印1 2 3 4.。。 998 999
	 */
	public static void main(String[] args) {
		printAll(7);
	}

	//首先确定要输出的最大位数
	//确定要输出的最大字符串，等于时停止
	//首先看字符串末尾是不是9，如果不是直接自增，否则向前进位
	//如果到字符串0位置都还是9，那么就新建一个“0”与源字符串进行拼接
	//将字符串输出即可
	public static void printAll(int n){ 
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < n; i++){
			sb.append('9');
		}
		//System.out.println(sb);
		//int[] arr = new int[n];
		StringBuffer str = new StringBuffer("");
		while(!str.toString().equals(sb.toString())){
			str = selfAdd(str);
			System.out.println(str);
		}
	}
	
	private static StringBuffer selfAdd(StringBuffer sb){
		char[] arr = sb.toString().toCharArray();
		if(arr.length == 0) {
			return new StringBuffer("1");
		}
		if(arr[arr.length - 1] != '9') {
			arr[arr.length - 1] += 1;
		}else {
			int index = arr.length - 1;
			while(index >= 0 && arr[index] == '9') {
				arr[index] = '0';
				index--;
			}
			if(index >= 0) {
				arr[index] += 1;
			}else {
				return new StringBuffer("1").append(new String(arr));
			}
		}
		return new StringBuffer(new String(arr));
	}
}
