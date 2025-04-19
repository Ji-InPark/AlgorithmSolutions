import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long result = 0;
		int for_num = Integer.parseInt(br.readLine());
		String[] input_string = br.readLine().split(" ");
		
		int[] input = new int[for_num];
		long[] sum = new long[for_num];
		long[] reverse_sum = new long[for_num];
		
		input[0] = Integer.parseInt(input_string[0]);
		sum[0] = input[0];
		
		for(int i = 1; i < for_num; i++)
		{
			input[i] = Integer.parseInt(input_string[i]);
			sum[i] = input[i] + sum[i - 1];
		}
		
		reverse_sum[for_num - 1] = input[for_num - 1];
		for(int i = for_num - 2; i >= 0; i--)
		{
			reverse_sum[i] = input[i] + reverse_sum[i + 1];
		}
		
		for(int i = 1; i < for_num - 1; i++)
		{
			result = Math.max(Math.max(sum[for_num - 1] * 2 - input[0] - input[i] - sum[i], reverse_sum[0] * 2 - input[for_num - 1] - input[for_num - 1 - i] - reverse_sum[for_num - 1 - i]), result);
		}
		for(int i = 1; i < for_num - 1; i++)
		{
			result = Math.max(sum[for_num - 1] - input[0] - input[for_num - 1] + input[i], result);
		}
		
		bw.write(result + "\n");
		bw.flush();

	}

}
