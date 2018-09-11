package ru.stqa.pft.sandbox;

public class MyFirstProgram {

	public static void main(String[] args) {
		hello();
		square();
		rectangle();

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

		Rectangle r = new Rectangle();
		r.a = 2;
		r.b = 3;
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

		// считает расстояние между точками p1 и p2 при помощи функции distance
    Point p1 = new Point(3,4);
    Point p2 = new Point(6,8);
    double result = distance(p1, p2);
    System.out.println("Расстояние между точками p1 и p2 = " + result);


    // считает расстояние между точками p1 и p2 при помощи методов calcDistance
    System.out.println("Расстояние между точками p1 и p2 = " + p2.calcDistance(p1));

    System.out.println("Расстояние между точками p1 и p2 = " + p2.calcDistance(10,1));
	}

	public static void hello(){
		String name = "world";
		System.out.println("Hello, " + name + "!");
	}
	public static void square(){
		double l = 8;
		double s = l * l;
		System.out.println("Площадь квадрата со стороной " + l + " = " + s);
	}
	public static void rectangle(){
		double a = 3;
		double b = 4;
		double d = a * b;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + d);
	}

	public static double area(Square s){
		return s.l * s.l;
	}

	public static double area(Rectangle r){
		return r.a * r.b;
	}

	public static double distance(Point p1, Point p2){
		return Math.sqrt((Math.pow((p2.x-p1.x),2))+(Math.pow((p2.y-p1.y),2)));
	}
}