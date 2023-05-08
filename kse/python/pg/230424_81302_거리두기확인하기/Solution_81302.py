"""
    1. 오른쪽에 사람이 있는 경우
    2. 아래에 사람이 있는 경우
    3. 대각선 방향에 사람이 있는데 오른쪽,아래가 파티션이 아닌 경우
    4. 오른쪽에 빈테이블 -> 사람이 있는 경우
    5. 아래에   테이블 -> 사람이 있는 경우
"""


def check(board):
    SIZE = 5
    for y in range(SIZE):
        for x in range(SIZE):
            if (board[y][x] == "P"):
                if (x < 4):  # 가로 보기
                    if (board[y][x + 1] == "P"):  # 1.오른쪽에 사람이 있는 경우
                        return 0
                    elif (x < 3 and board[y][x + 1] != "X" and board[y][x + 2] == "P"):  # 4.오른쪽에 빈테이블과 사람이 있는 경우
                        return 0
                if (y < 4):
                    if (board[y + 1][x] == "P"):  # 2.아래에 사람이 있는 경우
                        return 0
                    if (y < 3 and board[y + 1][x] != "X" and board[y + 2][x] == "P"):  # 5.아래에 빈테이블과 사람이 있는 경우
                        return 0
                if (x < 4 and y < 4):
                    if (board[y + 1][x + 1] == "P"):  # 3.대각선에 사람이 있는데
                        if (board[y][x + 1] == "O"): return 0  # 오른쪽에 빈테이블인 경우
                        if (board[y + 1][x] == "O"): return 0  # 아래에 빈테이블인 경우
                    if (y > 0):
                        if (board[y - 1][x + 1] == "P"):  # 3.대각선에 사람이 있는데
                            if (board[y][x + 1] == "O"): return 0  # 오른쪽에 빈테이블인 경우
                            if (board[y - 1][x] == "O"): return 0  # 아래에 빈테이블인 경우

    return 1


def solution(places):
    answer = []

    # 대기실 하나씩 2차원 배열로 바꿔서 check() 함수에 넣어준다.
    for one_place in places:
        board = list(map(lambda x: list(x), one_place))
        answer.append(check(board))

    return answer