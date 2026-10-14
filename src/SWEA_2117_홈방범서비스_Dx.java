import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_2117_홈방범서비스_Dx {
	private static int N;
	private static int M;
	private static int[][] city;
	private static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] infos = br.readLine().split(" ");

			// 도시 크기
			N = Integer.parseInt(infos[0]);

			// 한 집당 비용
			M = Integer.parseInt(infos[1]);

			city = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] line = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(line[j]);
				}

			}

			int maxProfit = -1;
			res = -1;
			// 도시 크기 만큼 k늘리면 전체가 커버가능.
			for (int k = 1; k <= N+1; k++) {
				res= Math.max(res, func(k));
				
				
				
			}
			
			System.out.println("#"+tc+" "+res);

		} // end of tc
	}// end of main

	public static int func(int k) {

		int maxInCurrK = -1;
		int cost = k * k + (k - 1) * (k - 1);
		int profit = 0;
		
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				maxInCurrK = Math.max(maxInCurrK, cntHouse(i, j, k));
				
			}
		}
		
		profit = maxInCurrK*M - cost;
		
		return profit >= 0 ? maxInCurrK : -1;
	}

	public static int cntHouse(int x, int y, int k) {

		int cnt = 0;

		if (city[y][x] == 1)
			cnt++;

		for (int i = 1; i < k; i++) {
			{

				for (int j = 1; j < k - i; j++) {
					// 중심 기준 오른쪽 아래 보기
					if (y + j >= 0 && y + j < N && x + i < N && x + i >= 0) {
						if (city[y + j][x + i] == 1) {
							cnt++;
							//System.out.println(x + i + ", " + (y + j) + "," + "1");
						}
					}
					// 오른쪽 위 보기
					if (y - j >= 0 && y - j < N && x + i < N && x + i >= 0) {
						if (city[y - j][x + i] == 1) {
							cnt++;
							//System.out.println(x + i + ", " + (y - j) + "," + "2");
						}
					}

					// 왼쪽 아래 보기
					if (y + j >= 0 && y + j < N && x - i < N && x - i >= 0) {
						if (city[y + j][x - i] == 1) {
							cnt++;
							//System.out.println(x - i + ", " + (y + j) + "," + "3");

						}
					}
					// 왼쪽 위 보기
					if (y - j >= 0 && y - j < N && x - i < N && x - i >= 0) {
						if (city[y - j][x - i] == 1) {
							cnt++;
							//System.out.println(x - i + ", " + (y - j) + "," + "4");
						}

					}
				}

				

			}
		}
		for (int c = 1; c < k; c++) {
			if (x + c >= 0 && x + c < N) {
				if (city[y][x + c] == 1)
					cnt++;
			}
			if (x - c >= 0 && x - c < N) {
				if (city[y][x - c] == 1)
					cnt++;
			}
			if (y + c >= 0 && y + c < N) {
				if (city[y+c][x ] == 1)
					cnt++;
			}
			if (y - c >= 0 && y - c < N) {
				if (city[y-c][x ] == 1)
					cnt++;
			}
		}
		return cnt;
	}
}// end of class
