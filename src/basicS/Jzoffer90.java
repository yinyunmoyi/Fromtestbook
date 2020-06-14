package basicS;

public class Jzoffer90 {

	//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
	//如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
	
	//符合要求的序列末尾的值一定是头结点，头结点为界限把之前的数分成了比它小和比它大两部分
	//如果划分满足要求，就继续递归
	public static void main(String[] args) {
		System.out.println(VerifySquenceOfBST1(new int[] {1,3,2,5,7,6,4}));
	}
	
	
	public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0){
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }
    
    public boolean VerifySquenceOfBST(int[] arr, int start, int end){
        if((end - start) <= 1){
            return true;
        }
        int i = start;
        while(i < end && arr[i] < arr[end]){
            i++;
        }
        int nextStart = i;
        int nextEnd = ((start == i) ? start : (i - 1));
        while(i <= end){
            if(arr[i] < arr[end]){
                return false;
            }
            i++;
        }
        return VerifySquenceOfBST(arr, start, nextEnd) && VerifySquenceOfBST(arr, nextStart, end - 1);
    }
    
    public static boolean VerifySquenceOfBST1(int [] sequence) {
        if(sequence == null){
            return false;
        }
        return VerifySquenceOfBST1(sequence, 0, sequence.length - 1);
    }
    
    public static boolean VerifySquenceOfBST1(int[] arr, int start, int end){
        System.out.println(start + " " + end);
    	if(start >= end){
            return true;
        }
        int posi = 0;
        int i = start;
        while(i < end && arr[i] < arr[end]){
            i++;
        }
        if(i == end){
            return VerifySquenceOfBST1(arr, start, end - 1);
        }else{
            posi = i;
            while(i < end){
                if(arr[i] < arr[end]){
                    return false;
                }
                i++;
            }
            return VerifySquenceOfBST1(arr, start, posi - 1)&&
                VerifySquenceOfBST1(arr, posi, end - 1);
        }
    }
}
