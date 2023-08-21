package com.example.algorithm.ps;

import java.util.*;

public class Solution121683 {
    public String solution(String input_string) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();  // 외톨이 알파벳 저장할 Set
        Map<Character, Boolean> map = new HashMap<>(); // < 알파벳, 외톨이 알파벳인가? >
        char[] charArray = input_string.toCharArray();

        char tmp = ' ';
        for (char c : charArray) {
            if(tmp == c) continue;  // 이전 문자와 같다면 외톨이 알파벳이 아님
            if(map.containsKey(c)) set.add(c); // 이전 문자와 같지 않고, map 포함되어 있다면, Set에 저장
            else map.put(c, true);  // 포함되어 있지 않다면, map에 저장
            tmp = c; // 이전 문자 갱신
        }

        set.stream().sorted().forEach(s -> sb.append(s)); // StringBuilder에 Set 원소들을 정렬하여 저장
        return sb.toString().equals("") ? "N" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution121683().solution("edeaaabbcd"));
    }
}
