package java.boj.Simulation;

import java.util.Arrays;
import java.util.Scanner;

public class Main2290 {
	static char[][] lcd;
	private static void horizon(int r, int c, int s){
		while(s-->0){
			lcd[r][c] = '-';
			c++;
		}
	}
	private static void vertical(int r, int c, int s){
		while(s-->0) {
			lcd[r][c] = '|';
			r++;
		}
	}
	private static void display(char ch, int startIdx, int s){
		switch(ch){
			case '0' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx, s);
				vertical(1, startIdx + s + 1, s);
				vertical(2+s, startIdx, s);
				vertical(2+s, startIdx + s + 1, s);
				horizon((2*s+3) - 1, startIdx+1, s);
			}
			case '2' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx + (s+2 - 1), s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx, s);
				horizon((2*s+3) - 1,startIdx+1, s);
			}
			case '3' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx + (s+2 - 1), s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx + (s+2 - 1), s);
				horizon((2*s+3) - 1,startIdx+1, s);
			}
			case '4' -> {
				vertical(1, startIdx, s);
				vertical(1, startIdx + s + 1, s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx + s + 1, s);
			}
			case '5' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx, s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx + s + 1, s);
				horizon((2*s+3) - 1, startIdx+1,s);
			}
			case '6' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx, s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx, s);
				vertical(2+s, startIdx + s + 1, s);
				horizon((2*s+3) - 1, startIdx+1,s);
			}
			case '7' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx + (s+2 - 1), s);
				vertical(2+s, startIdx + (s+2 - 1), s);
			}
			case '8' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx, s);
				vertical(1, startIdx + s + 1, s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx, s);
				vertical(2+s, startIdx + s + 1, s);
				horizon((2*s+3) - 1, startIdx+1, s);
			}
			case '9' -> {
				horizon(0, startIdx+1, s);
				vertical(1, startIdx, s);
				vertical(1, startIdx + s + 1, s);
				horizon(2+s-1, startIdx+1, s);
				vertical(2+s, startIdx + s + 1, s);
				horizon((2*s+3) - 1, startIdx+1, s);
			}
			case '1' -> {
				vertical(1, startIdx + s + 1, s);
				vertical(2+s, startIdx + s + 1, s);
			}
		}
		startIdx += s+2+1;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		String n = sc.next();
		lcd = new char[2*s + 3][(s+2) * n.length() + n.length()];
		int idx = 0;

		for(char[] ch : lcd)
			Arrays.fill(ch, ' ');

		for(int i=0; i<n.length(); i++){
			char ch = n.charAt(i);
			display(ch, idx, s);
			idx+=s+2+1;
		}
		for(char[] ch : lcd){
			for (char c : ch) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
