/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam;

class Car {
    String make;
    String model;
    int year;

    void start() {
        System.out.println("The car is starting.");
    }
}

public class ClassAndObjectExample2 {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.make = "Toyota";
        myCar.model = "Camry";
        myCar.year = 2022;
        myCar.start();
    }
}

