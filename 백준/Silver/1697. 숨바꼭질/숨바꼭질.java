import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]), k = Integer.parseInt(input[1]), result = 0;
		
		boolean[] arr = new boolean[100005];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(n);
		
		Loop:
		while(true)
		{
			int size = q.size();
			
			for(int i = 0; i < size; i++)
			{
				int now = q.poll();
				
				if(now == k)
					break Loop;
				
				if(check(now * 2, arr))
				{
					arr[now * 2] = true;
					q.add(now * 2);
				}
				if(check(now + 1, arr))
				{
					arr[now + 1] = true;
					q.add(now + 1);
				}
				if(check(now - 1, arr))
				{
					arr[now - 1] = true;
					q.add(now - 1);
				}
					
			}
			
			result++;
		}
		
		bw.write(result + "\n");
		bw.flush();
		

	}
	
	public static boolean check(int n, boolean[] arr)
	{
		if(n > 100000 || n < 0 || arr[n])
			return false;
		
		return true;
	}

}
