package com.personal.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int M;
	static int[][] matrix;
	static int[][] putSeedDay;
	static int[][] putSeedCnt;

	static int maxHarvest;
	// 오른쪽, 앞쪽, 왼쪽, 뒤쪽의 순서
	static int[] dys = { 0, -1, 0, 1 };
	static int[] dxs = { 1, 0, -1, 0 };

	static int curX = -1;
	static int curY = -1;

	// 1 : 산 , 2 : 싹,곡식

	public static boolean canGo(int x, int y, int day) {

		// 범위
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return false;
		}
		// 1 : 산 ,
		else if (matrix[y][x] == 1) {
			return false;
		} else if (matrix[y][x] == 2) {
			// 심었는데 아직 안열렸으면 false
			if ((day - putSeedDay[y][x]) < (3 + putSeedCnt[y][x]))
				return false;
		}

		return true;
	}

	public static void dfs(int day, int harvest, int dirsIdx) {
		if (day == M) {
			maxHarvest= Math.max(maxHarvest, harvest);
			return;
		}

		// s남은 날로 안될떄 가지치기 추가

		int nx;
		int ny;

		int newdirsIdx = dirsIdx;

		boolean goFlag = false;
		// 현재 방향부터 오른쪽,위,왼,아래 부터 가도록 함
		while (true) {

			if (dirsIdx == 4) {
				break;
			}
			newdirsIdx = (dirsIdx + 3) % 4;

			nx = curX + dxs[newdirsIdx];
			ny = curY + dys[newdirsIdx];
			if (canGo(nx, ny, day)) {
				goFlag = true;

				break;
			}
			dirsIdx++;
		}

		if (goFlag) {
			// 이동하기전에 현재 농지라면 씨뿌리기
			if (matrix[curY][curX] == 0) {
				matrix[curY][curX] = 2;

				putSeedDay[curY][curX] = day;
				curX = curX + dxs[newdirsIdx];
				curY = curY + dys[newdirsIdx];
				dfs(day + 1, harvest, newdirsIdx);

				// 이동하기전에 현재 곡식이라면 빈농지로 만들기
			} else if (matrix[curY][curX] == 2) {

				matrix[curY][curX] = 0;
				
				// 씨 뿌린날 제거
				putSeedDay[curY][curX] = 0;
				putSeedCnt[curY][curX]++;
				
				curX = curX + dxs[newdirsIdx];
				curY = curY + dys[newdirsIdx];
				dfs(day + 1, harvest + 1, newdirsIdx);
			}
		}else {
			// 가만히
			dfs(day + 1, harvest, newdirsIdx);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			matrix = new int[N][N];

			putSeedDay = new int[N][N];
			putSeedCnt = new int[N][N];
			maxHarvest = Integer.MIN_VALUE;
			// matrix 초기화
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					matrix[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] originalMatrix = new int[N][N];
			
			for (int i = 0; i < matrix.length; i++) {
				originalMatrix[i] = matrix[i].clone(); // 또는 System.arraycopy(...)
			}
			
			// 농지에서부터 시작
			for (int j = 0; j < N - 1; j++) {
				for (int i = 0; i < N - 1; i++) {
					if (matrix[j][i] == 0) {
						matrix = new int[N][N];
						for (int k = 0; k < matrix.length; k++) {
							matrix[k] = originalMatrix[k].clone(); // 또는 System.arraycopy(...)
						}
						curX = i;
						curY = j;
						// 모든 방향 시작
						for (int dir = 0; dir < 4; dir++) {
							dfs(0, 0, dir);
						}

					}
				}
			}
			
			System.out.println("#"+tc+" "+maxHarvest);
		}

		
		
	}// end of main

}// end of class;
