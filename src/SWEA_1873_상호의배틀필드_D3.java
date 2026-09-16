

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1873_상호의배틀필드_D3 {

	static char[][] matrix;

	static char[] dirs = { 'U', 'D', 'R', 'L' };
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[] shape = { '^', 'v', '>', '<' };

	static int H, W;

	static int[] tankPos = new int[2];

	public static int[] findTank() {

		int[] tankPos = new int[2];

		for (int j = 0; j < H; j++) {
			for (int i = 0; i < W; i++) {
				for (int k = 0; k < 4; k++) {
					if (matrix[j][i] == shape[k]) {
						tankPos[0] = i;
						tankPos[1] = j;
						return tankPos;
					}
				}
			}
		}
		return null;
	}

	public static boolean canMove(int x, int y, int dirIdx) {
		// 범위 내 인가
		if ((x + dx[dirIdx]) < 0 || (y + dy[dirIdx]) < 0 || (x + dx[dirIdx]) >= W || (y + dy[dirIdx]) >= H)
			return false;

		if (matrix[y + dy[dirIdx]][x + dx[dirIdx]] != '.')
			return false;

		return true;
	}

	public static void shot() {
		int dirIdx = -1;

		for (int i = 0; i < dirs.length; i++) {
			if (shape[i] == matrix[tankPos[1]][tankPos[0]]) {
				dirIdx = i;
			}
		}

		int bulletX = tankPos[0];
		int bulletY = tankPos[1];

		while (bulletX >= 0 && bulletY >= 0 && bulletX < W && bulletY < H) {

			if (matrix[bulletY][bulletX] == '*') {
				matrix[bulletY][bulletX] = '.';
				break;

			} else if (matrix[bulletY][bulletX] == '#')break;  // 강철이면 총알 스탑
				

			bulletX += dx[dirIdx];
			bulletY += dy[dirIdx];

		}
	}

	public static void move(int x, int y, char dir) {
		// 위, 아래, 오른, 왼

		int dirIdx = -1;
		for (int i = 0; i < dirs.length; i++) {
			if (dirs[i] == dir) {
				dirIdx = i;
				break;
			}
		}

		if (canMove(x, y, dirIdx)) {

			tankPos[0] = x + dx[dirIdx];
			tankPos[1] = y + dy[dirIdx];
			matrix[tankPos[1]][tankPos[0]] = shape[dirIdx];
			matrix[y][x] = '.';

		} else {
			matrix[y][x] = shape[dirIdx];
		}

		return;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= N; tc++) {

			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			matrix = new char[H][W];

			for (int j = 0; j < H; j++) {
				st = new StringTokenizer(br.readLine());
				String line = new String();
				line = st.nextToken();
				for (int i = 0; i < W; i++) {
					matrix[j][i] = line.charAt(i);
				}
			}

			st = new StringTokenizer(br.readLine());

			int cmd_N = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			String cmds = st.nextToken();

			// {x,y}
			tankPos = findTank();

			for (int i = 0; i < cmds.length(); i++) {
				char cmd = cmds.charAt(i);
				if (cmd != 'S') {
					move(tankPos[0], tankPos[1], cmd);
				} else
					shot();
			}
			
			System.out.print("#"+tc+" ");
			
			for (int j = 0; j < H; j++) {
				for (int i = 0; i < W; i++) {

					System.out.print(matrix[j][i]);
				}
				System.out.println();
			}
		}

	}
}
