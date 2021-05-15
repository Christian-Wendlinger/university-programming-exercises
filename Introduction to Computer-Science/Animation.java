//Version der Spirale, die in DOMJudge abgegeben wurde von Melanie und Christian

import java.util.Scanner;

public class Animation {
	
	public static void main(String[] args){
		
		
		
		//Gleichung oben:	x + y < n-1 && y <= x+1
		//Gleichung links:	y > x+1 && y <= (n-1)- x 
		//Gleichung rechts:	x + y >= n-1 && y < x
		//Gleichung unten:	x + y > n-1 && y > x-1
		
		Scanner eingabe = new Scanner(System.in);
		
		int n = eingabe.nextInt();
		int x = eingabe.nextInt();
		int y = eingabe.nextInt();
		
		if(next(n, x, y) != null){
			int[] a = next(n, x, y);
			System.out.print(a[0] + " " + a[1]);
		}
		else{
			System.out.print("null");
		}
		
		
	}
	
	static int[] next(int n, int x, int y){
		int[] nextPos = new int[2];
		if(n%2 == 0 || n < 1 || x >= n || y >= n || x < 0 || y < 0){
			return null;
		}
		else if(x == (n-1)/2 && y == (n-1)/2){
			nextPos[0] = 0;
			nextPos[1] = 0;
			return nextPos;
		}
		else{
			if(x + y < n-1 && y <= x+1){
				nextPos[0] = x+1;
				nextPos[1] = y;
			}
			else if(y > x+1 && y <= (n-1)- x){
				nextPos[0] = x;
				nextPos[1] = y-1;
			}
			else if(x + y >= n-1 && y < x){
				nextPos[0] = x;
				nextPos[1] = y+1;
			}
			else if(x + y > n-1 && y > x-1){
				nextPos[0] = x-1;
				nextPos[1] = y;
			}
			
			return nextPos;
		}
	}
}