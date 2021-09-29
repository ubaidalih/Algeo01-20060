import java.util.Scanner;

public class menu {

	public static void main(String[] args) {

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
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
				else if(subMenu == 3){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
				else if(subMenu == 4){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
			} else if (menuUtama == 2) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Reduksi Baris");
				System.out.println("2. Metode Ekspansi Kofaktor");
				System.out.print("Masukkan metode pilihan : ");
				int subMenu = scanner.nextInt();
				if(subMenu == 1){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
				else if(subMenu == 2){
					System.out.println("INPUT");
					System.out.println("1. Input melalui keyboard");
					System.out.println("2. Input melalui file");
					System.out.print("Masukkan metode pilihan : ");
					int menuInput = scanner.nextInt();
					if(menuInput == 1){

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
			} else if (menuUtama == 5) {
				System.out.println("INPUT");
				System.out.println("1. Input melalui keyboard");
				System.out.println("2. Input melalui file");
				System.out.print("Masukkan metode pilihan : ");
				int menuInput = scanner.nextInt();
				if(menuInput == 1){

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
			} else if (menuUtama == 6) {
				System.out.println("Terimakasih");
				break;
			} else {
				System.out.println("Mohon ulangi dengan input yang valid");
			}
		}
	}

}