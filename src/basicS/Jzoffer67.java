package basicS;

public class Jzoffer67 {

	//求1+2+3+...+n，要求不能使用乘除法、
	//for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
	
	//用递归去完成累加，利用&&来完成短路功能，每次n》0时都会自增sum，但一旦n为0因为短路所以后面的自增就不会执行
	//这样就能完成累加
	public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
	
	//还是用递归，只不过用异常去终止递归条件，要注意，导入的包是java.lang.*，try和finally中都要有return语句
	public int Sum_Solution1(int n) {
        try{
            int sum = n;
            int num = 1/n;
            sum += Sum_Solution(n - 1);
            return sum;
        }catch(Exception e){
            return n;
        }
    }
}
