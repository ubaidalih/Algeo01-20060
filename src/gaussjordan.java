// coba coba gauss jordan tapi belum meliputi semua kemungkinan
// belum dibuat solusi juga

import java.util.Arrays;
import java.util.Scanner;

public class MyClass {

    static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }	
	
	static void gaussJordan(double mat[][], int row, int col){
		
		// Proses gauss
		int idxCol = 0;
		for (int i = 0; i < row; i++) {
			
			// Ngecek udah sampe kolom terakhir belom
			if (idxCol == col - 1) {
				break;
			}
			
			// Ngecek depannya udah bukan 0
			boolean cek;
			while (true) {
				if (mat[i][idxCol] == 0) {
					cek = false;
					for (int j = i + 1; j < row; j++) {
						if (mat[j][idxCol] != 0) {
							swap_row(mat, col, i, j);
							cek = true;
							break;
						}
					}
					if (cek) {
						break;
					} else {
						idxCol++;
					}
				} else {
					break;
				}
				if (idxCol == col - 1) {
					break;
				}
			}

			// Ngecek udah sampe kolom terakhir belom
			if (idxCol == col - 1) {
				break;
			}			

			// Ngebagi depannya biar jadi 1
			double pembagi = (double)mat[i][idxCol];
			for (int j = idxCol; j < col; j++) {
				mat[i][j] = mat[i][j] / pembagi;
			}
			
			// Ngurangin paling depan biar jadi 0
			double pengali;
			for (int j = i + 1; j < row; j++) {
				
				if (mat[j][idxCol] == 0) {
					continue;
				}
				
				pengali = (double)mat[j][idxCol] / (double)mat[i][idxCol];
				for (int k = idxCol; k < col; k++) {
					mat[j][k] = mat[j][k] - pengali * mat[i][k];
				}
			}
			idxCol++;
		}
		
		System.out.println(Arrays.deepToString(mat));
		
		// proses backtracking
		
		// Ngurangin semua yang di atas 1 biar jadi 0
		int idxSatu;
		
		for (int i = col - 2; i > -1; i--) {
			
			// Nyari letak 1 di col ini
			idxSatu = row - 1;
			while (true) {
				if (mat[idxSatu][i] == 1) {
					break;
				}
				idxSatu--;
				if (idxSatu == -1) {
					break;
				}
			}
			
			// Berarti ga ada 1 di col itu
			if (idxSatu == -1) {
				continue;
			}
			
			// Kurangin elemen non 0 terbelakang biar jadi 0
			double pengali;
			for (int j = idxSatu - 1; j > -1; j--) {
				if (mat[j][i] == 0) {
					continue;
				}
				pengali = (double)mat[j][i] / (double)mat[idxSatu][i];
				for (int k = i; k < col; k++) {
					mat[j][k] = mat[j][k] - pengali * mat[idxSatu][k];
				}
			}
		}
	}
	
	// Driver
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Row: ");
		int row = scanner.nextInt();
		System.out.print("Column: ");
		int col = scanner.nextInt();
		
		double matriks[][] = new double[row][col];
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j++) {
				System.out.print("Masukkan elemen "+i+" "+j+": ");
				matriks[i][j] = scanner.nextDouble();
			}
		}
		gaussJordan(matriks, row, col);
		System.out.println(Arrays.deepToString(matriks));
	}
}

