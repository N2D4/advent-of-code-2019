import lib.algorithms.BinarySearch;
import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntObjPair;
import lib.utils.Arr;
import lib.utils.MathUtils;
import lib.utils.Utils;
import lib.utils.various.LongRange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
public class D14_2_SpaceStoichiometry extends AbstractSubmission {

    @Override
    public void testCase() {
        Map<String, IntObjPair<List<IntObjPair<String>>>> res = Utils.stream(sc.readAllLines())
                .map(String::trim)
                .filter(a -> !a.isEmpty())
                .map(a -> a.split(" => "))
                .peek(debug::println)
                .collect(Collectors.toMap(a -> a[1].split(" ")[1], a -> new IntObjPair<>(Integer.parseInt(a[1].split(" ")[0]), Arr.stream(a[0].split(", ")).map(D14_2_SpaceStoichiometry::tr).collect(Collectors.toList()))));

        out.println(BinarySearch.searchInRange(new LongRange(0, 1000000000000l), l -> {
            Map<String, Long> count = new HashMap<>();
            count.put("FUEL", -l);

            boolean cont = true;
            while (cont) {
                cont = false;
                for (String key : new ArrayList<>(count.keySet())) {
                    if (key.equals("ORE")) continue;
                    IntObjPair<List<IntObjPair<String>>> pair = res.get(key);
                    long mult = MathUtils.ceilDiv(-count.get(key), pair.a);
                    if (mult <= 0) continue;
                    cont = true;
                    count.put(key, count.get(key) + mult * pair.a);
                    pair.b.forEach(a -> count.put(a.b, count.getOrDefault(a.b, 0l) - mult * a.a));
                }
            }

            return count.getOrDefault("ORE", 0l) < -1000000000000l;
        }) - 1);
    }


    private static IntObjPair<String> tr(String s) {
        return new IntObjPair<>(Integer.parseInt(s.split(" ")[0]), s.split(" ")[1]);
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
        buildAndRun(D14_2_SpaceStoichiometry.class, System.getProperty("user.home") + "/Downloads", "unique_id_D14_1_SpaceStoichiometry_14.12.19_14:14_Advent of Code");
    }

    public D14_2_SpaceStoichiometry() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}