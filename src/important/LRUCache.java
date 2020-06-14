package important;
import java.util.HashMap;
class LRUCache {

	//缓存池的容量
    int capacity;
    //map存储从key到缓存node的映射
    private HashMap<Integer, CachNode> map;
    //缓存node组成的双向链表，双向链表的好处在于只要拿到节点引用就可以通过
    //pre和next删除该节点
    private NodeList nodeList;
    
    //构造方法
    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeList = new NodeList(capacity);
        map = new HashMap<>();
    }

    //查询key，如果map中不存在该key就直接返回-1，否则就将该node移动到链表头
    //最后返回node的值
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            CachNode node = map.get(key);
            nodeList.moveToHead(map.get(key));
            return node.val;
        }
    }

    //加入一对值，如果key在map中存在就仅仅只是修改node的值并将其移动到链表头
    //如果不存在的话分为两种情况
    //如果数据已经达到缓存的极限，就要淘汰末尾的节点
    //首先返回末尾的节点，然后返回其引用，通过引用拿到key去对应map中删掉
    //最后map中存入值，并把新node放在链表头处
    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).val = value;
            nodeList.moveToHead(map.get(key));
        }else{
            CachNode node = new CachNode(key, value);
            if(map.size() == capacity){
                CachNode delNode = nodeList.delTail();
                map.remove(delNode.key);
            }
            map.put(key, node);
            nodeList.addToHead(node);
        }
    }

    //缓存node组成的双向链表
    class NodeList{
    	//维护了一对node：start和end
        CachNode start;
        CachNode end;
        int capacity;
        
        //在构造方法中，初始化两个node，并建立联系
        //有了这两个node就无需在之后的操作中进行判空操作了
        NodeList(int capacity){
            this.capacity = capacity;
            start = new CachNode();
            end = new CachNode();
            start.next = end;
            end.pre = start;
        }

        //将node移动到链表头
        void moveToHead(CachNode node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            addToHead(node);
        }

        //删除链表末尾节点
        CachNode delTail(){
            CachNode delNode = end.pre;
            delNode.pre.next = end;
            end.pre = delNode.pre;
            delNode.next = null;
            delNode.pre = null;
            return delNode;
        }

        //将node添加到链表头
        void addToHead(CachNode node){
            node.next = start.next;
            node.pre = start;
            start.next = node;
            node.next.pre = node;
        }
    }

    //缓存node设置向前向后两个指针，并设计了val和key
    //加入key之后每一个node都有它对应的key，这样在删除map
    //中的元素时很方便，这个字段的设置节省了一个map
    class CachNode{
        CachNode next;
        CachNode pre;
        int val;
        int key;
        CachNode(int key, int val){
            this.val = val;
            this.key = key;
        }
        CachNode(){}
    }
}
