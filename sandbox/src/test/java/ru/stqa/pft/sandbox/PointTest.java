package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(1, 20);
    Point p2 = new Point(1, 5);

    Assert.assertEquals(p1.distance(p2), 15.0);

    Point p3 = new Point(-2.3, 4);
    Point p4 = new Point(8.5, 0.7);

    Assert.assertEquals(p3.distance(p4), 11.292918134831227);
  }
}
