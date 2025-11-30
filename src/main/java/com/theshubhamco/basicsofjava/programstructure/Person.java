//Package declaration
package com.theshubhamco.basicsofjava.programstructure;

//Import statements

//Class declaration
public class Person {
 // Fields
 String name;
 int age;
 static int count;

 // Constructor
 public Person(String name, int age) {
     this.name = name;
     this.age = age;
     count++;
 }

 // Instance method
 void display() {
     System.out.println("Name: " + name + ", Age: " + age);
 }

 // Static method
 static int getCount() {
     return count;
 }

 // Main method
 public static void main(String[] args) {
     // Creating objects
     Person p1 = new Person("Alice", 30);
     Person p2 = new Person("Bob", 25);
     
     // Calling instance method
     p1.display();
     p2.display();
     
     // Calling static method
     System.out.println("Total Persons: " + Person.getCount());
 }
}