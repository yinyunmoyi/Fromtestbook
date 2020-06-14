package basicS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Jzoffer02 {

    /**
	 * 请，将一个字符串中的每个空格替换成“%20”。 例如，当字符串为We Are
	 * Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 */
	public static void main(String[] args) {

	}

	// 26 ms 9508K
	//直接使用string的replace方法
	public static String replaceSpace(StringBuffer str) {
		String st = new String(str);
		String st1 = st.replace(" ", "%20");
		return st1;
	}

	// 21 ms 9324K
	//链式编程用tostring方法构造字符串，再用字符串的replace方法
	public static String replaceSpace_a(StringBuffer str) {
		return str.toString().replace(" ", "%20");
	}


	// 21 ms 9580K
	//转化为字符数组，新建一个stringbuffer对象，
	//遍历字符数组，逐个添加进新的stringbuffer对象中
	public String replaceSpace_c(StringBuffer str) {
		char[] a = str.toString().toCharArray();

		StringBuffer nst = new StringBuffer();

		for (int i = 0; i < a.length; i++) {

			if (a[i] == ' ') {
				nst.append("%20");
			} else {
				nst.append(a[i]);
			}
		}

		return nst.toString();
	}
	
	//26 ms	9500K
	//转换为字符数组，先遍历一遍确定新字符数组的大小
	//建立一个新字符数组，将正确的值移入该数组中
	public String replaceSpace_d(StringBuffer str) {
    	char[] a = str.toString().toCharArray();
 
        int i, j, num = 0;
        for(i = 0; i < a.length; i++){
            if(a[i] == ' '){
                num++;
            }
        }
        char[] b = a;
        a = new char[b.length + 2 * num];
        for(i = 0, j = i; i < b.length; i++){
            if(b[i] == ' '){
                a[j++] = '%';
                a[j++] = '2';
                a[j++] = '0';
            }else{
                a[j++] = b[i];
            }
        }
        return new String(a);
    }

	//19 ms	9488K
	//始终对一个stringbuffer对象操作，没有建立字符串或字符数组
	//用stringbuffer的charat方法遍历整个字符串缓冲区，确定新字符串的大小
	//用stringbuffer的setLength方法去扩充大小
	//用stringbuffer的setcharat（location ， st）来设置每一个位置的字符
	//由于从前向后设置部分内容会被覆盖，故从后向前设置
	public String replaceSpace_e(StringBuffer str) {
    	int i, j, num = 0;
        for(i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                num++;
            }
        }
        
        i = str.length() - 1;
        j = str.length() + 2*num - 1;
        str.setLength(j + 1);
        for(; j >= 0 && i != j; i--){
            if(str.charAt(i) == ' '){
                str.setCharAt(j--, '0');
                str.setCharAt(j--, '2');
                str.setCharAt(j--, '%');
            }else{
                str.setCharAt(j--, str.charAt(i));
            }
        }
        
        return str.toString();
    }
}
