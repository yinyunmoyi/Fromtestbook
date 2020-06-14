package leetcode;

public class leet00062_415 {
	
	
	
	
	/*
	 * 字符串加法
	 * 先把结果创建为空字符串，采用在前面累加的方式加出结果
	 * 两个字符串同时从后向前遍历，记录进位值然后同时生成
	 */
	
	public static String addStrings(String num1, String num2) {
        String res = "";
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int k1 = num1.length() - 1, k2 = num2.length() - 1;
        int beforeVal = 0;
        while(k1 >= 0 || k2 >= 0){
            int n1 = (k1 >= 0 ? arr1[k1] - '0' : 0);
            int n2 = (k2 >= 0 ? arr2[k2] - '0' : 0);
            int n = n1 + n2 + beforeVal;
            res = (char)((n % 10) + '0') + res;
            beforeVal = n / 10;
            k1--;k2--;
        }
        return (beforeVal == 1 ? "1" : "") + res;
    }

}
