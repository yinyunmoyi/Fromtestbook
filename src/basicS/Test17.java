package basicS;
@SuppressWarnings("unused")
public class Test17 {

	/**
	 * 实现一个AVL树avlSearchTree类 要实现的方法和类：
	 *  avlNode、makeEmpty、isEmpty
	 * 空参构造、contains、findMax、findMin、
	 * insert、remove、printTree(未实现)
	 */
	public static void main(String[] args) {
		avlSearchTree<Integer> ast = new avlSearchTree<Integer>();
	}

}

class avlSearchTree<AnyType extends Comparable<? super AnyType>> {

	//唯一的域：根节点
	private avlNode<AnyType> root;

	//AVL树的节点构造，比普通二叉树多了一个高度值
	//高度是从本节点到端节点的路径段数
	private static class avlNode<AnyType> {

		avlNode<AnyType> left;
		avlNode<AnyType> right;
		AnyType element;
		int height;

		public avlNode(AnyType element, avlNode<AnyType> left,
				avlNode<AnyType> right) {
			super();
			this.left = left;
			this.right = right;
			this.element = element;
			// 新建立的节点一定是叶子节点，高度一定是0
			height = 0;
		}

		public avlNode(AnyType element) {
			this(element, null, null);
		}
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public avlSearchTree() {
		super();
		root = null;
	}

	//contains方法与普通二叉树一致
	public boolean contains(AnyType element) {
		return contains(root, element);
	}

	private boolean contains(avlNode<AnyType> x, AnyType element) {

		if (x == null) {
			return false;
		}

		if (element.compareTo(x.element) > 0) {
			return contains(x.right, element);
		} else if (element.compareTo(x.element) < 0) {
			return contains(x.left, element);
		} else {
			return true;
		}
	}

	//findMax方法与普通二叉树一致
	public AnyType findMax() {
		return findMax(root).element;
	}

	private avlNode<AnyType> findMax(avlNode<AnyType> x) {

		if (x == null) {
			throw new RuntimeException();
		}

		avlNode<AnyType> t = x;
		while (t.right != null) {
			t = t.right;
		}
		return t;
	}

	//findMin方法与普通二叉树一致
	public AnyType findMin() {
		return findMin(root).element;
	}

	private avlNode<AnyType> findMin(avlNode<AnyType> x) {

		if (x == null) {
			throw new RuntimeException();
		}

		avlNode<AnyType> t = x;
		while (t.left != null) {
			t = t.left;
		}
		return t;
	}

	//放回节点的高度值
	//这里将null节点的高度值设置成-1
	private int height(avlNode<AnyType> x) {
		return x == null ? -1 : x.height;
	}

	//插入方法需要递归，故将其传入新购造的方法中
	public void insert(AnyType element) {
		// mid_height = 1;
		root = insert(root, element);
	}

	// private int mid_height = 1;

	//除了最后加上了平衡操作，其余所有操作都与普通二叉树一致
	private avlNode<AnyType> insert(avlNode<AnyType> x, AnyType element) {

		if (x == null) {
			// avlNode<AnyType> y = new avlNode<AnyType>(element);
			// y.height = mid_height;
			return new avlNode<AnyType>(element);
		}

		int compare = element.compareTo(x.element);

		if (compare > 0) {
			x.right = insert(x.right, element);
		} else if (compare < 0) {
			x.left = insert(x.left, element);
		} else {
			;
		}
		//对每个节点都进行平衡操作
		//这里返回的实际上是从插入节点到根节点的所有节点
		//这些节点都有可能产生不平衡
		//即使节点不平衡也要进行高度的更新，因为一旦插入一个新节点
		//这些节点的高度值都要更新一遍
		return balance(x);
	}

	private avlNode<AnyType> balance(avlNode<AnyType> x) {

		//平衡方法首先检查左右子树的高度值是否差大于1，若大于1就是不平衡
		//这里基于平衡操作的四种情形设计了四种分支
		//分别进行对应的单旋转和双旋转
		if ((height(x.left) - height(x.right)) > 1) {
			//这里有一个细节问题：这里的判断用的是大于等于号
			//实际上在进行插入操作时不可能发生相等的情况，因为插入后相等
			//就说明该子树的高度插入前后没有变化，也就不可能产生不平衡
			//只要出现了不平衡情况，就不可能出现等号的情况
			//这里之所以用了等号实际上是考虑到删除后也要进行平衡操作
			//删除一个结点后，因为另一个子树高度比该子树高度大了2
			//且另一个子树本身并没有限定，它完全有可能出现某个子树左右平衡的情况
			//此时依然需要单旋转进行解决，故用等号把上述所有的可能都归于下条语句
			if (height(x.left.left) >= height(x.left.right)) {
				//左-左插入后的结果用单旋转
				//对删除的情况也一样适用
				//因为无论是插入还是删除本质上都是对树结构的改变
				//这里将树传入对它进行结构上的改变，没必要弄清树为什么已经有了这样的结构
				x = rotateWithLeftChild(x);
			} else {
				//左-右插入后的结果用双旋转
				x = doubleRotateWithLeftChild(x);
			}
		} else if ((height(x.right) - height(x.left)) > 1) {
			if (height(x.right.right) >= height(x.right.left)) {
				x = rotateWithRightChild(x);
			} else {
				x = doubleRotateWithRightChild(x);
			}
		}

		//在对x结点，也就是传入子树的根节点，下的各结构进行更新外
		//还需要进行高度的更新
		//在旋转时也对x的高度进行了更新，这里再更新一次是为了让
		//所有的平衡结点也进行高度的更新
		x.height = Math.max(x.left.height, x.right.height) + 1;
		return x;
	}

	//绕左孩子进行单旋转
	private avlNode<AnyType> rotateWithLeftChild(avlNode<AnyType> x) {

		//将左孩子提为根节点，并重新调整结构
		avlNode<AnyType> t = x.left;
		x.left = t.right;
		t.right = x;
		//将修改的两个结点高度进行更新
		//子树的各节点高度不必更新，因为他们到端节点的部分没有改变
		x.height = Math.max(x.left.height, x.right.height);
		t.height = Math.max(x.height, t.left.height);

		return t;
	}

	//绕右孩子进行单旋转
	private avlNode<AnyType> rotateWithRightChild(avlNode<AnyType> x) {

		avlNode<AnyType> t = x.right;
		x.right = t.left;
		t.left = x;
		x.height = Math.max(x.left.height, x.right.height);
		t.height = Math.max(x.height, t.right.height);

		return t;
	}
	
	//绕左孩子进行双旋转
	private avlNode<AnyType> doubleRotateWithLeftChild(avlNode<AnyType> x){
		
		//先对左子树进行单旋转操作，将左子树的右孩子提为根节点
		x.left = rotateWithRightChild(x.left);
		//再对本树进行单旋转操作，将左孩子提为根节点
		//至此，原左子树的右孩子进行两次提拔到达了根节点的位置
		//双旋转完成
		return rotateWithLeftChild(x);
	}
	
	//绕右孩子进行双旋转
	private avlNode<AnyType> doubleRotateWithRightChild(avlNode<AnyType> x){
		
		x.right = rotateWithLeftChild(x.right);
		return rotateWithRightChild(x);
	}
	
	//删除操作除了最后需要平衡外与普通二叉树一样
	public void remove(AnyType element){
		root = remove(root , element);
	}
	
	private avlNode<AnyType> remove(avlNode<AnyType> x , AnyType element){
		
		if(x == null){
			return x;
		}
		
		int compare = element.compareTo(x.element);
		
		if(compare > 0){
			x.right = remove(x.right , element);
		}else if(compare < 0){
			x.left = remove(x.left , element);
		}else if(x.left != null && x.right != null){
			x.element = findMin(x.right).element;
			x.right = remove(x.right , x.element);
		}else{
			x = (x.left == null?x.right:x.left);
		}
		
		return balance(x);
	}
	
	//printTree用来从小到大顺序输出树的值
	//它本身也需要递归去实现
	public void printTree(){
		
		if(root == null){
			System.out.println("empty tree");
		}else{
			printTree(root);
		}
	}
	
	//中序遍历树并打印
	private void printTree(avlNode<AnyType> x){
		
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
