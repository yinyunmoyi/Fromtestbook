package basicS;
import java.util.*;
public class dd{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = "";
        String str1 = "";
        str1 = sc.nextLine();
        str = sc.nextLine();
        Integer n = new Integer(str1);
        print(n, str);
    }
    
    public static void print(int n, String str){
        if(n <= 0 || str == null || str.length() == 0){
            return;
        }
        char[] arr = str.toCharArray();
        int k = 0;
        while(n > 0 && k < arr.length){
            boolean flag = false;
            ArrayList<Character> list = new ArrayList<Character>();
            if(arr[k] >= '0' && arr[k] <= '1'){
                flag = (arr[k++] == '1' ? true : false); 
            }
            int m = 7;
            while(m > 0 && k < arr.length && !(arr[k] >= '0' && arr[k] <= '1')){
                list.add(arr[k]);
                k++;m--;
            }
            printArr(list,flag);
            n--;
            System.out.print(" ");
        }
    }
    
    public static void printArr(ArrayList<Character> list, boolean flag){
        if(flag){
            for(Character ch : list){
                System.out.print(ch);
            }
        }else{
            Stack<Character> stack = new Stack<Character>();
            for(Character ch : list){
                stack.push(ch);
            }
            while(!stack.isEmpty()){
                System.out.print(stack.pop());
            }
        }
    }
}