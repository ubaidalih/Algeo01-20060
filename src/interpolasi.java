import java.util.*;
import java.io.*;

class interpolasi{
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
	
	// fungsi buat nuker baris
    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }	
    static void interpolasi(double mat[][],double ans[], int n){
        double[][] polinom = new double[n+2][n+2];
        for(int i=0; i<=n; i++){
            polinom[i][0] = 1;
            for(int j=1; j<=n; j++){
                polinom[i][j] = Math.pow(mat[i][0], j);
            }
            polinom[i][n+1] = mat[i][1];
        }
        boolean solvable = true;
        gauss(polinom,ans,solvable, n+1, n+1);
    }
    static void displayInterpolasi(double ans[], int n){
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
                System.out.println("x^" + i);
            }
            else{
                System.out.print("x^" + i +" + ");
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan banyak nilai yang akan ditaksir : ");
        int tc = scanner.nextInt();
        for(int i=0; i<tc; i++){
            System.out.print("Masukkan nilai yang akan ditaksir : ");
            double x = scanner.nextDouble();
            System.out.print("f("+x+") = ");
            double taksiran = 0;
            for(int j=0; j<n+1; j++){
                taksiran += ans[j]*Math.pow(x, j); 
            }
            System.out.format("%.6f", taksiran);
            System.out.println();
        }
    }

    static void saveFileInterpolasi(double ans[], int n){
        try {
            FileWriter writer = new FileWriter("interpolasi.txt");
            writer.write("Persamaan Interpolasi : \n");
            writer.write("f(x) = ");
            for (int i = 0; i < n+1; i++){
                String s = String.format("%.6f", ans[i]);  
                writer.write(s);
                if(i == 0){
                    writer.write(" + ");
                }
                else if(i == 1){
                    writer.write("x" + " + ");
                }
                else if(i == n){
                    writer.write("x^" + i +"\n");
                }
                else{
                    writer.write("x^" + i +" + ");
                }
            }
    
            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan banyak nilai yang akan ditaksir : ");
            int tc = scanner.nextInt();
            for(int i=0; i<tc; i++){
                System.out.print("Masukkan nilai yang akan ditaksir : ");
                double x = scanner.nextDouble();
                writer.write("f("+x+") = ");
                double taksiran = 0;
                for(int j=0; j<n+1; j++){
                    taksiran += ans[j]*Math.pow(x, j); 
                }
                String s = String.format("%.6f", taksiran); 
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
    public static void main(String[] args){
        double[][] mat = { {0.1, 0.003}, {0.3, 0.067}, {0.5, 0.148} };
        double ans[]= new double[3]; //double[n+1]

        interpolasi(mat,ans,2);
        //displayInterpolasi(ans,2);
        saveFileInterpolasi(ans,2);
    }
}