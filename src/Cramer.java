import java.util.*;


class Cramer{
    static void swap_col(double mat[][], int row, int i, int j){
        for (int k = 0; k < row; k++){
            double temp = mat[k][i];
            mat[k][i] = mat[k][j];
            mat[k][j] = temp;
        }
    }	

    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }	


    static double[][] removeLastCol(double mat[][])
    {
        int row = mat.length;
        int col = mat[0].length;
    
        double [][] newArray = new double[row][col-1]; //new Array will have one column less
    
    
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < (col - 1); j++)
            {
                newArray[i][j] = mat[i][j];
            }
        }
    
        return newArray;
    }


    // static double determinanOBE(double mat[][], int n){
    //     int cnt = 0;
    //     for (int k = 0; k < n; k++){
    //         int i_max = k;
    //         int v_max = (int)mat[i_max][k];
 
    //         for (int i = k + 1; i < n; i++){
    //             if (Math.abs(mat[i][k]) > v_max){
    //                 v_max = (int)mat[i][k];
    //                 i_max = i;
    //             }
    //         }
    //         if (i_max != k){
    //             swap_row(mat,n,k,i_max);
    //             cnt++;
    //         }
        
    //         for (int i = k + 1; i < n; i++){
    //             double f = mat[i][k]/mat[k][k];
    //             for (int j = k + 1; j < n; j++){
    //                 mat[i][j] -= mat[k][j] * f;
    //             }
    //             mat[i][k] = 0;
    //         }
    //     }
    //     double det;
    //     if(cnt%2 == 0) det = 1;
    //     else det = -1;

    //     for(int i=0; i<n; i++){
    //         det *= mat[i][i];
    //     }            
    //     return det;
    // }

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

    static void cramer(double mat[][],double ans[],boolean solvable, int n){
        double[][] utama = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                utama[i][j] = mat[i][j];
            }
        }
        double dMain = determinanKofaktor(utama, n);
        if (dMain == 0){
            solvable = false;
        } else{
        
            // mencari dx dy dz
            for (int i = 0; i < mat.length; i++){
                double temp[][] = new double[n][mat[0].length];
                for(int y = 0; y < n; y++){
                    for(int u = 0; u < mat[0].length; u++){
                        temp[y][u] = mat[y][u];
                    }
                }
                swap_col(temp, n, i, temp[0].length - 1);
                // for (int p = 0; p < temp.length; p++) { //this equals to the row in our matrix.
                //     for (int l = 0; l < temp[p].length; l++) { //this equals to the column in each row.
                //        System.out.print(temp[p][l] + " ");
                //     }
                //     System.out.println(); //change line on console as row comes to end in the matrix.
                // }
                double[][] tinggalItung = removeLastCol(temp);
                double detKomponen = determinanKofaktor(tinggalItung, n);
                ans[i] = detKomponen / dMain;
                
            }
        }
    }

    public static void main(String[] args){
        double[][] mat = { {2.0, 1.0, -1.0, 1.0}, {3.0, 2.0, 2.0, 13.0}, {4.0, -2.0, 3.0, 9.0} };
        double[] ans = new double[3];
        boolean solvable = true;
        cramer(mat,ans,solvable, 3);
        // for (int i = 0; i < matrix.length; i++) { //this equals to the row in our matrix.
        //     for (int j = 0; j < matrix[i].length; j++) { //this equals to the column in each row.
        //        System.out.print(matrix[i][j] + " ");
        //     }
        //     System.out.println(); //change line on console as row comes to end in the matrix.
        // }
    }
}
