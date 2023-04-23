def find_points(line):  # 교점을 구하는 함수
    points = []  # 교점을 저장하는 배열
    for L1 in line:
        a, b, e = L1  # 비교할 직선 1
        for L2 in line:
            c, d, f = L2  # 비교할 직선 2
            bunmo = a * d - b * c
            if (bunmo != 0):  # 두 직선이 평행하지 않는 경우
                x = (b * f - e * d) / bunmo
                y = (e * c - a * f) / bunmo

                # x,y 값이 실수인 경우는 포함하지 않는다.
                if (x % 1 == 0 and y % 1 == 0 and [x, y] not in points):
                    # x,y가 *, / 연산으로 인해 float type으로 저장되어 있다.
                    # 이후 계산의 편리를 위해 사전에 type을 변경 해준다.
                    x, y = int(x), int(y)
                    points += [[x, y]]
    return points


def solution(line):
    answer = []
    points = find_points(line)  # 교점을 저장하는 배열

    # 교점이 1개뿐이어서 이후 계산이 필요없는 경우 제외
    # 교점이 없는 경우는 입력되지 않는다.
    if (len(points) == 1):
        answer = ["*"]
    else:
        # 배열의 크기를 구하고
        # 그림판의 영역과 좌표값을 1사분면내로 옮겨주기 위해
        # x,y좌표값의 최대값과 최소값을 구해준다.
        listx = list(map(lambda x: x[0], points))
        listy = list(map(lambda x: x[1], points))
        maxx = max(listx)
        minx = min(listx)
        maxy = max(listy)
        miny = min(listy)

        sizex = abs(maxx - minx)  # answer 문자열의 가로 사이즈
        sizey = abs(maxy - miny)  # answer 문자열의 세로 사이즈
        for i in range(sizey + 1):
            answer += [["."] * (sizex + 1)]  # '.'으로 사이즈에 맞게 초기화

        for px, py in points:  # y의 경우 위가 0 아래가 n이기 때문에
            px = px - minx  # 반대로 뒤집어 주기 위해
            py = py - miny  # sizey-py로 계산해준다.
            answer[sizey - py][px] = "*"  # 교점에 해당하는 배열에 '*'를 찍어준다.

        for i in range(len(answer)):  # 문자열 합치기
            answer[i] = "".join(answer[i])

    return answer