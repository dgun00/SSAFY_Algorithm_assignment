import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 접근 :
 * 걍 완탐... 인데 최적화하려면 ? visited 배열 만들어서 이미 지나간거는 작은놈이 세고갔으니까 넘기기?
 *
 *
 *
 *
 */
public class SWEA_1861_정사각형방_D4 {

	static int[] dxs = { 0, 0, 1, -1 };
	static int[] dys = { 1, -1, 0, 0 };

	static int N;
	static int[][] grid;

	static boolean[][] visited;
	static int cnt;


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			 N = Integer.parseInt(br.readLine());

			grid = new int[N][N];

			for (int j = 0; j < N; j++) {
				String[] line = br.readLine().split(" ");

				for (int i = 0; i < N; i++) {
					grid[j][i] = Integer.parseInt(line[i]);

				}
			}
			visited = new boolean[N][N];

			
			int maxCntOfVal = 0;
			int maxCnt = -1;
			
		
			
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					cnt = 1;
					if(visited[j][i]) continue;
					
					go(i, j);
					
					if (maxCnt < cnt) {
						
						maxCnt = cnt;
						maxCntOfVal = grid[j][i];
						
						
					// Cnt값이 현재 최대랑 같으면 val이 작은거 저장
					}else if ((maxCnt == cnt) && (maxCntOfVal > grid[j][i] ) ) {
						maxCntOfVal = grid[j][i];
					}

				}
			}
			
			System.out.println("#"+tc+" "+maxCntOfVal+" "+maxCnt);

//			System.out.println(Arrays.deepToString(grid));

		} // end of testCase

	}// end of main
	
	
	public static void go(int stX, int stY) {
		visited[stY][stX] = true;
	
		
		for (int i = 0; i < 4; i++) {
			int nx = stX + dxs[i];
			int ny = stY + dys[i];

			// 다음으로 갈 수 잇을때(+1 차이, 경계X)
			if (isInRange(nx,ny) && canGo(nx, ny, grid[stY][stX])) {
				cnt ++;
				go(nx, ny);
				
			}

		}
		// 더 이상 못갈때
		return ;
	}

	public static boolean isInRange(int x, int y) {
		// 다음 좌표가 범위 밖이면 false
		if (x < 0 || y < 0 || x >= N || y >= N)
			return false;
		return true;
	}
	public static boolean canGo(int x, int y, int prevVal) {

		// 현 좌표 + 1 이 다음 좌표의 값이 아닐때 false
		if (prevVal + 1 != grid[y][x])
			return false;

		return true;
	}

}// end of class
