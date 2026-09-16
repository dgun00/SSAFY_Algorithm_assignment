package com.ssafy.assignment;

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
			
			
			int[] needHeight = new int[N];
			
			int odd =0; // 반드시 홀수날 물 줘야하는 개
			int even =0; // 짝수날 물 줄개
			for (int i = 0; i < N; i++) {			
				needHeight[i] = ((maxHeight - trees[i]));	
				if(needHeight[i] % 2 != 0) {
					odd++;
				}
				even += needHeight[i]/2;
				
				
			}     
		
			int min = Math.min(odd,even);
			
			res = min *2;
			
			odd -= min;
			even -= min;
			
			if (odd > even) { // 홀수날이 많은 경우
				res += odd * 2 - 1;
			} else if (odd < even) { // 짝수날이 많은 경우
				res += even / 3 * 4; // _ㅉ_ㅉ_ㅉ => ㅎㅉㅎㅉ
				if      (even % 3 == 1) res += 2;
				else if (even % 3 == 2) res += 3;
			} else { // odd == even 아무것도 안하면 됨
				
			}
			
			
			System.out.println("#"+tc+" "+res);
			
			
			
			
			
		}

	}
}
