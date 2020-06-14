package basicS;

public class Jzoffer01 {

	/**
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
	 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
	 * 判断数组中是否含有该整数。
	 */
	public static void main(String[] args) {

	}

	//从左下角往上搜索，右移或上移直到找到对应元素
	//右下角不能一次判断决定走向但是左下角可以
	//169 ms	15480K
	public static boolean Find_a(int target, int [][] array) {
        
        int i = array.length - 1;
        int j = 0;
        while(i >= 0 && j < array[0].length){
            if(target > array[i][j]){
                j++;
            }else if(target < array[i][j]){
                i--;
            }else{
                return true;
            }
        }
        
        return false;
    }
	
	//逐行遍历，每行使用二分查找
	//193 ms	17596K
	public static boolean Find(int target, int [][] array) {
        
        int high, low, mid;
        for(int i = 0; i < array.length; i++){
            high = array[0].length - 1;
            low = 0;
            while(low <= high){
                mid = (high + low)/2;
                if(array[i][mid] > target){
                    high = mid - 1;
                }else if(array[i][mid] < target){
                    low = mid + 1;
                }else{
                    return true;
                }
            }
        }
        
        return false;
    }
}
