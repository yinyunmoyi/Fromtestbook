package leetcode;
import java.util.*;

public class leet00061_218 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//首先应该先把数据转换为点，一个大楼转换为两个点，一个左角点一个右角点
	//应该想到的是把这些点按照位置循环然后生成结果
	//但是生成结果的过程中不能单纯按照两个点的高低顺序来生成拐点
	//因为在有高水平线的作用下，低点就会被全部忽略，如果高点的右点达到
	//那么低水平线就会被暴露出来，也就是说结果的生成和当前楼的最高高度息息相关
	//首先将数据转换为一个个的点，为了区分左点还是右点，把左点的高度设置为负值
	//然后对集合进行排序，位置靠前的在前面，位置相同的高度低的在前面
	//然后建立一个记录最高高度的堆heightQueue，将0高度放入，这是因为当所有点都排除的时候
	//高度为0的点也要加入res，设置一个变量pre，表示之前的最高高度
	//开始遍历后，如果是左点，就将其加入最高高度堆，否则就将高度排除
	//如果当前最高高度和pre不同，就说明有拐点出现
	//此时加入结果res，并更新pre
	public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < buildings.length; i++){
            list.add(new int[]{buildings[i][0], -buildings[i][2]});
            list.add(new int[]{buildings[i][1], buildings[i][2]});
        }
        Collections.sort(list, new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2){
                if(o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        Queue<Integer> heightQueue = new PriorityQueue<>(new Comparator<Integer>() {
        	public int compare(Integer a, Integer b) {
        		
        		return (b - a);
        	}
        });
        heightQueue.offer(0);
        int pre = 0;
        for(int[] arr : list){
            if(arr[1] < 0){
                heightQueue.offer(-arr[1]);
            }else{
                heightQueue.remove(arr[1]);
            }
            int cur = heightQueue.peek();
            if(cur != pre){
                ArrayList<Integer> slist = new ArrayList<>();
                slist.add(arr[0]);
                slist.add(cur);
                res.add(slist);
                pre = cur;
            }
        }
        return res;
    }
}
