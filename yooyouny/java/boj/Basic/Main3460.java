package java.boj.Basic;

import java.util.Scanner;

public class Main3460 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int i=0; i<tc; i++){
			int n = sc.nextInt();
			String s = Integer.toBinaryString(n);
			for(int j=s.length()-1; j>=0; j--) {
				if (s.charAt(j) == '1')
					System.out.print(s.length()-1-j + " ");
			}
		}
	}

}
