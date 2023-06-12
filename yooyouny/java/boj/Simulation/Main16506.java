package java.boj.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main16506 {
	private static String getOpcode(String input){
		if(input.startsWith("ADD"))
			return "0000";
		else if(input.startsWith("SUB"))
			return "0001";
		else if(input.startsWith("MOV"))
			return "0010";
		else if(input.startsWith("AND"))
			return "0011";
		else if(input.startsWith("OR"))
			return "0100";
		else if(input.equals("NOT"))
			return "0101";
		else if(input.startsWith("MULT"))
			return "0110";
		else if(input.startsWith("LSFTL"))
			return "0111";
		else if(input.startsWith("LSFTR"))
			return "1000";
		else if(input.startsWith("ASFTR"))
			return "1001";
		else if(input.startsWith("RL"))
			return "1010";
		else if(input.startsWith("RR"))
			return "1011";
		return "";
	}
	private static String toBinary(int n, int radix){
		StringBuilder sb = new StringBuilder();
		while(n > 0){
			sb.append(n % 2);
			n /= 2;
		}
		while(sb.length() < radix){
			sb.append("0");
		}
		return sb.reverse().toString();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();

		for(int i=0; i<n; i++){
			StringBuilder sb = new StringBuilder();
			String[] token = br.readLine().split(" ");
			boolean chkDigit = false;

			sb.append(getOpcode(token[0])); //opcode
			if(token[0].charAt(token[0].length() - 1) == 'C') {
				sb.append("1");
				chkDigit = true;
			}else {
				sb.append("0");
			}
			sb.append("0"); //0

			sb.append(toBinary(Integer.parseInt(token[1]), 3));// rd

			if(token[0].startsWith("MOV") || token[0].startsWith("NOT"))// ra
				sb.append("000");
			else
				sb.append(toBinary(Integer.parseInt(token[2]), 3));

			if(!chkDigit) {//rb
				sb.append(toBinary(Integer.parseInt(token[3]), 3));
				sb.append("0");
			}else {
				sb.append(toBinary(Integer.parseInt(token[3]), 4));
			}
			sb.append("\n");
			result.append(sb.toString());
		}
		System.out.println(result.toString());
	}
}
