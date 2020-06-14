package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class leet00054_127 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//一个word改一个字母变为另一个word，例如dag和cot
    //dag修改为dot是有可能的路径，但是dag修改为aag也是有可能的路径
    //因为aag通过后续的修改也有可能变成cot，这样考虑每一个位置都要替换成26的字母的任意一位
    //要进行DFS，也就是说对于任意一个单词，要把它所有的改一个单词的可能都包括进去
    //建立一个队列，第一遍把beginWord放进去，然后把它边一个字母的所有可能都考虑到
    //如果某个可能在set中，就加入队列，同时把该字符串从set中删掉
    //删掉的原因和在迷宫中行走考虑两个点的最短距离时，走到同一个点是无意义的
    //要避免这种可能造成循环的可能
    //队列一次吞吐一代的字符串，每一代间隔分明，最后如果找到了endWord，直接返回代数即可
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)){
            return 0;
        }
        HashSet<String> set = new HashSet<>();
        for(String str : wordList){
            set.add(str);
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int res = 0;
        while(!queue.isEmpty()){
            for(int i = queue.size(); i > 0; i--){
                String str = queue.poll();
                char[] arr = str.toCharArray();
                for(int j = 0; j < arr.length; j++){
                    char mych = arr[j];
                    for(char ch = 'a'; ch <= 'z'; ch++){
                        arr[j] = ch;
                        String val = new String(arr);
                        if(val.equals(endWord)){
                            return res + 2;
                        }
                        if(set.contains(val) && !val.equals(str)){
                            queue.offer(val);
                            set.remove(val);
                        }
                    }
                    arr[j] = mych;
                }
            }
            res++;
        }
        return 0;
    }
}
