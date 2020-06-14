package leetcode;

public class leet00048_395 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//这个解法是ON方的，它是一个双层循环，记录每一个i到j的字符串然后判断是不是满足要求
    //但是这里做了一个加速，也就是前一层循环的成功时最后一个index，下一层循环的起始就是index+1
    //因为这样做时是无法漏掉结果的
    //假设2-9是满足要求的，下一次4-11满足要求，这种情况是不可能出现的
    //假设上述情况出现了，那么有两种情况，4-11没有增加2-9中新的字母，如果是这样那么2-11一定满足要求
    //如果4-11增加了新的字母，那么必然是9-11增加的，新的字母依然出现次数大于k，2-11一样满足要求
    //所以就证明这样的加速是合理的
    //除此之外判断k也很巧妙
    //用一个数组来记录各字母出现的次数，如果次数小于3，就让数mask对应位置变为1
    //如果次数大于等于3就让对应位置变为0，这样满足要求的数最后一定是0
    //就可以省去hashmap并遍历的操作
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0 || k == 0){
            return 0;
        }
        int res = 0;
        for(int i = 0; i <= s.length() - k; ){
            int[] times = new int[26];
            int maxPosi = i;
            int mask = 0;
            for(int j = i; j < s.length(); j++){
                int posi = s.charAt(j) - 'a';
                times[posi]++;
                if(times[posi] < k){
                    mask |= (1 << posi);
                }else{
                    mask &= (~(1 << posi));
                }
                if(mask == 0){
                    res = Math.max(res, j - i + 1);
                    maxPosi = j;
                }
            }
            i = maxPosi + 1;
        }
        return res;
    }

}
