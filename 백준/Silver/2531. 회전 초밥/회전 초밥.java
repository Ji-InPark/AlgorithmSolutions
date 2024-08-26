import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input_nums = br.readLine().split(" ");

        int n = Integer.parseInt(input_nums[0]), d = Integer.parseInt(input_nums[1]),
                k = Integer.parseInt(input_nums[2]), c = Integer.parseInt(input_nums[3]);

        int[] nums = new int[3005], arr = new int[n * 2];

        int result = 0, diff = 0;

        for(int i = 0; i < n; i++)
        {
            arr[i] = arr[n + i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < k; i++)
        {
            if(nums[arr[i]]++ == 0)
            {
                diff++;
            }
        }

        result = diff;

        for(int i = 0; i < n; i++)
        {

            if(--nums[arr[i]] == 0)
                diff--;
            if(nums[arr[i + k]]++ == 0)
                diff++;

            if(diff > result)
            {
                result = diff;
            }

            if(nums[c] == 0 && diff == result)
            {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
