import java.util.*;

class interpolasi{
    static void gauss(double mat[][], double ans[], int row, int col){
        for(int j = 0; j < col; j++) {
            int now = 0;   
            while (j<row && j+now<col && mat[j][j+now] != 1) {
                if (mat[j][j+now] != 0 && mat[j][j+now] != 1) {
                    double f = mat[j][j+now];
                    for (int k=0; k<= col; k++) {
                        mat[j][k] /= f;
                    }
                    break;
                }
                else if (mat[j][j] == 0) {
                    now++;
                }
            }
            for(int i = j+1; i < row; i++) {
                if (mat[i][j+now] != 0.0) {
                    double f = mat[i][j+now];
                    for (int k = 0; k <= col; k++) {
                        mat[i][k] = mat[i][k] - f/(mat[j][j+now])*mat[j][k];
                    }
                }
            }
        }
        //print(mat,row,col);

        boolean solvable = true;
        int barisnol = 0;
        for(int i = row-1; i>=0; i--){
            int j = 0;
            while(j<=col && mat[i][j]==0){
                j++;
            }
            if(j ==  col){
                solvable = false;
            }
            else if(j == col+1){
                barisnol++;
            }
            else{
                break;
            }
        }

        if(solvable){
            if(row - barisnol == col){
                int newrow = row - barisnol;
                for (int i = newrow - 1; i >= 0; i--){
                    ans[i] = mat[i][col];
                    for (int j = i + 1; j < newrow; j++){
                        ans[i] -= mat[i][j] * ans[j];
                    }
                    ans[i] = ans[i] / mat[i][i];
                }
                
                System.out.println();
                System.out.println("Solusi sistem persamaan : ");
                for (int i = 0; i < col; i++){
                    System.out.format("%.6f", ans[i]);
                    System.out.println();
                }
            }
            else{
                System.out.println("Sistem persamaan memiliki solusi parametrik.");
                //solusi parametriknya belom buat.
            }
        }
        else{
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        }
    }
	
	// fungsi buat nuker baris
    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }	
    static void interpolasi(double mat[][], int n){
        double[][] polinom = new double[n+2][n+2];
        for(int i=0; i<=n; i++){
            polinom[i][0] = 1;
            for(int j=1; j<=n; j++){
                polinom[i][j] = Math.pow(mat[i][0], j);
            }
            polinom[i][n+1] = mat[i][1];
        }
        double ans[]= new double[n+1];
        gauss(polinom,ans, n+1, n+1);
        System.out.println("Persamaan Interpolasi : ");
        System.out.print("f(x) = ");
        for (int i = 0; i < n+1; i++){
            System.out.format("%.6f", ans[i]);
            if(i == 0){
                System.out.print(" + ");
            }
            else if(i == 1){
                System.out.print("x" + " + ");
            }
            else if(i == n){
                System.out.print("x^" + i);
            }
            else{
                System.out.print("x^" + i +" + ");
            }
        }

    }
    public static void main(String[] args){
        double[][] mat = { {0.1, 0.003}, {0.3, 0.067}, {0.5, 0.148} };
        double[] ans = new double[4];
        interpolasi(mat, 2);
    }
}