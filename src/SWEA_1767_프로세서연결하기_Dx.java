

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기_Dx {

	static int N;
	static int[][] MATRIX;

	static int InitialconnectedCoreCnt;
	static int InitialunconnectedCoreCnt;
	static int res;

	static int maxCoreCnt;
	static int minWireCnt;

	// int[2] : {x,y}
	static List<int[]> unconnectedCorePos;

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	public static void canRes(int wireCnt, int coreCnt) {
		if (coreCnt > maxCoreCnt) {
			maxCoreCnt = coreCnt;
			minWireCnt = wireCnt;
		} else if ((coreCnt == maxCoreCnt) && (minWireCnt > wireCnt)) {
			minWireCnt = wireCnt;
		}

	}

	public static int connectWire(int coreIdx, int dirIdx) {
		// coreIdx : unconnectedCorePos의 인덱스
		// dirIdx : 방향 인덱스
		int curX = unconnectedCorePos.get(coreIdx)[0];
		int curY = unconnectedCorePos.get(coreIdx)[1];

		// 해당 코어 현방향 전선 수
		int cnt = 0;

		while (!isEdge(curX, curY)) {

			int nx = curX + dx[dirIdx];
			int ny = curY + dy[dirIdx];

			if (MATRIX[ny][nx] != 0)
				return -1;
			else {
				MATRIX[ny][nx] = 2;
				cnt++;
				curX = nx;
				curY = ny;
			}

		}

		return cnt;
	}

	public static void dfs(int idx, int wireCnt, int coreCnt) {
		// idx : 초기에 연결 하지 못한 코어 배열(unconnectedCorePos)의 인덱스
		// wireCnt : 현재까지 커넥트된 전선 수
		// coreCnt : 현재까지 커넥트된 코어 수

		if (InitialunconnectedCoreCnt == idx) {

			canRes(wireCnt, coreCnt);
//			System.out.println(minWireCnt);
			return;
		}
		
		// 해당 노드를 선택하지 않는 경우
		dfs(idx + 1, wireCnt, coreCnt);
		
		int curX = unconnectedCorePos.get(idx)[0];
		int curY = unconnectedCorePos.get(idx)[1];

		int[] originalCol = new int[N];
		int[] originalRow = new int[N];

		for (int i = 0; i < N; i++) {
			originalCol[i] = MATRIX[curY][i];
			originalRow[i] = MATRIX[i][curX];
		}

		for (int dirIdx = 0; dirIdx < dx.length; dirIdx++) {

			int tmpCnt = connectWire(idx, dirIdx);

			// connectWire == -1 이면 해당 코어 커넥트 실패
			if (tmpCnt == -1) {
				// 오염되지않은 MATRIX로 롤백
		
				for (int i = 0; i < N; i++) {
					MATRIX[curY][i] = originalCol[i];
					MATRIX[i][curX] = originalRow[i];
				}
				continue;
			}
			
			dfs(idx + 1, wireCnt + tmpCnt, coreCnt + 1);
			
			// 오염되지않은 MATRIX로 롤백
			for (int i = 0; i < N; i++) {
				MATRIX[curY][i] = originalCol[i];
				MATRIX[i][curX] = originalRow[i];
			}
		}

		

	}

	public static boolean isEdge(int x, int y) {
		return (x == 0 || y == 0 || x == N - 1 || y == N - 1) ? true : false;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			InitialconnectedCoreCnt = 0;
			InitialunconnectedCoreCnt = 0;

			maxCoreCnt = Integer.MIN_VALUE;
			minWireCnt = Integer.MAX_VALUE;

			MATRIX = new int[N][N];

			// {x,y}
			unconnectedCorePos = new ArrayList<>();

			// MATRIX 초기화
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						MATRIX[j][i] = 1;

						if (isEdge(i, j)) {
							InitialconnectedCoreCnt++;
						} else {
							unconnectedCorePos.add(new int[] { i, j });
							InitialunconnectedCoreCnt++;
						}
					}
				}
			}
			dfs(0, 0, InitialconnectedCoreCnt);
			System.out.println("#" + tc + " " + minWireCnt);

		}

	} // end of main
} // end of class
