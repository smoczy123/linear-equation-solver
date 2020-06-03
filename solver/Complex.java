package solver;

public class Complex {
    private double real;
    private double imag;

    Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex a) {

        Complex out = new Complex(this.real, this.imag);
        out.real += a.real;
        out.imag += a.imag;
        return out;
    }

    public Complex conjugate() {
        return new Complex(this.real, this.imag * -1);
    }

    public Complex multiply(Complex a) {
        double outReal = (this.real * a.real) - (this.imag * a.imag);
        double outImag = (this.real * a.imag) + (this.imag * a.real);
        return new Complex(outReal, outImag);
    }

    public Complex multiply(double a) {
        return new Complex(this.real * a, this.imag * a);
    }

    public Complex divide(double a) {
        return new Complex(this.real / a, this.imag / a);
    }

    public Complex divide(Complex a) {
        return this.multiply(a.conjugate()).divide(a.real * a.real + a.imag * a.imag);
    }

    @Override
    public String toString() {
        String out;
        if (this.real == 0 && this.imag == 0) {
            out = "0";
        } else if (this.real == 0) {
            out = this.imag + "i";
        } else if (this.imag == 0) {
            out = "" + this.real;
        } else if (this.imag < 0) {
            out = "" + this.real + this.imag + "i";
        } else {
            out = "" + this.real + "+" + this.imag + "i";
        }
        return out;
    }
    public boolean equalsZero() {
        return this.real == 0 && this.imag == 0;
    }
}
