/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam;

// Parent class
class Fruit {
    void display() {
        System.out.println("This is a fruit.");
    }
}

// Child class inheriting from Fruit
class Apple extends Fruit {
    @Override
    void display() {
        System.out.println("This is an apple.");
    }
}

public class InheritanceExample4 {
    public static void main(String[] args) {
        Fruit fruit = new Fruit();
        Apple apple = new Apple();

        fruit.display();
        apple.display();
    }
}

