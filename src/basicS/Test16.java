package basicS;

//import basicS.avlSearchTree.avlNode;

//@SuppressWarnings("unused")
@SuppressWarnings("rawtypes")
public class Test16 {

	/**
	 * 普通二叉查找树BinarySearchTree的实现
	 * 要实现的方法和类：
	 * BinaryNode、makeEmpty、isEmpty
	 * 空参构造、contains、findMax
	 * findMin、insert、remove、printTree(未实现)
	 * 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BinarySearchTree bn = new BinarySearchTree();
	}

}


class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	
	//唯一的域变量，根节点本身，每次对二叉树进行修改操作实际上都在修改根节点
	BinaryNode<AnyType> root;
	//一个嵌套类，描述节点构造
	

	//二叉树的无参构造，就是给根节点赋null
	public BinarySearchTree() {
		super();
		root = null;
	}
	
	//二叉树的清空
	public void makeEmpty(){
		root = null;
	}
	
	//判断二叉树是否为空
	public boolean isEmpty(){
		return root == null;
	}
	
	//判断二叉树是否包括该元素，因为递归的原因又将它传入一个新的方法中
	public boolean contains(AnyType element){
		return contains(root,element);
	}

	//判断二叉树是否包含该元素
	private boolean contains(BinaryNode<AnyType> x, AnyType element) {
		
		//如果树是null，那么就返回null，查找结束没有元素
		if(x == null){
			return false;
		}
		
		//如果元素比该节点元素大，那么去右子树寻找，否则去左子树
		//直至树为空或两结点相等，返回true
		//这里使用的compareto方法都是因为AnyType实现了Comparable的结果
		if(element.compareTo(x.element) > 0){
			return contains(x.right,element);
		}else if(element.compareTo(x.element) < 0){
			return contains(x.left,element);
		}else{
			return true;
		}
	}
	
	//查找二叉树的最大值
	public AnyType findMax(){
		
		//如果根节点为null，那么就抛出一个异常，无法查找最大值
		if(root == null){
			throw new RuntimeException();
		}
		//将根节点的索引赋给另一个变量，以免之后修改根节点索引
		BinaryNode<AnyType> t = root;
		
		//只要改结点还有右结点就继续向右查找
		while(t.right != null){
			t = t.right; 
		}
		//直至没有右结点了，此时数据即为最大值
		return t.element;
	}
	
	//查找二叉树的最小值，因为后面要实现一个在子树中查找最值的方法
	//故实现那个新的方法并把功能传递过去
	public AnyType findMin(){
		
		if(root == null){
			throw new RuntimeException();
		}
		return findMin(root).element;
	}
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
		while(t.left != null){
			t = t.left; 
		}
		return t;
	}
	
	//插入也需要递归，故重新建立一个函数
	//插入实质上就是更新根节点，故要把新的根节点赋给原根节点
	public void insert(AnyType element){
		root = insert(root,element);
	}

	//插入操作
	private BinaryNode<AnyType> insert(BinaryNode<AnyType> x, AnyType element) {
		
		//如果树为空，那么就返回一个以该元素为数据域的新节点
		//也就是插入新节点
		if(x == null){
			return new BinaryNode<AnyType>(element);
		}
		
		//如果树的数据域比元素的小，那么说明该元素在右子树内
		//也就是说如果该元素存在的话，更新一定发生在右子树内
		//故将右子树的索引更新
		//否则更新左子树的索引
		if(element.compareTo(x.element) > 0){
			x.right = insert(x.right,element);
		}else if(element.compareTo(x.element) < 0){
			x.left = insert(x.left,element);
		}else{
			//若树的数据域与要插入的值相等，那么什么都不做
			;
		}
		//如果递归没有因为子树等于null，也就是插入新节点结束
		//那么就把索引返回，相当于什么都没有做
		return x;
	}
	
	//将二叉树的数据删除
	//因为需要递归操作，故新建了一个同名方法
	public void remove(AnyType element){
		root = remove(root,element);
	}

	private BinaryNode<AnyType> remove(BinaryNode<AnyType> x, AnyType element) {
		
		//如果树节点为空，那么就返回空
		//适用于传入root，且root为空的情况
		//还适用于递归中没有找到该元素的情况
		if(x == null){
			return null;
		}
		
		//将比较的值记录一下，以免多次计算
		int compareTo = element.compareTo(x.element);
		
		//如果树节点的数据域比元素小，那么就更新右子树的索引
		//否则更新左子树
		if(compareTo > 0){
			x.right = remove(x.right , element);
		}else if(compareTo < 0){
			x.left = remove(x.left , element);
			
			//直至找到了要删除的元素，当该节点此时既有左孩子又有右孩子时
			//选取右子树的最小值作为该节点的值，当然也可以选择左子树的最大值更新节点值
			//将值更新后，把对应子树的该元素删除，再次调用递归
		}else if(x.left != null && x.right != null){
			x.element = findMin(x.right).element;
			x.right = remove(x.right , x.element);
		}else{
			//找到了要删除的元素，但是他只有左孩子或只有右孩子
			//如果有左孩子就把连接给左孩子，否则就给右孩子
			//这样的处理也可以应用于叶子节点上，因为此时什么都没有，一定会被赋值为null
			x = (x.right == null ? x.left:x.right);
		}
		return x;
	}
	
	public void printTree(){
		
		if(root == null){
			System.out.println("empty tree");
		}else{
			printTree(root);
		}
	}
	
	//中序遍历树并打印
	private void printTree(BinaryNode<AnyType> x){
		
		//因为每一个结点都要访问它的左右子树
		//势必出现叶子节点或其他节点没有左或右子树的情形
		//此时什么都不做，直接完成方法的调用
		//继续递归
		if(x != null){
			printTree(x.left);
			System.out.print(x.element+" ");
			printTree(x.right);
		}
	}
	
}
