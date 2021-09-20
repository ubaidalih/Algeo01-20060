//cuma iseng-iseng buat, algoritma garis besarnya udah benar tapi belum berbentuk library
//format input output masih sangat berantakan dan belom ngikutin panduan
//persamaan parameter blom dibuat
//presisi masih ada masalah, kayaknya masalah di pembagian sementara ngakalinnya kayak line 37

import java.util.*;
class Gauss{
 
    static void gauss(double mat[][], int row, int col){
        int sol = -1;
        for (int k = 0; k < row; k++){
            
            int i_max = k;
            int v_max = (int)mat[i_max][k];
 
            for (int i = k + 1; i < row; i++){
                if (Math.abs(mat[i][k]) > v_max){
                    v_max = (int)mat[i][k];
                    i_max = i;
                }
            }
            if (mat[k][i_max] == 0){
                sol = k;
            }
            if (i_max != k){
                swap_row(mat,col,k,i_max);
            }
            
            
            for(int j=k; j<=col; j++){
                mat[k][j] /= v_max;
            }
            print(mat, 3, 3);
        
 
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
 
        double x[]= new double[col];
        for (int i = row - 1; i >= 0; i--){
            x[i] = mat[i][col];
            for (int j = i + 1; j < row; j++){
                x[i] -= mat[i][j] * x[j];
            }
            x[i] = x[i] / mat[i][i];
        }
      
        System.out.println();
        System.out.println("Solusi sistem persamaan : ");
        for (int i = 0; i < col; i++){
            System.out.format("%.6f", x[i]);
            System.out.println();
        }
    }
 
    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k <= col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }
 
    static void print(double mat[][], int row, int col){
        for (int i = 0; i < row; i++, System.out.println()){
            for (int j = 0; j <= col; j++){
                System.out.print(mat[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }       
    }

    static void read(double mat[][]){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        for(int i=0; i<row; i++){
            for(int j=0; j<=col; j++){
                mat[i][j] = in.nextInt();
            }
        }
    }
 
  //Buat driver sendiri kalo mau ngetes
}