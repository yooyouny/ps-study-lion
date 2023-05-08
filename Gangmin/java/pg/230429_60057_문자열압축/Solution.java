class Solution {
   public static int solution(String s) {
        //비교 기준 문자열을 넣을 base
        String base = "";
        //중복된 횟수 -> 첫 번쨰 charset은 미리 base에 넣고 생각하기 때문에 초기값이 1
        int count = 1;
        //첫번쨰 반복시 압축이 하나도 안된 상태와 비교해야 값이 초기화된다.
        int answer = s.length();
        //size는 문자열을 자르는 크기 -> 문자열 길이의 반 이상 넘어가면 의미없음
        for(int size = 1; size <= s.length()/2; size++){
            //첫번째 charset은 미리 설정하고 비교, 이후에는 else문에서 초기화
            base = s.substring(0, size);
            //압축할 문자열을 담을 sb
            StringBuilder sb = new StringBuilder();
            //자를 크기인 size단위로 index를 증가
            //length()와 같게 해줘야 모두 중복인 경우 substring(s.length(), s.length())가 되면서 ""가 들어가고
            //else문으로 들어가 sb에 완성된 charset을 넣는다.
            for(int index = size; index <= s.length(); index += size){
                //index가 String index를 넘어서면 안되기 때문에
                int cutI = Math.min(index + size, s.length());
                //base와 비교할 다음 charset
                String compare = s.substring(index, cutI);
                //중복된 charset이 나오면 count증가
                if(base.equals(compare))
                    count++;
                //중복되지 않은 charset이 나오면 sb에 넣어 결과 문자열을 완성시킨다.
                else{
                    //2보다 작으면 중복이 한번도 안 일어났기 때문에, count를 추가할 필요 x
                    if(count >= 2)
                        sb.append(count);
                    //숫자를 먼저 추가하고, base charset을 추가해준다.
                    //마지막 charset이 중복되지 않으면
                    sb.append(base);
                    //중복되지 않는 charset을 base(기준 charset)으로 바꿔주고 다시 반복문으로 확인
                    base = compare;
                    
                    //count도 1로 초기화
                    count = 1;
                }
            }
            //"abcabcde"같이 문자열을 나누었을 때 size로 딱 나누어 떨어지지 않는 경우
            //위의 반복에서 튀어 나오기 때문에 base로 저장된 마지막 문자열을 붙여 준다.
            //만약 딱 떨어져도 어차피 base에는 그럼 ""가 있기 때문에, 기존 sb에 변화는 없음
            sb.append(base);
            answer = Math.min(sb.length(), answer);
        }
        return answer;
    }
}
