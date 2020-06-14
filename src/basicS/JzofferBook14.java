package basicS;

public class JzofferBook14 {

	
	
	//再走一遍
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//股票的最大利润
	//某股票的价格按照先后时间顺序存在一个数组中
	//例如{9,11,8,5,7,12,16,14}，如果能在价格为5时买入并在价格为16时卖出就能获得最大利润
	//求最大利润
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getLi(new int[] {9,11,8,5,7,12,16,14}));
	}
	
	//用一个值存最小值即可，因为最小值的范围只大不小，所以没有必要用队列
	public static int getLi(int[] res) {
		int min = Integer.MAX_VALUE;
		int maxRes = Integer.MIN_VALUE;
		for(int i = 0; i < res.length; i++) {
			maxRes = Math.max(maxRes, res[i] - min);
			min = Math.min(min, res[i]);
		}
		return maxRes < 0 ? 0 : maxRes;
	}

}
