import lib.algorithms.ExtendedEuclid;
import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.utils.MathUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
public class D22_2_SlamShuffle extends AbstractSubmission {

    List<String> lines;

    @Override
    public void testCase() {
        lines = new ArrayList<>(sc.readAllNonEmptyLines());
        Collections.reverse(lines);
        out.println(card(2020, 119315717514047l, 101741582076661l));
        for (int i = 0; i < 11; i++) {
            out.print(card(i, 11l, 4) + " ");
        }
        out.println();
    }

    public long card(long card, long mod, long times) {
        // ax + b
        long a = 1;
        long b = 0;

        for (String line : lines) {
            if (line.charAt(0) == 'c') {
                b = MathUtils.realMod(b + Integer.parseInt(line.split(" ")[1]), mod);
            } else if (line.charAt(5) == 'w') {
                long n = Integer.parseInt(line.split(" ")[3]);
                a = mdiv(a, n, mod);
                b = mdiv(b, n, mod);
            } else {
                a = MathUtils.realMod(-a, mod);
                b = MathUtils.realMod(-b - 1, mod);
            }
        }

        debug.println("   " + a + "x + " + b);

        assert a != 1 : "unimplemented";
        b = mmul(b, mdiv(1 - mexp(a, times, mod), 1 - a, mod), mod); // sum of the geometric series b + ba + ba^2 + ba^3 + ... + ba^(times-1). Note that a > 1, which is unusual for geometric series
        a = mexp(a, times, mod);

        debug.println(a + "x + " + b);

        return MathUtils.realMod(mmul(a, card, mod) + b, mod);
    }

    public long mdiv(long a, long b, long mod) {
        return mmul(a, ExtendedEuclid.modularInverse(b, mod), mod);
    }

    public long mmul(long a, long b, long mod) {
        return MathUtils.modMul(a, b, mod);
    }

    public long mexp(long a, long exponent, long mod) {
        return MathUtils.realMod(BigInteger.valueOf(a).modPow(BigInteger.valueOf(exponent), BigInteger.valueOf(mod)).longValueExact(), mod);
    }



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
        buildAndRun(D22_2_SlamShuffle.class, System.getProperty("user.home") + "/Downloads", "unique_id_D22_1_SlamShuffle_22.12.19_12:06_Advent of Code");
    }

    public D22_2_SlamShuffle() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}