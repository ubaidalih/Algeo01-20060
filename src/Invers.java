import java.util.*;


class Invers{
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

    static void invers(double mat[][], int n){
        double[] pengali = new double[n];
        for(int i = 0; i < n; i++){
            pengali[i] = mat[i][(mat[0].length) - 1];
        }

        double[][] dikali = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dikali[i][j] = mat[i][j];
            }
        }

        
        double[][] hasilinvers = new double[n][n];

        matriksInvers(dikali, hasilinvers, n);

        double[] finol = new double[n];
        for(int i = 0; i < n; i++){
            finol[i] = 0;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                finol[i] = finol[i] + (hasilinvers[i][j] * pengali[j]);
            }
        }
        for (int i = 0; i < finol.length; i++) { //this equals to the row in our matrix.
            System.out.print(finol[i] + " ");
            }
    }

    static void cramer(double mat[][], int n){
        double[][] utama = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                utama[i][j] = mat[i][j];
            }
        }
        double dMain = determinanKofaktor(utama, n);
        double[] ans = new double[n];
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
        for (int i = 0; i < ans.length; i++) { //this equals to the row in our matrix.
            System.out.print(ans[i] + " ");
            }
    }

    public static void main(String[] args){
        // double[][] mat = { {2.0, 1.0, -1.0, 1.0}, {3.0, 2.0, 2.0, 13.0}, {4.0, -2.0, 3.0, 9.0} };
        double[][] mat = { {2.0, 1.0, -3.0, -9.0}, {1.0, 0, 5.0, 14.0}, {-3.0, 2.0, -1.0, 4.0 }};
        // double[][] mat = { {2.0, 1.0, -3.0}, {1.0, 0, 5.0}, {-3.0, 2.0, -1.0 }};
        double[][] ans = new double[3][3];
        invers(mat, 3);
        // matriksInvers(mat, ans , 3);
        // for (int i = 0; i < ans.length; i++) { //this equals to the row in our matrix.
        //     for (int j = 0; j < ans[i].length; j++) { //this equals to the column in each row.
        //        System.out.print(ans[i][j] + " ");
        //     }
        //     System.out.println(); //change line on console as row comes to end in the matrix.
        // }
    }
}

