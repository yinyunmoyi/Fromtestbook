package leetcode;

import java.util.HashMap;

public class leet00056_166 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//首先先将整数部分取出
    //然后开始计算小数部分，小数部分每次循环余数都乘10
    //把新的余数更新，同时把计算结果放到小数部分字符串后
    //如果余数在计算时重复就说明出现了循环部分，此时就要把循环部分用小数包括起来
    //这里用hashmap同时记录各余数出现的位置和余数去重功能
    //注意计算结果重复不代表出现循环，出现相同的余数才代表出现循环
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0){
            return "0";
        }
        int n1 = numerator < 0 ? -1 : 1, n2 = denominator < 0 ? -1 : 1;
        long k1 = Math.abs((long)numerator);
        long k2 = Math.abs((long)denominator);
        long first = k1 / k2;
        HashMap<Long, Integer> map = new HashMap<>();
        long num1 = k1 % k2;
        String str = "";
        int posi = 0;
        while(num1 != 0){
            num1 = num1 * 10;
            if(map.containsKey(num1)){
                int xposi = map.get(num1);
                str = str.substring(0, xposi) + "(" + str.substring(xposi, str.length()) + ")";
                break;
            }else{
                str += (num1 / k2);
                map.put(num1, posi);
                num1 = num1 % k2;
            }
            posi++;
        }
        str = str.equals("") ? first + "" : first + "." + str;
        return n1 * n2 > 0 ? str : "-" + str;
    }
}
