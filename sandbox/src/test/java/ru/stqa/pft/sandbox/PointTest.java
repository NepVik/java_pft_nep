package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance() {
    Point d1 = new Point(0, 1, 0, 5);
    Assert.assertEquals(d1.distance(), 4.0);
    Point d2 = new Point(-4, 0, 5, 0);
    Assert.assertEquals(d2.distance(), 9.0);
  }
}
