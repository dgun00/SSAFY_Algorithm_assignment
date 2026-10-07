

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1251_하나로_D4 {

	public static class Edge {
		int to;
		double cost;

		public Edge(int to, double cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] islandPos = new int[N][2];

			String[] Xs = br.readLine().split(" ");
			String[] Ys = br.readLine().split(" ");

			// 섬 정보 저장
			for (int i = 0; i < N; i++) {
				islandPos[i][0] = Integer.parseInt(Xs[i]);
				islandPos[i][1] = Integer.parseInt(Ys[i]);
			}

			double E = Double.parseDouble(br.readLine());

			ArrayList<Edge>[] edges = new ArrayList[N];

			for (int i = 0; i < N; i++) {
				edges[i] = new ArrayList<Edge>();
			}

			// 간선 정보 추가, N(N-1)/2개
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					double cost = getCost(i, j, E, islandPos);
					edges[i].add(new Edge(j, cost));

				}
			}

			double[] minEdges = new double[N];
			boolean[] visited = new boolean[N];

			Arrays.fill(minEdges, Double.MAX_VALUE);

			double res = 0;
			minEdges[0] = 0;

			int c;
			for (c = 0; c < N; c++) {
				double min = Double.MAX_VALUE;
				int minVertex = -1;

				// 최소비용 연결점 탐색
				for (int i = 0; i < N; i++) {
					if (!visited[i] && minEdges[i] < min) {
						min = minEdges[i];
						minVertex = i;
					}
				}

				// 단절된 트리일때 break;
				if (minVertex == -1)
					break;

				res += min;
				visited[minVertex] = true;

				for (int i = 0; i < edges[minVertex].size(); i++) {
					if (!visited[edges[minVertex].get(i).to]
							&& minEdges[edges[minVertex].get(i).to] > edges[minVertex].get(i).cost) {
						minEdges[edges[minVertex].get(i).to] =  edges[minVertex].get(i).cost;
					}
				}
			}
			System.out.print("#"+tc+" ");
			System.out.println(c==N?Math.round(res):-1);
		} // end of tc
	}// end of main

	static double getCost(int a, int b, double E, int[][] islandPos) {
		double distSquare = (double)(islandPos[a][0] - islandPos[b][0]) *  (double)(islandPos[a][0] - islandPos[b][0])
				+  (double)(islandPos[a][1] - islandPos[b][1]) *  (double)(islandPos[a][1] - islandPos[b][1]);
		return E * distSquare;
	}
}// end of class
