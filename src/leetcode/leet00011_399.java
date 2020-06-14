package leetcode;
import java.util.*;
public class leet00011_399 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//把所有的除法结果都装进map中，以被除数为键，值为一个map，以除数为键，值为结果
    //初始化的时候要求如果hashmap值存在就不再新建而是加入，故选用putIfAbsent方法
    //当对应的值存在且不为null时才执行插入
    //然后开始遍历，每一个结果都是这样计算的，假如计算a除以c，一开始的r是1
    //然后取出a对应的除法结果一个一个往下递归，递归过程中将r值乘以map中的值，也就是相当于代换变量
    //这其中有一些递归会形成死循环，为了不形成死循环就加入了一个seen，一旦算回来了就返回-1
    //如果某个分支返回-1则继续循环直至返回的值不是-1返回结果
    //如果m中压根不存在该字符串也说明计算不出结果，也返回-1
    //当s和t是同一个数的时候返回1，递归结束
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> m = new HashMap<>();
		for (int i = 0; i < values.length; i++) {
			m.putIfAbsent(equations.get(i).get(0), new HashMap<String, Double>());
			m.putIfAbsent(equations.get(i).get(1), new HashMap<String, Double>());
			m.get(equations.get(i).get(0)).put(equations.get(i).get(1), values[i]);
			m.get(equations.get(i).get(1)).put(equations.get(i).get(0), 1 / values[i]);
		}
		double[] res = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++)
			res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), 1, m, new HashSet<String>());
		return res;
    }
    
    public double dfs(String s, String t, double r, Map<String, Map<String, Double>> m, Set<String> seen) {
		if (!m.containsKey(s) || !seen.add(s))
			return -1;
		if (s.equals(t))
			return r;
		Map<String, Double> next = m.get(s);
		for (String c : next.keySet()) {
			double result = dfs(c, t, r * next.get(c), m, seen);
			if (result != -1)
				return result;
		}
		return -1;
	}
}
