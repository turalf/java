import java.util.*;


public class GoodSubstring{
    static long[] _prefixHashes = null;
    static HashSet<Long> _hashes = new HashSet<Long>();
    static int[] _prefixBadCount = null;
    static long[] _pow;
    static int M = 97;
    static int K = 1;
    static long P = (long)(1e9 + 7);

    public static void main(String [] args){
        try(Scanner sc = new Scanner(System.in)){
            char[] str = sc.nextLine().toCharArray();
            char[] isBad = sc.nextLine().toCharArray();
            _prefixHashes = new long[str.length];
            _prefixBadCount = new int[str.length];
            K = Integer.parseInt(sc.nextLine());
            _pow = new long[2000];

            calcPrefixHashes(str);
            calcPrefixBadCount(str, isBad);
            calcPows();
            solve(str);

            System.out.println(_hashes.size());
        }
    }

    private static void calcPows(){
        _pow[0] =1;
        for(int i=1;i<_pow.length;i++){
             _pow[i] = _pow[i-1] * M;
        }
    }

    public static void calcPrefixHashes(char[] str){
        _prefixHashes[0] = str[0]-'a'+1;

        for(int i=1;i<str.length;i++){
            _prefixHashes[i] = (_prefixHashes[i-1] * M + str[i]-'a'+1);
        }
    }   

    public static void calcPrefixBadCount(char[] str, char[] isBad){
        _prefixBadCount[0] = isBad(str[0],isBad) ? 1 : 0;

        for (int i=1;i<str.length;i++){
            _prefixBadCount[i] = _prefixBadCount[i-1] + (isBad(str[i],isBad) ? 1 : 0);
        }
    }

    public static void solve(char[] str){
        for(int i=0;i<str.length;i++){
            for(int j = i;j<str.length;j++){
                int badCount = _prefixBadCount[j] - ((i>0) ? _prefixBadCount[i-1] : 0);

                if(badCount <= K){
                    long hashVal = _prefixHashes[j] - ( i>0 ?  (_prefixHashes[i-1] * _pow[j-i+1]) : 0);
                    //System.out.println(i+","+j);
                    _hashes.add(hashVal);
                }
            }
        }    
    }

    private static boolean isBad(char c, char[] isBad){
        return isBad[c-'a'] == '0';
    }
}
