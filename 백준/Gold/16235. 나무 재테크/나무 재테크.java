

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
    static int[] dy = new int[]{1, 0, -1, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), k = Integer.parseInt(numInputs[2]);

        var treeSimulator = new TreeSimulator(n, createNutrients(n, br));
        treeSimulator.plantTrees(getTreeInfos(m, br));
        treeSimulator.simulate(k);

        bw.write(treeSimulator.getAllTreesCount() + "\n");
        bw.flush();
    }

    private static int[][] getTreeInfos(int m, BufferedReader br) throws IOException {
        var result = new int[m][3];

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");

            result[i][0] = Integer.parseInt(inputs[0]) - 1;
            result[i][1] = Integer.parseInt(inputs[1]) - 1;
            result[i][2] = Integer.parseInt(inputs[2]);
        }

        return result;
    }

    private static int[][] createNutrients(int n, BufferedReader br) throws IOException {
        var result = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                result[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        return result;
    }

    private static class TreeSimulator {

        int n;
        int[][] nutrients, farm;
        PriorityQueue<Integer>[][] trees;
        LinkedList<Integer>[][] deadTrees;

        public TreeSimulator(int n, int[][] nutrients) {
            this.n = n;
            this.nutrients = nutrients;
            initFarm(n);
            initAllTrees(n);
        }

        public void simulate(int year) {
            for (int i = 0; i < year; i++) {
                simulateSpring();
                simulateSummer();
                simulateAutumn();
                simulateWinter();
            }
        }

        public int getAllTreesCount() {
            var result = 0;

            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    result += trees[i][j].size();
                }
            }

            return result;
        }

        public void plantTrees(int[][] treeInfos) {
            for (var treeInfo : treeInfos) {
                int x = treeInfo[0], y = treeInfo[1], age = treeInfo[2];

                trees[x][y].add(age);
            }
        }

        private void initFarm(int n) {
            this.farm = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(farm[i], 5);
            }
        }

        private void initAllTrees(int n) {
            trees = new PriorityQueue[n][n];
            deadTrees = new LinkedList[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    trees[i][j] = new PriorityQueue<>();
                    deadTrees[i][j] = new LinkedList<>();
                }
            }
        }

        private void simulateSpring() {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    var nowTrees = this.trees[i][j];
                    var nextTrees = new ArrayList<Integer>();

                    while (!nowTrees.isEmpty()) {
                        var nowTree = nowTrees.poll();

                        if (this.farm[i][j] - nowTree < 0) {
                            deadTrees[i][j].add(nowTree);
                            continue;
                        }

                        this.farm[i][j] -= nowTree;
                        nextTrees.add(nowTree + 1);
                    }

                    nowTrees.addAll(nextTrees);
                }
            }
        }

        private void simulateSummer() {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    var nowDeadTrees = this.deadTrees[i][j];

                    while (!nowDeadTrees.isEmpty()) {
                        this.farm[i][j] += nowDeadTrees.poll() / 2;
                    }
                }
            }
        }

        private void simulateAutumn() {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    var nowTrees = this.trees[i][j];

                    for (var tree : nowTrees) {
                        if (tree % 5 != 0) {
                            continue;
                        }

                        for (int k = 0; k < 8; k++) {
                            int nx = i + dx[k], ny = j + dy[k];

                            if (isValid(nx, ny)) {
                                this.trees[nx][ny].add(1);
                            }
                        }
                    }
                }
            }
        }

        private void simulateWinter() {
            for (int i = 0; i < this.n; i++) {
                for (int j = 0; j < this.n; j++) {
                    this.farm[i][j] += this.nutrients[i][j];
                }
            }
        }

        private boolean isValid(int x, int y) {
            return 0 <= x && x < this.n && 0 <= y && y < n;
        }
    }
}
