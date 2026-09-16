import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1267_작업순서_D6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		for(int tc=1; tc<=2; tc++) {
			String[] V_E = br.readLine().split(" ");
			
			int V =Integer.parseInt(V_E[0]);
			int E =Integer.parseInt(V_E[1]);
			
			// 0번 인덱스 버리기
			List<Integer>[] incomingEdges = new ArrayList[V+1];
			
			for (int i = 0; i < V+1; i++) {
			    incomingEdges[i] = new ArrayList<>();
			}
			
			String[] edges = br.readLine().split(" ");
			
			
			boolean[] done = new boolean[V];
			Deque<Integer> que = new ArrayDeque<>();
			
			
			for(int i=0; i<E*2; i+=2) {
				int st = Integer.parseInt(edges[i]);
				int fi = Integer.parseInt(edges[i+1]);
				
				// 해당 정점에 들어오는 간선 저장
				incomingEdges[fi].add(st);
				
			}
			
			
			
			while()
//			for (int i = 1; i < incomingEdges.length; i++) {
//				System.out.print(i+":");
//				for(int j=0; j<incomingEdges[i].size();j++) {
//					System.out.print(incomingEdges[i].get(j)+" ");
//				}
//				System.out.println();
//			}
			
			
			
		}
		
		
	}
}
