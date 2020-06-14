package basicS;

public class Jzoffer72 {

	/**
	 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
	 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
	 * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
	 * 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
	 */
	public static void main(String[] args) {

	}

	//对于”abcXYZdef”，首先按照位数将字符串分成两部分，各自反转
	//然后对整体字符串进行一次反转即可
	public String LeftRotateString(String str,int n) {
        if(str == null || str.length() == 0 || n > str.length()){
            return new String();
        }else if(n == str.length() || n == 0){
            return str;
        }
        char[] arr = str.toCharArray();
        reverse(arr, 0, n - 1);
        reverse(arr, n, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
        return new String(arr);
    }
    
    public void reverse(char[] arr, int start, int end){
        while(start < end){
            char c = arr[start];
            arr[start] = arr[end];
            arr[end] = c;
            start++;end--;
        }
    }
}
