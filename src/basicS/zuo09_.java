package basicS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class zuo09_ {

	/**
	 * 生成窗口最大值数组
	 * 有一个整型数组arr和大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
	 * 对于数组：[4,3,5,4,3,3,6,7]，窗口大小为3，那么首次出现时窗口是：4,3,5
	 * 然后是3,5,4，直到最后窗口中是3,6,7
	 * 写一个方法，入参为数组arr和w，求一个大小为n-w+1的数组，这个数组代表每个位置下窗口中的最大元素
	 * 此时应该返回[5,5,5,4,6,7]
	 *  
	 * 给定一个数组arr和数num， 求最大值减去最小值 《= num的子数组数量，要求时间复杂度ON
	 * 
	 * 单调栈
	 * 求一个数组中各位置中左边离该位置最近的比它大的数和右边离该位置最近的比它大的数
	 * 用两个integer数组表示出来，如果没有就置位null,要求时间复杂度On，假设数组中没有重复元素
	 * 
	 * 求最大直方图面积
	 * 假设有一个数组4,3,2,5,1，数组中没有重复值，这个数组每一个数代表直方图的高度
	 *                  |
	 *            |     |
	 *            | |   |
	 *            | | | |
	 *            | | | | | 
	 * 求这个直方图中最大的矩形面积，这里是8,要求时间复杂度ON
	 * 
	 * 求最大子矩阵的大小
	 * 给一个矩阵只有0和1，求这个矩阵中全是1的最大子矩阵的面积
	 * 
	 * 求山的对数
	 * 有这样一个数组，这些数组中的所有数构成一个环，每个数都是一座山的高度，假设站在一座山上能看到另一座山就说这两座山
	 * 组成一对，求这样的对一共有多少，所谓能看到就是路径上所有山的最大值也小于等于这两座山的高度中较小的那座
	 * 因为是环所以这样的路径有两条，可以顺时针看或逆时针看，山的高度可能出现相同值
	 */
	public static void main(String[] args) {
		//System.out.println(Arrays.toString(getMaxInWindow(new int[]{4,3,5,4,3,3,6,7}, 3)));
		int[] arr = MyArrays.creatArray(5, 0, 10);
		//int[] arr = {8, 2, 4, 9, 7};
		System.out.println(Arrays.toString(arr));
		//System.out.println(getSelectNum(arr, 5));
		//int[] arr = {4,3,2,5,1};
		//Integer[][] twoArrays = getTwoArrays(arr);
		//for (Integer[] integers : twoArrays) {
		//	System.out.println(Arrays.toString(integers));
		//}
		//System.out.println(getMaxArea(arr));
		//int[][] matrix = MyArrays.creatMatrix(5, 5, 0, 1);
		//for (int[] is : matrix) {
			//System.out.println(Arrays.toString(is));
		//}
		//System.out.println(getMaxSubMatrix(matrix));
		System.out.println(getMountain(arr));
	}

	//生成窗口最大值数组
	// * 有一个整型数组arr和大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑动一个位置
	// * 对于数组：[4,3,5,4,3,3,6,7]，窗口大小为3，那么首次出现时窗口是：4,3,5
	// * 然后是3,5,4，直到最后窗口中是3,6,7
	// * 写一个方法，入参为数组arr和w，求一个大小为n-w+1的数组，这个数组代表每个位置下窗口中的最大元素
	// * 此时应该返回[5,5,5,4,6,7]
	
	//设置了一个双向队列完成这个方法，每次从数组中取出新值观察队列队尾是不是比这个数大，如果大就加入队尾，否则从队尾
	//将数取出，然后继续检查队尾直至队列中为空或队尾比该数大，加入队尾
	//每次窗口移动有数要取出时，检查队头的数下标是不是过期，然后取出队头的数
	//这个队列实现了在减小数据量的时候时时刻刻能取出最大值，队列存的是数组中的下标
	public static int[] getMaxInWindow(int[] arr, int size){
		if(arr == null || arr.length < size || size <= 0){
			return null;
		}
		int[] newArr = new int[arr.length - size + 1];
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < arr.length; i++){
			while(!list.isEmpty() && arr[list.peekLast()] <= arr[i]){
				list.pollLast();
			}
			list.addLast(i);
			if(list.peekFirst() == i - size){
				list.pollFirst();
			}
			if(i >= size - 1){
				newArr[i - size + 1] = arr[list.peekFirst()];
			}
		}
		return newArr;
	}
	
	 //* 给定一个数组arr和数num， 求最大值减去最小值 《= num的子数组数量，要求时间复杂度ON
	
	//建立两个双端队列实现窗口时时刻刻能取出最大值和最小值
	//窗口左边界为L右边界为R，然后每次右边界推进，推进过程中如果发现LR之间的数不满足要求，更新sum值
	//这种更新的依据是，如果产生不满足要求的数组，那么继续扩充依然不满足要求
	//这样一次性确定了以L开头的子数组有多少满足条件，接下来继续推进L，直至L到达右边界
	public static int getSelectNum(int[] arr, int num){
		LinkedList<Integer> maxList = new LinkedList<Integer>();
		LinkedList<Integer> minList = new LinkedList<Integer>();
		int sum = 0;
		int L = 0;
		int R = 0;
		while(L <= arr.length - 1){
			if(!maxList.isEmpty() && maxList.peekFirst() == L - 1){
				maxList.pollFirst();
			}
			if(!minList.isEmpty() && minList.peekFirst() == L - 1){
				minList.pollFirst();
			}
			while((maxList.isEmpty() || arr[maxList.peekFirst()] - arr[minList.peekFirst()] <= num) 
					&& R < arr.length){
				while(!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[R]){
					maxList.pollLast();
				}
				maxList.addLast(R);
				while(!minList.isEmpty() && arr[minList.peekLast()] >= arr[R]){
					minList.pollLast();
				}
				minList.addLast(R);
				R++;
			}
			sum += (arr[maxList.peekFirst()] - arr[minList.peekFirst()] <= num)
					? (R - L) : (R - L - 1);
			L++;
		}
		return sum;
	}
	
	//单调栈
	// * 求一个数组中各位置中左边离该位置最近的比它大的数和右边离该位置最近的比它大的数
	// * 用两个integer数组表示出来，如果没有就置位null,要求时间复杂度On，假设数组中没有重复元素
	
	//建立一个栈，将数组元素依次放入栈中，每次要求栈中从底到顶都是从大到小，每次满足这个规律就进栈，否则使小于新数的部分出栈
	//每次出栈时记录下：出栈时是因为哪个数要进栈，这个数就是右边离该位置最近的比它大的数
	//出栈后的栈顶，这个数就是左边离该位置最近比它小的数
	//如果不存在出栈的原因，只是因为没有新的数了，那么置为null
	//如果出栈后栈为空，对应位置也置为null
	public static Integer[][] getTwoArrays(int[] arr){
		Integer[][] integerArray = new Integer[2][arr.length];
		Stack<Integer> stack = new Stack<Integer>();
		int k;
		for(int i = 0; i < arr.length; i++){
			System.out.println(stack);
			System.out.println(arr[i]);
			while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
				System.out.println("in");
				k = stack.peek();
				integerArray[1][k] = arr[i];
				stack.pop();
				integerArray[0][k] = stack.isEmpty() ? null : arr[stack.peek()];
			}
			stack.push(i);
		}
		while(!stack.isEmpty()){
			k = stack.peek();
			integerArray[1][k] = null;
			stack.pop();
			integerArray[0][k] = stack.isEmpty() ? null : arr[stack.peek()];
		}
		return integerArray;
	}
	
	//求最大直方图面积，假设有一个数组4,3,2,5,1，数组中没有重复值，这个数组每一个数代表直方图的高度
	// *                  |
	// *            |     |
	// *            | |   |
	// *            | | | |
	// *            | | | | | 
	// * 求这个直方图中最大的矩形面积，这里是8,要求时间复杂度ON
	
	//建立一个单调栈，要求栈底到栈顶是从小到大的，然后每次寻找一个值的左边离他最近的比它小的和右边离它最近的比它小的
	//每次弹出更新以此数为基本单位刷出的矩阵大小
	public static int getMaxArea(int[] arr){
		int max = Integer.MIN_VALUE;
		int k, leftPos, rightPos;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < arr.length; i++){
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
				k = stack.peek();
				stack.pop();
				rightPos = i;
				leftPos = stack.isEmpty() ? -1 : stack.peek();
				max = Math.max((rightPos - leftPos - 1) * arr[k], max);
			}
			stack.push(i);
		}
		while(!stack.isEmpty()){
			k = stack.peek();
			stack.pop();
			rightPos = arr.length;
			leftPos = stack.isEmpty() ? -1 : stack.peek();
			max = Math.max((rightPos - leftPos - 1) * arr[k], max);
		}
		return max;
	}
	
	//求最大子矩阵的大小
	// * 给一个矩阵只有0和1，求这个矩阵中全是1的最大子矩阵的面积
	
	//这题可以用求最大直方图面积的方法轻松完成，建立一个辅助数组与矩阵的一行大小相同
	//每次遍历一行，每次如果该位置是0那么就将b对应位置置为0，如果是1就将b加一
	//然后调用求最大直方图面积方法，在进行下一行
	//相当于做了n次（行）求最大直方图面积
	public static int getMaxSubMatrix(int[][] matrix){
		int[] b = new int[matrix[0].length];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				b[j] = matrix[i][j] == 0 ? 0 : b[j] + 1;
				
			}
			max = Math.max(max, getMaxArea(b));
		}
		return max;
	}
	
	//求山的对数
	// * 有这样一个数组，这些数组中的所有数构成一个环，每个数都是一座山的高度，假设站在一座山上能看到另一座山就说这两座山
	// * 组成一对，求这样的对一共有多少，所谓能看到就是路径上所有山的最大值也小于等于这两座山的高度中较小的那座
	// * 因为是环所以这样的路径有两条，可以顺时针看或逆时针看，山的高度可能出现相同值
	
	//如果数组中所有值都不相等，那么这个对数就是2n-3，可以先假定最高峰和次高峰然后分析其他情况易得
	
	//先遍历一遍找到最大值，将最大值加入栈中，然后开始从下一个值加入，这是一个单调栈，每次栈弹出时，
	//弹出的原因和下方两个值一定是弹出的值周围离他最近的比它大的，这样就构成了2对
	//如果弹出的值重叠了k次，那么就是k个之间计算一次，k往两边在计算一次，共C以k为底2+2*k次
	//最后遍历一周回到原始位置开始最后的弹出，在弹出倒数第二个值时检查倒数第一个是否重载了2次或2次以上作出相应处理
	//最后一项只有内部成对，最后将累加值返回
	
	//因为先将最大值入栈，才简化了整个过程
	public static int getMountain(int[] arr){
		int maxIndex = 0;
		for(int i = 1; i < arr.length; i++){
			maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex ;
		}
		Stack<mountain> stack = new Stack<>();
		int sum = 0, times;
		int index = nextIndex(arr.length - 1, maxIndex);
		stack.push(new mountain(arr[maxIndex], 1));
		while(index != maxIndex){
			while(!stack.isEmpty() && stack.peek().value < arr[index]){
				times = stack.peek().num;
				sum += numC(2, times) + 2 * times;
				stack.pop();
			}
			if(stack.peek().value == arr[index]){
				stack.peek().num++;
			}else{
				stack.push(new mountain(arr[index], 1));
			}
			index = nextIndex(arr.length - 1, index);
		}
		
		while(!stack.isEmpty()){
			times = stack.pop().num;
			sum += numC(2, times);
			if(stack.size() >= 2){
				sum += 2 * times;
			}else if(!stack.isEmpty() && stack.size() == 1){
				sum += (stack.peek().num > 1 ? 2 * times : times);
			}
		}
		
		return sum;
	}
	
	private static int numC(int i, int times) {
		
		return times < 2 ? 0 : times * (times - 1)/2;
	}

	private static int nextIndex(int size, int index){
		return index == size ? 0 : index + 1;
	}
}
class mountain{
	int value;
	int num;
	mountain(int index, int num){
		this.value = index;
		this.num = num;
	}
}


