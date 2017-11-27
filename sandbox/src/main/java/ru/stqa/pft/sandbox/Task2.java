package ru.stqa.pft.sandbox;

public class Task2 {

  public static void main (String args[]) {

    Point p1 = new Point(1, 20);
    Point p2 = new Point(1, 5);

    System.out.println("Расстояние от точки p1 до точки p2 равно - " + p1.distance(p2));
  }
}
