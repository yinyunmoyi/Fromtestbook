package important;
import java.util.HashMap;
import java.util.LinkedHashSet;

class LFUCache {

	/*
	 * key到value的映射map
	 */
    private HashMap<Integer, Integer> keyToVal;
    
    /*
     * key到频次的映射map
     */
    private HashMap<Integer, Integer> keyToCount;
    
    /*
     * 频次到key集合的映射map，key集合采用LinkedHashSet，可以方便的取出最早加入的元素
     */
    private HashMap<Integer, LinkedHashSet<Integer>> countToKeys;
    
    /*
     * 当前最小频次
     */
    int minCount;
    
    /*
     * cache的容量
     */
    int capacity;

    /*
     * 初始化各参数，注意把countToKeys的1频次初始化，把最小频次置为1
     */
    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToCount = new HashMap<>();
        countToKeys = new HashMap<>();
        this.capacity = capacity;
        countToKeys.put(1, new LinkedHashSet<Integer>());
        minCount = -1;
    }

    /*
     * 通过key获取value
     * 首先把原来的频次集合中的key删掉，然后如果删掉的频次是最小频次且恰好删掉之后最小频次集合为空
     * 就更新最小频次
     * 如果对应count+1的频次集合为空，就新建该频次
     * 然后分别更新keyToCount和countToKeys，最后返回结果
     */
    public int get(int key) {
        if(!keyToVal.containsKey(key)){
            return -1;
        }
        int count = keyToCount.get(key);
        countToKeys.get(count).remove(key);
        if(minCount == count && countToKeys.get(count).size() == 0){
            minCount++;
        }
        if(countToKeys.get(count + 1) == null){
            countToKeys.put(count + 1, new LinkedHashSet<Integer>());
        }
        keyToCount.put(key, count + 1);
        countToKeys.get(count + 1).add(key);
        return keyToVal.get(key);
    }

    /*
     * 插入key和value
     * 首先capacity为0属于特殊情况，取delKey时会直接报空指针，把这个情况处理掉
     * 如果size已经到达了上限需要淘汰，首先根据mincount确定要删除的key，然后更新3个集合
     * 最后在3个集合中插入新数据，把mincount设置为1
     */
    public void put(int key, int value) {
        if(capacity <= 0){
            return;
        }
        if(keyToVal.containsKey(key)){
            keyToVal.put(key, value);
            get(key);
        }else{
            if(keyToVal.size() == capacity){
                int delKey = countToKeys.get(minCount).iterator().next();
                keyToVal.remove(delKey);
                keyToCount.remove(delKey);
                countToKeys.get(minCount).remove(delKey);
            }
            keyToVal.put(key, value);
            keyToCount.put(key, 1);
            minCount = 1;
            countToKeys.get(minCount).add(key);
        }
    }
}
