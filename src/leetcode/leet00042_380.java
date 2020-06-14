package leetcode;
import java.util.*;

public class leet00042_380 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	//打破了原来只能用两个map的方法
	//该方法是改进版本，用了一个map和一个list
	//每次remove时把list的对应位置的值设置为list最后一个位置的值
	//然后再删除，这样该方法的复杂度只有O1
	//随机取数通过随机出一个位置然后取数的方式实现
	ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public leet00042_380() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = locs.containsKey(val);
        if ( contain ) return false;
        locs.put( val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean contain = locs.containsKey(val);
        if ( ! contain ) return false;
        int loc = locs.get(val);
        if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
            int lastone = nums.get(nums.size() - 1 );
            nums.set( loc , lastone );
            locs.put(lastone, loc);
        }
        locs.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get( rand.nextInt(nums.size()) );
    }
}
