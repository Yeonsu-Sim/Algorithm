import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Main {
	static class Line implements Comparable<Line> {
		int start, end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Line o) {
			return Integer.compare(this.start, o.start);
		}

		@Override
		public String toString() {
			return "Line [start=" + start + ", end=" + end + "]";
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = parseInt(br.readLine());
		
		Line[] lines = new Line[N];
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = parseInt(st.nextToken());
			int end = parseInt(st.nextToken());
			lines[i] = new Line(start, end);
		}
		
		Arrays.sort(lines);
		
		int start = lines[0].start;
		int end = lines[0].end;
		int length = 0;
		for (Line line : lines) {
			if (line.start <= end) {
				if (line.end > end) {
					end = line.end;
				}
			} else {
				length += end - start;
				start = line.start;
				end = line.end;
			}
		}
		length += end - start;
		
		System.out.println(length);
		
	}
}
