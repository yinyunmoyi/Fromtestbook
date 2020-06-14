package basicS;

import java.util.Stack;

public class zuo13_ {

	/**
	 * 给一个字符串str，str表示一个公式，公式里有整数、加减乘除号和左右括号
	 * 如str = "48*((70-65)-43)+8*1"应该返回-1816
	 * str给出的公式一定是合乎逻辑的，无需检查过程中的溢出现象，如果是负数需要用括号括起来
	 * 但出现在开头或括号部分的开头则无需括起来
	 */
	public static void main(String[] args) {
		System.out.println(getRes("-1+48*((70-65)-43)+8*1"));
	}
	
	public static int getRes(String str){
		return getRes(str.toCharArray(), 0)[0];
	}
	
	//主要思想就是用一个栈处理运算优先级的问题，每次一个数字和符号入栈，如果栈顶是乘或者除
	//就把下面的数字取出计算，把计算结果再放入栈中，最后栈中只剩下数字和加减，依次取出栈中元素计算即可
	//累积数字的问题：因为需要把几个连续的数字字符变成数字，需要设置一个值pre为0，每次遇到数字就把pre = pre * 10 + 数字 - '0'
	//当做新的pre，这样就能解决累积数字的问题
	//栈的类型不要设置为char或int，应该设置为string这样转换都很方便
	//如果遇见左括号就递归处理这个过程直到右括号位置，递归函数返回一个大小为2的int数组分别代表括号运算的结果和右括号的位置
	//然后posi直接跳到右括号的右边继续计算，结果赋值给pre
	//每次遇到右括号就退出第一个while循环，因为括号左边一定是数字，此时要把数字单独放入栈中
	//第二个while就是清空栈的情形，如果遇见负号在数字前这种特殊情况也可以应对，因为此时pre为0就相当于普通运算
	//如果0入栈对计算无影响
	public static int[] getRes(char[] charArr, int posi){
		int pre = 0;
		Stack<String> stack = new Stack<String>();
		while(posi < charArr.length && charArr[posi] != ')'){
			if(charArr[posi] >= '0' && charArr[posi] <= '9'){
				pre = pre * 10 + charArr[posi] - '0';
			}else if(charArr[posi] != '('){
				add(stack, pre);
				stack.push(charArr[posi] + "");
				//System.out.println("1 " + stack);
				pre = 0;
			}else{
				int[] arr = getRes(charArr, posi + 1);
				pre = arr[0];
				posi = arr[1];
			}
			posi++;
		} 
		add(stack, pre);
		int sum = 0;
		//System.out.println(stack);
		while(!stack.isEmpty()){
			int num = new Integer(stack.pop());
			if(!stack.isEmpty() && stack.pop().equals("-")){
				num = (-1) * num;
			}
			sum += num;
		}
		return new int[]{sum, posi};
	}
	
	public static void add(Stack<String> stack, int pre){
		//System.out.println(stack);
		if(!stack.empty() && (stack.peek().equals("*") || stack.peek().equals("/"))){
			String k = stack.pop();
			int num = new Integer(stack.pop());
			if(k.equals("*")){
				stack.push((num * pre) + "");
			}else{
				stack.push((num / pre) + "");
			}
		}else{
			stack.push(pre + "");
		}
		
	}

}
