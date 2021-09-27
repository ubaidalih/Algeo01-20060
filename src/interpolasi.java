import java.util.*;

class interpolasi{
    static void gauss(double mat[][], double ans[], int row, int col){
        int sol = -1;
        for (int k = 0; k < row; k++){
            
            int i_max = k;
            double v_max = mat[i_max][k];
 
            for (int i = k + 1; i < row; i++){
                if (Math.abs(mat[i][k]) > v_max){
                    v_max = mat[i][k];
                    i_max = i;
                }
            }
            if (mat[k][i_max] == 0){
                sol = k;
            }
            if (i_max != k){
                swap_row(mat,col+1,k,i_max);
            }
            
            
            for(int j=k; j<=col; j++){
                mat[k][j] /= v_max;
            }
        
 
            for (int i = k + 1; i < row; i++){
                double f = mat[i][k]/mat[k][k];
                for (int j = k + 1; j <= col; j++){
                    mat[i][j] -= mat[k][j] * f;
                }
                mat[i][k] = 0;
            }
        }
        
        
        if (sol != -1){
            if (mat[sol][col] != 0){
                System.out.print("Sistem persamaan tidak memiliki solusi.");
            }
            else{
                System.out.print("Sistem persamaan memiliki tak hingga solusi. (Belum buat solusi parametrik)");
            }
            return;
        }
        
 
        for (int i = row - 1; i >= 0; i--){
            ans[i] = mat[i][col];
            for (int j = i + 1; j < row; j++){
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