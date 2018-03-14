package com.xinwei.orderDb.service.implTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

interface A {
	public String show();
}

class B implements A {
	public String show() {
		return "B";
	}
}

class C extends B {
	public String show() {
		return "C";
	}
}

public class Test {
	public static void main(String[] args) {

		// A a = new B();
		// B b = new C();
		// A c = new C();
		// System.out.println(a.show() + b.show() + c.show());

		// 数组1，十万数据
		int[] arr = new int[100000];
		for (int i = 0; i < 100000; i++) {
			arr[i] = i;
		}
		// 数组2，十万数据
		int[] arr2 = new int[100000];
		for (int i = 0; i < 100000; i++) {
			arr2[i] = i + 9000;
		}

		long stratTime = System.currentTimeMillis();
		HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> set2 = new HashSet<>();
		for (Integer integer : arr) {
			set.add(integer);
		}
		for (Integer integer : arr2) {
			if (!set.add(integer)) {
				set2.add(integer);
			}
		}
		System.out.println("set重复个数" + set2.size());
		long endTime = System.currentTimeMillis();
		System.out.println("set用时" + (endTime - stratTime));

		long stratTime1 = System.currentTimeMillis();
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
		for (Integer integer : arr) {
			arrayList.add(integer);
		}
		for (Integer integer : arr2) {
			if (arrayList.contains(integer)) {
				arrayList2.add(integer);
			}
		}
		System.out.println("list重复个数" + arrayList2.size());
		long endTime1 = System.currentTimeMillis();
		System.out.println("list用时" + (endTime1 - stratTime1));
	}
	@org.junit.Test
	public void dateTest() {
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		String aString = "aaaaaaaaaaaaaaaaaaaaa1234";
		System.out.println(aString.substring(aString.length() - 4, aString.length()));
	}
}
