package java.boj.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main16113 {
	private static String getRowLine(char[][] signal, int width){
		String row = "";
		for(int j=4; j>=0; j--){
			row+=signal[j][width];
		}
		return row;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		char[][] signal = new char[5][(n/5)+1];
		int idx = 0;
		String answer = "";

		for(int i=0; i<5; i++){
			signal[i] = input.substring(idx, Math.min(idx+n/5, n)).toCharArray();
			idx += n/5;
		}
		boolean chk = false;
		for(int i=0; i<signal[0].length; ){
			String row = getRowLine(signal, i);
			if(row.equals(".....")) {
				i += 1;
			}else {
				if (row.equals("#####")) {// 0, 1, 6, 8
					if (i == signal[0].length - 1 || getRowLine(signal, i + 1).equals(".....")) {
						answer += "1";
						chk = true;
					} else {
						chk = false;
						if (getRowLine(signal, i + 1).equals("#...#"))
							answer += "0";
						else if (getRowLine(signal, i + 2).equals("#####"))
							answer += "8";
						else
							answer += "6";
					}
				} else if (row.equals("###.#")) {
					answer += "2";
					chk = false;
				} else if (row.equals("#.#.#")) {
					answer += "3";
					chk = false;
				} else if (row.equals("..###")) {
					answer += "4";
					chk = false;
				} else if (row.equals("#.###")) {// 5, 9
					if (getRowLine(signal, i + 2).equals("#####"))
						answer += "9";
					else
						answer += "5";
					chk = false;
				} else {
					answer += 7;
					chk = false;
				}
				if(chk)
					i+=1;
				else
					i += 4;
			}
		}
		System.out.println(answer);
	}
}
