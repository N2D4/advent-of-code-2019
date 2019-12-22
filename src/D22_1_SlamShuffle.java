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


@ContestSubmission(ContestType.RAW_IO)
@CacheVersion(0)
public class D22_1_SlamShuffle extends AbstractSubmission {

    List<String> lines;

    @Override
    public void testCase() {
        lines = sc.readAllNonEmptyLines();
        out.println(card(2019, 10_007));
        for (int i = 0; i < 10; i++) {
            out.print(card(i, 10) + " ");
        }
        out.println();
    }

    public long card(long card, long mod) {
        for (String line : lines) {
            if (line.charAt(0) == 'c') {
                //debug.println("c " + line);
                card = MathUtils.realMod(card - Integer.parseInt(line.split(" ")[1]), mod);
            } else if (line.charAt(5) == 'w') {
                //debug.println("w " + line);
                card = MathUtils.realMod(card * Integer.parseInt(line.split(" ")[3]), mod);
            } else {
                //debug.println("e " + line);
                card = MathUtils.realMod(-1 - card, mod);
            }
        }
        return card;
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
        buildAndRun(D22_1_SlamShuffle.class, System.getProperty("user.home") + "/Downloads", "unique_id_D22_1_SlamShuffle_22.12.19_12:06_Advent of Code");
    }

    public D22_1_SlamShuffle() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}