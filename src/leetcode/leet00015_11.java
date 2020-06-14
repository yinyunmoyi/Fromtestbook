package leetcode;

public class leet00015_11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//初始解法，用双层循环计算所有的可能结果
	public static int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
		for(int i = 0; i < height.length - 1; i++) {
        	for(int j = i + 1; j < height.length; j++) {
        		max = Math.max(Math.min(height[i], height[j]) * (j - i), max);
        	}
        }
		return max;
    }
	
	//改进算法，一个算法复杂度On的算法
	//它省掉了一些不可能出现的情况
	//分析这个可以用矩阵分析，矩阵的行和列都是数组的index
	//如果一个位置的行是x，列是y，该位置就代表从x到y的水的体积
	//由定义可知，对角线及对角线以下都是无意义的，无需计算
	//从右上角开始计算，如果右上角的hx小与hy，那么第一行都没有意义了无需计算
	//因为第一行相当于1位置到其他位置的水的体积，而1的高度又小与右边界的高度
	//所以无论怎么拓展这些高度都会小与右边界向左拓展的高度
	//同理如果右高左低，那么一列都会无效，这样就能把复杂度降低到ON
	public int maxArea1(int[] height) {
        int l=0, h=height.length - 1, max=0;
        
        int res = 0;
        while(l < h){
            if(height[h] >= height[l]){
                res = height[l] * (h - l);
                l++;
            }else{
                res = height[h] * (h - l);
                h--;
            }
            
            max = res > max? res : max;
        }
        
        return max;
    }
}
