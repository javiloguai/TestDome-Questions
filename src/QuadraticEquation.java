import java.math.BigDecimal;
public class QuadraticEquation {
	public static Roots findRoots(double a, double b, double c) {
		if (a==0)return null;
		BigDecimal a1 = new BigDecimal(Double.toString(a));

		BigDecimal b1 = new BigDecimal(Double.toString(b));
		BigDecimal c1 = new BigDecimal(Double.toString(c));
		BigDecimal b2 = b1.multiply(b1);
		BigDecimal fourac = (new BigDecimal(4)).multiply(a1).multiply(c1);
		BigDecimal twoa = (new BigDecimal(2)).multiply(a1);
		BigDecimal  srqt =  new BigDecimal(Math.sqrt(b2.subtract(fourac).doubleValue()));
		BigDecimal sol1 = null;
		BigDecimal sol2 =null;
		Boolean hasTwoSols= true;

		try {
			sol1= new BigDecimal(0);
			sol1 = (b1.negate().add(srqt)).divide(twoa);
		} catch (ArithmeticException e) {
			hasTwoSols = false;
		}

		try {
			sol2= new BigDecimal(0);
			sol2 = (b1.negate().subtract(srqt)).divide(twoa);
		} catch (ArithmeticException e) {
			hasTwoSols = false;
		}

		if (hasTwoSols){
			return new Roots(sol1.doubleValue(),sol2.doubleValue());
		}else if (sol1!=null){
			return new Roots(sol1.doubleValue());

		}else if (sol2!=null){
			return new Roots(sol2.doubleValue());
		}

		return null;
	}

	public static void main(String[] args) {
		Roots roots = null;
		try{
			roots = QuadraticEquation.findRoots(2, 10, 8);
		}catch (NullPointerException e){
			System.out.println("Exception");
		}
		System.out.println("Roots: " + roots.x1 + ", " + roots.x2);
	}
}

class Roots {
	public final Double x1, x2;

	public Roots(double x1) {
		this.x1 = x1;
		this.x2 = null;
	}

	public Roots(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
}