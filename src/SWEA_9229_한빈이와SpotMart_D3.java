

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와SpotMart_D3 {

	public static int TwoPointer(int[] ary, int TARGET) {

		int front = 0;
		int rear = ary.length - 1;

		int minDiff = Integer.MAX_VALUE;

		while (true) {

			if (front >= rear) {
				break;
			}

			if (ary[front] + ary[rear] == TARGET) {
				return TARGET;
			}

			else if (ary[front] + ary[rear] > TARGET) {
				rear--;
				
			} else if (ary[front] + ary[rear] < TARGET) {
				minDiff = Math.min(minDiff, TARGET - ary[front] - ary[rear]);
				front++;
			}

		}
		
	
		return (0 > TARGET - minDiff) ? -1 : TARGET - minDiff ;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			String N_M_LINE = br.readLine();
			st = new StringTokenizer(N_M_LINE);

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			String weights = br.readLine();

			st = new StringTokenizer(weights);

			int[] ary = new int[N];
			int idx = 0;

			while (st.hasMoreTokens()) {
				ary[idx++] = Integer.parseInt(st.nextToken());

			}

			Arrays.sort(ary);

			int res = TwoPointer(ary, M);

			System.out.println("#" + test_case + " " + res);

		}
	}
}
