import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 접근:
 * 
 * 들어오는간선 수 저장하는 배열만들고
 * 그 수가 0인 노드 출력하고 그 노드에서 나오는간선을 다른노드 들어오는간선 수를 줄이기
 * 
 * 
 * 
 * 
 * 
 */
public class SWEA_3952_줄세우기_D4 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc);

			String[] info = br.readLine().split(" ");

			int N = Integer.parseInt(info[0]);
			int M = Integer.parseInt(info[1]);

			// 들어오는 간선 개수, 0인덱스 버림
			int[] inDegrees = new int[N + 1];

			// 인접리스트, 0인덱스 버림
			List<Integer>[] adjList = new ArrayList[N + 1];

			for (int i = 0; i < N + 1; i++) {
				adjList[i] = new ArrayList<>();
			}

			// 정보 저장
			for (int i = 0; i < M; i++) {
				String[] order = br.readLine().split(" ");

				int st = Integer.parseInt(order[0]);
				int fi = Integer.parseInt(order[1]);

				adjList[st].add(fi);
				inDegrees[fi]++;

			}

		
			Deque<Integer> q = new ArrayDeque<>();

			// 들어오는 간선이 0인애 큐에 넣기
			for (int i = 1; i <= N; i++) {
				if (inDegrees[i] == 0) {
					q.offer(i);
				}
			}

			while (!q.isEmpty()) {

				int polled = q.poll();

				sb.append(" ").append(polled);

				// 들어오는 간선이 0에서 나오는 것들 제거 하면서, 연결된애가 들어오는 간선 0이 되면 큐에넣기
				for (int i = 0; i < adjList[polled].size(); i++) {
					int adj = adjList[polled].get(i);

//					if (--inDegrees[adj] == 0) {
//						q.add(adj);
//					}
					
					inDegrees[adj]--;
					
					if(inDegrees[adj]==0)
						q.add(adj);
					
				}

			}

			System.out.println(sb);

		} // end of tc

	}// end of main
}// end of class
