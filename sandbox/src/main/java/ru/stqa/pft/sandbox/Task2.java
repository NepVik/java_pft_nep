package ru.stqa.pft.sandbox;

public class Task2 {

  public static void main (String args[]) {

    Point d1 = new Point(3, 2, 7, 5);
    Point d2 = new Point(5, 4, 1, 9);

    System.out.println("1. Расстояние от точки c координатами: \nx1 = " + d1.x1 + "\ny1 = " + d1.y1 + "\n до точки с координатами: \nx2 = " + d1.x2 + "\ny2 = " + d1.y2 + "\nравно - " + d1.distance());
    System.out.println();
    System.out.println("2. Расстояние от точки c координатами: \nx1 = " + d2.x1 + "\ny1 = " + d2.y1 + "\n до точки с координатами: \nx2 = " + d2.x2 + "\ny2 = " + d2.y2 + "\nравно - " + d2.distance());


  }

}
