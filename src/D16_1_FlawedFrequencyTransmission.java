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
public class D16_1_FlawedFrequencyTransmission extends AbstractSubmission {

    @Override
    public void testCase() {
        String s = sc.nextLine();
        for (int i = 0; i < 100; i++) {
            debug.println(i + ": " + s);
            int[] pattern = new int[] {0, 1, 0, -1};
            StringBuilder ns = new StringBuilder();
            for (int j = 1; j <= s.length(); j++) {
                int total = 0;
                for (int k = 0; k < s.length(); k++) {
                    total += (s.charAt(k) - '0') * pattern[((k + 1) / j) % pattern.length];
                    //debug.println((s.charAt(k) - '0') + " " + pattern[((k + 1) / j) % pattern.length]);
                }
                //debug.println(Math.abs(total % 10));
                ns.append(Math.abs(total % 10));
            }
            s = ns.toString();
        }

        out.println(s);
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
        buildAndRun(D16_1_FlawedFrequencyTransmission.class, System.getProperty("user.home") + "/Downloads", "unique_id_D16_1_FlawedFrequencyTransmission_16.12.19_14:00_Advent of Code");
    }

    public D16_1_FlawedFrequencyTransmission() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}