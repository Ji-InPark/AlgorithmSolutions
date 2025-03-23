

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var map = new LinkedHashMap<Integer, HashSet<Integer>>();

        for (int i = 0; i < n * n; i++) {
            var inputs = br.readLine().split(" ");
            var key = Integer.parseInt(inputs[0]);

            map.put(key, new HashSet<>());
            for (int j = 1; j <= 4; j++) {
                map.get(key).add(Integer.parseInt(inputs[j]));
            }
        }

        var arr = new int[n + 2][n + 2];

        for (int i = 0; i < n + 2; i++) {
            arr[0][i] = arr[n + 1][i] = arr[i][0] = arr[i][n + 1] = -1;
        }

        for (var entry : map.entrySet()) {
            var pq = new PriorityQueue<int[]>((o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                } else if (o1[1] != o2[1]) {
                    return o2[1] - o1[1];
                } else if (o1[2] != o2[2]) {
                    return o1[2] - o2[2];
                }

                return o1[3] - o2[3];
            });

            var likeSet = entry.getValue();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][j] != 0) {
                        continue;
                    }

                    int likes = 0, empty = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k], ny = j + dy[k];
                        if (likeSet.contains(arr[nx][ny])) {
                            likes++;
                        } else if (arr[nx][ny] == 0) {
                            empty++;
                        }
                    }

                    pq.add(new int[]{likes, empty, i, j});
                }
            }

            var result = pq.poll();

            arr[result[2]][result[3]] = entry.getKey();
        }

        var likeArr = new int[]{0, 1, 10, 100, 1000};
        var result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                var key = arr[i][j];
                var likeSet = map.get(key);

                var likes = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (likeSet.contains(arr[nx][ny])) {
                        likes++;
                    }
                }

                result += likeArr[likes];
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

