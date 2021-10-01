package pack;
import java.util.*;
import java.io.*;

public class menu {

	public static void main(String[] args){

		while(true) {
			// Menu utama
			System.out.println("MENU");
			System.out.println("1. Sistem Persamaan Linear");
			System.out.println("2. Determinan");
			System.out.println("3. Matriks Balikan");
			System.out.println("4. Interpolasi Polinom");
			System.out.println("5. Regresi Linear Berganda");
			System.out.println("6. Keluar");
			
			// Input pilihan menu
			Scanner scanner = new Scanner(System.in);
			System.out.print("Masukkan metode pilihan : ");
			int menuUtama = scanner.nextInt();
			
			// Masuk ke metode yang dipilih
			if (menuUtama == 1) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Eliminasi Gauss");
				System.out.println("2. Metode Eliminasi Gauss Jordan");
				System.out.println("3. Metode matriks balikan");
				System.out.println("4. Kaidah Cramer");
				System.out.print("Masukkan metode pilihan : ");
				int subMenu = scanner.nextInt();
				if(subMenu == 1){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int row, col;
						row = scanner.nextInt();
						col = scanner.nextInt();
						double mat[][] = new double[row][col];
						//algeo.readMatriks(mat,row,col);
						for(int i=0; i<row; i++){
							for(int j=0; j<col; j++){
								mat[i][j] = scanner.nextDouble();
							}
						}
						algeo.gauss(mat, row, col);
						boolean solvable = true;
						String temp[] = new String[col-1];
						double ans[] = new double[col-1];
						algeo.solusiSPL(mat,row,col,solvable,temp);

						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							algeo.displaySPL(temp,solvable, col-1);
						}
						else if(menuOutput == 2){
							algeo.saveFileSPL(temp,solvable, col-1);
						}
					}
					else if(menuInput == 2){
						double[][] mat = algeo.inputFile("1a.txt");
						int row = mat.length;
						int col = mat[0].length;
						algeo.gauss(mat, row, col);
						boolean solvable = true;
						String temp[] = new String[col-1];
						algeo.solusiSPL(mat,row,col,solvable,temp);

						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						menuInput = scanner.nextInt();
						if(menuInput == 1){
							algeo.displaySPL(temp,solvable, col-1);
						}
						else if(menuInput == 2){
							algeo.saveFileSPL(temp,solvable, col-1);
						}
					}
				}
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int row, col;
						row = scanner.nextInt();
						col = scanner.nextInt();
						double mat[][] = new double[row][col];
						//algeo.readMatriks(mat,row,col);
						for(int i=0; i<row; i++){
							for(int j=0; j<col; j++){
								mat[i][j] = scanner.nextDouble();
							}
						}
						algeo.gaussJordan(mat, row, col);
						boolean solvable = true;
						String temp[] = new String[col-1];
						double ans[] = new double[col-1];
						algeo.solusiSPL(mat,row,col,solvable,temp);

						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							algeo.displaySPL(temp,solvable, col-1);
						}
						else if(menuOutput == 2){
							algeo.saveFileSPL(temp,solvable, col-1);
						}
					}
					else if(menuInput == 2){
						
					}
				}
				else if(subMenu == 3){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int n;
						n = scanner.nextInt();
						double mat[][] = new double[n][n];
						//algeo.readMatriks(mat,row,col);
						for(int i=0; i<n; i++){
							for(int j=0; j<n; j++){
								mat[i][j] = scanner.nextDouble();
							}
						}
						boolean solvable = true;
						double ans[] = new double[n];
						String ansNew[] = new String[n];
						algeo.invers(mat, ans, solvable, n);
						algeo.doubleToString(ans, ansNew);
						
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							algeo.displaySPL(ansNew,solvable, n);
						}
						else if(menuOutput == 2){
							algeo.saveFileSPL(ansNew,solvable, n);
						}
					}
					else if(menuInput == 2){
						
					}
				}

				else if(subMenu == 4){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int n;
						n = scanner.nextInt();
						double mat[][] = new double[n][n];
						//algeo.readMatriks(mat,row,col);
						for(int i=0; i<n; i++){
							for(int j=0; j<n; j++){
								mat[i][j] = scanner.nextDouble();
							}
						}
						boolean solvable = true;
						double ans[] = new double[n];
						algeo.cramer(mat, ans, solvable, n);
						String ansNew[] = new String[n];;
						algeo.doubleToString(ans, ansNew);
						
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							algeo.displaySPL(ansNew,solvable, n);
						}
						else if(menuOutput == 2){
							algeo.saveFileSPL(ansNew,solvable, n);
						}
					}
					else if(menuInput == 2){
						
					}
				}
			} else if (menuUtama == 2) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Ekspansi Kofaktor");
				System.out.println("2. Metode Reduksi Baris");
				System.out.print("Masukkan metode pilihan : ");
				int subMenu = scanner.nextInt();
				if(subMenu == 1){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int n = scanner.nextInt();
						double mat[][] = new double[n][n];
						algeo.readMatriks(mat, n, n);
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							System.out.print("Determinan = ");
							System.out.format("%.6f", algeo.determinanKofaktor(mat,n));
							System.out.println();
						}
						else if(menuOutput == 2){
							try{
								FileWriter writer = new FileWriter("determinan.txt");
								writer.write("Determinan = ");
								String s = String.format("%.6f", algeo.determinanKofaktor(mat,n));
								writer.write(s);
								writer.close();
							}
							catch (IOException e) {
								System.out.println("Gagal menyimpan ke dalam file.");
								e.printStackTrace();
							}
						}

					}
					else if(menuInput == 2){
						double mat[][] = algeo.inputFile("1a.txt");
						int n = mat.length;
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							System.out.print("Determinan = ");
							System.out.format("%.6f", algeo.determinanKofaktor(mat,n));
							System.out.println();
						}
						else if(menuOutput == 2){
							try{
								FileWriter writer = new FileWriter("determinan.txt");
								writer.write("Determinan = ");
								String s = String.format("%.6f", algeo.determinanKofaktor(mat,n));
								writer.write(s);
								writer.close();
							}
							catch (IOException e) {
								System.out.println("Gagal menyimpan ke dalam file.");
								e.printStackTrace();
							}
						}
					}
					
				}
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int n = scanner.nextInt();
						double mat[][] = new double[n][n];
						algeo.readMatriks(mat, n, n);
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							System.out.print("Determinan = ");
							System.out.format("%.6f", algeo.determinanOBE(mat,n));
							System.out.println();
						}
						else if(menuOutput == 2){
							try{
								FileWriter writer = new FileWriter("determinan.txt");
								writer.write("Determinan = ");
								String s = String.format("%.6f", algeo.determinanOBE(mat,n));
								writer.write(s);
								writer.close();
							}
							catch (IOException e) {
								System.out.println("Gagal menyimpan ke dalam file.");
								e.printStackTrace();
							}
						}

					}
					else if(menuInput == 2){
						double mat[][] = algeo.inputFile("1a.txt");
						int n = mat.length;
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							System.out.print("Determinan = ");
							System.out.format("%.6f", algeo.determinanOBE(mat,n));
							System.out.println();
						}
						else if(menuOutput == 2){
							try{
								FileWriter writer = new FileWriter("determinan.txt");
								writer.write("Determinan = ");
								String s = String.format("%.6f", algeo.determinanOBE(mat,n));
								writer.write(s);
								writer.close();
							}
							catch (IOException e) {
								System.out.println("Gagal menyimpan ke dalam file.");
								e.printStackTrace();
							}
						}
					}
				}
			} else if (menuUtama == 3) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Adjoin");
				System.out.println("2. Metode Reduksi Baris");
				System.out.print("Masukkan metode pilihan : ");
				int subMenu = scanner.nextInt();
				if(subMenu == 1){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int row;
						row = scanner.nextInt();
						double mat[][] = new double[row][row];
						algeo.readMatriks(mat, row, row);
						double ans[][] = new double[row][row];
						boolean solvable = true;
						double cekDet = algeo.determinanKofaktor(mat, row);
						if (cekDet == 0){
							solvable = false;
						} else{
							algeo.matriksInvers(mat, ans, row);
						}
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							algeo.displayInvers(ans, solvable, row);
						}
						else if(menuOutput == 2){
							algeo.saveFileInvers(ans, solvable, row);
						}
					}
					else if(menuInput == 2){
						
					//ALGORITMA
					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					menuInput = scanner.nextInt();
					if(menuInput == 1){

					}
					else if(menuInput == 2){
						
					}
					}
				}
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){
						int row;
						row = scanner.nextInt();
						double mat[][] = new double[row][row*2];
						for(int i=0; i < row; i++){
							for(int j=0; j < row; j++){
								mat[i][j] = scanner.nextDouble();
							}
						}
						for(int i = 0; i < row; i++){
							for(int j = 0; j < row; j++ ){
								if(i==j){
									mat[i][j+row]= 1;
								}
								else{
									mat[i][j+row]=0;
								}
							}
						}
						// double ans[][] = new double[row][row];
						// double red[][] = new double[row][row*2];
						boolean solvable = true;
						double cekDet = algeo.determinanKofaktor(mat, row);
						if (cekDet == 0){
							solvable = false;
						} else{
							algeo.gaussJordan(mat, row, row*2);
						}
						System.out.println("OUTPUT");
						System.out.println("1. Output melalui screen");
						System.out.println("2. Output melalui file");
						System.out.print("Masukkan metode pilihan : ");
						int menuOutput = scanner.nextInt();
						if(menuOutput == 1){
							for(int i=0; i<row; i++){
								for(int j=3; j<row*2; j++){
									System.out.print(mat[i][j]);
									System.out.print(" ");
								}
								System.out.println();
							}
						}
						else if(menuOutput == 2){
							// algeo.saveFileInvers(mat, solvable, row);
						}
					}
					else if(menuInput == 2){
						
					}
					//ALGORITMA
					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					menuInput = scanner.nextInt();
					if(menuInput == 1){

					}
					else if(menuInput == 2){
						
					}
				}
			} else if (menuUtama == 4) {
				System.out.println("INPUT");
				System.out.println("1. Input melalui keyboard");
				System.out.println("2. Input melalui file");
				System.out.print("Masukkan metode pilihan : ");
				int menuInput = scanner.nextInt();
				if(menuInput == 1){
					int n;
					n = scanner.nextInt();
					double mat[][] = new double[n+1][2];
					algeo.readMatriks(mat,n+1,2);
					/*for(int i=0; i<n+1; i++){
						for(int j=0; j<2; j++){
							mat[i][j] = scanner.nextDouble();
						}
					}*/
					double ans[] = new double[n+1];
					algeo.interpolasi(mat,ans,n);

					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuOutput = scanner.nextInt();
					if(menuOutput == 1){
						algeo.displayInterpolasi(ans, n);
					}
					else if(menuOutput == 2){
						algeo.saveFileInterpolasi(ans, n);
					}
				}
				else if(menuInput == 2){
					double mat[][] = algeo.inputFile("6a.txt");
					int n = mat.length - 1;
					double ans[] = new double[n+1];
					algeo.interpolasi(mat,ans,n);

					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuOutput = scanner.nextInt();
					if(menuOutput == 1){
						algeo.displayInterpolasi(ans, n);
					}
					else if(menuOutput == 2){
						algeo.saveFileInterpolasi(ans, n);
					}
				}
				
			} else if (menuUtama == 5) {
				System.out.println("INPUT");
				System.out.println("1. Input melalui keyboard");
				System.out.println("2. Input melalui file");
				System.out.print("Masukkan metode pilihan : ");
				int menuInput = scanner.nextInt();
				if(menuInput == 1){
					int row, col;
					row = scanner.nextInt();
					col = scanner.nextInt();
					double mat[][] = new double[row][col];
					//algeo.readMatriks(mat,row,col);
					for(int i=0; i<row; i++){
						for(int j=0; j<col; j++){
							mat[i][j] = scanner.nextDouble();
						}
					}
					double ans[] = new double[col];
					algeo.regresi(mat,ans, row, col-1);

					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuOutput = scanner.nextInt();
					if(menuOutput == 1){
						algeo.displayRegresi(ans, col-1);
					}
					else if(menuOutput == 2){
						algeo.saveFileRegresi(ans, col-1);
					}
				}
				else if(menuInput == 2){
					double mat[][] = algeo.inputFile("7.txt");
					int row = mat.length;
					int col = mat[0].length;
					double ans[] = new double[col];
					algeo.regresi(mat,ans, row, col-1);

					System.out.println("OUTPUT");
					System.out.println("1. Output melalui screen");
					System.out.println("2. Output melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuOutput = scanner.nextInt();
					if(menuOutput == 1){
						algeo.displayRegresi(ans, col-1);
					}
					else if(menuOutput == 2){
						algeo.saveFileRegresi(ans, col-1);
					}
				}
				
			} else if (menuUtama == 6) {
				System.out.println("Terimakasih");
				break;
			} else {
				System.out.println("Mohon ulangi dengan input yang valid");
			}
		}
	}

}