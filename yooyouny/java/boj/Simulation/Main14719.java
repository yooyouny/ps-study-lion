package java.boj.Simulation;

import java.util.Scanner;

public class Main14719 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int[] ground = new int[w];
		for(int i=0; i<w; i++) {
			ground[i] = sc.nextInt();
		}
		int amount = 0;
		for(int i=1; i<w-1; i++){
			int leftHeight = 0;
			int rightHeight = 0;

			for(int j=i-1; j>=0; j--){
				leftHeight = Math.max(leftHeight, ground[j]);
			}
			for(int j=i+1; j<w; j++){
				rightHeight = Math.max(rightHeight, ground[j]);
			}
			if(leftHeight > ground[i] && rightHeight > ground[i])
				amount += Math.min(leftHeight, rightHeight) - ground[i];
		}

		System.out.println(amount);
	}

}
