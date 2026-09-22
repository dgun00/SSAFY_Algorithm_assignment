import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
xx
/*
 * 접근:
 * 
 * 모든격자에 대해 bfs(사각형과 디저트가 겹치지않는방향으로)
 * 
 * 꺾인 방향이 (좌상,좌하, 우상,우하) 중복되거나 진행안될떄 돌아가기
 * 기저는 출발점으로 돌아왔을때 -> 코스 완성
 * 
 * 가지치기는.... 보류
 * 
 * bfs 아닌듯 ㅅㅂ~ 나중에 풀자
 */
public class SWEA_2105_디저트카페_Dx {
	
	static int[][] grid;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			
			grid = new int[N][N];
			
			for(int j=0; j<N;j++) {
				String[] line = br.readLine().split(" ");
				for(int i=0; i<N; i++) {
					grid[j][i] = Integer.parseInt(line[i]);
				}
			}
		}// end of testcase
		
	}// end of main
	
}// end of class
