package leetcode;

public class leet00063_43 {
	
	//字符串乘法
	/*
	 * 先创建一个n+m长的数组，然后用一个双层循环，i位置的数乘以j位置的数一定放在i+j+1位置，
	 * 如果累加原位置后大于10就把多余数字放在i+j位置，
	 * 用这样的方式来避免设置进位数。最后筛选掉前面的0，返回字符串即可。
	 */
	public static String multiply(String num1, String num2) {
        char[] arr = new char[num1.length() + num2.length()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = '0';
        }
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        for(int i = arr2.length - 1; i >= 0; i--){
            for(int j = arr1.length - 1; j >= 0; j--){
                int num = arr[i + j + 1] - '0' +
                        new Integer(arr1[j] - '0') * new Integer(arr2[i] - '0');
                arr[i + j + 1] = (char)(num % 10 + '0');
                arr[i + j] += num / 10;
            }
        }
        String res = "";
        int k = 0;
        while(k < arr.length && arr[k] == '0'){
            k++;
        }
        for(int i = k; i < arr.length; i++){
            res += arr[i];
        }
        return res.equals("") ? "0" : res;
    }
}
