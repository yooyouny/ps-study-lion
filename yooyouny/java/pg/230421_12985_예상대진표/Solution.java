import java.util.*;
class Solution
{
	public int solution(int n, int a, int b)
	{
		int round = 0;
		int first = 0, second = 0, num = n/2;
		boolean chk = false;

		Queue<Integer> winners = new LinkedList<>();
		for(int i=1; i<=n; i++){
			winners.add(i);
		}

		while(!chk){

			for(int i=0; i<num; i++){
				first = winners.isEmpty() ? 0 : winners.poll();
				second = winners.isEmpty() ? 0 : winners.poll();

				if(first == a && second == b || first == b && second == a){
					chk = true;
					break;
				}else{
					if(first == a || first == b)
						winners.add(first);
					else
						winners.add(second);
				}
			}
			num = num/2;
			round++;
		}

		return round;
	}
}
