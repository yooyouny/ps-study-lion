class Solution {공
   static boolean shortDistanceCheck(String[] map, int posX, int posY){
 
	//거리가 1인 pos확인
	int[] xStep = {1, -1, 0, 0};
        int[] yStep = {0, 0, 1, -1};

        for(int step = 0; step < 4; step++){
            //1만큼 떨어진 자리 인덱스
	    int othersX = posX + xStep[step];
            int othersY = posY + yStep[step];
	    //map을 넘어가면 continue;
            if(othersX < 0 || othersX > 4 || othersY < 0 || othersY >4)
            continue; 
	    //사람있으면 true반환
            if(map[othersX].charAt(othersY) == 'P')
                return (true);
        }
        return (false);
    }

    static boolean longDistanceCheck(String[] map, int posX, int posY){
        //거리 2
	int[] xStep = {2, -2, 0, 0};
        int[] yStep = {0, 0, 2, -2};
        for(int step = 0; step < 4; step++){
            int othersX = posX + xStep[step];
            int othersY = posY + yStep[step];
            if(othersX < 0 || othersX > 4 || othersY < 0 || othersY >4)
            continue; 
            if(map[othersX].charAt(othersY) == 'P'){
                //거리가 2만큼떨어진 사람사이에 파티션 확인
		int partitionPosX = (posX + othersX)/2;
                int partitionPosY = (posY + othersY)/2;
                //없으면 true
		if(map[partitionPosX].charAt(partitionPosY) != 'X')
                    return (true);
            }
    
        }
        return (false);
    }

    static boolean diagonalCheck(String[] map, int posX, int posY){
        int[] xStep = {1, -1, 1, -1};
        int[] yStep = {1, 1, -1, -1};
        for(int step = 0; step < 4; step++){
            int othersX = posX + xStep[step];
            int othersY = posY + yStep[step];
            if(othersX < 0 || othersX > 4 || othersY < 0 || othersY >4)
                continue; 
            if(map[othersX].charAt(othersY) == 'P'){
                //여기서 중요한게 쇼트서킷 때문에 !=로 하면 아마 앞쪽놈이 X가 있으면
                //false뜨면서 뒤쪽 안보고 지나쳐버림 -> PXPX이런식으로 배치돼어 있을 수도 있는데
                //넘어가 버리면, 1이 나와야되는데 0이 나옴
                //대각선은 X는같고Y는 1차이나거나, Y는 같고 X는 1차이나야 함
		if(!(map[othersX].charAt(posY) == 'X' && map[posX].charAt(othersY) == 'X'))
                    return (true);
            }
        }
        return (false);
    }

    static int isQuarantined(String [] map){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(map[i].charAt(j) == 'P'){
		//조건에 해당하면 격리실패
                    if(shortDistanceCheck(map, i, j))
                        return (0);
                    if(longDistanceCheck(map, i, j))
                        return (0);
                    if(diagonalCheck(map, i, j))
                        return (0);
                }
            }
        } 
	// 세가지 조건을 통과하면 격리 성
        return (1);
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i = 0; i < answer.length; i++){
            answer[i] = isQuarantined(places[i]);
        }
        return answer;
    }
}
