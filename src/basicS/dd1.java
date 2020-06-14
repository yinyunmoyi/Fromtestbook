/*
 * package basicS;
 * 
 * import java.util.Scanner;
 * 
 * class dd1{ public void main(String[] args){ Scanner sc = new
 * Scanner(System.in); int[] arr = new int[10]; int i = 0;
 * while(sc.hasNextInt()) { arr[0] = sc.nextInt(); arr[1] = sc.nextInt(); arr[2]
 * = sc.nextInt(); arr[3] = sc.nextInt(); arr[4] = sc.nextInt(); arr[5] =
 * sc.nextInt(); arr[6] = sc.nextInt(); arr[7] = sc.nextInt(); arr[8] =
 * sc.nextInt(); arr[9] = sc.nextInt(); } //arr[0] = //arr[1] = sc.nextInt();
 * //arr[2] = sc.nextInt(); //arr[3] = sc.nextInt(); //arr[4] = sc.nextInt();
 * //arr[5] = sc.nextInt(); //arr[6] = sc.nextInt(); //arr[7] = sc.nextInt();
 * //arr[8] = sc.nextInt(); //arr[9] = sc.nextInt(); point[] pointArr = new
 * point[5]; pointArr[0] = new point(arr[0], arr[1]); pointArr[1] = new
 * point(arr[2], arr[3]); pointArr[2] = new point(arr[4], arr[5]); pointArr[3] =
 * new point(arr[6], arr[7]); pointArr[4] = new point(arr[8], arr[9]);
 * printMinRoad(pointArr); }
 * 
 * int min = Integer.MAX_VALUE; public void printMinRoad(point[] arr){
 * printMinRoad(arr, 0); System.out.print(min); }
 * 
 * public void printMinRoad(point[] arr, int nowposi){ if(nowposi ==
 * arr.length){ min = Math.min(min, getSum(arr)); } for(int i = nowposi; i <
 * arr.length; i++){ swap(arr, i , nowposi); printMinRoad(arr, nowposi + 1);
 * swap(arr, i , nowposi); } }
 * 
 * public int getSum(point[] arr){ double sum = 0; int x = 0; int y = 0; for(int
 * i = 0; i < arr.length; i++){ sum += Math.pow(Math.pow((arr[i].x - x), 2) +
 * Math.pow((arr[i].y - y), 2), 0.5); x = arr[i].x; y = arr[i].y; } sum +=
 * Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5); return (int)sum; }
 * 
 * public void swap(point[] arr, int start, int end){ point p = arr[start];
 * arr[start] = arr[end]; arr[end] = p; } }
 * 
 * 
 * class point{ int x; int y; point(int x, int y){ this.x = x; this.y = y; } }
 * 
 */