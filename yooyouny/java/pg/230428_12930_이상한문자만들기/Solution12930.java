class Solution12930 {
	public static String solution(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<s.length(); i++){
			if(i % 2 == 0){
				sb.append(Character.toString(s.charAt(i)).toUpperCase());
			}else{
				sb.append(Character.toString(s.charAt(i)).toLowerCase());
			}
		}
		return sb.toString();
	}
	public static void main(String[] args){
		System.out.println(solution("  tRy hello  WORLD    "));
	}
}
