package leetcode;
import java.util.*;

public class leet00020_56 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//把所有数据按照开始点来排序，设置一组中间数据
	//遍历时对比该组数据并修正，因为是存入引用所以及时存入res中也能修改
	
	//这类问题都要先对数据进行正确的排序
	//注意一下转数组的方法，toArray参数穿的是返回类型
	public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
			return intervals;

		Arrays.sort(intervals, new Comparator<int[]>() {
			public int compare(int[] i1, int[] i2) {
				return i1[0] - i2[0];
			}
		});

		List<int[]> result = new ArrayList<>();
		int[] newInterval = intervals[0];
		result.add(newInterval);
		for (int[] interval : intervals) {
			if (interval[0] <= newInterval[1])
				newInterval[1] = Math.max(newInterval[1], interval[1]);
			else {                            
				newInterval = interval;
				result.add(newInterval);
			}
		}

		return result.toArray(new int[result.size()][]);
    }
}
