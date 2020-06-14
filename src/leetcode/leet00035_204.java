package leetcode;

public class leet00035_204 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	//申请一个和要判断的数等大的数组
    //每次遇到非质数，就把他的整数倍位置全都变为true
    //每个数是否是质数取决于它之前的所有数
    //最后返回false的位置个数即可
    public int countPrimes(int n) {
        int num = 0;
        boolean[] flag = new boolean[n];
        for(int i = 2; i < n; i++){
            if(!flag[i]){
                num++;
                for(int j = 2; i * j < n; j++){
                    flag[i * j] = true;
                }
            }
        }
        return num;
    }
}
