package java.boj.Simulation;

import java.util.Scanner;

public class Main8911 {
	static int direction, nowR, nowC, minR, minC, maxR, maxC;
	private static void move(String order){
		int[] dx = {};
		for(char ch : order.toCharArray()){
			switch(ch){
				case 'F' -> {
					if(direction == 1){
						nowC++;
					}
					else if(direction == 0){
						nowR++;
					}
					else if(direction == 3){
						nowC--;
					}
					else if(direction == 2) {
						nowR--;
					}
				}
				case 'B' -> {
					if(direction == 1){
						nowC--;
					}
					else if(direction == 0) {
						nowR--;
					}
					else if(direction == 3){
						nowC++;
					}
					else if(direction == 2) {
						nowR++;
					}
				}
				case 'L' -> {
					direction--;
					if(direction == -1) direction = 3;
				}
				case 'R' -> {
					direction++;
					if(direction == 4) direction = 0;
				}
			}
			maxC = Math.max(maxC, nowC);
			maxR = Math.max(maxR, nowR);
			minC = Math.min(minC, nowC);
			minR = Math.min(minR, nowR);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0; i<T; i++){
			direction = 0;// 북쪽
			nowC = 0;
			nowR = 0;
			minC = 0;
			minR = 0;
			maxC = 0;
			maxR = 0;
			move(sc.next());
			System.out.println((maxR - minR) * (maxC - minC));
		}
	}
}
