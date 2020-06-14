package basicS;

public class Test08 {

	/**
	 * 用二分法求一个函数的零点，y=e^x*x-1
	 */
	public static void main(String[] args) {
		clacou y = new clacou();
		double k = cal(y,0,1);
		System.out.println("该函数在0-1之间的解为"+k);
	}

	//第一个参数代表用类表示的方程，第二第三个参数代表区间范围
	private static double cal(clacou y, double i, double j) {
		
		double center = (i+j)/2;
		double k = y.function(center);
		if(Math.abs(k)<0.000001){
			return center;
		}
		
		if(k>0){
			return cal(y,i,center);
		}else if(k<0){
			return cal(y,center,j);
		}else{
			return center;
		}
	}

}

class clacou{
	public double function(double x){
		return Math.pow(Math.E, x)*x-1 ;
	}
}
