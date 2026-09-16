

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반_D4 {

	static int[] people;
	static int N, B;
	static int minDiff;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			people = new int[N];

			for (int i = 0; i < N; i++) {
				people[i] = Integer.parseInt(st.nextToken());
			}

			minDiff = Integer.MAX_VALUE;

			makeCombi(0, 0);

			System.out.println("#" + tc + " " + minDiff);
		}

	}// end of main

	public static void makeCombi(int idx, int sum) {

		if ((idx == N)) {
			if (sum >= B) {
				minDiff = Math.min(minDiff, sum - B);
			}
			return;
		}

		// 이번 인덱스 사람 키 포함
		makeCombi(idx + 1, sum + people[idx]);

		// 이번 인덱스 사람 키 안포함
		makeCombi(idx + 1, sum);

	}
}// end of class
