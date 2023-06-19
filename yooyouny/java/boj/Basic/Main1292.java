package java.boj.Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1292 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
		int num = 1, sum = 0, cnt = 0, term = 1;
		for(int i=1; i<=end; i++){
			if(i>=start) sum+=num;
			cnt++;
			if(cnt == term){
				num++;
				cnt = 0;
				term++;
			}
		}
		System.out.println(sum);
	}

}
