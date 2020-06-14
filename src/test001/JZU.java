package test001;

public class JZU {
    public static boolean Find(int target, int [][] array) {
        
        if(target < array[0][0] || target > array[array.length - 1][array[0].length - 1]){
            return false;
        }
        return Find(target, array, 0, 0,  array.length - 1, array[0].length - 1);
    }
    
    private static boolean Find(int target, int [][] array, int hei_low, int wid_low, int hei_high, int wid_high) {
            
        if(!(hei_low == hei_high && wid_low == wid_high)){
            int wid_mid = (wid_low + wid_high)/2;
            int hei_mid = (hei_low + hei_high)/2;
            
            if(array[hei_mid][wid_mid] > target){
                return Find(target, array, hei_low, wid_low, hei_mid, wid_mid);
            }else if(array[hei_high][wid_mid] < target && array[hei_mid][wid_high] < target && array[hei_high][wid_high] > target){
                return Find(target, array, hei_mid, wid_mid, hei_high, wid_high);
            }else if(target == array[hei_mid][wid_mid] || target == array[hei_high][wid_mid] || 
                    target == array[hei_mid][wid_high] || target == array[hei_high][wid_high]){
                return true;
            }else{
                if(Find(target, array, hei_low, wid_mid, hei_mid, wid_high) || Find(target, array, hei_mid, wid_low, hei_high, wid_mid)){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return array[hei_low][wid_low] == target;
        }
    }
}