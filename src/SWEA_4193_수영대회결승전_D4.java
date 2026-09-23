import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_4193_수영대회결승전_D4 {

	static int[] dxs = { -1, 1, 0, 0 };
	static int[] dys = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] grid = new int[N][N];
			String[] line;

			for (int j = 0; j < N; j++) {

				line = br.readLine().split(" ");
				for (int i = 0; i < N; i++) {
					grid[j][i] = Integer.parseInt(line[i]);
				}

			}

			int[] stPos = new int[2];
			int[] fiPos = new int[2];

			line = br.readLine().split(" ");

			// Pos[0] : y, Pos[1] : x
			stPos[0] = Integer.parseInt(line[0]);
			stPos[1] = Integer.parseInt(line[1]);

			line = br.readLine().split(" ");

			fiPos[0] = Integer.parseInt(line[0]);
			fiPos[1] = Integer.parseInt(line[1]);

			// {y,x,time}
			Deque<int[]> que = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];

			// time초때,  y, x 에 있따는 뜻
			que.add(new int[] { stPos[0], stPos[1], 0 });
			visited[stPos[0]][stPos[1]] = true;

			int res = -1;

			while (!que.isEmpty()) {

				int[] curInfo = que.poll();
				int time = curInfo[2];

				if (( curInfo[0] == fiPos[0]) && (curInfo[1] == fiPos[1])) {
//					System.out.println(curInfo[0] + " " + curInfo[1]);
					res = time;
					break;
				}

				for (int i = 0; i < 4; i++) {
					int ny = curInfo[0] + dys[i];
					int nx = curInfo[1] + dxs[i];

					// 범위 밖 , 벽, 방문한곳일때
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || grid[ny][nx] == 1 || visited[ny][nx] == true) {
						continue;
					}

					// 소용돌이 있는 구간일때
					if (grid[ny][nx] == 2) {

						// 소용돌이 사라지는시간
						if ((time - 2) % 3 == 0) {
							// 소용돌이 사라진곳으로 이동
							que.add(new int[] { ny, nx, time + 1 });
							visited[ny][nx] = true;
						} else {
							// 위치그대로, 시간은 증가한채로 큐에 다시 넣기
							que.add(new int[] { curInfo[0], curInfo[1], time + 1 });
							visited[curInfo[0]][curInfo[1]] = true;
							
						}
						continue;


					}

					// 정상진행
					que.add(new int[] { ny, nx, time + 1 });
					visited[ny][nx] = true;

				}
			}
			System.out.println("#" + tc + " " + res);
		} // end of testcase
	}// end of main
}// end of class
