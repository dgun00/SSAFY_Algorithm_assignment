import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5648_원자소멸시뮬레이션_Dx {
	// 상 하 좌 우
	static int[] dys = { -1, 1, 0, 0 };
	static int[] dxs = { 0, 0, -1, 1 };
	static int N;
	static int totalEnergy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			List<int[]> atomsInfo = new ArrayList<>();

			// 원자 움직이는 격자 초기화
			int[][] grid = new int[2001][2001];
			
//			// grid, -1 초기화 : 원자가 없을을 의미
//			for (int i = 0; i < grid[0].length; i++) {
//				Arrays.fill(grid[i], -1);
//			}
			
			// 원자 정보 초기
			for (int i = 0; i < N; i++) {
				String[] info = br.readLine().split(" ");
				// x,y 좌표는 격자에 맞게 1000씩 더하기
				atomsInfo.add(new int[] { 1000 + Integer.parseInt(info[0]), 1000 + Integer.parseInt(info[1]),
						Integer.parseInt(info[2]), Integer.parseInt(info[3]) });
			}
			totalEnergy = 0;
			Set<Integer> removeSet = new HashSet<>();

			while (true) {
				
				List<Integer> sortedRemoveList = new ArrayList<>(removeSet);
				// 내림차순 정렬
				sortedRemoveList.sort(Comparator.reverseOrder());

				
				// 삭제 리스트 순회 하면서 atomsInfo에서 삭제 
				for (int idx : sortedRemoveList) {

					// 해당 위치 그리드 원상복구
					int[] curInfo = atomsInfo.get(idx);
					
					grid[curInfo[1]][curInfo[0]] = 0;

					// 에너지 방출
					totalEnergy += curInfo[3];

					// 큰 번호부터 지우기 (앞쪽 인덱스들이 밀리지 않음)
					
					atomsInfo.remove(idx);
					
				}
				 removeSet.clear(); 

				// 종료 조건
				if (atomsInfo.size() == 0) {
					break;
				}
				
				// 격자에 위치 넣기
				for (int i = 0; i < atomsInfo.size(); i++) {
					int[] curInfo = atomsInfo.get(i);

					// 이 위치 원자 수
					grid[curInfo[1]][curInfo[0]] +=1;
				}

				// 다음 위치 범위 밖인지 확인 && info업데이트
				for (int i = 0; i < atomsInfo.size(); i++) {

					int[] curInfo = atomsInfo.get(i);

					int nx = curInfo[0] + dxs[curInfo[2]];
					int ny = curInfo[1] + dys[curInfo[2]];

					// 범위 밖 탈출
					if (!isInRange(nx, ny)) {
						removeSet.add(i);
						continue;
					}
					
					
//					// 충돌
//					if (grid[ny][nx] != -1) {
//						removeSet.add(i);
//						// 기존에 먼저 격자에 있던 인덱스도 넣기 , Set이니까 여러번 넣어도 ㄱㅊ
//						removeSet.add(grid[ny][nx]);
//						continue;
//					}

					// 저번 자리 0으로 복구
					grid[curInfo[1]][curInfo[0]] = 0;
					
					// 다음 좌표 업데이트
					curInfo[0] = nx;
					curInfo[1] = ny;

				}
				
				
				for (int i = 0; i < atomsInfo.size(); i++) {
					
				}

			}
		
			System.out.println(totalEnergy);
		} // end of testcase

	}// end of main

	static public boolean isInRange(int x, int y) {
		if (x < 0 || y < 0 || x >= 2001 || y >= 2001) {
			return false;
		}
		return true;
	}
}// end of class
