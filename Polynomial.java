public class Polynomial{
    public double [] coefficients;
    public Polynomial(){
        coefficients= new double[1];
        coefficients[0]=0;
    }
    public Polynomial(double [] d){
        coefficients=d;
    }
    public Polynomial add(Polynomial p){
        int lenA=coefficients.length;
        int lenB=p.coefficients.length;
        double [] new_coefficients;
        if(lenA<lenB){
            new_coefficients=new double[lenB];
            for (int i = 0; i < lenA; i++) {
                new_coefficients[i]=coefficients[i]+p.coefficients[i];
            }
            for(int i = lenA; i < lenB; i++) {
                new_coefficients[i]=p.coefficients[i];
            }
        }else{
            new_coefficients=new double[lenA];
            for (int i = 0; i < lenB; i++) {
                new_coefficients[i]=coefficients[i]+p.coefficients[i];
            }
            for(int i = lenA; i < lenA; i++) {
                new_coefficients[i]=coefficients[i];
            }
        }
        p.coefficients=new_coefficients;
        return p;
    }
    public double evaluate(double x){
        double sum=0;
        double temp;
        double idx=coefficients.length;
        for(int i=0;i<idx;i++){
            temp=1;
            for(int j=0;j<i;j++){
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