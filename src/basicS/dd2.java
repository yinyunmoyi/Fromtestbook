package basicS;
import java.util.*;

public class dd2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        point[] arr = new point[n];
        int x = 0, y = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++){
                x = sc.nextInt();
                y = sc.nextInt();
                arr[i] = new point(x, y);
            }
        } 
        printRes(arr);
    }
    
    static int num = 0;
    public static void printRes(point[] arr){
        boolean[][] flag = new boolean[40][50];
        for(int i = 0; i < arr.length; i++){
            add(arr[i], flag, arr);
        }
        System.out.println(num);
    }
    
    public static void add(point p, boolean[][] flag, point[] arr){
        if(isH(p, arr, flag)){
            for(int i = 0; i < flag[0].length; i++){
                flag[p.x][i] = true;
            }
            num++;
        }
        if(isS(p, arr, flag)){
            for(int i = 0; i < flag.length; i++){
                flag[i][p.y] = true;
            }
            num++;
        }
        if(isX1(p, arr, flag)){
            for(int i = p.x, j = p.y; i > 0 && j > 0; i--, j--){
                flag[i][j] = true;
            }
            for(int i = p.x, j = p.y; i < flag.length && j < flag[0].length; i++, j++){
                flag[i][j] = true;
            }
            num++;
        }
        if(isX2(p, arr, flag)){
            for(int i = p.x, j = p.y; i > 0 && j < flag[0].length; i--, j++){
                flag[i][j] = true;
            }
            for(int i = p.x, j = p.y; i < flag.length && j > 0; i++, j--){
                flag[i][j] = true;
            }
            num++;
        }
    }
    
    public static boolean isH(point p, point[] arr, boolean[][] flag){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].x == p.x && !flag[arr[i].x][arr[i].y]){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isS(point p, point[] arr, boolean[][] flag){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].y == p.y && !flag[arr[i].x][arr[i].y]){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isX1(point p, point[] arr, boolean[][] flag){
        for(int i = 0; i < arr.length; i++){
            if((arr[i].y - p.y)==(arr[i].x - p.x) && !flag[arr[i].x][arr[i].y]){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isX2(point p, point[] arr, boolean[][] flag){
        for(int i = 0; i < arr.length; i++){
            if((arr[i].y - p.y)==(p.x - arr[i].x) && !flag[arr[i].x][arr[i].y]){
                return true;
            }
        }
        return false;
    }
} 

class point{
    int x;
    int y;
    point(int x, int y){
        this.x = x;
        this.y = y;
    }
}