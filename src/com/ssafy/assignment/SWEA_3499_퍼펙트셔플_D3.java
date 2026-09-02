package com.ssafy.assignment;

import java.util.*;

public class SWEA_3499_퍼펙트셔플_D3 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();

			String[] ary = new String[N];

			for (int i = 0; i < N; i++) {
				ary[i] = sc.next();
			}

			String[] shuffledAry = new String[N];

			Deque<String> q1 = new ArrayDeque<>();
			Deque<String> q2 = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {

				if (i < N / 2)
					q1.offer(ary[i]);
				else if (N % 2 != 0 && i == N / 2)
					q1.offer(ary[i]);

				else
					q2.offer(ary[i]);

			}
			String poped;
			int idx = 0;

			while (true) {
				poped = q1.poll();
				if (poped == null)
					break;

				shuffledAry[idx++] = poped;

				poped = q2.poll();
				if (poped == null)
					break;

				shuffledAry[idx++] = poped;

			}
			System.out.print("#" + test_case+" ");
			for (String st : shuffledAry) {
				System.out.print(st + " ");
			}
			System.out.println();
		}

	}// end of main
}// end of class
