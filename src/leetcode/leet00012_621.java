package leetcode;

import java.util.Arrays;

public class leet00012_621 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(leastInterval(new char[] {'A','A','A','B','B','B','C'},2));
	}

	//again
	
	
	
	
	
	
	
	
	
	
	
	
	//这是一个贪心问题，需要给出一个布局策略，首先应该意识到方案和顺序无关
	//先把最高频次的字母根据间隔放好，然后填充其他字母的位置
	//猜测其他字母在添加过程中一定是位置一个一个增加的，不会再出现空位
	//这样的话最后的排队长度有两种可能，第一空位没有被补满，此时答案只与最高频次结果相同
	//如果位置补满了，那么就等于字母的总个数，两者取较大值即可
	public static int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }
}
