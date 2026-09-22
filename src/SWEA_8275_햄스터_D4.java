import java.io.*;
import java.util.*;

다시풀기

public class SWEA_8275_햄스터_D4 {
    static int N, X, M;          // N: 우리 개수, X: 우리당 최대 햄스터 수, M: 기록 개수
    static int[][] rec;          // rec[i] = {l, r, s} : i번째 기록 (l~r 구간 합이 s)
    static int[] arr;            // 현재 탐색 중인 배치 (1-indexed, arr[0]은 사용 안 함)
    static int[] best;           // 지금까지 찾은 최적 배치
    static int bestSum;          // best 배치의 총 햄스터 수 (최대화 대상)
    static boolean found;        // 조건을 만족하는 배치를 하나라도 찾았는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 개수
        StringBuilder sb = new StringBuilder();          // 출력은 한 번에 모아서 (System.out.print 반복 호출 방지)

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 우리 개수 (1~6)
            X = Integer.parseInt(st.nextToken()); // 우리당 최대 햄스터 수 (1~10)
            M = Integer.parseInt(st.nextToken()); // 기록 개수 (1~10)

            rec = new int[M][3];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                rec[i][0] = Integer.parseInt(st.nextToken()); // l : 구간 시작 (1-indexed)
                rec[i][1] = Integer.parseInt(st.nextToken()); // r : 구간 끝 (1-indexed)
                rec[i][2] = Integer.parseInt(st.nextToken()); // s : l~r 구간의 합이 s여야 함
            }

            // 배열을 1-indexed로 써서 rec의 l, r 값을 그대로 인덱스로 사용 (idx 변환 실수 방지)
            arr = new int[N + 1];
            best = null;      // 테스트케이스마다 초기화 필수 (안 하면 이전 케이스 결과가 남음)
            bestSum = -1;     // 아직 후보 없음을 나타내는 초기값
            found = false;

            dfs(1, 0); // 1번 우리부터 채우기 시작, 지금까지 합은 0

            sb.append('#').append(tc).append(" ");
            if (!found) {
                sb.append(-1).append('\n'); // 조건 만족하는 배치가 하나도 없는 경우
            } else {
                // 1번 우리부터 N번 우리까지 순서대로 출력
                for (int i = 1; i <= N; i++) {
                    sb.append(best[i]);
                    if (i != N) sb.append(' '); // 마지막 칸 뒤에는 공백 안 붙임
                }
                sb.append('\n');
            }
        }
        System.out.print(sb); // 전체 결과를 한 번에 출력 (I/O 비용 최소화)
    }

    /**
     * idx: 지금부터 채워야 할 우리 번호 (1-indexed)
     * sum: 1번 ~ (idx-1)번 우리까지 채운 햄스터 수의 합
     */
    static void dfs(int idx, int sum) {
        // 베이스 케이스: N번 우리까지 다 채웠다 = 하나의 완성된 배치
        if (idx > N) {
            // 이미 재귀 도중 r==idx 시점마다 검사를 통과해왔으므로
            // 여기 도달했다는 것 자체가 "모든 기록을 만족하는 배치"라는 뜻
            if (sum > bestSum || (sum == bestSum && isSmaller(arr, best))) {
                bestSum = sum;                       // 합이 더 크거나, 같으면 사전순이 더 앞선 것으로 갱신
                best = Arrays.copyOf(arr, N + 1);    // arr을 복사해서 저장 (참조만 두면 이후 재귀에서 값이 바뀜)
                found = true;
            }
            return;
        }

        // idx번째 우리에 0마리부터 X마리`까지 하나씩 다 시도 (자릿수 완전탐색)
        for (int v = 0; v <= X; v++) {
            arr[idx] = v; // idx번째 칸을 v로 확정

            // 가지치기: idx번째 칸을 방금 채웠으므로,
            // 오른쪽 끝(r)이 idx인 기록은 이 시점에 이미 구간이 완성된 것  -> 바로 검사 가능
            boolean ok = true;
            for (int[] c : rec) {
                if (c[1] == idx) { // 이 기록의 r이 방금 채운 idx와 같다면
                    int s = 0;
                    for (int k = c[0]; k <= c[1]; k++) s += arr[k]; // l~r 구간 합 계산
                    if (s != c[2]) { // 기록된 값 s와 다르면 이 배치는 틀림
                        ok = false;
                        break; // 더 볼 필요 없이 바로 중단
                    }
                }
            }
            if (!ok) continue; // 조건 위반 -> 이 v는 버리고 다음 v로 (더 깊이 안 들어감 = 가지치기)

            dfs(idx + 1, sum + v); // 다음 칸으로 진행
        }
    }

    /**
     * a가 b보다 사전순으로 앞서면 true
     * (정렬 비교가 아니라, 배열을 1번 인덱스부터 순서대로 비교)
     * b가 null이면 아직 후보가 없다는 뜻이므로 무조건 a를 채택
     */
    static boolean isSmaller(int[] a, int[] b) {
        if (b == null) return true;
        for (int i = 1; i <= N; i++) {
            if (a[i] != b[i]) return a[i] < b[i]; // 처음으로 다른 자리에서 더 작은 쪽이 사전순 우선
        }
        return false; // 완전히 동일한 배치 (실제로는 발생 안 함)
    }
}