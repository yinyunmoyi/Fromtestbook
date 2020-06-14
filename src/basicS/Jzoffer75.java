package basicS;

public class Jzoffer75 {

	/**
	 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
	 * 要求时间复杂度ON，空间复杂度O1
	 */
	public static void main(String[] args) {

	}
	
	//这个算法用了异或的性质，如果一个数组中只有一个数字出现过一次，其余数字每个都出现偶数次，那么异或和一定是那个数字
	//这道题里先求异或和，因为非偶数次的数字出现了两个，所以最后的值一般不会是要找的数字
	//拿到这个异或和先从右向左第一个1的位置，记录下这个位置，再遍历这个数组，把这个位置为1的和为0的分开异或
	//两组异或的结果就分别是要找的数，因为首先相同的数字肯定会被分到一组，其次要找的数字肯定会分到不同的两组
	//因为异或的结果是1代表本位两个数一定不同，这样就可以得到结果
	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array == null || array.length == 0){
            return;
        }
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            sum ^= array[i];
        }
        int posi = index(sum);
        int sum1 = 0, sum2 = 0;
        for(int i = 0; i < array.length; i++){
            if(isOk(array[i], posi)){
                sum1 ^= array[i];
            }else{
                sum2 ^= array[i];
            }
        }
        num1[0] = sum1;
        num2[0] = sum2;
    }
    
    public int index(int num){
        int k = 0;
        while((num&1) != 1){
            num = num >> 1;
            k++;
        }
        return k;
    }
    
    public boolean isOk(int num, int index){
        num = num >> index;
        return (num&1) == 1 ? true : false;
    }

}
