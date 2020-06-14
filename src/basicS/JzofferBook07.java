package basicS;

public class JzofferBook07 {

	/**
	 * 有一个字符序列：0123456789101112131415161718192021...
	 * 位置编号从0开始，要求输入位置给出对应位置的数字
	 */
	public static void main(String[] args) {
		System.out.println(returnNum(0));
	}
	
	//先判断到底对应的数字是几位数，因为1位数只可能占9位，2位数只可能占180位，每次num减这些位数
	//什么时候减的结果小于0什么时候就落在对应的区间，然后再找对应的数字，根据余数找对应结果
	public static int returnNum(int num){
		int k = 1;
		while(num - Math.pow(10, k - 1) * 9 * k > 0){
			num = num - (int)Math.pow(10, k - 1) * 9 * k;
			k++;
		}
		int num1 = num;
		int i = num1 % k;
		int r = (num1 - i)/k;
		int numPosi = (int)Math.pow(10, k - 1);
		numPosi += r;
		char[] arr = new String(numPosi + "").toCharArray();
		if(i == 0 && arr[arr.length - 1] == '0'){
			return 9;
		}else if(i == 0 && arr[arr.length - 1] != '0'){
			return arr[arr.length - 1] - '0' - 1;
		}else{
			return arr[i - 1] - '0';
		}
	}

}
