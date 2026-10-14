import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_3282_Knapsack_D3 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			
			
			String[] infos = br.readLine().split(" ");
			
			int N = Integer.parseInt(infos[0]);
			int K = Integer.parseInt(infos[1]);
			
			int[] vol = new int[N+1];
			int[] val = new int[K+1];

			
			for(int i=1; i<=Integer.parseInt(infos[0]);i++) {
				String[] line = br.readLine().split(" ");
				vol[i] = Integer.parseInt(line[0]);
				val[i] = Integer.parseInt(line[1]);
			}
			
			
			int[][] DP = new int[N+1][K+1];
			
			
			for(int i=1; i<=N;i++) {
				
				for(int v =1; v<=K; v++) {
					if(vol[i] <= v) {
						DP[i][v] = Math.max(DP[i-1][v], val[i]+DP[i-1][v-vol[i]]);
					}
					
					else {
						DP[i][v] = DP[i-1][v];
					}
				}
			}
			
			System.out.println("#"+tc+" "+DP[N][K]);
		}
	}
}
