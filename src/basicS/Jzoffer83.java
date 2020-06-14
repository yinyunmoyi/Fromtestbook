package basicS;

public class Jzoffer83 {

	/**
	 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
	 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
	 * ACMer希望你们帮帮他,并把问题更加普遍化,
	 * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
	 */
	public static void main(String[] args) {
		//System.out.println(NumberOf1Between1AndN_Solution(10000));
	}

	//对于数字21345，首先先把它分成1346-21345考虑，首先先考虑万位上出现的1次数，
	//因为此时最高位是2，不是1，所以一次累加10000次，如果最高位是1，那么应该累加1346次
	//然后考虑除了万位以外的1次数，因为剩余4个位置可以随便取，应该累加2（万位）*4*1000次
	//然后递归考虑1345，直到最后
	//某次如果最高位是0，那么就直接返回0
	public int NumberOf1Between1AndN_Solution(int n) {
        if(n <= 0){
            return 0;
        }
        return count(new String(n + "").toCharArray(), 0);
    }
    
    public int count(char[] arr, int posi){
        if(arr.length == posi + 1){
            return arr[arr.length - 1] == '0' ? 0 : 1;
        }
        int sum = 0;
        if(arr[posi] - '0' > 1){
            sum += Math.pow(10, arr.length - posi - 1);
        }else if(arr[posi] - '0' == 1){
            sum += new Integer(new String(arr, posi + 1, arr.length - posi - 1)) + 1;
        }else{
            return 0;
        }
        sum += (arr[posi] - '0') * Math.pow(10, arr.length - 2 - posi) * (arr.length - 1 - posi);
        sum += count(arr, posi + 1);
        return sum;
    }
}
