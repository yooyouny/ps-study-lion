package java.boj.Basic;

import java.util.Scanner;

public class Main2501 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();
		int[] answer = new int[100000];
		int num = 1, idx = 0;
		while(N >= num){
			if(N % num == 0) {
				answer[idx++] = num;
			}
			if (idx == k) {
				System.out.println(num);
				break;
			}
			num++;
		}
		if (idx != k)
			System.out.println(0);

	}

}
