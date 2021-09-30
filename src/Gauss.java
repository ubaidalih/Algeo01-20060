//cuma iseng-iseng buat, algoritma garis besarnya udah benar tapi belum berbentuk library
//format input output masih sangat berantakan dan belom ngikutin panduan
//persamaan parameter blom dibuat
//presisi masih ada masalah, kayaknya masalah di pembagian sementara ngakalinnya kayak line 37

import java.util.*;
import java.io.*;

class Gauss{

    static void inputAugmented(double mat[][], int row, int col){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<row; i++){
            for(int j=0; j<=col; j++){
                mat[i][j] = scanner.nextDouble();
            }
        }
    }
 
    static void gauss(double mat[][], double ans[], boolean solvable, int row, int col){
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

        solvable = true;
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
                /*
                System.out.println("Sistem persamaan memiliki solusi unik.");
                System.out.println("Solusi sistem persamaan : ");
                for (int i = 0; i < col; i++){
                    System.out.format("%.6f", ans[i]);
                    System.out.println();
                }*/
            }
            else{
                //System.out.println("Sistem persamaan memiliki solusi parametrik.");
                //solusi parametriknya belom buat.
            }
        }
    }
    static void displayGauss(double ans[],boolean solvable, int col){
        if(solvable){
            System.out.println("Solusi sistem persamaan : ");
            for (int i = 0; i < col; i++){
                System.out.print("x_"+ (i+1) + " = ");
                System.out.format("%.6f", ans[i]);
                System.out.println();
            }
        }
        else{
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        }
    }

    static void saveFileGauss(double ans[], boolean solvable, int col){
        if(solvable){
            try{
                FileWriter writer = new FileWriter("splgauss.txt");
                writer.write("Solusi sistem persamaan : \n");
                for (int i = 0; i < col; i++){
                    writer.write("x_"+ (i+1) + " = ");
                    String s = String.format("%.6f", ans[i]);
                    writer.write(s);
                    writer.write("\n");
                }
                writer.close();
                System.out.println("File berhasil disimpan.");
            }
            catch (IOException e) {
                System.out.println("Gagal menyimpan ke dalam file.");
                e.printStackTrace();
            }
            
        }
        else{
            try{
                FileWriter writer = new FileWriter("splgauss.txt");
                writer.write("Sistem persamaan tidak memiliki solusi");
                writer.close();
                System.out.println("File berhasil disimpan.");
            }
            catch (IOException e) {
                System.out.println("Gagal menyimpan ke dalam file.");
                e.printStackTrace();
            }
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

    public static void main(String[] args){
        double[][] mat = { {1, -1, 0, 0, 1, 3}, {1, 1, 0, -3, 0, 6}, {2, -1, 0, 1, -1, 5}, {-1, 2, 0, -2, -1, -1} };
        //double[][] mat2 = { {2,0,8,0,8}, {0,1,0,4,6}, {-4,0,6,0,6}, {0,-2,0,3,-1}, {2,0,-4,0,-4}, {0,1,0,-2,0} };
        /*Scanner scanner = new Scanner(System.in);
        int row, col;
        row = scanner.nextInt();
        col = scanner.nextInt();
        double mat[][] = new double[row][col+1];
        inputAugmented(mat, row, col);*/
        double ans[]= new double[5];
        boolean solvable = true;
        gauss(mat,ans,solvable,4,5);
        print(mat,4,5);
        displayGauss(ans,solvable,5);
    }
 
  //Buat driver sendiri kalo mau ngetes
}