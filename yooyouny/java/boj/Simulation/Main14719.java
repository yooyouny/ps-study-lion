package java.boj.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main14719 {
	static int[] ground;
	static int amount;
	private static void getAmountOfRain(int startIdx, int endIdx){
		int maxHeight = 0;
		for(int i=startIdx; i<=endIdx; i++){
			maxHeight = Math.max(maxHeight, ground[i]);
		}
		System.out.println(maxHeight);

		for(int i=startIdx; i<=endIdx; i++){
			amount += maxHeight - ground[i];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		ground = new int[w];
		List<Integer> maxIds = new ArrayList<>();
		int maxHeight = 0;
		for(int i=0; i<w; i++) {
			ground[i] = sc.nextInt();
			if(maxHeight < ground[i]) {
				maxHeight = ground[i];
			}
		}
		for(int i=0; i<w; i++) {
			if(maxHeight == ground[i]){
				maxIds.add(i);
			}
		}
		for(int i=0; i<maxIds.size(); i++) {
			int idx = maxIds.get(i);
			// idx가 0인 경우 ?????
			getAmountOfRain(0, idx - 1);
			getAmountOfRain(idx+1, w-1);
		}

		System.out.println(amount);
	}

}
