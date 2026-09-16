import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 접근:
 * 
 * 전체 matrix에 주변 지뢰수 탐색 후 숫자 기입
 * 순회해서 0인 좌표들 저장
 * 0 저장한 배열 또 순회해서 visit[위치][위치] == false이면 클릭(cnt++) -> 연쇄 반응 -> 연쇄반응 난곳 visit처리
 * -> 지뢰 아님 && visit==false 인곳 카운트해서 cnt에 더함
 *
 * 
 */
public class SWEA_1868_파핑파핑지뢰찾기_D4 {

	static int N;
	static char[][] originalMatrix;
	// 주변 지뢰 표시 맵
	static int[][] mineCntMap;

	// 숫자 표시 됐는지 확인용 2차 배열
	static boolean[][] visited;

	// 주변 지뢰 0인 좌표 리스트
	static List<int[]> zeroPosList;

	static int clickCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			originalMatrix = new char[N][N];
			mineCntMap = new int[N][N];
			zeroPosList = new ArrayList<int[]>();

			visited = new boolean[N][N];
			clickCnt = 0;
			// 맵 초기 설정
			for (int j = 0; j < N; j++) {
				String line = br.readLine();
				for (int i = 0; i < N; i++) {
					originalMatrix[j][i] = line.charAt(i);
					if (originalMatrix[j][i] == '*') {
						// 지뢰면 -1 넣기
						mineCntMap[j][i] = -1;
					}
				}
			}

			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (originalMatrix[j][i] != '*') {
						mineCntMap[j][i] = cntAroundMine(i, j);

						// 주변에 지뢰 없는 좌표 저장
						if (mineCntMap[j][i] == 0)
							// {x,y} 형태로 저장
							zeroPosList.add(new int[] { i, j });

					}
				}
			}

			for (int i = 0; i < zeroPosList.size(); i++) {
				int x = zeroPosList.get(i)[0];
				int y = zeroPosList.get(i)[1];
				
				if (visited[y][x] == false) {
					click(x, y);
					clickCnt++;
				}
			}

			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (visited[j][i] == false && originalMatrix[j][i] != '*')
						clickCnt++;
				}
			}

			System.out.println("#" + tc + " " + clickCnt);

		} // end of cases

	}// end of main

	// 클릭시 연쇄 반응
	static void click(int sx, int sy) {
	    Deque<int[]> stack = new ArrayDeque<>();
	    visited[sy][sx] = true;
	    stack.push(new int[]{sx, sy});

	    while (!stack.isEmpty()) {
	        int[] cur = stack.pop();
	        int x = cur[0], y = cur[1];

	        if (mineCntMap[y][x] != 0) continue; // 숫자칸은 열리기만 하고 전파 안 함

	        // 0이면 전파
	        for (int dy = -1; dy <= 1; dy++) {
	            for (int dx = -1; dx <= 1; dx++) {
	                if (dx == 0 && dy == 0) continue;
	                int nx = x + dx, ny = y + dy;
	                if (!isInRange(nx, ny) || visited[ny][nx]) continue;
	                visited[ny][nx] = true;
	                stack.push(new int[]{nx, ny});
	            }
	        }
	    }
	}

	

	static public int cntAroundMine(int x, int y) {

		int cnt = 0;

		if (isInRange(x - 1, y - 1) && originalMatrix[y - 1][x - 1] == '*')
			cnt++;
		if (isInRange(x, y - 1) && originalMatrix[y - 1][x] == '*')
			cnt++;

		if (isInRange(x + 1, y - 1) && originalMatrix[y - 1][x + 1] == '*')
			cnt++;

		if (isInRange(x - 1, y) && originalMatrix[y][x - 1] == '*')
			cnt++;
		if (isInRange(x + 1, y) && originalMatrix[y][x + 1] == '*')
			cnt++;

		if (isInRange(x - 1, y + 1) && originalMatrix[y + 1][x - 1] == '*')
			cnt++;
		if (isInRange(x, y + 1) && originalMatrix[y + 1][x] == '*')
			cnt++;
		if (isInRange(x + 1, y + 1) && originalMatrix[y + 1][x + 1] == '*')
			cnt++;

		return cnt;
	}

	static public boolean isInRange(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;

		return true;
	}

}// end of class
