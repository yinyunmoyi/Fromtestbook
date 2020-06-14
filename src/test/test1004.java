package test;

public class test1004 {

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	 *  输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
	 *  例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
	 *  NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 *  
	 *  ￥(4min)
	 */
	public static void main(String[] args) {

	}
	
	public static int getXunFla(int[] arr){
		int begin = 0, end = arr.length - 1;
		while(begin < end){
			int mid = begin + (end - begin)/2;
			if(arr[mid] > arr[begin]){
				begin = mid;
			}else if(arr[mid] < arr[begin]){
				end = mid;
			}else{
				begin++;
			}
		}
		
		
		return arr[begin];
	}

}
