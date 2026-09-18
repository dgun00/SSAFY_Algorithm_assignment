import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
 * (3 ≤ N ≤ 10) (1 ≤ M ≤ 5) (10 ≤ C ≤ 30)
 * N : 격자
 * M : 한번에 벌통 탐색할 수 잇는 수 -> M은 N이하로 주어짐
 * C : 한 일꾼이 벌통에서 최대로 채취가능한 총 꿀양
 * 
 * 벌통에 있는 모든 꿀을 한번에 채취
 * 각 용기에 있는 꿀의 양의 제곱만큼의 수익
 * 
 * --
 * 접근:
 * 걍 완탐?
 * 
 * 
 * 다른 행일떄, 같은 행일때 따로 구해보죠
 * 
 * 다른행일때는 각 행 최대값 구해서 큰거  ,
 * 같은행일떄는 안겹치게 각 구해서 큰거 
 * 
 * Math.max(다른행max,같은행max) ㄱㄱ
 * 
 */
public class SWEA_2115_벌꿀채취_Dx {
	static int N;
	static int M;
	static int C;
	static int[][] grid;

	static int maxIncome;
	static int maxIncomeInCol;
	static int[] maxIncomeInColList;
	static int[] maxIncomeInSameColList;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] cond = br.readLine().split(" ");

			N = Integer.parseInt(cond[0]);
			M = Integer.parseInt(cond[1]);
			C = Integer.parseInt(cond[2]);

			grid = new int[N][N];

			for (int j = 0; j < N; j++) {
				String[] line = br.readLine().split(" ");
				for (int i = 0; i < N; i++) {
					grid[j][i] = Integer.parseInt(line[i]);
				}
			}
			maxIncomeInColList = new int[N];
			for (int colIdx = 0; colIdx < N; colIdx++) {
				findMaxinAnotherCol(colIdx);
			}
			
			Arrays.sort(maxIncomeInColList);
			
			int anotherColMax = maxIncomeInColList[N-1] + maxIncomeInColList[N-2];
			
			maxIncomeInSameColList = new int[N];
			
			for (int colIdx = 0; colIdx < N; colIdx++) {
				findMaxinSameCol(colIdx);
			}
			
			Arrays.sort(maxIncomeInSameColList);
			
			int sameColMax = maxIncomeInSameColList[N-1] ;
			
			int res = Math.max(anotherColMax, sameColMax);
			
			System.out.println("#"+tc+" "+res);
//			System.out.println(Arrays.toString(maxIncomeInColList));
//			System.out.println(anotherColMax);
			
		} // end of testcase
		

	}// end of main

	public static void findMaxinSameCol(int colIdx) {
		int maxIncomeInSameCol = 0;

		for (int st = 0; st < N-2*M; st++) {
			int tmpIncome1 = 0;
			maxIncome = 0;
			getMaxIncome(colIdx, st, 0, 0, 0);
			tmpIncome1 = maxIncome;
			
			for (int st2 = st + M; st2 < N-M; st2++) {
				int tmpIncome2 = 0;
				maxIncome = 0;
				getMaxIncome(colIdx, st2, 0, 0, 0);
				tmpIncome2 = maxIncome;

				maxIncomeInSameCol = Math.max(maxIncomeInSameCol, tmpIncome1 + tmpIncome2);
			}

		}

		maxIncomeInSameColList[colIdx] = maxIncomeInSameCol;
	}

	public static void findMaxinAnotherCol(int colIdx) {

		maxIncomeInCol = Integer.MIN_VALUE;
		maxIncome = Integer.MIN_VALUE;
		for (int st = 0; st <= N - M; st++) {
			// 행 내의 부분배열의 최대값

			getMaxIncome(colIdx, st, 0, 0, 0);
//			System.out.println(colIdx + " " + st + ":" + maxIncome);

			// 이번 행 내에서 최대 수익
			maxIncomeInCol = Math.max(maxIncome, maxIncomeInCol);
		}
		maxIncomeInColList[colIdx] = maxIncomeInCol;
	}

	// 행의 부분배열에서의 최대 수익
	public static void getMaxIncome(int colIdx, int st, int cnt, int honey, int income) {

		if (honey > C)
			return;

		if (cnt == M) {
			maxIncome = Math.max(income, maxIncome);
			return;
		}

		// 이번 인덱스 꿀 선택
		getMaxIncome(colIdx, st, cnt + 1, honey + grid[colIdx][st + cnt],
				income + ((grid[colIdx][st + cnt]) * (grid[colIdx][st + cnt])));

		// 꿀 안선택
		getMaxIncome(colIdx, st, cnt + 1, honey, income);

	}
}// end of class
