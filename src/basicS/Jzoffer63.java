package basicS;

public class Jzoffer63 {

	//给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
	//其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
	
	//用两个数组来装所有的中间结果，让计算没有重复性
	public int[] multiply(int[] A) {
        if(A == null){
            return null;
        }
        int[] b = new int[A.length];
        if(A.length == 0){
            return b;
        }
        int[] c = new int[A.length];
        int[] d = new int[A.length];
        int temp = 1;
        for(int i = 0; i < c.length; i++){
            c[i] = temp;
            temp *= A[i];
        }
        temp = 1;
        for(int j = 0; j < d.length; j++){
            d[j] = temp;
            temp *= A[A.length - j - 1];
        }
        for(int k = 0; k < b.length; k++){
            b[k] = d[d.length - k - 1] * c[k];
        }
        return b;
    }
}
