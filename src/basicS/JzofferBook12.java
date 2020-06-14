package basicS;

public class JzofferBook12 {

	/**
	 * 在一个数组中除了一个数只出现了一次，其余数都出现了三次，找到那个出现了一次的数
	 * 要求时间复杂度ON，空间复杂度O1
	 */
	public static void main(String[] args) {
		System.out.println(findOneNum(new int[]{2,2,3,6,6,5,5,5,2,6}));
	}

	//因为数组中除了要找的数都出现了3次，那么每一位的和一定是3的倍数，如果哪一位不是3的倍数
	//要找的数在这一位一定是1，其余是0
	//故创建一个32大小的数组，将每个数字每位都累加，在对比一遍，拿到要找的数
	//因为遍历的次数，数组的大小都是固定的，所以相当于空间复杂度O1
	public static int findOneNum(int[] arr){
		byte[] flag = new byte[32];
		int k = 0;
		for(int i = 0; i < arr.length; i++){
			k = 0;
			while(arr[i] != 0){
				flag[k++] += (byte)(arr[i]&1);
				arr[i] = arr[i] >> 1;
			}
		}
		int num = 0;
		for(int i = 0; i < 32; i++){
			if(flag[i] % 3 != 0){
				num += Math.pow(2, i);
			}
		}
		return num;
	}
}
