
import java.util.HashMap;
import java.util.Map;
class Solution {
      public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> clothesMap = getClothesMap(clothes);
        //모든 의상을 고르지 않은 경우
        return answer = getCombinationCount(clothesMap) - 1;
    }

    private int getCombinationCount(Map<String, Integer> map){
        int tmp = 1;
        for (String s : map.keySet()) {
            //안 고른 경우도 포함해서 조건부 확률을 구해준다.
            tmp *= map.get(s) + 1;
        }
        return tmp;
    }



    //TODO: 의상분류 별로 몇개의 의상이 있는지 갯수 세기
    private Map<String, Integer> getClothesMap(String[][] clothes){
        Map<String, Integer> clothesMap = new HashMap<>();
        for (String[] cloth : clothes) {
            if(clothesMap.containsKey(cloth[1])){
                clothesMap.put(cloth[1], clothesMap.get(cloth[1]) + 1);
            }else {
                clothesMap.put(cloth[1], 1);
            }
        }
        return clothesMap;
    }
}
