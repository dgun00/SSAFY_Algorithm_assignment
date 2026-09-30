import java.io.*;
import java.util.*;

/*
 * 접근:
 * 
 * 회의실 문제랑 똑같은듯
 * 
 * 끝나는 시간 순으로 오름차순 하고 순회하면서 회의 이어나갈수있으면 cnt++
 * 
 * 
 */
public class SWEA_5202_화물도크_D3 {
	
	static List<int[]> tasks;

	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		
		for (int tc = 1; tc <= TC; tc++) {
			
			tasks = new ArrayList<>();

			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				String[] task = br.readLine().split(" ");

				tasks.add(new int[] { Integer.parseInt(task[0]), Integer.parseInt(task[1]) });
				
			}
			
			// 끝나는 시간 오름차순 정렬
			tasks.sort((a,b)->{
				if(a[1]==b[1])return Integer.compare(a[0],b[0]);
				return Integer.compare(a[1],b[1]);
			});
			
			
			System.out.println("#"+tc+" "+func());
			
			

		} // end of tc

	}// end of main

	public static int func() {

		int curFi = tasks.get(0)[1];
		
		// 첫 적재는 바로 카운트
		int cnt=1;
		
		for(int i=1; i<tasks.size();i++) {
			
			
			int nxtSt = tasks.get(i)[0]; 
			int nxtFi = tasks.get(i)[1]; 
			
			// 현재 끝나는 시간이 다음 시작 시간보다 크면 넘어가기
			if(curFi > nxtSt)continue;
			else {
				cnt++;
				
				// 현재시간 갱신
				curFi = nxtFi;
			}
		}
		
		return cnt;
	}
}// end of class
