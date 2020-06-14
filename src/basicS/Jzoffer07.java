package basicS;

public class Jzoffer07 {

	/**
	 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
		n<=39
	 */
	public static void main(String[] args) {

	}
	
	//18 ms	9340K
	public int Fibonacci(int n) {
        int[] a = new int[40];
        int i;
        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }else if(n == 2){
            return 1; 
        }
        a[0] = 0; a[1] = 1; a[2] =1;
        for(i = 3; i < a.length; i++){
            a[i] = a[i-1] +a[i-2];
            if(i == n){
                break;
            }
        }
        return a[i];
    }
	
	//15 ms	9288K
	//用两个数去记录计算过程，一次循环计算两个数
	//根据输入位置是奇数还是偶数来判断返回哪个数
	public int Fibonacci_a(int n) {
        if(n <= 0){
            return 0;
        }else if(n == 1){
            return 1;
        }
        int m = 0,k = 1;
        for(int i = 1; i <= n/2; i++){
            m = m + k;
            k = m + k;
           
        }
         if(n%2 == 0){
            return m;
         }else{
             return k;
         }
    }
	
	//15 ms	9544K
	//用三个数去记录中间变量，每次计算出第三个值之后更新第一、第二个值
	public int Fibonacci_b(int n) {
        if(n <= 0){
            return 0;
        }else if(n == 1||n == 2){
            return 1;
        }
        int a = 1, b = 1, c = 2;
        for(int i = 3;i <= n; i++){
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
	
	//17 ms	9332K
	//代码较短，只用两个值去记录，循环次数也是n次
	//不用检查n为1和2的情况，但是这种算法有点计算重复，每次已经计算出了下一个数但要到下一次循环才取出
	public int Fibonacci_c(int n) {
        if(n <= 0){
            return 0;
        }
        int a = 1, b = 0;
        for(int i = 1;i <= n; i++){
            a = a + b;
            b = a - b;
        }
        return b;
    }
	
	//853 ms	9432K
	//一个最简单的，不好的递归，进行了大量的重复计算
	public int Fibonacci_d(int n) {
        if(n <= 0){
            return 0;
        }else if(n == 1||n == 2){
            return 1;
        }
        
        return Fibonacci_d(n-1) + Fibonacci_d(n-2);
    }
	
	//26 ms	9352K
	//这个递归降低了一些深度，但对于海量数据而言依然不可行
	public int Fibonacci_e(int n) {
        if(n <= 0){
            return 0;
        }else if(n == 1||n == 2){
            return 1;
        }else if(n == 3){
            return 2;
        }
        
        return 3*Fibonacci_e(n-3) + 2*Fibonacci_e(n-4);
    }
	
	//19 ms	9376K
	//用递归的形式完成上面的循环，速度略慢于循环
	public int Fibonacci_f(int n) {
        return Fibonacci_f(n, 0, 1);
    }
    
    private int Fibonacci_f(int n, int x, int y){
        if(n <= 0){
            return 0;
        }else if(n == 1){
            return y;
        }else{
            return Fibonacci_f(n-1, y, x+y);
        }
        
    }
    
    //21 ms	9488K
    //利用矩阵计算斐波那契数列，并借助快速求幂来降低算法运行时间
    public int Fibonacci_g(int n) {
        if(n <= 0){
            return 0;
        }else if(n == 1 || n == 2){
            return 1;
        }
        
        //原理是斐波那契数列的第一项是矩阵{{1, 1}, {1, 0}}的n-2次方的第一列之和
        //这个结论是根据递推式导出的结果
        int[][] a = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(a, n-2);
        return res[0][0] + res[1][0];
    }
    
    //矩阵乘法的实现
    private int[][] matrixPower(int[][] a, int[][] b){
        
    	//两个矩阵相乘，新矩阵的行是第一个矩阵的行，列是第二个矩阵的列
        int[][] c = new int[a.length][b[0].length];
        //前两层嵌套分别是遍历新矩阵的行和列
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b[0].length; j++){
            	//最后一层嵌套是求新矩阵的每一个元素都是一系列值的和
                for(int k = 0; k < a[0].length; k++){
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return c;
    }
    
    //矩阵快速求幂的实现，根整数的快速求幂原理相同
    private int[][] matrixPower(int[][] a, int n){
        
    	//新建矩阵b和c作为计算的中间变量
        int[][] b = new int[a.length][a[0].length];
        int[][] c = a;
        //把矩阵b变成单位矩阵
        for(int i = 0; i < a.length; i++){
                b[i][i] = 1;
        }
        
        //循环的判断条件是n大于0，当n为0时退出循环
        while(n > 0){
        	//快速乘幂其实就是把幂变成二进制，再将二进制拆成几项的和
        	//这样二进制有几项就乘几项，大大降低了运行时间
        	//如果n的最后一位是1，说明这一位是要算入最后和中去的
        	//如果这最后一位不是1，说明求幂的时候不需要计入，这与拆二进制项为0不算，1算是一致的
        	//随着n向右移最后一位被移除，此时相当于加法已经完成了一部分，再去判断现在的最后一位
        	//直到最后n变为0，没有可以移动的位了
            if((n&1) != 0){
                b = matrixPower(b, c);
            }
            //每一次的乘方都在累加，2次方，4次方，8次方，但每一位不一定都加入最后结果
            //还要看该位是不是0，上方的判断语句就可以完成这个功能
            c = matrixPower(c, c);
            n >>= 1;
        }
        
        return b;
    }
	
	

}
