public class Main {

    public static void main(String[] args) {
        System.out.println("This program will solve matricies with rotations method.");

        double[][] matrix=new double[][] {
                new double[]{4,1,-2,6},
                new double[]{3,0,1,7},
                new double[]{0,0,2,6}
        };
        print(matrix,false);
        rotations(matrix);
    }


    private static void print(double[][] m, boolean solution) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if(j==3)
                    System.out.print("| ");
                if(solution) {
                    System.out.print("x" + i + ": ");
                    System.out.print(m[i][j] + " ");
                }
                else
                    System.out.print(m[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static boolean solvable(double[][] m) {
        boolean state=false;
        for(int i=0; i<m.length-1; i++)
            if(m[i][i]!=0.0)
                state=true;
        return state;
    }

    private static double[][] mTranspose(double[][] m) {
        double[][] m_transposed=new double[m[0].length][m.length];
        for(int i=0; i<m.length; i++)
            for(int j=0; j<m[0].length; j++)
                m_transposed[j][i]=m[i][j];
        return m_transposed;
    }

    private static void rotations(double[][] matrix) {
        double[][] sol=new double[matrix.length][1];
        double[] temp_first=new double[matrix[0].length];
        double[] temp_second=new double[matrix[0].length];
        for(int i=0; i<matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                double divisor = Math.sqrt(Math.pow(matrix[i][i], 2) + Math.pow(matrix[j][i], 2));
                System.out.print("DIV: " + divisor + "\n");
                double C = matrix[i][i] / divisor;
                double S = matrix[j][i] / divisor;

                for (int el = 0; el < matrix[i].length; el++) {
                    temp_first[el] = matrix[i][el] * C + matrix[j][el] * S;
                    temp_second[el] = matrix[i][el] * (-S) + matrix[j][el] * C;
                }

                for (int el = 0; el < matrix[i].length; el++) {
                    matrix[i][el] = temp_first[el];
                    matrix[j][el] = temp_second[el];
                }
            }
            System.out.println("HERE IS AFTER: "+i);
            print(matrix,false);
        }

        if(!solvable(matrix)) {
            System.out.print("I don't think we can really solve this system. Too many solutions.");
            return;
        }

        for(int i=0; i<matrix.length; i++)
            sol[i][0]=0.0;

        print(matrix,false);

        for(int i=matrix.length-1; i>-1; i--)
            for(int th=i; th<matrix[i].length-1; th++)
                sol[i][0] = (matrix[i][matrix[i].length - 1] - matrix[i][th] * sol[th][0]) / matrix[i][i];
        System.out.print("END:\n");
        print(sol,true);
    }
}
