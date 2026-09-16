import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class SWEA_1267_작업순서_D6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		for (int tc = 1; tc <=10; tc++) {
			StringBuilder sb = new StringBuilder();
			String[] V_E = br.readLine().split(" ");

			int V = Integer.parseInt(V_E[0]);
			int E = Integer.parseInt(V_E[1]);

			// 0번 인덱스 버리기
			List<Integer>[] inEdges = new ArrayList[V + 1];
			List<Integer>[] outEdges = new ArrayList[V + 1];

//			// 0번 열,행은 버림 
//			boolean[][] edgeMatrix = new boolean[V+1][V+1];

			for (int i = 0; i < V + 1; i++) {
				inEdges[i] = new ArrayList<>();
				outEdges[i] = new ArrayList<>();
			}

			String[] edges = br.readLine().split(" ");

			Deque<Integer> que = new ArrayDeque<>();

			for (int i = 0; i < E * 2; i += 2) {
				int st = Integer.parseInt(edges[i]);
				int fi = Integer.parseInt(edges[i + 1]);
//				
//				edgeMatrix[st][fi] = true;
//				
				// 해당 정점에 들어오는 간선 저장
				inEdges[fi].add(st);

				// 해당 정점에서 나가는 간선 저장
				outEdges[st].add(fi);

			}
			boolean[] done = new boolean[V + 1];

			// 초기 작업 상태일때 inedge가 0인 작업 큐에 넣기
			for (int i = 1; i <= V; i++) {
				if (inEdges[i].size() == 0) {
					que.offer(i);

					// 작업 끝낫다는 표시
					done[i] = true;
				}
			}

			sb.append("#").append(tc);
			
			while (!que.isEmpty()) {
				int curTask = que.poll();
				sb.append(" ").append(curTask);

				// 현재 큐에서 나온 작업이 뻗어나간 엣지 제거
				for (int i = 0; i < outEdges[curTask].size(); i++) {
					// 현재 큐에서 나온 작업이 뻗어나가는 작업 구함
					int fi = outEdges[curTask].get(i);

					inEdges[fi].remove((Integer) curTask);

				}

				// inEdge가 0인 작업 큐에 넣기
				for (int i = 1; i <= V; i++) {
					if (inEdges[i].size() == 0 && !done[i]) {
						que.offer(i);
						// 작업 끝낫다는 표시
						done[i] = true;
					}
				}

			}
			
			System.out.println(sb);
			

		} // end of testcase

	}// end of main
}// end of class
