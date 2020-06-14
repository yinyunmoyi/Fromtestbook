package test;

import java.util.LinkedList;
import java.util.Queue;


public class test0010 {

	/**
	 * //猫狗队列问题
	//给定宠物类，猫类，狗类如下，要求实现一个队列结构，这个结构可以用add添加猫或狗类进入队列当中去
	//实现pollAll方法，可以弹出一个猫或狗
	//实现pollCat，弹出一个猫，实现pollDog弹出一个狗
	//实现isEmpty方法，查看队列中是否还有元素
	//实现isDogEmpty方法，查看是否还有狗，实现isCatEmpty方法，查看是否还有猫(24min)
	 */
	public static void main(String[] args) {

	}

}

class Pet{
	private String type;
	public Pet(String type){
		this.type = type;
	}
	public String getPetType(){
		return this.type;
	}
}

class Cat extends Pet{
	public Cat() {
		super("cat");
	}
}

class Dog extends Pet{
	public Dog(){
		super("dog");
	}
}

class CatInQueue{
	int index;
	Cat cat;
	CatInQueue(Cat cat, int index){
		this.cat = cat;
		this.index = index;
	}
}

class DogInQueue{
	int index;
	Dog dog;
	DogInQueue(Dog dog, int index){
		this.dog = dog;
		this.index = index;
	}
}
class PetQueue{
	Queue<CatInQueue> catQueue;
	Queue<DogInQueue> dogQueue;
	int index;
	PetQueue(){
		catQueue = new LinkedList<CatInQueue>();
		dogQueue = new LinkedList<DogInQueue>();
	}
	
	public void add(Pet pet){
		if("cat".equals(pet.getPetType())){
			catQueue.offer(new CatInQueue((Cat)pet, index++));
		}else if("dog".equals(pet.getPetType())){
			dogQueue.offer(new DogInQueue((Dog)pet, index++));
		}else{
			throw new IllegalStateException();
		}
	}
	
	public Pet pollAll(){
		if(!catQueue.isEmpty() && dogQueue.isEmpty()){
			return catQueue.poll().cat;
		}else if(catQueue.isEmpty() && !dogQueue.isEmpty()){
			return dogQueue.poll().dog;
		}else if(!catQueue.isEmpty() && !dogQueue.isEmpty()){
			return catQueue.peek().index < dogQueue.peek().index ? 
					catQueue.poll().cat : dogQueue.poll().dog;
		}else{
			throw new IllegalStateException();
		}
	}
	
	public boolean isEmpty(){
		return catQueue.isEmpty() && dogQueue.isEmpty();
	}
	
	public boolean isDogEmpty(){
		return dogQueue.isEmpty();
	}
	
	public boolean isCatEmpty(){
		return catQueue.isEmpty();
	}
}
