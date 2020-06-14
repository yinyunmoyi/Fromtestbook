package basicS;

public class Jzoffer98 {

	
	
	
	
	//再解决一次
	
	
	
	
	
	
	
	
	
	
	
	
	
	//请用来匹配包括'.'和'*'的正则表达式。
	//模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
	//在本题中，匹配是指字符串的所有字符匹配整个模式。
	//例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
	
	
	//这道题的难点在于处理*，因为*处理时如果直接跳过会错过一些结果，如果不直接跳就地检查又会错过跳过的结果
	//这道题实际上是一种分支思想，用递归的处理方法，这两种路径如果一条是有true就说明匹配成功
	//此外边界条件的处理也很复杂，如果str和pattern同时越界则返回true，如果仅str越界那么还有匹配的可能，
	//因为此时pattern可以是a*这样的构造，如果仅pattern越界那么就不可能匹配成功了
	public boolean match(char[] str, char[] pattern)
    {
        if(str == null || pattern == null){
            return false;
        }
        return match(0, str, 0, pattern);
    }
    
    public boolean match(int strPos, char[] str, int patPos, char[] pattern){
        //两类边界条件
    	if(strPos >= str.length && patPos >= pattern.length){
            return true;
        }
        if(strPos < str.length && patPos >= pattern.length){
            return false;
        }
        //如果第二位是*那么就用分支思想两条路出发
        if((patPos + 1) < pattern.length && pattern[patPos + 1] == '*'){
            return (strPos < str.length && (pattern[patPos] == str[strPos] || pattern[patPos] == '.') && match(strPos + 1, str, patPos, pattern)) ||
                match(strPos, str, patPos + 2, pattern);
        }else{
        	//如果第二位不是*那么就普通匹配，如果不成功就返回false
            if(strPos < str.length && (( pattern[patPos] == str[strPos]) || pattern[patPos] == '.')){
                return match(strPos + 1, str, patPos + 1, pattern);
            }else{
                return false;
            }
        }
    }

}
