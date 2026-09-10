package com.ssafy.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2806_Nqueen_D3 {

	static int N;
	static int[] cols;

	static int res;

	// '＼' 방향 체크 -> 행,열 인덱스 뺼셈
	static int[] diagonalVal1;

	// '/' 방향 체크 -> 행,열 인덱스 덧셈
	static int[] diagonalVal2;

	static public boolean isPossible(int c, int r) {

		for (int i = 0; i < c; i++) {
			if(cols[i]==r)return false;
			if(diagonalVal1[i] == (c-r)) return false;
			if(diagonalVal2[i] == (c+r)) return false;

		}

		return true;
	}

	static public void setQueen(int c) {

		
		if(c == N) {
			res++;
			return;
		}
		
		for (int r = 0; r < N; r++) {

			if (!isPossible(c, r))
				continue;

			cols[c] = r;
			diagonalVal1[c] = c - r;
			diagonalVal2[c] = c + r;

			setQueen(c + 1);

		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			cols = new int[N];
			diagonalVal1 = new int[N];
			//Arrays.fill(diagonalVal1, -1);
			diagonalVal2 = new int[N];
			//Arrays.fill(diagonalVal2, -1);
			
			res=0;
			setQueen(0);
			
			System.out.println("#"+tc+" "+res);
		}

	}
}
