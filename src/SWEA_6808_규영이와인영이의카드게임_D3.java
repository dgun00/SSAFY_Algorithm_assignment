

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6808_규영이와인영이의카드게임_D3 {

	static int[] guCards;
	static int[] inCards;
	static int[] numbers;
	static boolean[] isSelected = new boolean[19];

	static int cnt = 0;
	
	static int guScore = 0;
	static int inScore = 0;
	
	static int guWin = 0;
	static int guLose =0;
	
	public static void playGame() {
		
		for(int i=1; i<=9;i++) {
			if(guCards[i] > numbers[i])
				guScore += guCards[i]+numbers[i];
			else
				inScore += guCards[i]+numbers[i];

		}
		
		if(guScore>inScore) {
			guWin+=1;
		}else {
			guLose+=1;
		}
		guScore = 0;
		inScore = 0;
		
		return;
	}
	
	
	public static void perm(int pos) {

		
		for (int i = 1; i <= 9; i++) {

			if (pos > 9) {
				playGame();
				return;
			}
			if (isSelected[inCards[i]])
				continue;

			numbers[pos] = inCards[i];

			isSelected[inCards[i]] = true;

			perm(pos + 1);

			isSelected[inCards[i]] = false;

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int test_case = Integer.parseInt(st.nextToken());
		
		
		for (int tc = 1; tc <= test_case; tc++) {
			
			numbers = new int[10];

			guCards = new int[10];
			inCards = new int[10];

			boolean[] cards = new boolean[19];

			st = new StringTokenizer(br.readLine());

			// 카드 초기화

			for (int i = 1; i <= 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				guCards[i] = num;
				cards[num] = true;
			}
			
			int inIdx = 1;

			for (int i = 1; i <= 18; i++) {
				if (!cards[i]) {
					inCards[inIdx++] = i;
					cards[i] = true;
				}

			}

			

			perm(1);
			System.out.println("#"+tc+" "+guWin+" "+guLose);
			
			guLose = 0;
			guWin = 0;
		}

	}
}
