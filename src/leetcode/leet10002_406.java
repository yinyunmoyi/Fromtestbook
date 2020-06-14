package leetcode;
import java.util.*;

public class leet10002_406 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//初始方案，首先将people封装成一个数组，然后对其进行排序，排序的逻辑
    //就是优先按位置排序，然后按高度排序
    //然后从位置小到大的顺序遍历所有数据
    //在遍历的过程中插入准备好的链表中，因为插入操作频繁所以用链表
    //插入时从第一个位置开始后移，移动到满足要求为止
    //最后将链表再次转换为数组
    public int[][] reconstructQueue(int[][] people) {
        People[] p = new People[people.length];
        for(int i = 0; i < people.length; i++){
            p[i] = new People(people[i][0], people[i][1]);
        }
        Arrays.sort(p, new Comparator<People>() {
            public int compare(People p1, People p2){
                if(p1.posi != p2.posi) {
                	return p1.posi - p2.posi;
                }
            	return p2.height - p1.height;
            }
        });
        LinkedList<People> list = new LinkedList<>();
        for(int i = 0; i < p.length; i++){
            int num = 0;
            int times = p[i].posi;
            for(People pl : list){
                if(times > 0 && pl.height >= p[i].height){
                	times--;
                }else if(times == 0){
                    break;
                }
                num++;
            }
            list.add(num, p[i]);
        }
        int k = 0;
        for(People pl : list){
            people[k][0] = pl.height;
            people[k++][1] = pl.posi;
        }
        return people;
    }
    
      //改进版本，先对数据进行排序，排序方式就是先把身高高的挑出来，身高相同的位置小的放在前面
  	  //然后依次加入链表即可
      public int[][] reconstructQueue1(int[][] people) {
          Arrays.sort(people, new Comparator<int[]>(){
  			public int compare(int[] arr1, int[] arr2) {
  				return arr1[0] != arr2[0] ? arr2[0] - arr1[0] : arr1[1] - arr2[1];
  			}
  		});
  		for(int[] arr : people) {
  			System.out.println(Arrays.toString(arr));
  		}
  		LinkedList<int[]> list = new LinkedList<>();
  		for(int[] p : people) {
  			list.add(p[1], p);
  		}
  		return list.toArray(people);
      }
}

class People{
    int height;
    int posi;
    People(int height, int posi){
        this.height = height;
        this.posi = posi;
    }
}
