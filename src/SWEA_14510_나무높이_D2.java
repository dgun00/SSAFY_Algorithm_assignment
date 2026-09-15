

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_14510_나무높이_D2 {
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			
			int maxHeight =-1;
			
			int[] trees = new int[N];
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, trees[i]);
			}
			
			int res = 0;
			// [0]: 0 , [1] : 높이 1 남은 나무 카운트, [2] : 높이 2 남은 나무 카운트
			int[] RemainedDayCnt =new int[3];
			
			int[] needHeight = new int[N];
			
			for (int i = 0; i < N; i++) {
				// 홀,짝 날을 하나로 묶어서 높이가 1이나 2남을때까지 다써버리기
				res += 2*((maxHeight - trees[i])/3);
				needHeight[i] = ((maxHeight - trees[i])%3);	
				
				RemainedDayCnt[needHeight[i]]++;
				
			}
			
			
			if(RemainedDayCnt[1] > RemainedDayCnt[2]) {
				
				int diff = RemainedDayCnt[1] - RemainedDayCnt[2];
				
				res += 2*(diff)-1;
				
				res += 2*RemainedDayCnt[2];
				
				
			}else if (RemainedDayCnt[1] <= RemainedDayCnt[2]){
				int diff = RemainedDayCnt[2] - RemainedDayCnt[1];
				

				res += 4*(diff/3);
				
				if((diff%3)==1)
					res += 2;
				else if((diff%3)== 2)
					res += 3;
		
				res += 2*RemainedDayCnt[1];

			}
			
			System.out.println("#"+tc+" "+res);
			
			
			
			
			
		}

	}
}
