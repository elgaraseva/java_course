package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(3,4);
    Point p2 = new Point(6,8);
    assert distance(p1, p2) == 5;
  }
  @Test
  public void testDistance2(){
    Point p1 = new Point(-3,4);
    Point p2 = new Point(6,-8);
    Assert.assertEquals(distance(p1, p2), 15.0);
  }
  @Test
  public void testDistance3(){
    Point p1 = new Point(0,4);
    Point p2 = new Point(6,0);
    Assert.assertEquals(p2.calcDistance(p1), 7.211102550927978);
  }
  @Test
  public void testDistance4(){
    Point p2 = new Point(6,8);
    Assert.assertEquals(p2.calcDistance(0,0), 10.0);
  }
}
