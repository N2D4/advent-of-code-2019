import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.utils.Arr;
import lib.utils.Utils;
import lib.utils.various.Range;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;


/* BEGIN-NO-BUNDLE */

/*
 * All shortcuts:
 * dpt, dpt1, dpt2, dpt3
 * bfs
 * aj6, aj8
 * nobundle
 * defmod
 * scanarr
 */

/* END-NO-BUNDLE */


@ContestSubmission(ContestType.RAW_IO)
@CacheVersion(0)
public class D16_2_FlawedFrequencyTransmission extends AbstractSubmission {

    @Override
    public void testCase() {
        String orig = sc.nextLine();
        String s = orig;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            sb.append(s);
        }
        while ((sb.length() & sb.length()-1) != 0) {
            sb.append("0");
        }
        s = sb.toString();

        for (int i = 0; i < 100; i++) {
            debug.println(i + ": " + s);
            long[] x = Utils.stream(s).mapToLong(a -> a - '0').toArray();
            long[] su = new long[x.length + 1];
            for (int j = su.length - 1; j >= 1; j--) {
                su[j-1] = su[j] + x[j-1];
            }
            long[] res = new long[x.length];
            final int[] sa = new int[] {1, -1, -1, 1};
            for (int k = 0; k < res.length; k++) {
                int lo = 0;
                int sta = k;
                while (sta < x.length) {
                    res[k] += su[sta] * sa[lo % 4];
                    sta += k + 1;
                    lo++;
                }
            }
            s = new String(Utils.stream(res).mapToInt(a -> '0' + (int) (Math.abs(a) % 10)).toArray(), 0, x.length);
        }

        out.println(s);

        s += s;
        int indx = Integer.parseInt(orig.substring(0, 7)) % (s.length() / 2);
        out.println(s.substring(indx, indx + 8));
    }


    /*public long[] rf(long[] x, int N, int m, int o) {
        if (x.length == 1) return new long[] { x[0] * a[(o+1)/(k+1) % 4] };
        if (x.length % 2 != 0) {
            throw new IllegalArgumentException("Array length is not a power of 2!");
        }

        long[] evens = IntStream.range(0, x.length/2).mapToLong(a -> x[2*a]).toArray();
        long[] odds = IntStream.range(0, x.length/2).mapToLong(a -> x[2*a+1]).toArray();

        long[] evensRes = rf(evens, N/2, 2*m, o);
        long[] oddsRes = rf(odds, N/2, 2*m, o+m);

        long[] res = new long[x.length];

        return ;
    }*/



    @Override
    public void init() {
        // Nothing here! Good for initializing look-up tables or cached values

        // Note that there could be multiple Problem instances running on different threads. Writing static variables
        // here (or anywhere else) could lead to race conditions! Use static initialization instead
    }











    // Set to TRUE to override default (which may both be true or false). Setting to FALSE has no effect

    /* DISABLE-CODE-COMPRESSION: TRUE */
    /* GLOBAL-DISABLE-CODE-COMPRESSION: DEFAULT */
    /* JAVA-6-COMPATIBILITY-MODE: DEFAULT */
    /* KEEP-UNUSED-DEPENDENCIES: DEFAULT */





    /* BEGIN-NO-BUNDLE */
    public static void main(String[] args) throws Exception {
        buildAndRun(D16_2_FlawedFrequencyTransmission.class, System.getProperty("user.home") + "/Downloads", "unique_id_D16_1_FlawedFrequencyTransmission_16.12.19_14:00_Advent of Code");
    }

    public D16_2_FlawedFrequencyTransmission() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}