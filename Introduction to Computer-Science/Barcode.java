import java.util.Scanner;

public class Barcode {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		int z1 = scan.nextInt();
		int z2 = scan.nextInt();
		int z3 = scan.nextInt();
		int z4 = scan.nextInt();
		int z5 = scan.nextInt();
		int z6 = scan.nextInt();
		int z7 = scan.nextInt();
		int z8 = scan.nextInt();
		int z9 = scan.nextInt();
		int z10 = scan.nextInt();
		int z11 = scan.nextInt();
		int z12 = scan.nextInt();
		
		scan.close();
		
		int e1 = z1*1 + z2*3 + z3*1 + z4*3 + z5*1 +z6*3 + z7*1 + z8*3 + z9*1 + z10*3 + z11*1 + z12*3;
		
		System.out.println((10-(e1%10))%10);
	}
}
