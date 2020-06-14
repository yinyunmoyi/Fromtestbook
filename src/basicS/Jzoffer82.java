package basicS;

import java.util.Arrays;
import java.util.Comparator;

public class Jzoffer82 {

	//输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	//例如输入数组{3，32，321}，
	//则打印出这三个数字能排成的最小数字为321323。
	
	//使用一个比较器即可，一个正确比较规则有三个性质，自反性：0为相等、对称性：a》b则b《a，传递性
	public String PrintMinNumber(int [] numbers) {
        Integer[] arr = new Integer[numbers.length];
        for(int i = 0; i < arr.length; i++){
            arr[i] = numbers[i];
        }
        Arrays.sort(arr, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                String str1 = i1.toString() + i2.toString();
                String str2 = i2.toString() + i1.toString();
                return str1.compareTo(str2);
            }
        });
        String str = "";
        for(int i = 0; i < arr.length; i++){
            str = str + arr[i];
        }
        return str;
    }
}
