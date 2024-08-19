import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] arr = new int[1000005], tastes = new int[100005];
		
		Arrays.fill(tastes, Integer.MAX_VALUE);
		
		int n = Integer.parseInt(br.readLine()), index = 0;
		
		for(int a = 0; a < n; a++)
		{
			String[] input = br.readLine().split(" ");
			
			if(input[0].equals("1"))
			{
				int cnt = Integer.parseInt(input[1]);
				int find = 0;
				
				while(cnt > 0)
				{
					cnt -= arr[tastes[find++]];
				}
				
				find--;
				
				bw.write(tastes[find] + "\n");
				
				arr[tastes[find]]--;
				
				if(arr[tastes[find]] == 0)
				{
					tastes[find] = Integer.MAX_VALUE;
					Arrays.sort(tastes, 0, index--);					
				}
			}
			else
			{
				int taste = Integer.parseInt(input[1]); 
				
				if(arr[taste] == 0)
				{
					tastes[index++] = taste;
				}
				arr[taste] += Integer.parseInt(input[2]);
				
				Arrays.sort(tastes, 0, index);
			}
		}
		
		bw.flush();
	}

}
