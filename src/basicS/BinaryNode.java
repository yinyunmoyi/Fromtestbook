package basicS;

public class BinaryNode<AnyType>{
	
	//三个域变量，分别代表左孩子、右孩子、数值域
	public BinaryNode<AnyType> left;
	public BinaryNode<AnyType> right;
	public AnyType element;
	
	//一个有参构造
	public BinaryNode(AnyType element,BinaryNode<AnyType> left, BinaryNode<AnyType> right ) {
		super();
		this.left = left;
		this.right = right;
		this.element = element;
	}
	
	//一个有参构造，他可以直接传入一个值新建节点
	public BinaryNode(AnyType element){
		this(element,null,null);
	}
}
