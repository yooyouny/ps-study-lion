int[] solution(String[] gems) {
	//중복 되지 않는 보석의 갯수를 구할 set
        HashSet<String> kinds = new HashSet<>(Arrays.asList(gems));
        //map으로 모든 보석을 골랐는지 확인
	HashMap<String, Integer> hm = new HashMap<>();
	//queue
        Queue<String> q = new LinkedList<>();
	
	//1개 있으면 그냥 1부터 1까지 보면 끝
        if(kinds.size()==1) return new int[]{1,1};

        int start=0, tmp_start=0;
	//최소 거리 저장
        int min_distance = Integer.MAX_VALUE;
	
	//모든 보석 순회
        for(String gem: gems){
            //queue에 보석을 넣는다.
		q.add(gem);
		//보석이 있으면 가져와 1더하기
		//없으면 1로 초기화
		//
            hm.put(gem, hm.getOrDefault(gem,0)+1);

            while(true){
		    
                String tmp = q.peek();
                //tmp를 봤는데 1보다 크면 중복이 있다는 뜻
		if(hm.get(tmp)>1){
			//하나 빼준다.
                    hm.put(tmp, hm.get(tmp)-1);
                    q.poll();
                    //시작점 갱신
		    tmp_start++;
                }
		//중복이 
                else    break;
            }

	    //모든 보석을 종류별로 보고 최소길이면 갱신
            if(hm.size()==kinds.size() && min_distance>q.size()){
                min_distance = q.size();
                start = tmp_start;
            }
        }

        return new int[]{start+1, start+min_distance};
    }
