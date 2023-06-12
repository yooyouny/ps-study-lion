package java.boj.Simulation;

import java.util.Scanner;

public class Main3568 {
	/*변수 이름이 길 수도 있음을 간과함*/
	static StringBuilder result = new StringBuilder();
	public static void getType(String[] input) {
		for (int i = 1; i < input.length; i++) {
			StringBuilder sb = new StringBuilder();
			String var = "";
			for (int j = 0; j < input[i].length(); j++) {
				char ch = input[i].charAt(j);
				if (ch == '*' || ch == '&') {
					sb.append(ch);
				} else if (ch == '[') {
					sb.append(']');
				} else if (ch == ']') {
					sb.append('[');
				} else if (('A' <= ch && ch <= 'Z') || ('a' <= ch && ch <= 'z')) { // 알파벳일 경우 변수이므로 변수명에 저장
					var += ch;
				}
			}
			result.append(input[0] + sb.reverse() + " " + var + ";\n");// builder는 하나의 문자열로 취급되기 때문에 개행문자 넣어줘야함
		}
	}
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		String line = sc.nextLine();
		line = line.replace(",", "").replace(";", "");
		String[] input = line.split(" ");
		getType(input);
		System.out.println(result.toString());
	}
}
