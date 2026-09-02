package com.ssafy.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산유효성검사_D4 {

	public static boolean isNum(String el) {
		
		
		if (Character.isDigit(el.charAt(0))) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int res = 1;

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < N; i++) {

				String curLine = br.readLine();
				StringTokenizer st = new StringTokenizer(curLine);

				int tokenCnt = st.countTokens();

				// 자식 1개면 X
				if (tokenCnt == 3) {
					res = 0;
				
				}

				// 자식 있는데 본인이 연산기호가 아니면 X
				else if (tokenCnt == 4) {
					String idx = st.nextToken();
					String data = st.nextToken();

					if (isNum(data)) {
						res = 0;
		
					}

				}

				// 말단 노드인데 숫자가 아니면 X
				else if (tokenCnt == 2) {
					String idx = st.nextToken();
					String data = st.nextToken();

					if (!(isNum(data))) {
						res = 0;
	
					}
				}

			}
			sb.append("#").append(test_case).append(" ").append(res);
			System.out.println(sb);
		}

	}// end of main

}// end of class
