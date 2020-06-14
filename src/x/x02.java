package x;

public class x02 {

	/**
	 * 三种基本单例设计模式
	 * 饿汉式、懒汉式、用静态内部类实现（外部类初始化时不会初始化内部类，用到的时候再初始化，有延迟意义）
	 */
	public static void main(String[] args) {

	}

}

class singleton1{
	private singleton1(){}
	private static singleton1 s = new singleton1();
	public static singleton1 getSingle(){
		return s;
	}
}

class singleton2{
	private singleton2(){}
	private volatile static singleton2 s;
	public static singleton2 getSingle(){
		if(s == null){
			synchronized (singleton2.class) {
				if(s == null){
					s = new singleton2();
				}
			}
		}
		return s;
	}
}

class singleton3{
	private singleton3(){}
	private static class singleton{
		static singleton3 s = new singleton3();
	}
	public static singleton3 getSingle(){
		return singleton.s;
	}
}
