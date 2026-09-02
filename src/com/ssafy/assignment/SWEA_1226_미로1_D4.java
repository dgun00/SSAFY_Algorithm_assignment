package com.ssafy.assignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SWEA_1226_미로1_D4 {

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static int start_x, start_y = -1;
	static int end_x, end_y = -1;

	static char[][] matrix;

	static int flag;

	public static void dfs(int x, int y) {

		if (x == end_x && y == end_y) {
			flag = 1;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (matrix[ny][nx] != '1') {
				matrix[ny][nx] = '1';
				dfs(nx, ny);

			}
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int MATRIXSIZE = 16;

		for (int test_case = 1; test_case <= 10; test_case++) {

			flag = 0;
			String T = br.readLine();
			matrix = new char[MATRIXSIZE][MATRIXSIZE];

			for (int i = 0; i < MATRIXSIZE; i++) {
				String line = br.readLine();
				for (int j = 0; j < MATRIXSIZE; j++) {
					matrix[i][j] = line.charAt(j);

					if (matrix[i][j] == '2') {
						start_x = j;
						start_y = i;
					}
					if (matrix[i][j] == '3') {
						end_x = j;
						end_y = i;
					}

				}
			}

			matrix[start_y][start_x] = '1';
			dfs(start_x, start_y);

			System.out.println("#" + test_case + " " + flag);

		} // end of case

	} // end of main
}// end of class
