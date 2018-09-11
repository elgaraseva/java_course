public class MyFirstProgram {

	public static void main(String[] args) {
		hello();
		square();
		rectangle();

		Square s = new Square();
		s.l = 5;
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + area(s));

		Rectangle r = new Rectangle();
		r.a = 2;
		r.b = 3;
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));

		Point p = new Point(1,2,3,4);
		System.out.println("Расстояние между точками p1 и p2 = " + p);
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

}