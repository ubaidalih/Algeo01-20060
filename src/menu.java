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
			} else if (menuUtama == 2) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Reduksi Baris");
				System.out.println("2. Metode Ekspansi Kofaktor");
			} else if (menuUtama == 3) {
				System.out.println("SUBMENU");
				System.out.println("1. Metode Adjoin");
				System.out.println("2. Metode Reduksi Baris");
			} else if (menuUtama == 4) {
				
			} else if (menuUtama == 5) {
				
			} else if (menuUtama == 6) {
				System.out.println("Terimakasih");
				break;
			} else {
				System.out.println("Mohon ulangi dengan input yang valid");
			}
		}
	}

}