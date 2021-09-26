//Fungsi determinan udah gg, algoritma udah bener harusnya
//Format input output belom ngikutin

import java.util.*;
class determinan{
	static void matriksInvers (double mat[][], double tmp[][], int n) {
		
		double det = determinanKofaktor(mat,n);
		
		matriksKofaktor(mat, tmp, n);
		
		matriksTranspose(tmp, n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp[i][j] = (1/det) * tmp[i][j];
			}
		}
		
	}
	
	static void matriksTranspose(double mat[][], int n) {
		double[][] tmp = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp[i][j] = mat[j][i];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = tmp[i][j];
			}
		}
	}
	
	static void matriksKofaktor(double mat[][], double tmp[][], int n) {
		double[][] temp = new double[n-1][n-1];
		
		for (int matRow = 0; matRow < n; matRow++) {
			int sign = -1;
			if ( (matRow % 2) == 0 ) {
				sign = 1;
			}
			for (int matCol = 0; matCol < n; matCol++) {
				int i = 0, j = 0;
	            for (int row = 0; row < n; row++){
	                for (int col = 0; col < n; col++){
	                    if (row != matRow && col != matCol){
	                        temp[i][j++] = mat[row][col];
	                        if (j == n - 1){
	                            j = 0;
	                            i++;
	                        }
	                    }
	                }
	            }
	            tmp[matRow][matCol] += sign * determinanKofaktor(temp, n-1);
	            sign = -sign;
			}
		}
	}
    static double determinanKofaktor(double mat[][], int n){
        double det = 0;

        if (n == 1) return mat[0][0];

        double[][] temp = new double[n-1][n-1];
     
        int sign = 1;
        for (int k = 0; k < n; k++){
            int i = 0, j = 0;
            int row, col;
            for (row = 0; row < n; row++){
                for (col = 0; col < n; col++){
                    if (row != 0 && col != k){
                        temp[i][j++] = mat[row][col];
                        if (j == n - 1){
                            j = 0;
                            i++;
                        }
                    }
                }
            }
            det += sign * mat[0][k] * determinanKofaktor(temp, n-1);
            sign = -sign;
        }
     
        return det;
    }
    static double determinanOBE(double mat[][], int n){
        int cnt = 0;
        for (int k = 0; k < n; k++){
            int i_max = k;
            int v_max = (int)mat[i_max][k];
 
            for (int i = k + 1; i < n; i++){
                if (Math.abs(mat[i][k]) > v_max){
                    v_max = (int)mat[i][k];
                    i_max = i;
                }
            }
            if (i_max != k){
                swap_row(mat,n,k,i_max);
                cnt++;
            }
        
            for (int i = k + 1; i < n; i++){
                double f = mat[i][k]/mat[k][k];
                for (int j = k + 1; j < n; j++){
                    mat[i][j] -= mat[k][j] * f;
                }
                mat[i][k] = 0;
            }
        }
        double det;
        if(cnt%2 == 0) det = 1;
        else det = -1;

        for(int i=0; i<n; i++){
            det *= mat[i][i];
        }            
        return det;
    }

    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }
    public static void main(String args[]){
        double[][] mat = { { 1, 2, 3 }, { 1, 4, 2 }, {8, 2, 1} };
        System.out.println(determinanKofaktor(mat, 3));
        System.out.println(determinanOBE(mat, 3));
    }
}