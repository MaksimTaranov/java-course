public class ComplexNumber{
    private double real;
    private double imag;

    ComplexNumber(double real, double imag){
        this.real= real;
        this.imag = imag;
    }

    boolean is_zero(){
        return real == 0 && imag == 0;
    }

    void print_numb(){
        if(imag > 0 )
            System.out.print(real + "+" + imag +"i ");
        else if (imag < 0)
            System.out.print(real + imag +"i ");
        else
            System.out.print(real + " ");
    }


    ComplexNumber add(ComplexNumber numb){
        double newreal = this.real + numb.real;
        double newimag = this.imag + numb.imag;
        return new ComplexNumber(newreal, newimag);
    }

    ComplexNumber subtract(ComplexNumber numb){
        double newreal = this.real - numb.real;
        double newimag = this.imag - numb.imag;
        return new ComplexNumber(newreal, newimag);
    }

    ComplexNumber multiply(ComplexNumber numb){
        double newreal = this.real * numb.real - this.imag * numb.imag;
        double newimag = this.real * numb.imag +  this.imag * numb.real;
        return new ComplexNumber(newreal, newimag);
    }

    ComplexNumber divide(ComplexNumber numb){
        if (numb.real == 0 && numb.imag == 0){
            throw new ArithmeticException("Нельзя делить на ноль");
        }
        double newreal = (this.real * numb.real + this.imag * numb.imag) / (numb.real * numb.real + numb.imag * numb.imag);
        double newimag = (this.imag * numb.real - this.real * numb.imag) / (numb.real * numb.real + numb.imag * numb.imag);
        return new ComplexNumber(newreal, newimag);
    }
}
