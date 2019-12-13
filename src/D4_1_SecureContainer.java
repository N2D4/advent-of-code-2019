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
public class D4_1_SecureContainer extends AbstractSubmission {

    @Override
    public void testCase() {
        out.println(
                IntStream.range(359282, 820401)
                        .filter(a -> ("" + a).chars().reduce((c, b) -> c < 0 || b == c ? -1 : b).getAsInt() < 0)
                        .filter(a -> Arrays.equals(("" + a).chars().toArray(), ("" + a).chars().sorted().toArray()))
                        .count()
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
        buildAndRun(D4_1_SecureContainer.class, System.getProperty("user.home") + "/Downloads", "unique_id_D4_1_SecureContainer_04.12.19_13:11_Advent of Code");
    }

    public D4_1_SecureContainer() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}