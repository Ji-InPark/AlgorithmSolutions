import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var nums = br.readLine().split(" ");
        int n = Integer.parseInt(nums[0]);
        long atk = Long.parseLong(nums[1]);
        var arr = new Base[n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            var isMonster = inputs[0].equals("1");
            int inputAtk = Integer.parseInt(inputs[1]), inputHp = Integer.parseInt(inputs[2]);

            if (isMonster) {
                arr[i] = new Monster(inputHp, inputAtk);
            } else {
                arr[i] = new Potion(inputHp, inputAtk);
            }
        }

        var result = binarySearch(arr, atk);

        bw.write(result + "\n");
        bw.flush();
    }

    private static long binarySearch(Base[] arr, long atk) {
        long l = 0, r = Long.MAX_VALUE - 20000000L;

        while (l + 1 < r) {
            var mid = l + (r - l) / 2;

            if (canClear(arr, mid, atk)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return r;
    }

    private static boolean canClear(Base[] arr, long maxHp, long heroAtk) {
        long heroHp = maxHp;

        for (var item : arr) {
            if (item.isMonster()) {
                var totalAttackCount = ((item.hp / heroAtk) + (item.hp % heroAtk == 0 ? -1 : 0));

                heroHp -= totalAttackCount * item.atk;
            } else {
                heroHp = Math.min(heroHp + (long) item.hp, maxHp);
                heroAtk += item.atk;
            }

            if (heroHp <= 0) {
                return false;
            }
        }

        return true;
    }

    interface Item {

        boolean isMonster();
    }

    static class Potion extends Base {

        public Potion(int hp, int atk) {
            super(hp, atk);
        }

        @Override
        public boolean isMonster() {
            return false;
        }
    }

    static class Monster extends Base {

        public Monster(int hp, int atk) {
            super(hp, atk);
        }

        @Override
        public boolean isMonster() {
            return true;
        }
    }

    abstract static class Base implements Item {

        int hp, atk;

        public Base(int hp, int atk) {
            this.hp = hp;
            this.atk = atk;
        }
    }
}
