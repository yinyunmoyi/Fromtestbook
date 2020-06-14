package basicS;

public class Jzoffer65 {

	//将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
	//但是string不符合数字要求时返回0)，
	//要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
	public static void main(String[] args) {
		//System.out.println(StrToInt("-2147483648"));
	}
	
	//sum有可能会出现溢出的情况，所以定义成long
	public int StrToInt(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] arr = str.toCharArray();
        long sum = 0;
        int mul = 1;
        int pos = 0;
        if(arr[0] == '-' || arr[0] == '+'){
            pos = 1;
            mul = arr[0] == '-' ? -1 : 1;
        }
        for(int i = pos; i < arr.length; i++){
            if(arr[i] < '0' || arr[i] > '9'){
                return 0;
            }else{
                sum += ((arr[i] - '0') * Math.pow(10, arr.length - i - 1));
            }
        }
        return (int)sum * mul;
    }

}
