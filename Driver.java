import java.io.File;

public class Driver {
    public static void main(String [] args) {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,0,0,5};
    Polynomial p1 = new Polynomial(c1);
    double [] c2 = {0,-2,0,0,-9};
    Polynomial p2 = new Polynomial(c2);
    Polynomial s = p1.add(p2);
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    if(s.hasRoot(1))
    System.out.println("1 is a root of s");
    else
    System.out.println("1 is not a root of s");

    System.out.println("testing sorting");
    test_sort();
    
    System.out.println("testing Splitting");
    test_splitting();

    System.out.println("testing Singular multiplication");
    test_singular_multiplication();

    System.out.println("testing Multiplier");
    test_multiplier();

    System.out.println("testing wr");
    test_rw();
    }

    public static void test_splitting(){
        double[] c={1,2,3,4};
        Polynomial a=new Polynomial(c);
        Polynomial[] arr=Polynomial.slice_polynomial(a);
        for (Polynomial p : arr) {
            System.out.println("printing coefficents and exponents for "+p.toString());
            Polynomial.print_array(p.coefficients);
            Polynomial.print_array(p.exponents);
        }
    }

    public static void test_singular_multiplication(){
        double[] c={2,3};
        Polynomial a=new Polynomial(c);
        Polynomial[] arr=Polynomial.slice_polynomial(a);
        Polynomial c1=arr[0];
        Polynomial c2=arr[1];
        Polynomial d=Polynomial.singular_multiply(c1, c2);
        System.out.println("Print d coefficients and exponents");
        Polynomial.print_array(d.coefficients);
        Polynomial.print_array(d.exponents);
    }

    public static void test_multiplier(){
        double [] c1={-1,1};
        double [] c2={1,1};
        Polynomial a=new Polynomial(c1);
        Polynomial b=new Polynomial(c2);
        Polynomial c=a.multiply(b);
        System.out.println("Print c coefficients and exponents");
        Polynomial.print_array(c.coefficients);
        Polynomial.print_array(c.exponents);
        
    }
    public static void test_sort(){
        double[] p1={3.0,2.0,1.0};
        int[] p2={3,2,1};
        Polynomial p=new Polynomial(p1,p2);
        p.sort();
        System.out.println("Print p coefficients and exponents");
        Polynomial.print_array(p.coefficients);
        Polynomial.print_array(p.exponents);
    }

    public static void test_rw(){
        double[] p1={3.0,2.0,1.0,0.5};
        int[] p2={3,2,1,0};
        Polynomial p=new Polynomial(p1,p2);
        p.saveToFile("polynomial.txt");
        System.out.println(new File("."));
        Polynomial q=new Polynomial(new File("polynomial.txt"));
        
        System.out.println("Print q coefficients and exponents");
        Polynomial.print_array(q.coefficients);
        Polynomial.print_array(q.exponents);
    }

    }
    

