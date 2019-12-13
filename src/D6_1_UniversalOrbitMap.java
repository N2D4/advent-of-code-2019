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


@ContestSubmission(ContestType.CODEFORCES)
@CacheVersion(0)
public class D6_1_UniversalOrbitMap extends AbstractSubmission {

    @Override
    public void testCase() {
        Map<String, String> parent = new HashMap<>();
        List<String> lines = sc.readAllLines();

        for (String line : lines) {
            parent.put(line.split("\\)")[1], line.split("\\)")[0]);
        }

        int total = 0;
        for (String v : parent.keySet()) {
            while (!v.equals("COM")) {
                total++;
                v = parent.get(v);
            }
        }

        out.println(total);
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
        buildAndRun(D6_1_UniversalOrbitMap.class, System.getProperty("user.home") + "/Downloads", "unique_id_D6_1_UniversalOrbitMap_06.12.19_12:56_Advent of Code");
    }

    public D6_1_UniversalOrbitMap() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}