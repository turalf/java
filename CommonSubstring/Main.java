import java.util.*;
import java.io.*;

public class Main {

	static int M = 10;
	static long P = 250007;
	static List<Long> _prefixHashesS1 = null;
	static List<Long> _prefixHashesS2 = null;
	static List<Long> _pows = null;

	public static void main (String[] args) {

		FastScanner scan = new FastScanner();

		char[] s1 = scan.next().toCharArray();
		char[] s2 = scan.next().toCharArray();
		int left = 1,
		    right = Math.min(s1.length, s2.length);

		_prefixHashesS1 = getPrefixHashes(s1);
		_prefixHashesS2 = getPrefixHashes(s2);
		_pows = getPows(Math.max(s1.length, s2.length));


		int resultIndex = -1;
		while (left <= right) {
			int mid = (left + right) >> 1;

			int matchIndex = getCommonSubstringOfLength(mid, s1, s2);
			if (matchIndex == -1) {
				right = mid - 1;
			} else {
				resultIndex  = matchIndex;
				left = mid + 1;
			}
		}
		if (resultIndex < 0) {
			System.out.println(0);
		} else {
			System.out.println(createFromSubArray(s1, resultIndex, right));
			System.out.println(right);
		}

	}

	private static List<Long> getPrefixHashes(char[] s) {
		if (s.length == 0)
			return null;

		List<Long> hashValues = new ArrayList<Long>();
		hashValues.add((long)s[0]);

		for (int i = 1; i < s.length; i++) {
			hashValues.add((M * hashValues.get(i - 1) + s[i]) % P);
		}

		return hashValues;
	}

	private static List<Long> getPows(int length) {
		List<Long> pows = new ArrayList<Long>(length + 1);

		pows.add(1l);
		for (int i = 1; i <= length; i++) {
			pows.add((pows.get(i - 1) * M) % P);
		}

		return pows;
	}

	private static HashMap<Long, List<Integer>> getHashesOfLength(int length, char[] s, boolean isFirst) {
		HashMap<Long, List<Integer>> hashValues = new HashMap<Long, List<Integer>>();

		if (length > s.length)
			return hashValues;

		long pow = _pows.get(length);

		List<Long> _prefixHashes = isFirst ? _prefixHashesS1 : _prefixHashesS2;

		for (int i = 0; i < s.length - length + 1; i++) {
			long prefixHash = _prefixHashes.get(i + length - 1);

			final long hash = i > 0 ? (prefixHash -  pow * _prefixHashes.get(i - 1) % P) : prefixHash;

			if (!hashValues.containsKey(hash)) {
				hashValues.put(hash, new ArrayList<Integer>());
			}

			List<Integer> prevOccurrences = hashValues.get(hash);

			final int currentIndex = i;
			if (!prevOccurrences.stream().anyMatch(oc -> isMatch(s, s, oc, currentIndex, length))) {
				hashValues.get(hash).add(i);
			}
		}

		return hashValues;
	}

	public static int getCommonSubstringOfLength(int length, char[] s1, char[] s2) {
		HashMap<Long, List<Integer>> s1Hashes = getHashesOfLength(length, s1, true);

		long pow = _pows.get(length);

		for (int i = 0; i < s2.length - length + 1; i++) {
			long prefixHash = _prefixHashesS2.get(i + length - 1);

			final long hash = i > 0 ? (prefixHash -  pow * _prefixHashesS2.get(i - 1) % P) : prefixHash;

			if (s1Hashes.containsKey(hash)) {
				for (int pos : s1Hashes.get(hash)) {
					if (isMatch(s2, s1, i, pos, length)) {
						return pos;
					}
				}
			}
		}

		return -1;
	}


	private static boolean isMatch(char[] s1, char[] s2, int posS1, int posS2, int length) {
		for (int i = 0; i < length; i++) {
			if (s1[i + posS1] != s2[i + posS2])
				return false;
		}

		return true;
	}

	private static String createFromSubArray(char[] s1, int resultIndex, int right) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < right; i++) {
			sb.append(s1[resultIndex + i]);
		}

		return sb.toString();
	}






	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			try {
				br = new BufferedReader(new InputStreamReader(System.in));
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public String next() {
			if (st.hasMoreTokens())
				return st.nextToken();
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public String nextLine() {
			String line = "";
			try {
				line = br.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return line;
		}
	}
}



