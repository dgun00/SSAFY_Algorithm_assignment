import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5664_무선충전_Dx {

	// 정지, 상, 우 ,하, 좌
	static int[] dys = { 0, -1, 0, 1, 0 };
	static int[] dxs = { 0, 0, 1, 0, -1 };
	static int[][] BCInfos;
	static int[] inBC;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String[] infos = br.readLine().split(" ");
			int M = Integer.parseInt(infos[0]);
			int A = Integer.parseInt(infos[1]);

			int[][] map = new int[10][10];

			int[] AMove = new int[M + 1];
			int[] BMove = new int[M + 1];

			// 맨 처음 움직임 0
			AMove[0] = 0;
			BMove[0] = 0;

			// 사용자 무빙 저장
			{
				String[] move1 = br.readLine().split(" ");

				for (int i = 0; i < M; i++) {
					AMove[i + 1] = Integer.parseInt(move1[i]);
				}

				String[] move2 = br.readLine().split(" ");

				for (int i = 0; i < M; i++) {
					BMove[i + 1] = Integer.parseInt(move2[i]);
				}
			}

			// 충전기 정보 저장
			{
				BCInfos = new int[A][4];

				for (int j = 0; j < A; j++) {
					String[] line = br.readLine().split(" ");
					for (int i = 0; i < 4; i++) {
						BCInfos[j][i] = Integer.parseInt(line[i]);
					}
				}

			}

			int[] aPos = new int[] { 0, 0 };
			int[] bPos = new int[] { 9, 9 };

			inBC = new int[A];
			
			for (int i = 0; i <= M; i++) {
				// 이번턴 a,b 좌표들
//				int curAx = aPos[0]+dxs[AMove[i]];
//				int curAy = aPos[1]+dys[AMove[i]];
//				
//				int curBx = bPos[0]+dxs[BMove[i]];
//				int curBy = bPos[1]+dys[BMove[i]];

				aPos[0] = aPos[0] + dxs[AMove[i]];
				aPos[1] = aPos[1] + dys[AMove[i]];

				bPos[0] = bPos[0] + dxs[BMove[i]];
				bPos[1] = bPos[1] + dys[BMove[i]];

				cntInBc(aPos, bPos);
				
				if~(inBC배열에서 두개이상 겹치면 ~~~ 분배~~~or~~)
				

			}
		} // end of tc

	}// end of main

	public static void cntInBc(int[] aPos, int[] bPos) {
		for (int i = 0; i < BCInfos.length; i++) {
			int BCx = BCInfos[i][0];
			int BCy = BCInfos[i][1];
			int BCr = BCInfos[i][2];

			// 충전기 범위 안일때
			if (Math.abs(aPos[0] - BCx) + Math.abs(aPos[1] - BCy) <= BCr) {
				inBC[i]++;
			}

			// 충전기 범위 안일때
			if (Math.abs(bPos[0] - BCx) + Math.abs(bPos[1] - BCy) <= BCr) {
				inBC[i]++;
			}

		}
	}
}// end of class
