package x;

public class x03 {

	/**
	 * 装饰者设计模式，很多流对象都是基于此模式
	 * 存在一个公共接口code，定义一个codeing方法
	 * 如果现在有一个类person，有一个study方法，有一个子类HITPerson，想要在原来study的基础上加强该方法
	 * 应该如何设计？
	 */
	public static void main(String[] args) {

	}

}

interface code{
	void codeing();
}

class Person implements code{
	String name;
	public Person(String name){
		this.name = name;
	}
	
	public void codeing(){
		System.out.println("codeing");
	}
}

class HITPerson implements code{
	Person person;
	public HITPerson(Person person){
		this.person = person;
	}
	public void codeing(){
		person.codeing();
		System.out.println("HITcodeing");
	}
}
