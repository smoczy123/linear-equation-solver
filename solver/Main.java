package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        File file = new File(args[1]);
        int n;
        int m;
        Complex[] solutions;
        try (Scanner scanner = new Scanner(file)) {

            n = scanner.nextInt();
            m = scanner.nextInt();
            scanner.nextLine();
            Matrix matrix = new Matrix(n, m);
            for (int i = 0; i < m; i++) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] nums = line.split(" ");
                for (int j = 0; j < (n + 1); j++) {
                    String real = "";
                    String imag = "";
                    int k = 1;
                    if (nums[j].equals("i")) {
                        imag = "1";
                    } else if (nums[j].length() == 1) {
                        real = nums[j];
                    }
                    while (k != nums[j].length() && nums[j].length() != 1) {
                         if (nums[j].charAt(k) == '+') {
                             real = nums[j].substring(0, k);
                             imag = nums[j].substring(k+1, nums[j].length() - 1);
                             break;
                         }
                         if (nums[j].charAt(k) == '-') {
                             real = nums[j].substring(0,k);
                             imag = nums[j].substring(k, nums[j].length() - 1);
                             break;
                         }
                         if (k == nums[j].length() - 1) {
                             if (nums[j].charAt(k) == 'i') {
                                 imag = nums[j].substring(0, nums[j].length() - 1);
                             } else {
                                 real = nums[j];
                             }
                         }
                         k++;
                    }
                    double realDouble;
                    if (real.equals("")) {
                        realDouble = 0;
                    } else {
                        realDouble = Double.parseDouble(real);
                    }
                    double imagDouble;
                    if (k == nums[j].length() && imag.equals("")) {
                        imagDouble = 0;
                    } else if (imag.equals("")) {
                        imagDouble = 1;
                    } else if (imag.equals("-")) {
                        imagDouble = -1;
                    } else {
                        imagDouble = Double.parseDouble(imag);
                    }
                    Complex s = new Complex(realDouble, imagDouble);
                    matrix.getRow(i).setNum(j, s);
                }
            }
            matrix.printMatrix();
            solutions = matrix.solve();
            if (solutions == null) {
                System.out.print("Infinitely many solutions");
            } else if (solutions.length == 0) {
                System.out.print("No solutions");
            } else {
                System.out.print("The solution is: (");
                for (int i = 0; i < n; i++) {
                    System.out.print(solutions[i]);
                    if (i != n - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
            File out = new File(args[3]);
            try (FileWriter writer = new FileWriter(out)) {
                if (solutions == null) {
                    writer.write("Infinitely many solutions");
                } else if (solutions.length == 0) {
                    writer.write("No solutions");
                } else {
                    for (int i = 0; i < n; i++) {
                        writer.write(String.valueOf(solutions[i]));
                        if (i != n - 1) {
                            writer.write("\n");
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Output error");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
