import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntPair;
import lib.generated.ObjIntPair;
import lib.utils.Arr;
import lib.utils.MathUtils;
import lib.utils.Utils;
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
public class D2_2_1202ProgramAlert extends AbstractSubmission {

    @Override
    public void testCase() {
        out.println(
                Utils.let(
                        Arr.stream(sc.readAll().split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray(),
                        (int[] cs) -> Utils.let(
                                IntStream.range(0, 100)
                                        .boxed()
                                        .flatMap(i -> IntStream.range(0, 100).mapToObj(j -> new Pair<>(i, j)))
                                        .map(p -> new Pair<>(
                                                p,
                                                Utils.let(
                                                        IntStream.concat(
                                                                        IntStream.concat(
                                                                                Arr.stream(cs).limit(1),
                                                                                IntStream.of(p.a, p.b)
                                                                        ),
                                                                        Arr.stream(cs).skip(3))
                                                                .toArray(),
                                                        (int[] ncs) -> IntStream.range(0, ncs.length / 4 + 1)
                                                                                .map(i -> 4 * i)
                                                                                .boxed()
                                                                                .reduce(
                                                                                        ncs,
                                                                                        (arr, i) ->                                           arr.length == 1 ? arr
                                                                                                  :                                              arr[i] == 99 ? new int[] {arr[0]}
                                                                                                  : MathUtils.max(arr[i+1], arr[i+2], arr[i+3]) >= arr.length ? new int[] {-1}
                                                                                                  : Utils.let(
                                                                                                        new Pair<>(
                                                                                                                arr[i+3],
                                                                                                                arr[i] == 1 ? arr[arr[i+1]] + arr[arr[i+2]] : arr[arr[i+1]] * arr[arr[i+2]]
                                                                                                        ),
                                                                                                        (Integer vp, Integer vv) -> IntStream.concat(
                                                                                                                IntStream.concat(
                                                                                                                        Arr.stream(arr)
                                                                                                                                .limit(vp),
                                                                                                                        IntStream.of(vv)
                                                                                                                ),
                                                                                                                Arr.stream(arr)
                                                                                                                        .skip(vp + 1)
                                                                                                        ).toArray()
                                                                                                  ),
                                                                                        (a, b) -> (new int[0][0])[-1]
                                                                                )[0]
                                                )
                                        ))
                                        .peek(System.out::println)
                                        .filter(p -> p.b.equals(19690720))
                                        .findAny().get().a,
                                (Integer noun, Integer verb) -> 100 * noun + verb
                        )
                )
        );
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
        buildAndRun(D2_2_1202ProgramAlert.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D2_2_1202ProgramAlert() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}