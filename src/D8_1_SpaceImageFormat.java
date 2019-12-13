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
import java.util.stream.Collectors;
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
public class D8_1_SpaceImageFormat extends AbstractSubmission {

    @Override
    public void testCase() {
        String s = sc.nextLine();
        out.println(Utils.stream(IntStream.range(0, s.length())).filter(a -> a % 150 == 0).mapToObj(i -> s.substring(i, Math.min(s.length(), i + 150))).min(Comparator.comparing((String a) -> Utils.stream(a).filter(b -> b == '0').count())).map(a -> Utils.stream(a).filter(b -> b == '1').count() * Utils.stream(a).filter(b -> b == '2').count()).get());
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
        buildAndRun(D8_1_SpaceImageFormat.class, System.getProperty("user.home") + "/Downloads", "unique_id_D8_1_SpaceImageFormat_08.12.19_21:39_Advent of Code");
    }

    public D8_1_SpaceImageFormat() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}