package java.boj.Simulation;

import java.util.Scanner;

public class Main8911 {
	static char[][] lcd;
	private static void vertical(int height, int start){
		for(int j=height-1; j>=0; j--){
			lcd[j][start] = '|';
		}
	}

	private static void display(char num, int start){
		if(num == 1){

		}else if(num == 2){

		}else if(num == 3){

		}else if(num == 4){

		}else if(num == 5){

		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		String n = sc.next();
		lcd = new char[2*s + 3][(s + 2) * n.length() + (n.length() - 1)];
		int idx = 0;
		for(int i=0; i<n.length(); i++){
			char num = n.charAt(i);
			if(num == 1){
				display(num, idx + 3);
				idx+=2;
			}else{
				display(num, idx);
				idx+=(s+2) + 1;
			}
		}
	}

}
