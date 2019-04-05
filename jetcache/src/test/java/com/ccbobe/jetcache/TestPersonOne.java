package com.ccbobe.jetcache;

public class TestPersonOne {
	
	public static void main(String[] args) {
		Person person = new Person("ccbobe",12);
		//exchange(person);
		//System.out.println(person);
		//exchange(person);
		exchange3(person);
	}
	
	public static void exchange(Person person){
		person.setName("bobe");
		System.out.println(person);
	}
	
	public static void exchange2(Person person){
		System.out.println(person);
	}
	
	public static void exchange3(Person person){
		person.setName("123");
		person.setAge(12);
		System.out.println(person);
	}
}
