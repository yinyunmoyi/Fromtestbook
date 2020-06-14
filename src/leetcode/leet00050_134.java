package leetcode;

public class leet00050_134 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	 //最垃圾的算法，构造了一个辅助数组来快速计算每个路径的代价
    //但是还是需要一个双层循环
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] nextLoad = new int[gas.length];
        int sum = 0;
        for(int i = 0; i < gas.length; i++){
            nextLoad[i] = -1 * cost[i] + gas[(i + 1) < gas.length ? (i + 1) : 0];
            sum += nextLoad[i];
        }
        if(sum < 0){
            return -1;
        }
        for(int i = 0; i < gas.length; i++){
            if(isOK(i, gas[i], cost, nextLoad)){
                return i;
            }
        }
        return -1;
    }
    
    private boolean isOK(int start, int value, int[] cost, int[] nextLoad){
        int posi = start;
        do{
            int nextPosi = ((posi + 1) < cost.length ? posi + 1 : 0);
            if(value < cost[posi]){
                return false;
            }
            value += nextLoad[posi];
            posi = nextPosi;
        }while(posi != start);
        int prePosi = ((posi - 1) >= 0 ? posi - 1 : cost.length - 1);
        return value - nextLoad[prePosi] >= cost[prePosi];
    }
    
  //从前向后遍历一遍，如果加出来结果为0就重新开始计数
    //这种算法基于这样一种思想：如果加出来某个数小于0，那么从开始到该位置所有位置
    //都不可能是问题的答案，这个证明可以用一个图像来证明
    //将gas值作为纵坐标，i为横坐标，cost为跳跃值
    //如果从某个位置开始最后降到了0以下，那么从中间某个位置开始一定也会降到0以下
    //这个零下比之前还要更小，因为对于每一个位置，上一个位置继承下来的结果都是正的
    //这样都不满足条件，从中间某个位置开始也一定不满足条件
    //同时还有一个限制，就是sum必须大于等于0，这样才可能有满足要求的可能
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int start = 0, sum = 0, k = 0;
        for(int i = 0; i < gas.length; i++){
            sum += (gas[i] - cost[i]);
            k += (gas[i] - cost[i]);
            if(k < 0){
                start = i + 1;
                k = 0;
            }
        }
        return (sum < 0) ? -1 : start;
    }

}
