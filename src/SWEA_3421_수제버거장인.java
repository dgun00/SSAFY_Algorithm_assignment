

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_3421_수제버거장인 {

	static int N; // 재료 개수

	static Deque<Integer> stk = new ArrayDeque<>();
	static List<int[]> pairs;
	static Map<Integer, List<Integer>> dontPicks;
	static int res;
	
	public static boolean canContain(int cnt) {

		// 이번 재료에서 조합되면 안되는 것들이 없을때 true 리턴
		if(!(dontPicks.containsKey(cnt))) return true;
		
		
		// 같이 조합 되면 안되는 것들 이 지금 스택에 있는지 확인
		for (Integer e : dontPicks.get(cnt)) {

			if (stk.contains(e)) {
				return false;
			}
		}
		
		
		return true;
	}

	public static void genSubset(int cnt) {

		if (cnt > N) {
			res++;
//			System.out.println(stk);
			return;
		}

		boolean containFlag = false;

		if (canContain(cnt)) {
			stk.push(cnt);
			containFlag = true;
		}

		genSubset(cnt + 1);

		// 이 depth에서 포함 되었으면 pop
		if (containFlag) {
			stk.pop();
		
			genSubset(cnt + 1);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {

			res=0;
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			pairs = new ArrayList<>();

			dontPicks = new HashMap<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				dontPicks.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
				dontPicks.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
			}
			
			genSubset(1);
			
			System.out.println("#"+tc+" "+res);
//			for(int i=0; i<=N;i++) {
//				if(dontPicks.containsKey(i)) {
//					System.out.println(i+":"+dontPicks.get(i));
//				}
//			}
//			
//			System.out.println("----");
		}

	}// end of main
}// end of class
