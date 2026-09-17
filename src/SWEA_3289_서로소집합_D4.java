import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_3289_서로소집합_D4 {

	static int N;
	static int M;
	static int[] p;
	static int[] depths;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] N_M = br.readLine().split(" ");

			N = Integer.parseInt(N_M[0]);
			M = Integer.parseInt(N_M[1]);
			
			// 0 인덱스는 버리기
			depths = new int[N+1];
			p = new int[N+1];
			
			
			StringBuilder sb = new StringBuilder(); 
			sb.append("#").append(tc).append(" ");
			
			makeSet(N);
			
			// 명령들 수행
			for (int i = 0; i < M; i++) {
				String[] cmdLine = br.readLine().split(" ");
				
				// cmd -> 0: union, 1: findSet
				int cmd = Integer.parseInt(cmdLine[0]);
				int a = Integer.parseInt(cmdLine[1]);
				int b = Integer.parseInt(cmdLine[2]);
				
				if(cmd==0) {
					union(a,b);
				}else {
					if(findSet(a)==findSet(b)) {
						sb.append(1);
					}else
						sb.append(0);
				}
			}
			
			System.out.println(sb);
		}
	}

	public static void makeSet(int N) {
		for (int i = 1; i <= N; i++) {
			// 처음 노드의 부모는 자기자신
			p[i] = i;
			depths[i] = 1;
		}
	}

	public static int findSet(int el) {

		if (p[el] == el)
			return el;
		else {
			// 압축
			int root = findSet(p[el]);
			p[el] = root;
			return root;
		}
	};

	public static void union(int a, int b) {
		int bigger = -1;
		int smaller = -1;
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (depths[aRoot] >= depths[bRoot]) {
			bigger = aRoot;
			smaller = bRoot;
		} else {
			bigger = bRoot;
			smaller = aRoot;
		}
		
		// 작은쪽 루트의 노드의 부모를 큰 쪽 루트의 노드로 변경
		p[smaller] = bigger;
		
	};
}
