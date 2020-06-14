package basicS;

import java.util.Arrays;

public class Jzoffer69 {

	//给一个数组，判断数组中的数是不是一个顺子，数组中的数全是正数和0， 0可以视为任何数
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//首先排序，然后统计0个数，然后遍历数组查看0能不能填补空白
	public boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length == 0){
            return false;
        }
        Arrays.sort(numbers);
        int sumOfZero = 0;
        while(numbers[sumOfZero] == 0){
            sumOfZero++;
        }
        int pre = numbers[sumOfZero];
        for(int i = sumOfZero + 1; i < numbers.length; i++){
            if(numbers[i] <= pre){
                return false;
            }
            if(numbers[i] != pre + 1){
                int sub = numbers[i] - (pre + 1);
                if(sub > sumOfZero){
                    return false;
                }else{
                    sumOfZero -= sub;
                }
            }
            pre = numbers[i];
        }
        return true;
    }

}
