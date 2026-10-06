import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.text.AbstractDocument.BranchElement;

public class SWEA_3124_최소스패닝트리_D4 {
	static int[] parents;
	static int[][] edges;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			edges = new int[E][3];

			// 0번 인덱스 버림
			parents = new int[V + 1];

			// 간선 정보 초기화
			for (int i = 0; i < E; i++) {
				String[] line = br.readLine().split(" ");

				edges[i][0] = Integer.parseInt(line[0]);
				edges[i][1] = Integer.parseInt(line[1]);
				edges[i][2] = Integer.parseInt(line[2]);
			}

			// 간선 정보 오름차순 정렬
			Arrays.sort(edges, (a, b) -> {
				return a[2] - b[2];
			});
			
			int cnt =0; // 간선 선택 수
			long weight =0; // 가중치 합
			
			makeSets();
			
			for(int[] e : edges) {
				if(union(e[0],e[1])) {
					cnt++;
					weight+=e[2];
					
					if(cnt == V-1)
						break;
				}
			}
			System.out.println("#"+tc+" "+weight);
		} // end of tc

	}// end of main

	static void makeSets() {
		for (int i = 1; i < parents.length; i++) {
			parents[i] = -1;

		}
	}

	static int findSet(int a) {
		if (parents[a] < 0)
			return a;

		return parents[a] = findSet(parents[a]);

	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		if (aRoot < bRoot) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}

		return true;

	}
}// end of class
