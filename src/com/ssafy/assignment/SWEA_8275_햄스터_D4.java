package com.ssafy.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class SWEA_8275_햄스터_D4 {

	static int N;
	static int X;
	static int M;
	static int[] cages;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 0번 인덱스는 버리기(기록이 1번 우리가 1로 돼있어서)
			cages = new int[N + 1];

			int[][] records = new int[M][3];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int L = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());

				// 기록 저장
				records[i] = new int[] { L, R, S };

			}

			multicombi(0, 1);
			System.out.println("----");
		} // end of testcase

	} // end of main

	public static void multicombi(int selectCnt, int start) {

		if (selectCnt == X * N) {
			System.out.println(Arrays.toString(cages));
			return;
		}

		for (int i = start; i <= N; i++) {
			if (cages[i] + 1 > X)
				continue;
			multicombi(selectCnt + 1, i);
			cages[i]++;
//			System.out.println(i+":"+selectCnt);
			multicombi(selectCnt + 1, i);
			cages[i]++;

			
		}

	}
} // end of class
