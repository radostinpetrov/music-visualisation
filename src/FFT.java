public class FFT {
    public static Complex[] fft(Complex[] x) {
        int n = x.length;
        if (n == 1) return new Complex[]{ x[0] };

        if (n % 2 != 0) throw new IllegalArgumentException("n must be a power of 2");

        // FFT of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // FFT of odd terms
        Complex[] odd = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // Combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + n/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }
}

class Complex {
    private final double re;
    private final double im;

    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    public Complex plus(Complex b) { return new Complex(re + b.re, im + b.im); }
    public Complex minus(Complex b) { return new Complex(re - b.re, im - b.im); }
    public Complex times(Complex b) { return new Complex(re*b.re - im*b.im, re*b.im + im*b.re); }
    public double abs() { return Math.hypot(re, im); }
}
