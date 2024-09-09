import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int result = 1;
		String[] input_nums = br.readLine().split(" ");
		String[] input_string = br.readLine().split(" ");
		Arrays.sort(input_string);
		StringBuffer sb = new StringBuffer("");
		
		int L = Integer.parseInt(input_nums[0]), C = Integer.parseInt(input_nums[1]);
		
		solution(input_string, L, 0, 0, sb);
		bw.flush();
	}
	
	public static void solution(String[] arr, int L, int cnt, int pre_index, StringBuffer sb) throws IOException
	{
		if(L == cnt)
		{
			String[] vowels = {"a", "e", "i", "o", "u"};
			int v_cnt = 0;
			String temp = sb.toString();
			for(int i = 0; i < 5; i++)
			{
				if(temp.contains(vowels[i]))
					v_cnt++;
			}
			if(L - v_cnt >= 2 && v_cnt != 0)
			{
				bw.write(temp + "\n");
			}
		}
		else
		{
			for(int i = pre_index; i < arr.length; i++)
			{
				sb.append(arr[i]);
				solution(arr, L, cnt + 1, i + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

}
