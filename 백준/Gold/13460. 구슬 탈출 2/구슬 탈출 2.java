

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]);

        var arr = new char[h][w];
        int sby = 0, sbx = 0, sry = 0, srx = 0;

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine();

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs.charAt(j);

                switch (arr[i][j]) {
                    case 'R':
                        sry = i;
                        srx = j;
                        break;
                    case 'B':
                        sby = i;
                        sbx = j;
                        break;
                }
            }
        }

        var q = new LinkedList<int[]>();
        var visited = new boolean[h][w][h][w];

        q.add(new int[]{sry, srx, sby, sbx});
        visited[sry][srx][sby][sbx] = true;

        var result = 1;
        Loop:
        while (result <= 10) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int ry = now[0], rx = now[1], by = now[2], bx = now[3];

                for (int j = 0; j < 4; j++) {
                    int isAllOut = 0;
                    var positions = new int[]{ry, rx, by, bx};

                    Go:
                    for (int k = 0; k < 4; k++) {
                        int nowIndex = k % 2 == 0 ? 0 : 2;
                        int nextIndex = (nowIndex + 2) % 4;

                        int ny = positions[nowIndex], nx = positions[nowIndex + 1];

                        while (ny >= 0 && nx >= 0) {
                            ny += dy[j];
                            nx += dx[j];

                            if (arr[ny][nx] == '#' || (ny == positions[nextIndex] && nx == positions[nextIndex + 1])) {
                                ny -= dy[j];
                                nx -= dx[j];
                                break;
                            }

                            if (arr[ny][nx] == 'O') {
                                isAllOut += nowIndex + 1;

                                positions[nowIndex] = -1;
                                positions[nowIndex + 1] = -1;

                                continue Go;
                            }
                        }

                        positions[nowIndex] = ny;
                        positions[nowIndex + 1] = nx;
                    }

                    if (isAllOut == 1) {
                        break Loop;
                    }

                    if (isAllOut != 0 || isVisited(visited, positions)) {
                        continue;
                    }

                    q.add(positions);
                    visit(visited, positions);
                }
            }

            result++;
        }

        if (result > 10) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void visit(boolean[][][][] visited, int[] positions) {
        visited[positions[0]][positions[1]][positions[2]][positions[3]] = true;
    }

    private static boolean isVisited(boolean[][][][] visited, int[] positions) {
        return visited[positions[0]][positions[1]][positions[2]][positions[3]];
    }
}
