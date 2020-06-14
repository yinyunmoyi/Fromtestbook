package leetcode;
import java.util.*;

public class leet00009_22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 用递归解决问题，如果长度不够就可以加左括号，如果左括号数量大于右括号就可以加右括号
	// 直至字符串长度到达输入值的2倍
	public List<String> generateParenthesis(int n) {
		String str = "";
		List<String> list = new ArrayList<>();
		generateParenthesis(str, n, 0, 0, list);
		return list;
	}

	public void generateParenthesis(String str, int max, int left, int right, List<String> list) {
		if (str.length() == 2 * max) {
			list.add(str);
			return;
		}
		if (left < max) {
			generateParenthesis(str + "(", max, left + 1, right, list);
		}
		if (right < left) {
			generateParenthesis(str + ")", max, left, right + 1, list);
		}
	}
}
