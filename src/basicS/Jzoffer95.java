package basicS;

public class Jzoffer95 {

	
	
	
	//again
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 请用来判断字符串是否表示数值（包括整数和小数）。
	 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 
	 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
	 */
	public static void main(String[] args) {
		System.out.println(isNumeric("123.45e+6".toCharArray()));
	}
	
	//所有匹配的字符串可以描述为以下模式：A（.B）（e/E C）
	//其中AC是可以带正负号的整数，B是无符号整数
	//构造两个函数去判断是否为有符号或无符号整数，这两个函数分别是isSignedNum、isUnSignedNum
	//方法在返回是否为整数的时候同时改变index，它是一个成员变量
	//此题的难点在于多个约束之间的耦合关系
	//A可以不存在，但此时B必须存在，这样的关系就描述为key = isUnSignedNum(str) || key;
	//如果存在E/e就必须存在C，这样的关系描述为key = key && isSignedNum(str);
	//可以方便的用布尔运算来描述这种关系，而一个函数无法同时返回boolean和位置的问题用成员变量去解决
	private static int index;
    public static boolean isNumeric(char[] str) {
        if(str == null || str.length == 0){
            return false;
        }
        boolean key = isSignedNum(str);
        if(index < str.length && str[index] == '.'){
            index++;
            key = isUnSignedNum(str) || key;
        }
        if(index < str.length && (str[index] == 'e' || str[index] == 'E')){
            index++;
            key = key && isSignedNum(str);
        }
        return key && index >= str.length;
    }
    
    public static boolean isSignedNum(char[] str){
        if(index < str.length && (str[index] == '-' || str[index] == '+')){
            index++;
        }
        return isUnSignedNum(str);
    }
    
    public static boolean isUnSignedNum(char[] str){
        if(index >= str.length){
            return false;
        }
        int posi = index;
        while(index < str.length && str[index] >= '0' && str[index] <= '9'){
            index++;
        }
        return index > posi;
    }
    
    public boolean isNumeric1(char[] str) {
        if(str == null || str.length == 0){
            return false;
        }
        boolean flag = false;
        Object[] res1 = isHaveFlag(str, 0);
        if(!(boolean)res1[0]){
            return false;
        }
        Object[] res2 = isok1(str, (int)res1[1]);
        flag = flag || (boolean)res2[0];
        Object[] res3 = isok2(str, (int)res2[1]);
        flag = flag && (boolean)res3[0];
        return flag && (int)res3[1] == str.length;
    }
    
    public Object[] isFlag(char[] str, int posi){
        int start = posi;
        while(posi < str.length && str[posi] >= '0' && str[posi] <= '9'){
            posi++;
        }
        if(start == posi ){
            return new Object[]{false, posi};
        }else{
            return new Object[]{true, posi};
        }
    }
    
    public Object[] isHaveFlag(char[] str, int posi){
        if(str[posi] == '-' || str[posi] == '+'){
            posi++;
        }
        if(posi == str.length){
            return new Object[]{false, str.length}; 
        }else if(str[posi] < '0' || str[posi] > '9'){
            return new Object[]{true, posi}; 
        }else{
            return isFlag(str, posi);
        }
    }
    
    public Object[] isok1(char[] str, int posi){
        if(posi == str.length){
            return new Object[]{true, str.length};
        }
        if((str[posi] == 'e' || str[posi] == 'E')){
            return new Object[]{true, posi};
        }
        if(str[posi] != '.'){
            return new Object[]{false, posi};
        }
        if(posi + 1 == str.length){
            return new Object[]{false, str.length};
        }else{
            return isFlag(str, posi + 1);
        }
    }
    
     public Object[] isok2(char[] str, int posi){
        if(posi == str.length){
            return new Object[]{true, str.length};
        }
        if(!(str[posi] == 'e' || str[posi] == 'E')){
            return new Object[]{false, posi};
        }
        if(posi + 1 == str.length){
            return new Object[]{false, str.length};
        }else{
            return isHaveFlag(str, posi + 1);
        }
    }

}
