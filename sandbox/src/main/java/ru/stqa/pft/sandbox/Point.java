package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;


  public Point(double abs, double ord) {
    x = abs;
    y = ord;
  }

  //метод для расчета расстояния от точки p1 до точки p2

  public double calcDistance(Point p2){
    return Math.sqrt((Math.pow((p2.x-x),2))+(Math.pow((p2.y-y),2)));
  }

  public double calcDistance(double abs, double ord){
    return Math.sqrt((Math.pow((this.x-abs),2))+(Math.pow((this.y-ord),2)));
  }
}
