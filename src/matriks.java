import java.util.*;

class matriks{
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
    static void matriksInvers (double mat[][], double ans[][], int n) {
		
		double det = determinanKofaktor(mat,n);
		
		matriksKofaktor(mat, ans, n);
		
		matriksTranspose(ans, n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans[i][j] = (1/det) * ans[i][j];
			}
		}
		
	}

	static void matriksInversJordan(double mat[][], double ans[][], int n){
		double[][] tmp = new double[n][2*n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				tmp[i][j] = mat[i][j];
			}
		}
		for(int i=0; i<n; i++){
			for(int j=n; j<2*n; j++){
				if(i+n == j){
					tmp[i][j] = 1;
				}
				else{
					tmp[i][j] = 0;
				}
			}
		}
		gaussjordan(tmp, n, 2*n);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				ans[i][j] = ans[i][j+n];
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

	static void regresi(double mat[][], int n, int k){
        double temp[][] = new double[k+1][k+2];
        //compute augmented baris pertama
        temp[0][0] = n;
        for(int j=1; j<=k+1; j++){
            for(int l=0; l<n; l++){
                temp[0][j] += mat[l][j-1];
            }
        }
        //compute augmented kolom pertama
        for(int i=1; i<=k; i++){
            for(int l=0; l<n; l++){
                temp[i][0] += mat[l][i-1];
            }
        }
        //compute sisanya
        for(int i=1; i<=k; i++){
            for(int j=1; j<=k+1; j++){
                for(int l=0; l<n; l++){
                    temp[i][j] += mat[l][i-1]*mat[l][j-1];
                }
            }
        }
        double ans[] = new double[k+1];
        gauss(temp,ans, k+1, k+1);

        System.out.println("Model regresi : ");
        System.out.print("y = ");
        for (int i = 0; i < k+1; i++){
            System.out.format("%.6f", ans[i]);
            if(i == 0){
                System.out.print(" + ");
            }
            else if(i == k){
                System.out.print("x_" + i);
            }
            else{
                System.out.print("x_" + i +" + ");
            }
        }
    }

    
}