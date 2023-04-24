def solution(n):
    answer = []
    # 삼각형 전체의 배열을 미리 만들어준다.
    board = list(map(lambda x: [0] * x, range(1, n + 1)))
    # 문제 패턴을 x,y값 증감에 따라 나눠준다.
    # [x, y]
    pattern = [[0, 1], [1, 0], [-1, -1]]
    numb = 1    #넣을 숫자
    xx = 0      #현재 위치하는 좌표값 x
    yy = -1     #현재 위치하는 좌표값 y

    #높이 n의 삼각형에서 패턴이 도는 횟수는 n번
    for i in range(n):
        # 지금 어떤 방향으로 가고 있는지 이전 코드와 동일하게
        # i%3으로 나눠서 찾아준다.
        now = pattern[i % 3]    # 이번 턴에서 이동할 방향의 x,y 증감값
        for j in range(n - i):
            xx += now[0]
            yy += now[1]
            board[yy][xx] = numb
            numb += 1

    for line in board:
        answer += line
    return answer