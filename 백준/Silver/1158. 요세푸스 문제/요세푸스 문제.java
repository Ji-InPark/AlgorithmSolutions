import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input_nums = br.readLine().split(" ");
		int n = Integer.parseInt(input_nums[0]), k = Integer.parseInt(input_nums[1]), index = -1;
		
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		for(int i = 1; i <= n; i++)
		{
			array.add(i);
		}
		
		bw.write("<");
		while(array.size() > 1)
		{
			index += k;
			index %= array.size();
			bw.write(array.get(index) + ", ");
			array.remove(index);
			if(index == 0)
				index = array.size() - 1;
			else
				index--;
		}
		
		bw.write(array.get(0) + ">");
		bw.flush();

	}

}
