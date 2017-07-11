import java.util.*;

public class Divide{
	public static void main(String ... args){
		try(Scanner sc = new Scanner(System.in)){
			double a = Double.parseDouble(sc.nextLine()),
				   b = Double.parseDouble(sc.nextLine()),
				   delta = Double.parseDouble(sc.nextLine());

			System.out.println(divide(a,b,delta));
		}	
	}

	public static double divide(double a, double b, double delta){
		double left = 0,right = Math.max(a,b);

		if(a<b) right = 1;
		else
			left = 1;

		while(left < right){
			double mid = (left+right)*0.5;

			if(Math.abs(mid*b - a) <= delta){
				return mid;
			}
			else if (mid * b < a) {
				left = mid;	
			}
			else
				right = mid;

		}

		return -1;
	}
}