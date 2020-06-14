package basicS;

import java.util.Arrays;
import java.util.LinkedList;

public class Jzoffer68 {

	//还要重新思考！！！！！！！！！！！！！！！！！！！！
	
	
	
	
	
	//指定数n、m，n个人做成一圈，从0号人开始报数，报数从0开始报到m - 1出局
	//下一个人继续报数，直到队伍中只剩下一人，返回此人的位置
	public static void main(String[] args) {
		//System.out.println(LastRemaining_Solution(5, 3));

	}

	//用一个布尔数组模拟这个过程，出局位置就设置为true，找到最后为false的位置
	public int LastRemaining_Solution(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        boolean[] flag = new boolean[n];
        int posi = 0;
        for(int i = n; i > 1; i--){
            int num = 1;
            while(true){
                if(flag[posi]){
                    posi = next(n, posi);
                }else{
                    if(num == m){
                        flag[posi] = true;
                        break;
                    }else{
                        num++;
                        posi = next(n, posi);
                    }
                }
            }
             posi = next(n, posi);
        }
        while(flag[posi]){
            posi = next(n, posi);
        }
        return posi;
    }
    
    public int next(int capa, int posi){
        if(posi == capa - 1){
            return 0;
        }else{
            return posi + 1;
        }
    }
    
    //用链表来模拟删除的过程，直接导出下一个删除的位置然后用remove删除
    public int LastRemaining_Solution1(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            list.add(i);
        }
        int posi = 0;
        while(list.size() > 1){
            int delPosi = posi + ((m-1)%list.size()) >= list.size() ?
                posi + ((m-1)%list.size()) - list.size() : posi + ((m-1)%list.size());
            list.remove(delPosi);
            posi = delPosi == list.size() ? 0 : delPosi;
        }
        return list.get(0);
    }














    //用数学方法推导递推式（用画图的方法去推导旧号和新号之间的关系，画完这个函数之后，从已知熟悉的函数去推导这个
    //图像的函数关系）
    //用f(n,m)表示该删除的元素位置，它删除的位置和f‘(n-1,m)删除一个元素之后要删除的位置相同
    //将f’转换为f，删除一个元素k之后k+1对应0位置，k+2对应1位置。。。k-1对应n-2位置
    //这样给出f中的位置求f‘位置，它们的关系应该是(f + k + 1)%n = f'
    //这样f’(n-1, m)就变为（f(n-1,m)+k+1）%n,将k=(m-1)%n带入得
    //f(n,m)=(f(n-1,m)+m)%n这样就得到了递推式，当n = 1时直接等于0
    public int LastRemaining_Solution3(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        int k = 0;
        for(int i = 2; i <= n; i++){
            k = (k + m) % i;
        }
        return k;
    }

    /*
        终极自我探索版：好理解但是稍微繁琐一些

        1、我们明确最后只剩下一个节点，这个节点的编号是0（假设每次出局都重新编号）
        如果我们能从剩n-1个人的节点号推到剩n个人的节点号，我们就能推导出一开始到底谁会剩到最后

        2、报数和位置的关系：假如我们有3个人：0/1/2，那么报数依次是0/1/2/3/4/5/6等
        所以有位置号=报数%3，这个关系可以用函数推导出来，具体做法是确立函数的每个坐标点然后连线
        推导关系式，这是一个锯齿形的函数图像。如果总人数是n，上述表达式就是：位置号=报数%n
        综上可得，总人数为n，叫到m-1淘汰时，被淘汰者的编号s=（m-1）%n

        3、下面推导旧号（长度为m）到新号（长度为m-1）的关系，假设出局的那个是s号：
        以新号为横坐标x，旧号为纵坐标y，绘制两者的图像，发现是一个分段函数，将此函数写出

        4、假设m已知的情况下：n为1时，淘汰的人编号为0，先用公式计算出s，然后将x为0带入分段函数得到
        共2人时的淘汰编号，也就是新的x，继续循环计算直到计算到共n个人时的淘汰编号。
         */
    public int LastRemaining_Solution4(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        int k = 0;
        for(int i = 2; i <= n; i++){
            int s = (m - 1) % i;
            if(k <= i - s - 2){
                k = k + s + 1;
            }else{
                k = k - i + 1 + s;
            }
        }
        return k;
    }
}
