def solution(n):
    answer = []
    front = list(map(lambda x : [], range(1,n+1)))  #배열을 합치기 쉽게 하기 위해
    back  = list(map(lambda x : [], range(1,n+1)))  #앞과 뒤로 분리
    numb  = 1                                       #배열에 들어갈 숫자
    for count in range(n):
        turn = count//3     #삼각형을 몇번째 돌고 있는지 계산
        if(count%3 == 0):   #아래로 내려가는 패턴
            for i in range(count-turn,n-turn):  # 1turn 돌 때마다 위아래 1개씩 뺀다
                front[i].append(numb)
                numb+=1
        elif(count%3 == 1): #왼쪽에서 오른쪽으로 가는 패턴
            for i in range(0,n-count):          # 매 턴마다 넣는 숫자 개수가 n-count개
                front[n-turn-1].append(numb)    # turn이 늘어날 때마다 n-1번째줄 n-2번째줄에 추가
                numb+=1
        elif(count%3 == 2): #위로 올라가는 패턴             # 2 ->  x에서 count만큼 가야돼 x=
            for i in range(n-turn-2,count-turn-2,-1):
                back[i].append(numb)
                numb+=1

    for i in range(n):
        answer += front[i]
        answer += back[i][::-1]

    return answer