import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] words = new String[n];

		HashMap<String, Integer> hm = new HashMap<>();
		int ans = 0;
		int ansIdx1 = 0;
		int ansIdx2 = 1;
		for (int i = 0; i < n; i++) {
			words[i] = br.readLine();
			for (int j = words[i].length(); j >= ans; j--) {
				String temp = words[i].substring(0, j);
				if (hm.containsKey(temp)) {
					if(j == ans) {
                        if (ansIdx1 > hm.get(temp)) {
                            ansIdx1 = hm.get(temp);
                            ansIdx2 = i;
                        }
					} else {
						ansIdx1 = hm.get(temp);
						ansIdx2 = i;
						ans = j;
                    }
				} else {
					hm.put(temp, i);
				}
			}
		}


        System.out.println(words[ansIdx1]);
        System.out.println(words[ansIdx2]);
	}

}
