package solver;

public class Matrix {
    private final Row[] matrix;

    Matrix(int n, int m) {
        this.matrix = new Row[m];
        for (int i = 0; i < m; i++) {
            this.matrix[i] = new Row(n+1);
        }
    }

    public Row getRow(int i) {
        return matrix[i];
    }
    public void swapRow(int i, int j) {
        Row temp = this.getRow(j);
        this.matrix[j] = this.matrix[i];
        this.matrix[i] = temp;
        System.out.printf("R%d <=> R%d\n", i, j);
    }

    public void swapColumn(int i, int j) {
        Complex temp;
        for (int k = 0; k < size(); k++) {
            temp = this.matrix[k].getNum(j);
            this.matrix[k].setNum(j, this.matrix[k].getNum(i));
            this.matrix[k].setNum(i, temp);
        }
        System.out.printf("C%d <=> C%d\n", i, j);
    }

    public int size() {
        return this.matrix.length;
    }

    public int rowSize() {
        return this.matrix[0].size();
    }

    public void printMatrix() {
        for (Row row: this.matrix) {
            row.printRow();
        }
    }

    public Complex[] solve() {

        int endRow = 0;
        int sigEqCount = 0;
        Complex[] out = new Complex[size()];
        System.out.println("Start solving the equation.");
        outerloop:
        for (int i = 0; i < size(); i++) {
            endRow = i;
            innerloop:
            while (true) {
                if (!matrix[i].getNum(i).equalsZero()) break;
                for (int j = (i+1); j < size(); j++) {
                    if (!this.matrix[j].getNum(i).equalsZero()) {
                        this.swapRow(i, j);
                        break innerloop;
                    }
                }
                for (int j = (i+1); j < rowSize() - 1; j++) {
                    if (!this.matrix[i].getNum(j).equalsZero()) {
                        this.swapColumn(i, j);
                        break innerloop;
                    }
                }
                for (int j = (i+1); j < size(); j++){
                    for (int k = (i+1); k < rowSize() - 1; k++) {
                        if (!this.matrix[j].getNum(k).equalsZero()) {
                            swapRow(i, j);
                            swapColumn(i, k);
                            break innerloop;
                        }
                    }
                }
                break outerloop;
            }
            System.out.println("R" + (i + 1)  + " / "+ matrix[i].getNum(i) + " -> R" + (i+1));
            matrix[i] = matrix[i].divide(matrix[i].getNum(i));
            if (i != size() - 1){
                for (int j = i + 1; j < size(); j++) {
                    System.out.println("" + matrix[j].getNum(i).multiply(-1) + " * R" + (i+1) + " + R" + (j+1) + " -> R" + (j+1));
                    Row adder = matrix[i].multiply(matrix[j].getNum(i).multiply(-1));
                    matrix[j].add(adder);
                }
            }
        }
        // Checking for contradictions and counting significant expressions
        for (int i = 0; i < size(); i++) {
            boolean allZeros = true;
            for (int j = 0; j < rowSize() - 1; j++) {
                if (!this.matrix[i].getNum(j).equalsZero()) {
                    allZeros = false;
                }
            }
            if (allZeros && !this.matrix[i].getNum(this.rowSize() - 1).equalsZero()) {
                return new Complex[0];
            }
            if(!allZeros) {
                sigEqCount++;
            }
        }
        // Checking if the equation has infinitely many solutions
        if (sigEqCount < this.rowSize() - 1) {
            return null;
        }
        for (int i = endRow; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                Row adder = matrix[i].multiply(matrix[j].getNum(i).multiply(-1));
                System.out.println("" + matrix[j].getNum(i).multiply(-1) + " * R" + (i+1) + " + R" + (j+1) + " -> R" + (j+1));
                matrix[j].add(adder);
            }
        }

        for (int i = 0; i < size(); i++) {
            out[i] = matrix[i].getNum(rowSize() - 1);
        }
        return out;
    }
}
