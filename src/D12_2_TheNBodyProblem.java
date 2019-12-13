import lib.algorithms.ExtendedEuclid;
import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntArrayList;
import lib.generated.IntList;
import lib.generated.LongArrayList;
import lib.generated.LongList;
import lib.utils.Arr;
import lib.utils.tuples.Pair;

import java.util.Arrays;
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
public class D12_2_TheNBodyProblem extends AbstractSubmission {

    @Override
    public void testCase() {
        long[][] moons = IntStream.range(0, 4)
                .mapToObj(i -> sc.nextLine())
                .map(s -> s.replaceAll("[xyz=<> ]", ""))
                .map(s -> Arr.stream(s.split(",")).mapToLong(Integer::parseInt).toArray())
                .toArray(long[][]::new);
        long[][] vs = new long[4][3];


        LongList ress = new LongArrayList();
        outer: for (int axis = 0; axis < 3; axis++) {
            long[][][] orig = new long[][][] {Arr.deepCopyOf(moons), Arr.deepCopyOf(vs)};
            for (int i = 0;; i++) {
                if ((i & i-1) == 0) System.err.println(i + ": " + Arr.toString(moons) + " " + Arr.toString(vs));
                for (int j = 0; j < moons.length; j++) {
                    for (int k = 0; k < moons.length; k++) {
                        vs[j][axis] += (int) Math.signum(moons[k][axis] - moons[j][axis]);
                    }
                }

                for (int j = 0; j < moons.length; j++) {
                    moons[j][axis] += vs[j][axis];
                }

                if (Arrays.deepEquals(orig, new long[][][] {moons, vs})) {
                    ress.add(i+1);
                    continue outer;
                }
            }
        }

        out.println(ress.stream().reduce(1, ExtendedEuclid::lcm));
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
        buildAndRun(D12_2_TheNBodyProblem.class, System.getProperty("user.home") + "/Downloads", "unique_id_D12_1_TheNBodyProblem_12.12.19_12:24_Advent of Code");
    }

    public D12_2_TheNBodyProblem() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}