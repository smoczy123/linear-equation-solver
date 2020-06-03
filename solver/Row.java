package solver;

public class Row {
    private final Complex[] row;

    Row(int n) {
        this.row = new Complex[n];
    }

    public Complex getNum(int i) {
        return row[i];
    }

    public int size() { return this.row.length; }

    public void setNum(int i, Complex num) {
        this.row[i] = num;
    }
    public Row multiply(double scalar) {
        int n = this.row.length;
        Row out = new Row(n);
        for (int i = 0; i < n; i++) {
            out.setNum(i, getNum(i).multiply(scalar));
        }
        return out;
    }
    public Row multiply(Complex scalar) {
        int n = this.row.length;
        Row out = new Row(n);
        for (int i = 0; i < n; i++) {
            out.setNum(i, getNum(i).multiply(scalar));
        }
        return out;
    }
    public Row divide (Complex divisor) {
        int n = this.row.length;
        Row out = new Row(n);
        for (int i = 0; i < n; i++) {
            out.setNum(i, getNum(i).divide(divisor));
        }
        return out;
    }
    public void add(Row second) {
        int n = this.row.length;
        for (int i = 0; i < n; i++) {
            Complex newValue = getNum(i).add(second.getNum(i));
            setNum(i, newValue);
        }
    }
    public void printRow() {
        for (int i = 0; i < this.row.length; i++) {
            System.out.print(this.row[i] + " ");
        }
        System.out.println();
    }
}
