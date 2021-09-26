// coba coba gauss jordan tapi belum meliputi semua kemungkinan
// belum dibuat solusi juga

import java.util.Arrays;
import java.util.Scanner;

public class MyClass {
	
	// ngetes suatu string apakah numerik
	static boolean tesDouble(String x) {
		
		try {
		    double doubleValue = Double.parseDouble(x);
		    return true;
		} catch (NumberFormatException e) {
		    return false;
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
	
	static void gaussJordan(double mat[][], int row, int col){
		
		// Proses gauss biasa
		int idxCol = 0; // Sekarang lagi mau ngebuat angak 1 di kolom ke berapa 
		for (int i = 0; i < row; i++) {
			
			// Ngecek udah sampe kolom terakhir belom
			if (idxCol == col - 1) {
				break;
			}
			
			// Ngecek apakah depannya udah bukan 0
			boolean cek;
			while (true) {
				if (mat[i][idxCol] == 0) {
					cek = false;
					for (int j = i + 1; j < row; j++) { // cari sampe ketemu yang bukan nol di kolom
						if (mat[j][idxCol] != 0) {
							swap_row(mat, col, i, j);
							cek = true;
							break;
						}
					}
					if (cek) {
						break;
					} else { // di idxCol ga ada angka selain 0, pindah kolom lain
						idxCol++;
					}
				} else { // depannya ga 0
					break;
				}
				// ngecek apakah idxCol dah pindah sampe ujung, utk ngebreak while
				if (idxCol == col - 1) {
					break;
				}
			}

			// Ngecek udah sampe kolom terakhir belom, ini utk ngebreak for ny
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
		int idxSatu; //untuk indeks satu terbawah tiap kolom
		
		for (int i = col - 2; i > -1; i--) {
			// Nyari letak 1 terbawah di col ini
			idxSatu = row - 1;
			while (true) {
				if (mat[idxSatu][i] == 1) {
					break;
				}
				idxSatu--;
				if (idxSatu == -1) { // ga ada 1 di col, break while
					break;
				}
			}
			
			// Berarti ga ada 1 di col itu, pindah kolom
			if (idxSatu == -1) {
				continue;
			}
			
			// Kurangin elemen non 0 di atas 1 biar jadi 0
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
		
		// cek jenis solusi
		boolean cekNolKiri = true; // cek apakah row matriks kiri terbawah nol semua
		boolean cekNolKanan = true; // cek apakah row matriks kanan terbawah nol
		for (int i = 0; i < col-1; i++) {
			if (mat[row-1][i] != 0) {
				cekNolKiri = false;
			}
		}
		if (mat[row-1][col-1] != 0) {
			cekNolKiri = false;
		}
		
		if (cekNolKiri && cekNolKanan) {
			System.out.println("Solusi ada tak hingga");
		} else if (cekNolKiri) {
			System.out.println("Tidak ada solusi");
		} else {
			System.out.println("Solusi unik");
		}
		
		// List nama variabel hasil yang mungkin (apabila solusi tak hingga)
		String listVar[] = new String[]{"a", "b", "c", "d","e","f","g","h","i","j","k"};
		int cnt = 0; //nama var apa yang udh kepake
		
		// inisialisasi array jawaban
		String ans[] = new String[col-1];
		for (int i = 0; i < col-1; i++) {
			ans[i] = "tai";
		}
		
		// menghitung hasil dan meletakkannya dalam matriks jawaban
		for (int i = row-1; i > -1; i--) {
			for (int j = col-2; j > -1; j--) {
				
				if ((mat[i][j]==1) && (ans[j]=="tai")) {
					
					double yangNilai = mat[i][col-1];
					String yangVar = "";
					for (int k = j + 1; k < col-1; k++) {
						if (tesDouble(ans[k])) {
							yangNilai = yangNilai - mat[i][k] * Double.parseDouble(ans[k]);
						} else {
							if (mat[i][k] == 0) {
								continue;
							} else if (mat[i][k] == 1) {
								yangVar = yangVar + " - " + ans[k];
							} else if (mat[i][k] == -1) {
								yangVar = yangVar + " + " + ans[k];
							} else if (mat[i][k] < 0){
								yangVar = yangVar + " " + String.format("%f", mat[i][k]) + ans[k];
							} else {
								yangVar = yangVar + " - " +String.format("%f", mat[i][k]) + ans[k];
							}
						}
					}

					ans[j] = String.format("%f", yangNilai) + yangVar;
					break;
						
				} else if (ans[j] == "tai") {
					ans[j] = listVar[cnt];
					cnt++;
				}
			}
		}
		
		System.out.println(Arrays.deepToString(ans));
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

