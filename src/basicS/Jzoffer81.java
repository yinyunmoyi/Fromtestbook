package basicS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Jzoffer81 {

	/**
	 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
	 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 
	 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
	 */
	public static void main(String[] args) {
		//System.out.println(GetUglyNumber_Solution(11));
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('a', 1);
		map.put('b', 2);
		map.put('c', 3);
		for (Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	//将所有的丑数都放在一个数组里，依次计算丑数依靠三个辅助位置
	//一个新的位置总是t2位置的数乘2，或t3位置的数乘3，或t5位置的数乘5中的最小值
	//乘出正确值之后，再更新t2、t3、t5位置，这样就保证了所有因数都是2、3、5
	public static int GetUglyNumber_Solution(int index) {
        int i = 1;
        if(index == 1){
            return 1;
        }
        int[] arr = new int[index];
        arr[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0;
        while(i < index){
        	arr[i] = min(arr[t2] * 2, arr[t3] * 3, arr[t5] * 5);
            while(arr[i] >= arr[t2] * 2){
                t2++;
            }
            while(arr[i] >= arr[t3] * 3){
                t3++;
            }
            while(arr[i] >= arr[t5] * 5){
                t5++;
            }
            i++;
        }
        return arr[arr.length - 1];
    }
    
    public static int min(int t1, int t2, int t3){
        int k = Math.min(t1, t2);
        return Math.min(k, t3);
    }
}
