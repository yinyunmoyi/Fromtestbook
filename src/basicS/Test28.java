package basicS;

import java.util.Arrays;

public class Test28 {

	/**
	 * 各类排序
	 * 简单排序、冒泡排序、优化冒泡排序、选择排序
	 * 插入排序、希尔排序、堆排序、合并排序
	 * 非递归合并排序、快速排序
	 */
	public static void main(String[] args) {
		int[] a = {17, 0, 1, 14, 15, 8, 12, 14, 2};
		int[] b = a.clone();
		System.out.println("初始"+Arrays.toString(a));
		easySort(a);
		System.out.println("排序后"+Arrays.toString(a));
		
		heapSort(b);
		System.out.println("最终"+Arrays.toString(b));
		//bubbleSort(a);
		//optBubbleSort(a);
		//selectionSort(a);
		//insertSort(a);
		//shellSort(a);
		//mergeSort(a);
		//optMergeSort(a);
		//quickSort(a);
	}
	
	public static void easySort(int[] a){
		
		int t, i, j;
		for(i = 0 ; i < a.length ; i++){
			for(j = i + 1; j < a.length ; j++){
				if(a[i] < a[j]){
					t = a[j];
					a[j] = a[i];
					a[i] = t;
				}
			}
		}
	}
	
	public static void bubbleSort(int[] a){
		int t, i, j;
		for(i = 0;i < a.length ; i++){
			for(j =a.length - 1 ; j > i;j--){
				if(a[j] > a[j - 1]){
					t = a[j];
					a[j] = a[j - 1];
					a[j - 1] = t;
				}
			}
		}
	}
	
	public static void optBubbleSort(int[] a){
		int t, i, j;
		boolean flag = true;
		for(i = 0 ; i < a.length && flag;i++){
			flag = false;
			for(j = a.length - 1 ; j > i ; j--){
				if(a[j] > a[j - 1]){
					t = a[j];
					a[j] = a[j - 1];
					a[j - 1] = t;
					flag = true;
				}
			}
		}
	}

	//选择排序是从第一项开始，寻找除了第一项以外的最大值下标，再把下标对应的值与第一项互换
	//这样每次都从剩下的位置寻找最大值，再交换，而不是次次交换提升了效率
	//选择排序把交换数据次数降低，提升了运行效率，选择排序 》 冒泡排序
	public static void selectionSort(int[] a){
		int t, max, i, j;
		for(i = 0; i < a.length ; i++){
			max = i;
			for(j = i + 1 ; j < a.length ; j++){
				//是赋值给下标，而不是直接影响值
				if(a[max] < a[j]){
					max = j;
				}
			}
			if(max != i){
				t = a[max];
				a[max] = a[i];
				a[i] = t;
			}
		}
	}
	
	//插入排序，原理是视第一项为有序项，从第二项开始依次使其加入序列
	//如果前方各项都比该值小，那就让各项都向后移位，直到找到正确的位置
	//将值加入该位置即可，全程都没有互换操作，有的只是简单的值传递
	//该方法性能更优越       插入排序 》 选择排序
	public static void insertSort(int[] a){
		int i,j,t;
		//循环从第二项开始，因为试第一项为有序项
		for(i = 1 ; i < a.length ; i++){
			//将a[i]的值记录下来，在后期值传递的过程中，原始的a[i]会被覆盖
			
			//这里吧第一个for循环的判断条件提上来，如果不满足就省去了赋值t和j的时间
			if(a[i - 1] < a[i]){
				t = a[i];
				//j从i-1开始向后递减查找a[j]与t的关系
				//如果t大，说明t值需要前移，前方元素需要向后移
				//在循环中通过值传递来让后一个元素等于前一个元素
				//然后更前方的元素再继续覆盖
				for(j = i - 1;a[j] < t && j >= 0;j--){
					a[j + 1] = a[j];
				}
				//在循环结束时，说明找到了a[i]的插入位置，此时所有元素都已经移动完成
				//只需在对应位置放入原来a[i]的值即可
				a[j + 1] = t;
			}
			
			
		}
	}
	
	//希尔排序，本质上是增强的插入排序
	//原理是取一个增量值，把整个序列按照增量值划分成几个部分
	//如增量值为5，则0 5 10 15 20一组，1 6 11 16 21 一组
	//把每组分别按照插入排序进行排序，这样增量值在递减过程中整个序列也趋于某种顺序
	//最后增量值为1，完成后及完成排序
	//希尔排序能把算法平均运行时间降到n的1.5次方
	public static void shellSort(int[] a){
		
		int t, j, d, i;
		//这里选取增量值为数组长的一半，且每次减少一半，增量值总会等于1，这一轮结束后循环结束
		for(d = a.length/2 ; d >= 1;d = d / 2){
			//i从d+1开始，因为属于这个序列的前一个值是1，它本身相当于有序
			//当i越界时循环结束，每次i加一
			for(i = d + 1 ; i < a.length ; i++){
				//如果满足这个表达式说明这一轮无需进行排序
				//待插入的值比前一个值小，说明它比前面的任何值都小，这自然满足要排序的目的
				//实质上这个是把第一次循环的条件提了上来，如果不满足就省去了赋值t和j的时间
				if(a[i] > a[i - d]){
					//把待插入元素值记录，以免被覆盖
					t = a[i];
					//循环开始，向前查找有没有项大于待插入元素，如果有就让整体元素向后移动
					for(j = i - d;j > 0 && t > a[j] ; j = j - d){
						a[j + d] = a[j];
					}
					//移动完毕后把待插入项放到正确的位置
					a[j + d] = t;
				}
			}
		}
	}
	
	//堆排序，原理是把数组整理成一个堆，这样第一个位置的元素自然是最大值或者最小值
	//把这个最值与数组最后一个位置调换，把前n-1个元素继续整理成一个堆
	//然后继续上面的过程，堆越来越小，直到数组有序
	//它把排序的平方运行时间降到了nlogn
	public static void heapSort(int[] a){
		
		int t, i, j;
		//这个循环的作用是将初始数组整理成一个小顶堆
		//只有整理成小顶堆，然后不断把第一个元素挪到最后一个才能形成从大到小的序列
		//要整理的元素从a.length/2开始，它是需要整理的第一个元素，是非叶子节点的最右值
		//叶子节点全都不用调整，i随着运行递减，直至i为0即调整堆顶元素，结束后即完成堆排序
		//这种整理堆排序的方法是从下至上的，每次运行时它下方的元素都处于满足小顶堆特性的状态
		for(i = a.length/2 - 1 ; i >= 0 ; i--){
			//自始至终处理范围都是a.length，因为初始阶段堆的大小并不变
			adjustDown(a , i , a.length);
		}
		System.out.println("此时已经生成堆"+Arrays.toString(a));
		//System.out.println("整理完毕");
		//真正的排序开始，一共进行n-1次调整即可
		//每次都把堆顶与堆最后一个互换位置，再调整剩下元素使其满足堆的性质
		for(j = 1 ; j < a.length ; j++){
			t = a[0];
			a[0] = a[a.length - j];
			a[a.length - j] = t;
			//随着运行，堆的大小在变小，处理堆的范围在缩小
			//每次互换后只需调用一次这个方法即可，因为除了堆顶元素下方元素都已经满足了堆的要求
			adjustDown(a , 0 , a.length - j);
			System.out.println("转换移动！"+Arrays.toString(a));
		}
	}
	
	//调整堆结构的方法，第二个参数是要调整的位置，第三个参数是堆的大小
	private static void adjustDown(int[] a , int i , int len){
		
		//把要调整的元素大小用t保存起来，防止被覆盖
		int t = a[i], j;
		//循环开始，j值从2i开始，2i正好是要调整元素的左孩子，每次循环之后j要乘2
		//因为调整通常不在一层进行，上下元素互换使原元素小于左右孩子后，
		//因此改变的左右孩子可能和他自己的左右孩子不满足堆的性质，需要再次调整
		//直至j等于数组大小时，一次调整结束
		//System.out.println("我是真的在执行");
		//System.out.println("此时的数组是" + Arrays.toString(a));
		for(j = 2 * i + 1 ; j < len ; j = j * 2 + 1 ){
			//a[j + 1] < a[j]说明右兄弟的值更小，它才是要与父母结点互换的结点
			//此时把j加一这样后面两者互换之后就可以满足小顶堆的性质了
			//j = len - 1 意味着左孩子是整个堆的最后一个元素
			//此时它没有兄弟元素故不必进行下列操作了
			//System.out.println("此时的j是谁？"+j);
			if(j < len - 1 && a[j + 1] < a[j]){
				j = j + 1;
			}
			//如果t > a[j]说明原要调整的结点比孩子结点大，此时把要调整的结点赋值为孩子结点
			//同时改变i为j，记录下最后一个改变的结点位置
			//在多次循环中，因为一次互换导致的子树也不满足堆的要求
			//要不断向下传递值
			if(t > a[j]){
				a[i] = a[j];
				i = j;
			}else{
				//如果本来就满足堆的性质则无需调整
				//直接跳出循环，此时它下方的元素已经全部满足堆的性质
				//无需进行下一步循环了
				break;
			}
		}
		//运行到这里通过一系列值传递，操作的最底层和相邻层一定有两个相同元素
		//此时利用记录下来的值把正确的值赋给它
		a[i] = t;
	}
	
	//合并排序，原理是将数组分成一半，前一半和后一半再分一半
	//直至分成单个元素，将它们两两排序再合并，2个合并为4个，这样下去直至合并成原数组
	//就像是多个叶子合成二叉树的过程，这里关键就是如何将两个数组合并成一个有序数组
	//合并排序的运行时间是nlogn，运行效果比较稳定
	//但需要n+logn的空间复杂度，不用递归后这个值可以减到n
	public static void mergeSort(int[] a){
		//这里用了另一个封装的函数，因为整个过程用递归完成
		mergeSort(a, 0, a.length - 1);
	}
	
	//mergeSort的第一个参数是数组，第二个参数是排序开始的位置，第三个参数是排序结束的位置
	private static void mergeSort(int[] a , int low , int high){
		
		//基线条件是low < high，只有当两个值相等时才不会继续调用本函数
		if(low < high){
			//计算mid，他是前后排序的分界线
			int mid = (low + high) / 2; 
			
			//将从low到mid排序
			mergeSort(a, low, mid);
			//将mid+1到high排序
			mergeSort(a, mid + 1, high);
			//最后把两者合并
			merge(a, low, mid, high);
		}
		
	}
	
	//合并函数，第一个参数是数组，后三个参数分别指示了待合并数组的位置
	//分别是low到mid和mid+1到high
	private static void merge(int[] a , int low , int mid ,int high){
		
		int i, j, k;
		//新建一个数组b，接下来的合并中，需要从这个数组中取值更改数组a中的值
		int[] b = new int[a.length];
		//把要合并的部分的b和a相等
		for(i = low ; i <= high ; i++){
			b[i] = a[i];
		}
		//循环开始，i和j分别是两个要排序数组的循环起点，k是一个指示a数组位置的变量，表示修改a值的位置
		//当两个待排序部分有一个已经遍历完成时，退出循环
		//最后每次把k加一
		for(i = low , j = mid + 1 , k = low ; i <= mid && j <= high ; k++){
			//两个待排序部分相互比较，较大的那个拿到a数组中，并把原来数的位置加1
			//以便下次继续比较
			if(b[i] >= b[j]){
				a[k] = b[i++];
			}else{
				a[k] = b[j++];
			}
		}
		//运行到这里意味着待排序部分的其中一个所有值都拿到了a数组，另一个还有部分值没有拿到
		//下面的两个循环就是哪个数组还没有运行完就继续把剩余的变量都依次拿到a数组
		while(i <= mid){
			a[k++] = b[i++];
		}
		while(j <= high){
			a[k++] = b[j++];
		}
	}
	
	//非递归的合并排序，将递归的算法空间复杂度进一步降低
	//这种方法创建了一个与a同大小的数组b，刚开始将大小为1的相邻有序序列重新排序
	//从a装入b中，然后取大小为2的相邻有序序列再重新排序从b装入a中
	//这样直到完成排序
	public static void optMergeSort(int[] a){
		//chg就是这个起中转作用的数组
		int[] chg = new int[a.length];
		//k是每次排序的有序序列大小，从1开始每次转换排序k都翻倍
		int k = 1;
		//循环的结束条件是当k大小超出数组大小，当k本身等于数组大小也可以
		while(k < a.length){
			//将a中大小为k的相邻有序序列重新排序，比如a中1,2,3,4，b中就是2,1,4,3
			//这就是k为1的状况
			mergePass(a, chg, k);
			//k翻倍，再将数组chg中的内容弄到a中来
			//k越界也没有关系，在方法内部不会执行任何操作
			k = k * 2;
			mergePass(chg, a, k);
			k = k * 2;
		}
	}
	
	//这个方法代表将a中将大小为k的相邻序列移动到b中，这里的移动有三种基本情况
	private static void mergePass(int[] a, int[] b, int k){
		int i = 0;
		//第一种情况，处理成对的序列
		while(i + 2 * k <= a.length){
			//两组重新排序后将a的值放入b中，每次i的值都加2k，再一次执行相同的操作
			merge(a, b, i, i+k-1, i+2*k-1);
			i = i + 2*k;
		}
		
		//第二种情况，在成对的处理完成之后，还剩一组不足一对的，类似0000 0000 0000 00
		//此时merge处理的两组是不等长的
		if(i + k < a.length){
			merge(a, b, i, i+k-1, a.length - 1);
			//最后一种情况，剩下的连半对都不到，类似0000 0000 00
			//此时直接把对应值复制过去
			//这个过程还是要加判断条件，以免越界的情况
		}else{
			for(int j = i ; j < a.length ; j++){
				b[j] = a[j];
			}
		}
	}
	
	//这个merge比之前多了一个数组参数，不需再new一个和初始化数组了
	private static void merge(int[] b, int[] a, int low , int mid ,int high){
		int i, j, k;

		for(i = low , j = mid + 1 , k = low ; i <= mid && j <= high ; k++){

			if(b[i] >= b[j]){
				a[k] = b[i++];
			}else{
				a[k] = b[j++];
			}
		}

		while(i <= mid){
			a[k++] = b[i++];
		}
		while(j <= high){
			a[k++] = b[j++];
		}
	}
	
	//快速排序，原理是设置一个关键元素，从后往前寻找比关键元素小的值与前面的值交换
	//再从前往后找比关键元素大的值与后面的值交换
	//当两边交替寻找到两个指针相等时，将这个位置设置为关键元素
	//将分割的两部分再通过同样的算法寻找，直到寻找的范围缩小为1个
	//当要排序的数组小于等于7时建议直接使用插入排序，这样效率更高
	public static void quickSort(int[] a){
		//传递给一个递归的方法
		quickSort(a, 0, a.length - 1);
	}
	
	//递归方法
	private static void quickSort(int[] a, int low, int high){
		//当low与high相等时，什么都不做
		while(low < high){
			//用partition方法切割数组，并取到关键元素的位置pivotkey
			int pivotkey = partition(a, low, high);
			//将数组按关键元素位置分别进行排序
			quickSort(a, low, pivotkey - 1);
			
			//这里对语句做了一个简单的优化，让low重新赋值了一下并把判断由if改为了while
			//让一部分循环代替递归工作，一定程度上降低了栈的深度
			//quickSort(a, pivotkey + 1, high);
			low = pivotkey + 1;
		}
	}
	
	//切割函数
	private static int partition(int[] a, int low, int high){
		
		//选取头中尾三个数的中间值作为low位置的值，它作为关键值划分效率比直接选low的值高
		int t, mid = (low + high)/2;
		if(a[low] > a[high]){
			t = a[low];
			a[low] = a[high];
			a[high] = t;
		}
		if(a[mid] > a[high]){
			t = a[mid];
			a[mid] = a[high];
			a[high] = t;
		}
		if(a[mid] > a[low]){
			t = a[mid];
			a[mid] = a[low];
			a[low] = t;
		}
		
		//设置第一个元素为关键元素，这里的pivotkey是元素值不是位置
		int pivotkey = a[low];
		//当low=high时退出循环
		while(low < high){
			//当后面有元素大时退出循环，并把大元素赋值给low的位置，也就是放到前面
			//这里必须要加low和high的判断关系，因为这两个值是不断变化的
			//即使low已经与high相等，后面的赋值语句也是自己给自己赋值，不会影响结果
			while(low < high && a[high] <= pivotkey){
				high--;
			}
			a[low] = a[high];
			//当前面有元素小时退出循环，并把小元素赋值给high的位置，也就是放到后面
			while(low < high && a[low] >= pivotkey){
				low++;
			}
			a[high] = a[low];
		}
		//最后low与high相等退出了循环，把pivotkey的值放到这个位置，切割排序完成
		a[low] = pivotkey;
		//最后返回pivotkey的位置，为切割子序列做准备
		return low;
	}
}
