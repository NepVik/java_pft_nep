package ru.stqa.pft.sandbox;

import com.sun.deploy.association.utility.AppAssociationWriter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test() {
    Equation e = new Equation(1, 1, 1);
    Assert.assertEquals(e.rooyNumber(), 0);
  }

  @Test
  public void test1() {
    Equation e = new Equation(1, 2, 1);
    Assert.assertEquals(e.rooyNumber(), 1);
  }

  @Test
  public void test2() {
    Equation e = new Equation(1, 5,6);
    Assert.assertEquals(e.rooyNumber(), 2);
  }
}
