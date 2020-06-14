package test;

import java.util.List;

public class test0059 {

	/**
	 * 晚会问题
	 * 一个公司的上下关系是一颗多叉树，给你多叉树的头结点，公司要举办晚会，每一个人都有一个活跃值
	 * 请给出最大的活跃值，每个人的活跃值有正有负，且有一个限值：
	 * 一个人的直系领导如果参加晚会，那么这个人一定不会去参加晚会
	 */
	public static void main(String[] args) {

	}

}

class companyNode{
	int element;
	List<companyNode> myStaff;
	public companyNode(int element, List<companyNode> myStaff) {
		super();
		this.element = element;
		this.myStaff = myStaff;
	}
}
