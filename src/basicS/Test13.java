package basicS;

public class Test13 {

	/**
	 * 用递归求一个数组的主元素
	 * 所谓主元素就是出现次数超过N/2次的元素，如果没有主元素也请指出
	 */
	public static void main(String[] args) {
		int[] a = {8,8,9,8,9,9,8};
		int[] b = {8,8,8,9,9,7,7};
		int[] c = {8,8,9,8,9,9};
		int[] d = {8,8,8,8,1,1};
		
		int e = maxi(a);
				
		}

	private static int maxi(int[] a) {
		
		if(a.length == 1){
			return a[0];
		}
		
		int[] b = new int[a.length];
		int k = 0;
		for(int i = 0;i < a.length;i = i + 2){
			//如果还剩最后一个元素，把他直接放入新数组
			if(i == a.length - 1){
				b[k] = a[i];
				break;
			}
			//如果两个相邻的元素相等，就把他放入新数组中
			if(a[i] == a[i+1]){
				b[k] = a[i];
				k++;
			}
		}
		return maxi(b);
	}
}

