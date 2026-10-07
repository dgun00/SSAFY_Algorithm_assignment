import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1247_최적경로_D5 {

	public static class Edge {
		int from;
		int dist;

		public Edge(int from, int dist) {
			super();
			this.from = from;
			this.dist = dist;
		}
	}

	static int[][] posAry;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());

			posAry = new int[N][2];

			String[] posInfo = br.readLine().split(" ");

			int[] st = new int[] { Integer.parseInt(posInfo[0]), Integer.parseInt(posInfo[1]) };
			int[] fi = new int[] { Integer.parseInt(posInfo[2]), Integer.parseInt(posInfo[3]) };

			int idx = 0;
			for (int i = 4; i < posInfo.length; i += 2) {
				posAry[idx][0] = Integer.parseInt(posInfo[i]);
				posAry[idx][1] = Integer.parseInt(posInfo[i + 1]);
				idx++;
			}

			ArrayList<Edge>[] adjList = new ArrayList[N + 1];

			for (int i = 0; i < N+1; i++) {
				for(int j=0; j<N+1; j++) {
					if(i==j)continue;
					// 
					adjList[i].add(new Edge(j,getDist(i,j)));
				}
				
			}

		}// end of tc
	}// end of main

	public static int getDist(int a, int b) {
		return Math.abs(posAry[a][0] - posAry[b][0]) + Math.abs(posAry[a][1] - posAry[b][1]);
	}
}// end of class
