package leetcode;

import java.util.*;
public class leet00040_378 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	
	
	
	
	
	
	
	
	//初始版本，设置一个堆，然后根据0,0位置的指针开始移动】
	//每次把堆顶的元素弹出然后将它周围的元素再次放入堆中
	//因为某些元素可能出现重复加入堆，所以又建立了一个boolean矩阵
	//来标记加入堆的元素
	public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Posi> queue = new PriorityQueue<>(new Comparator<Posi>() {
            @Override
            public int compare(Posi o1, Posi o2) {
                return o1.val - o2.val;
            }
        });
        boolean[][] arr = new boolean[matrix.length][matrix[0].length];
        queue.offer(new Posi(0,0,matrix[0][0]));
        arr[0][0] = true;
        for(int i = 0; i < k - 1; i++){
            Posi now = queue.poll();
            if(now.i < matrix.length - 1 && !arr[now.i + 1][now.j]){
                queue.offer(new Posi(now.i + 1, now.j, matrix[now.i + 1][now.j]));
                arr[now.i + 1][now.j] = true;
            }
            if(now.j < matrix[0].length - 1 && !arr[now.i][now.j + 1]){
                queue.offer(new Posi(now.i, now.j + 1, matrix[now.i][now.j + 1]));
                arr[now.i][now.j + 1] = true;
            }
        }
        return queue.poll().val;
    }
	
	//改进版本，这次加入堆的形式改变了
	//一次性将首行的元素加入堆中，然后从堆中取
	//取出的元素的下一行元素也放入堆中，这样一样能保证按照大小顺序存元素
	//不会出现某个元素的右边元素没有添加进堆中的情况
	//因为这样该元素必然上方的元素会加入堆中
	//有了这样的选择策略，就不用再用boolean矩阵了
	public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
	
	//二分法求法，对于一个排序矩阵而言，找到一个数的位置是很简单的
	//所以就用二分法找使小数为左上角的数，大数为右下角的数
	//然后mid取两者的平均值，然后根据平均值在矩阵中的排位count和k比较
	//然后决定再次的二分
	//确定一个数在矩阵中的排位需要从右上角遍历起，然后如果该数比mid大
	//就向左移动，直至比mid小，然后向下移动，每一次记录这个j值
	//就可以推导出排位count
	public int kthSmallest2(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}

class Posi{
    int i;
    int j;
    int val;
    public Posi(int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
