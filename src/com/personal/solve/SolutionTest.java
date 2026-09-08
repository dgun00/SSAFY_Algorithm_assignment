package com.personal.solve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SolutionTest {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        List<String> genreList = new ArrayList<>();
        List<Integer> playList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // 한 줄에 "장르,재생수" 형식이라고 가정
                String[] parts = line.split(",");
                genreList.add(parts[0].trim());
                playList.add(Integer.parseInt(parts[1].trim()));
            }
        }

        String[] genres = genreList.toArray(new String[0]);
        int[] plays = new int[playList.size()];
        for (int i = 0; i < plays.length; i++) {
            plays[i] = playList.get(i);
        }

        int[] result = solution.solution(genres, plays);
        System.out.println(Arrays.toString(result));
    }
}