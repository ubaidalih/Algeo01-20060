package pack;
import java.util.*;
import java.io.*;

public class algeo {
	
	/* METODE SPL */
	
	public static void gauss(double mat[][], int row, int col){
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
	}
	
	public static void gaussJordan(double mat[][], int row, int col) {
		gauss(mat, row, col);
		// proses backtracking
		
		// Ngurangin semua yang di atas 1 biar jadi 0
		
		for (int i = row-1; i > 0; i--) {
			int idxSatu = -1; //indeks kolom utk satu pertama pada tiap baris
			// Nyari letak 1 pertama di baris ini
			for (int j = 0; j < col-1; j++) {
				if (mat[i][j] == 1) {
					idxSatu = j;
					break;
				}
			}
			if (idxSatu == -1) { // ga ada 1 di baris, pindah baris
				continue;
			}
			// Kurangin elemen non 0 di atas 1 biar jadi 0
			double pengali;
			for (int j = i - 1; j > -1; j--) {
				if (mat[j][idxSatu] == 0) {
					continue;
				}
				pengali = (double)mat[j][idxSatu] / (double)mat[i][idxSatu];
				for (int k = idxSatu; k < col; k++) {
					mat[j][k] = mat[j][k] - pengali * mat[i][k];
				}
			}
		}
	}
	
	public static void invers(double mat[][],double finol[],boolean solvable, int n){
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

        double cekdet = determinanKofaktor(dikali, n);

        if (cekdet == 0){
            solvable = false;
        }else{
            double[][] hasilinvers = new double[n][n];

            matriksInvers(dikali, hasilinvers, n);
            for(int i = 0; i < n; i++){
                finol[i] = 0;
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    finol[i] = finol[i] + (hasilinvers[i][j] * pengali[j]);
                }
            }
        }
    }
	
    public static void cramer(double mat[][],double ans[],boolean solvable, int n){
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
	
	/* METODE DETERMINAN*/
	
	public static double determinanKofaktor(double mat[][], int n){
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

    public static double determinanOBE(double mat[][], int n){
       int cnt = 0;
       double mult = 1;
       // Proses gauss biasa
		int idxCol = 0; // Sekarang lagi mau ngebuat angak 1 di kolom ke berapa 
		for (int i = 0; i < n; i++) {
			
			// Ngecek udah sampe kolom terakhir belom
			if (idxCol == n - 1) {
				break;
			}
			
			// Ngecek apakah depannya udah bukan 0
			boolean cek;
			while (true) {
				if (mat[i][idxCol] == 0) {
					cek = false;
					for (int j = i + 1; j < n; j++) { // cari sampe ketemu yang bukan nol di kolom
						if (mat[j][idxCol] != 0) {
							swap_row(mat, n, i, j);
							cnt++;
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
				if (idxCol == n - 1) {
					break;
				}
			}

			// Ngecek udah sampe kolom terakhir belom, ini utk ngebreak for ny
			if (idxCol == n - 1) {
				break;
			}			

			// Ngebagi depannya biar jadi 1
			double pembagi = (double)mat[i][idxCol];
			mult *= pembagi;
			for (int j = idxCol; j < n; j++) {
				mat[i][j] = mat[i][j] / pembagi;
			}
			
			// Ngurangin paling depan biar jadi 0
			double pengali;
			for (int j = i + 1; j < n; j++) {
				
				if (mat[j][idxCol] == 0) {
					continue;
				}
				
				pengali = (double)mat[j][idxCol] / (double)mat[i][idxCol];
				for (int k = idxCol; k < n; k++) {
					mat[j][k] = mat[j][k] - pengali * mat[i][k];
				}
			}
			idxCol++;
		}
        double det;
        if(cnt%2 == 0) det = 1;
        else det = -1;

        for(int i=0; i<n; i++){
            det *= mat[i][i];
        }            
        return det*mult;
    }
	
	/* METODE INVERS */
    
	public static void matriksInvers (double mat[][], double tmp[][], int n) {
		
		double det = determinanKofaktor(mat,n);
		
		matriksKofaktor(mat, tmp, n);
		
		matriksTranspose(tmp, n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp[i][j] = (1/det) * tmp[i][j];
			}
		}
		
	}
	
	/* INTERPOLASI DAN REGRESI */
	
    public static void interpolasi(double mat[][],double ans[], int n){
        double[][] polinom = new double[n+1][n+2];
        for(int i=0; i<=n; i++){
            polinom[i][0] = 1;
            for(int j=1; j<=n; j++){
                polinom[i][j] = Math.pow(mat[i][0], j);
            }
            polinom[i][n+1] = mat[i][1];
        }
        gauss(polinom, n+1, n+2);
        boolean solvable = true;
        String temp[] = new String[n+1];
        solusiSPL(polinom, n+1, n+2, solvable, temp);
        stringToDouble(temp, ans);
    }
	
    public static void regresi(double mat[][], double ans[], int n, int k){
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
        gauss(temp, k+1, k+2);
        boolean solvable = true;
        String tempstr[] = new String[k+1];
        solusiSPL(temp, k+1, k+2, solvable, tempstr);
        stringToDouble(tempstr, ans);
    }
    
    /* SOLUSI SPL */
    public static void solusiSPL(double mat[][], int row, int col, boolean solvable, String ans[]) {
        solvable = true;
        int barisnol = 0;
        for(int i = row-1; i>=0; i--){
            int j = 0;
            while((j < col) && (mat[i][j]==(double)0)){
                j++;
            }
            System.out.print(barisnol);
            if(j == (col-1)){
                solvable = false;
            }else if(j == (col)){
                barisnol++;
            } else {
            	break;
            }
        }
        
		// List nama variabel hasil yang mungkin (apabila solusi tak hingga)
		String listVar[] = new String[]{"a", "b", "c", "d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int cnt = 0; //nama var apa yang udh kepake
		for (int i = 0; i < col-1; i++) {
			ans[i] = "tai";
		}
		
        if(solvable && (barisnol == row)){
        	for (int i = 0; i < col-1; i++) {
        		ans[i] = listVar[i];
        	}
        } else if(solvable) {
    		// menghitung hasil dan meletakkannya dalam matriks jawaban
    		for (int i = row-(barisnol+1); i > -1; i--) {
    			
    			// cek 1 terkiri
    			int satuTerkiri = 0;
    			for (int j = 0; j < col-1; j++) {
    				if (mat[i][j] == (double)1) {
    					satuTerkiri = j;
    					break;
    				}
    			}
    			for (int j = col-2; j >= satuTerkiri; j--) {
    				
    				if ((j==satuTerkiri) && (ans[j]=="tai")) {
    					
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
    					//System.out.print(i);
    					//System.out.println(j);
    					ans[j] = listVar[cnt];
    					cnt++;
    				}
    			}
    		}
        }
    }
    
    public static void stringToDouble (String ans[], double ansNew[]) {
    	for (int i = 0; i < ans.length; i++) {
    		ansNew[i] = Double.parseDouble(ans[i]);
    	}
    }
    
    
	/* FUNGSI UTILITAS */
	
    public static void swap_row(double mat[][], int col, int i, int j){
        for (int k = 0; k < col; k++){
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }
    
    public static void swap_col(double mat[][], int row, int i, int j){
        for (int k = 0; k < row; k++){
            double temp = mat[k][i];
            mat[k][i] = mat[k][j];
            mat[k][j] = temp;
        }
    }	
    
    public static double[][] removeLastCol(double mat[][])
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

	public static void matriksTranspose(double mat[][], int n) {
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
	
	public static void matriksKofaktor(double mat[][], double tmp[][], int n) {
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
    
	public static boolean tesDouble(String x) {
		
		try {
		    double doubleValue = Double.parseDouble(x);
		    return true;
		} catch (NumberFormatException e) {
		    return false;
		}
	}
    /* FUNGSI I/O */
    
    public static void print(double mat[][], int row, int col){
        for (int i = 0; i < row; i++, System.out.println()){
            for (int j = 0; j < col; j++){
                System.out.print(mat[i][j] + 0.0);
                System.out.print(" ");
            }
            System.out.println();
        }       
    }
    
    public static void readMatriks(double mat[][],int row, int col){
        Scanner in = new Scanner(System.in);
        for(int i=0; i < row; i++){
            for(int j=0; j < col; j++){
                mat[i][j] = in.nextDouble();
            }
        }
    }

    public static double[][] inputFile(String fileName){
        double temp[][] = new double[1][1];        
        try{
            //File file = new File(new File("../test/"+fileName).getCanonicalPath());
            File file = new File(new File(fileName).getCanonicalPath());
            Scanner scanner = new Scanner(file);

            int col = 0;
            int row = 0;
            while(scanner.hasNextLine()){
                if(row == 0){
                    col = (scanner.nextLine().trim().split(" ")).length;
                }
                else scanner.nextLine();
                row++;
            }

            Scanner scanner2 = new Scanner(file);
            double mat[][] = new double[row][col];
            for (int i = 0; i < row; i++){
                for (int j = 0; j <col; j++){
                    mat[i][j] = scanner2.nextDouble();
                }
            }
            temp = mat;
            //return mat;
        }
        catch (FileNotFoundException ex){
            System.out.println("File tidak ditemukan.");
        } 
        catch (Exception ex){
            ex.printStackTrace();
        }
        return temp;
    }


    public static void displaySPL(String ans[],boolean solvable, int col){
        if(ans[0] != "tai"){
            System.out.println("Solusi sistem persamaan : ");
            for (int i = 0; i < col; i++){
                System.out.print("x_"+ (i+1) + " = " +ans[i]);
                System.out.println();
            }
        }
        else{
            System.out.println("Sistem persamaan tidak memiliki solusi.");
        }
    }

    public static void saveFileSPL(String ans[], boolean solvable, int col){
        if(ans[0] != "tai"){
            try{
                FileWriter writer = new FileWriter("spl.txt");
                writer.write("Solusi sistem persamaan : \n");
                for (int i = 0; i < col; i++){
                    writer.write("x_"+ (i+1) + " = "+ ans[i]);
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


    public static void displayInvers(double ans[][], boolean solvable, int n){
        if(solvable){
            System.out.println("Matriks invers : ");
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    System.out.format("%.6f", ans[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("Matriks tidak memiliki invers.");
        }
    }

    public static void saveFileInvers(double ans[][], boolean solvable, int n){
        try{
            FileWriter writer = new FileWriter("invers.txt");
            if(solvable){
                writer.write("Matriks invers : ");
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        String s = String.format("%.6f", ans[i][j]);
                        writer.write(s);
                        writer.write(" ");
                    }
                    writer.write("\n");
                }
            }
            else{
                writer.write("Matriks tidak memiliki invers.");
            }
            writer.close();
            System.out.println("File berhasil disimpan.");
        }
        catch (IOException e) {
            System.out.println("Gagal menyimpan ke dalam file.");
            e.printStackTrace();
        }

    }
    
    public static void displayInterpolasi(double ans[], int n){
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
    
    public static void saveFileInterpolasi(double ans[], int n){
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
    
    public static void displayRegresi(double ans[], int k){
        System.out.println("Model regresi : ");
        System.out.print("y = ");
        for (int i = 0; i < k+1; i++){
            System.out.format("%.6f", ans[i]);
            if(i == 0){
                System.out.print(" + ");
            }
            else if(i == k){
                System.out.println("x_" + i);
            }
            else{
                System.out.print("x_" + i +" + ");
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan banyak nilai yang akan ditaksir : ");
        int tc = scanner.nextInt();
        for(int i=0; i<tc; i++){
            System.out.print("Masukkan nilai x_i yang akan ditaksir : ");
            double taksiran = ans[0];
            for(int j=1; j<=k; j++){
                double x = scanner.nextDouble();
                taksiran += x*ans[j];
            }
            System.out.print("y = ");
            System.out.format("%.6f", taksiran);
            System.out.println();
        }
    }

    public static void saveFileRegresi(double ans[], int k){
        try {
            FileWriter writer = new FileWriter("regresi.txt");
            writer.write("Model regresi : \n");
            writer.write("y = ");
            for (int i = 0; i < k+1; i++){
                String s = String.format("%.6f", ans[i]);
                writer.write(s);
                if(i == 0){
                    writer.write(" + ");
                }
                else if(i == k){
                    writer.write("x_" + i +"\n");
                }
                else{
                    writer.write("x_" + i +" + ");
                }
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan banyak nilai yang akan ditaksir : ");
            int tc = scanner.nextInt();
            for(int i=0; i<tc; i++){
                System.out.print("Masukkan nilai x_i yang akan ditaksir : ");
                double taksiran = ans[0];
                for(int j=1; j<=k; j++){
                    double x = scanner.nextDouble();
                    taksiran += x*ans[j];
                }
                writer.write("y = ");
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
    
    /* Driver */
    
	public static void main(String[] args) {
        double[][] mat = { {72.4, 76.3,0.9}, {41.6, 70.3, 0.91}, {34.3, 77.1, 0.96}, {35.1, 68.0, 0.89} };
        double ans[] = new double[3]; //double[k+1]
        regresi(mat,ans, 4, 2);
        displayRegresi(ans, 2);
		// TODO Auto-generated method stub
		/*Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

		double matriks[][] = new double[row][col];
		read(matriks, row, col);
		gaussJordan(matriks, row, col);
		print(matriks, row, col);
		//gauss(matriks, row, col);
		String ans[] = new String[col-1];
		double ansNew[] = new double[col-1];
		boolean solvable = false;
		solusiSPL(matriks, row, col, solvable, ans);
		System.out.println(Arrays.toString(ans));
		stringToDouble(ans,ansNew);
		System.out.print(ansNew[0]*2);*/
	}

}
