import java.util.*;
class Solution {
    public static String solution(String s) {
        //정렬을 위해 char배열
        char[] cArr = s.toCharArray();
        //삽입정렬 sorting
        for (int i = 1; i < cArr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if(cArr[j] > cArr[j - 1]){
                    char tmp = cArr[j];
                    cArr[j] = cArr[j - 1];
                    cArr[j - 1] = tmp;
                    //앞으로 전진하면서 비교하다 더이상 작은 값이 없으면 정지
                }else{
                    break;
                }
            }
        }
        return new String(cArr);
    }
    public static void main(String[] args) {
        System.out.println(solution("Zbcdefg"));
    }
}
