package leetcode;

import java.util.HashMap;

public class leet00038_454 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	//因为这道题只需要求满足要求的数的组数
	//所以用一个map来记录前两个数组的和还有次数
	//然后另外两个数组的数字遍历自由组合，如果能在map中找到对应负值，就加上对应数字的次数
	//直至遍历结束，这种方式只适合处理那种只需要几组而不需要给出四个数字的问题
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int res = 0;
        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                sum = C[i] + D[j];
                res += map.getOrDefault(-1 * sum, 0);
            }
        }
        return res;
    }
}
