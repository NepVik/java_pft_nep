package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(1, 20);
    Point p2 = new Point(1, 5);

    Assert.assertEquals(p1.distance(p2), 15.0);
  }
}
