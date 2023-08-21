import java.util.*;
class Solution121685 {
    public String[] solution(int[][] queries) {

        return Arrays.stream(queries)
                .map(query -> findGene(query[0], query[1] - 1))//RR Rr Rr rr을 0번쨰부터 세고 있기 떄문에 구하려는 순서에 -1 처리
                .toArray(String[]::new);
    }
    private String findGene(int generation, int order){
        if(generation == 1)// 루트 유전자
            return "Rr";

        String parent = findGene(generation-1, order/4);// 부모의 유전자 구하기
        if(parent.equals("RR") || parent.equals("rr")) return parent;// 부모가 처음과 마지막 그룹이면 자식도 부모와 동일
기
        //부모가 Rr인 중간그룹이면
        return switch(order%4){//현재 세대의 RR Rr Rr rr 순서로 리턴
            case 0 -> "RR";
            case 3 -> "rr";
            default -> "Rr";
        };
    }
}