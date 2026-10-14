

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SWEA_1952_수영장DP_Dx {
	private static int[] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] prices = new int[4 + 1];
			int[] plans = new int[12 + 1];

			for (int i = 1; i <= 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}

			DP = new int[13];

			for (int m = 1; m <= 12; m++) {
				DP[m] = DP[m - 1] + Math.min(plans[m] * prices[1], prices[2]);
				DP[m] = Math.min(DP[m], DP[Math.max(0, m - 3)] + prices[3]);
				
			}
			
			System.out.println("#"+tc+" "+ Math.min(DP[12],prices[4]) );
		} // end of tc

	}// end of main

}// end of class
