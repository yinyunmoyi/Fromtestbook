package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class leet00024_322 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//直接寻找DP思路，假定计算sum - 1的情况已知
    //求计算sum的情况，对数组的每个数i来说
   // 如果sum - i能达到，那么想要加到sum，次数就是dp[sum-coin]+1
    //把数组的每一位都遍历到，然后在这些可能到达的位置里找最小的
    public int coinChange(int[] coins, int amount) {
        if(amount<1) return 0;
        int[] dp = new int[amount+1];
        int sum = 0;
    
	    while(++sum<=amount) {
		    int min = -1;
    	    for(int coin : coins) {
    		    if(sum >= coin && dp[sum-coin]!=-1) {
    			    int temp = dp[sum-coin]+1;
    			    min = min<0 ? temp : (temp < min ? temp : min);
    		    }
    	    }
    	    dp[sum] = min;
	    }
	    return dp[amount];
    }
    
  //用队列实现的广度遍历
  //首先先将aim放入队列中，然后遍历所有数，把aim-n依次放入队列中
  //然后再持续这个过程，直到aim-n为0，这个过程可能陷入死循环，所以用一个boolean数组规定
  //同一个数只能进入队列一次
  //如果直到队列为空都没有结果那就返回-1
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[amount + 1];

        queue.offer(amount);
        visited[amount] = true;
        int currLevel = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                for (int coin : coins) {
                    int child = curr - coin;
                    if (child == 0) {
                        return currLevel;
                    } else if (child > 0 && !visited[child]) {
                        queue.add(child);
                        visited[child] = true;
                    }
                }
            }
            currLevel++;
        }

        return -1;
    }
}
