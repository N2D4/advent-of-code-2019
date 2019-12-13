import lib.algorithms.*;
import lib.contest.*;
import lib.dp.*;
import lib.generated.*;
import lib.graphs.*;
import lib.graphs.algorithms.*;
import lib.utils.*;
import lib.utils.function.*;
import lib.utils.tuples.*;
import lib.utils.various.*;
import lib.vectorization.*;

import java.io.*;
import java.util.*;
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
public class D12_1_TheNBodyProblem extends AbstractSubmission {

    @Override
    public void testCase() {
        long[][] moons = IntStream.range(0, 4)
                .mapToObj(i -> sc.nextLine())
                .map(s -> s.replaceAll("[xyz=<> ]", ""))
                .map(s -> Arr.stream(s.split(",")).mapToLong(Integer::parseInt).toArray())
                .toArray(long[][]::new);
        long[][] vs = new long[4][3];

        for (int i = 0; i < 1000; i++) {
            if (i < 10) out.println(i + ": " + Arr.toString(moons) + " " + Arr.toString(vs));
            for (int j = 0; j < moons.length; j++) {
                for (int k = 0; k < moons.length; k++) {
                    for (int l = 0; l < 3; l++) {
                        vs[j][l] += (int) Math.signum(moons[k][l] - moons[j][l]);
                    }
                }
            }

            for (int j = 0; j < moons.length; j++) {
                for (int k = 0; k < 3; k++) {
                    moons[j][k] += vs[j][k];
                }
            }
        }
        out.println(energy(moons, vs));
    }

    private static long energy(long[][] a, long[][] b) {
        return IntStream.range(0, a.length)
                .mapToObj(i -> new Pair<>(a[i], b[i]))
                .mapToLong(p -> Arr.stream(p.a).map(Math::abs).sum() * Arr.stream(p.b).map(Math::abs).sum())
                .sum();
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
        buildAndRun(D12_1_TheNBodyProblem.class, System.getProperty("user.home") + "/Downloads", "unique_id_D12_1_TheNBodyProblem_12.12.19_12:24_Advent of Code");
    }

    public D12_1_TheNBodyProblem() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}