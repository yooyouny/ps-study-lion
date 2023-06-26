package java1.boj.Basic;

import java.util.Scanner;

public class Main2460 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = Integer.MIN_VALUE;
		int now = 0;
		for(int i=0; i<10; i++){
			int out = sc.nextInt();
			int in = sc.nextInt();
			now += in;
			now -= out;
			answer = Math.max(answer, now);
		}
		System.out.println(answer);
	}

}
