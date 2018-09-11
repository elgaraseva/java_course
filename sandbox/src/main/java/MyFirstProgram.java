public class MyFirstProgram {

	public static void main(String[] args) {
		hello();
		square();
		rectangle();

		double len = 5;
		System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));

		double a = 2;
		double b = 3;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
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

	public static double area(double l){
		return l * l;
	}

	public static double area(double a, double b){
		return a * b;
	}

}