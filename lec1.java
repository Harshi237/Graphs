import java.util.*;

class lec1{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int e = sc.nextInt();
		int[][] arr = new int[n+1][n+1];

		for(int i =0; i<n; i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			arr[u][v] = 1;
		}
		for(int[] a :arr){
			for(int b:a){
				System.out.print(b+" ");
			}
			System.out.println();
		}

	}
}