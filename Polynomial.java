
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;




public class Polynomial{
    public double [] coefficients;
    public int[] exponents;
    public Polynomial(){
        coefficients=new double[0];
        exponents=new int[0];
    }
    public Polynomial(double[] d){
        coefficients=d;
        exponents=new int[d.length];
        for(int i=0;i<d.length;i++){
            exponents[i]=i;
        }
        Polynomial.eliminate_reundancy(this);
    }
    public Polynomial(double [] d, int[] i){
        coefficients=d;
        exponents=i;
    }
    public Polynomial(File f){
        String string_representation="";
        try {
            Scanner s=new Scanner(f);
            string_representation=s.nextLine().split("\\n")[0];
        } catch (Exception e) {
            System.err.println("s was not created");
        }
        coefficients=new double[0];
        exponents=new int[0];
        String [] polynomials=string_representation.split("(?=-)|[+]");
        for(String p:polynomials){
            String[] polynomial=p.split("x");
            if(polynomial.length==1){
                append(Double.parseDouble(polynomial[0]), 0);
            }else{
                append(Double.parseDouble(polynomial[0]),Integer.parseInt(polynomial[1]));
            }
        }
        
    }
    private void append(double c, int e){
        int length=coefficients.length;
        double[] new_coefficents=new double[length+1];
        int[] new_exponents=new int[length+1];
        for(int i=0;i<length;i++){
            new_coefficents[i]=coefficients[i];
            new_exponents[i]=exponents[i];
        }
        new_coefficents[length]=c;
        new_exponents[length]=e;
        coefficients=new_coefficents;
        exponents=new_exponents;
    }

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        int[] indices = new int[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;


            int tempIndex = indices[minIndex];
            indices[minIndex] = indices[i];
            indices[i] = tempIndex;
        }
        return indices;
    }

    public void sort(){
        int [] new_exponents=exponents;
        double [] new_coefficents=new double[exponents.length];
        int [] indices=selectionSort(new_exponents);
        for (int i = 0; i < exponents.length; i++) {
            new_coefficents[indices[i]]=coefficients[i];
        }
        exponents=new_exponents;
        coefficients=new_coefficents;        
    }
    public static double abs(double d){
        if(d>=0){
            return d;
        }else{
            return -d;
        }
    }
    public static boolean intable(double d){
        if((int)d==d){
            return true;
        }else{
            return false;
        }
    }
    public String double_to_string(double d){
        d=abs(d);
        if(intable(d)){
            int c=(int)d;
            return Integer.toString(c);
        }else{
            return Double.toString(d);
        }
    }

    public void saveToFile(String s){
        String w="";
        sort();
        eliminate_reundancy(this);
        for(int i=0;i<coefficients.length;i++){
            if(i==0){
                if(coefficients[i]<0){
                    w=w+"-";
                }
            }else{
                if(coefficients[i]<0){
                    w=w+"-";
                }else{
                    w=w+"+";
                }
            }
            if(exponents[i]==0){
                w=w+double_to_string(coefficients[i]);
            }else{
                w=w+double_to_string(coefficients[i])+"x"+Integer.toString(exponents[i]);
            }
        }
        try{
            FileWriter writer=new FileWriter(s);
            writer.write(w);
            writer.close();
        }catch(Exception ex){

        }
        

    }
    private static int indexOf(int[] arr, int val){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==val){
                return i;
            }
        }
        return -1;
    }
    private static boolean isIn(int[] arr, int val){
        if(indexOf(arr, val)==-1){
            return false;
        }
        return true;
    }


    private static int indexOf(double[] arr, double val){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==val){
                return i;
            }
        }
        return -1;
    }
    public static void print_array(int[] arr){
        for (int a:arr) {
            System.out.println(a);
        }
    }
    public static void print_array(double[] arr){
        for (double a:arr) {
            System.out.println(a);
        }
    }



    public static void eliminate_reundancy(Polynomial p){
        int [] idx_to_delete=new int[p.coefficients.length];
        int idx=0;

        //System.out.println("P:coefficents & exponents");
        //print_array(p.coefficients);
        //print_array(p.exponents);

        for (int i = 0; i < p.coefficients.length; i++) {
           if(p.coefficients[i]==0){
                idx_to_delete[idx]=i;
                idx++;
           }
        }
        int l=p.coefficients.length-idx;
        double [] new_coefficients=new double[l];
        int[] new_exponents=new int[l];
        int[] new_idx_to_delete=new int[idx];
        int new_idx=0;
        for(int i=0;i<idx;i++){
            new_idx_to_delete[i]=idx_to_delete[i];
        }

        //System.out.println("idx_to_delete");
        //print_array(new_idx_to_delete);

        for(int i=0;i<p.coefficients.length;i++){
            if(!isIn(new_idx_to_delete,i)){
                new_coefficients[new_idx]=p.coefficients[i];
                new_exponents[new_idx]=p.exponents[i];
                new_idx++;
            }         
        }
        p.coefficients=new_coefficients;
        p.exponents=new_exponents;

        //System.out.println("new_coefficents and exponents");
        //print_array(new_coefficients);
        //print_array(new_exponents);

    }
    public static Polynomial [] slice_polynomial(Polynomial p){
        Polynomial.eliminate_reundancy(p);
        Polynomial [] res= new Polynomial[p.coefficients.length];
        for(int i=0;i<p.coefficients.length;i++){
            double [] new_coefficients={p.coefficients[i]};
            int [] new_exponents={p.exponents[i]};
            res[i]=new Polynomial(new_coefficients,new_exponents);
        }
        return res;
    }
    public Polynomial add(Polynomial p){
        int total_length=exponents.length+p.exponents.length;
        for(int e:p.exponents){
            if(isIn(exponents,e)){
                total_length--;
            }
        }
        int [] new_exponents=new int[total_length];
        double [] new_coefficients=new double[total_length];
        int idx=0;
        for(int e:p.exponents){
            if(!isIn(exponents,e)){
                new_exponents[idx]=e;
                new_coefficients[idx]=p.coefficients[indexOf(p.exponents, e)];
            }else{
                new_exponents[idx]=e;
                new_coefficients[idx]=p.coefficients[indexOf(p.exponents, e)]+coefficients[indexOf(exponents, e)];
            }
            idx++;
        }
        for(int e:exponents){
            if(!isIn(p.exponents,e)){
                new_exponents[idx]=e;
                new_coefficients[idx]=coefficients[indexOf(exponents, e)];
                idx++;
            }
        }
        Polynomial res=new Polynomial(new_coefficients,new_exponents);
        eliminate_reundancy(res);
        res.sort();
        return res;
    }
    public static Polynomial singular_multiply(Polynomial A,Polynomial B){
        double [] new_coefficents={A.coefficients[0]*B.coefficients[0]};
        int [] new_exponents={A.exponents[0]+B.exponents[0]};
        return new Polynomial(new_coefficents,new_exponents);
    }
    public Polynomial multiply(Polynomial p){
        Polynomial [] A=slice_polynomial(this);
        Polynomial [] B=slice_polynomial(p);
        Polynomial temp;
        Polynomial res=new Polynomial();
        for(Polynomial a: A){
            for(Polynomial b:B){
                temp=Polynomial.singular_multiply(a, b);
                //print_array(temp.coefficients);
                //print_array(temp.exponents);
                res=res.add(temp);
            }
            
        }
        Polynomial.eliminate_reundancy(res);
        res.sort();
        return res;


    }
    public double evaluate(double x){
        double sum=0;
        double temp;
        double idx=coefficients.length;
        for(int i=0;i<idx;i++){
            temp=1;
            for(int j=0;j<exponents[i];j++){
                temp=temp*x;
            }
            sum=sum+coefficients[i]*temp;
        }
        return sum;
    }
    public boolean hasRoot(double x){
        return evaluate(x)==0;
    }


}