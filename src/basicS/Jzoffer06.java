package basicS;

public class Jzoffer06 {

	/**
	 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
	 *  输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
	 *  例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
	 *  NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
	 */
	public static void main(String[] args) {
		
	}

	//313 ms	27972K
	//这种方法是简单遍历，遍历整个数组，如果一旦后一个数比前面数小
	//就直接返回这个比较小的数，否则就是数组没有旋转，就返回第一个元素
	public int minNumberInRotateArray(int [] array) {
        
        if(array == null){
            return 0;
        }
        int num = array[0];
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] > array[i + 1]){
                num = array[i + 1];
                break;
            }
        }
        
        return num;
    }
	
	//341 ms	28180K
	//用递归的形式完成
	public int minNumberInRotateArray_a(int [] array) {
        
        if(array == null){
            return 0;
        }
        return minNumberInRotateArray_a(array, 0, array.length-1);
    }
    
    public int minNumberInRotateArray_a(int [] array, int begin, int end){
        if(end - begin == 1){
            return array[begin] > array[end]?array[end]:array[begin];
        }
        
        int mid = (begin+end)/2;
        //如果mid数比begin大或小，就一定能确定到底最小数在哪边，直接递归
        if(array[mid] > array[begin]){
            return minNumberInRotateArray_a(array, mid, end);
        }else if(array[mid] < array[begin]){
            return minNumberInRotateArray_a(array, begin, mid);
            //如果mid数和begin数相等，此时无法确定最小数在哪边，故范围只缩小1
        }else{
            return minNumberInRotateArray_a(array, begin+1, end);
        }
    }
    
    //341 ms	28900K
    //把上面的转换为了非递归版本
    public int minNumberInRotateArray_c(int [] array) {
        
        if(array == null){
            return 0;
        }
        int begin = 0;
        int end = array.length, mid;
        while(begin < end){
            mid = begin + (end - begin)/2;
            if(array[mid] > array[begin]){
                begin = mid;
            }else if(array[mid] < array[begin]){
                end = mid;
            }else{
                begin = begin + 1;
            }
        }
        return array[begin];
    }
}
