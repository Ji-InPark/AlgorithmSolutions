import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int[][] arr, dp;
	static boolean[][] v;
	
	static int result = 0, row, col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input_nums = br.readLine().split(" ");
		
		row = Integer.parseInt(input_nums[0]);
		col = Integer.parseInt(input_nums[1]);
		
		arr = new int[row + 2][col + 2];
		v = new boolean[row + 2][col + 2];
		dp = new int[row + 2][col + 2];
		
		for(int i = 0; i < row + 2; i++)
		{
			Arrays.fill(v[i], true);
		}
		
		for(int i = 1; i <= row; i++)
		{
			String[] input = br.readLine().split("");
			
			for(int j = 0; j < input.length; j++)
			{
				if(input[j].equals("H"))
					arr[i][j + 1] = -1;
				else
					arr[i][j + 1] = Integer.parseInt(input[j]);				
				
				v[i][j + 1] = false;
			}
		}
		
		solve(1, 1, 0);
		
		bw.write(result + "\n");
		bw.flush();
		
	}
	
	public static int solve(int x, int y, int length)
	{
		int go = arr[y][x];
		
		if(go == -1)
		{
			if(result < length)
				result = length;
			
			return 0;
		}
		
		if(dp[y][x] < length)
			dp[y][x] = length;
		
		
		v[y][x] = true;
		
		if(x + go > col)
		{
			if(result < length + 1)
				result = length + 1;
		}
		else
		{
			if(v[y][x + go])
			{
				result = -1;
				return -1;
			}
			else
				if(dp[y][x + go] < length + 1)
					if(solve(x + go, y, length + 1) == -1)
						return -1;
					
		}
		
		if(x - go < 1)
		{
			if(result < length + 1)
				result = length + 1;
		}
		else
		{
			if(v[y][x - go])
			{
				result = -1;
				return -1;
			}
			else
				if(dp[y][x - go] < length + 1)
					if(solve(x - go, y, length + 1) == -1)
						return -1;
		}
		
		if(y + go > row)
		{
			if(result < length + 1)
				result = length + 1;
		}
		else
		{
			if(v[y + go][x])
			{
				result = -1;
				return -1;
			}
			else
				if(dp[y + go][x] < length + 1)
					if(solve(x, y + go, length + 1) == -1)
						return -1;
		}
		
		if(y - go < 1)
		{
			if(result < length + 1)
				result = length + 1;
		}
		else
		{
			if(v[y - go][x])
			{
				result = -1;
				return -1;
			}
			else
				if(dp[y - go][x] < length + 1)
					if(solve(x, y - go, length + 1) == -1)
						return -1;
		}
		
		v[y][x] = false;
		
		return 0;
	}

}
