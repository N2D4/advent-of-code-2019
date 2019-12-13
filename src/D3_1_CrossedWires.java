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
import java.util.function.ToIntFunction;


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
public class D3_1_CrossedWires extends AbstractSubmission {

    @Override
    public void testCase() {
        Set<IntIntPair>[] sets = new Set[2];
        for (int it = 0; it < sets.length; it++) {
            Set<IntIntPair> set = sets[it] = new HashSet<>();
            String[] cmds = sc.nextLine().split(",");
            IntIntPair pos = new IntIntPair(0, 0);
            for (String cmd : cmds) {
                char c = cmd.charAt(0);
                int xdir = c == 'L' ? -1 : c == 'R' ? 1 : 0;
                int ydir = c == 'U' ? -1 : c == 'D' ? 1 : 0;
                int dist = Integer.parseInt(cmd.substring(1));
                for (int i = 0; i < dist; i++) {
                    pos = new IntIntPair(pos.a + xdir, pos.b + ydir);
                    set.add(pos);
                }
            }
        }

        out.println(Arrays.toString(sets));
        sets[0].retainAll(sets[1]);
        out.println(sets[0].stream().mapToInt(a -> Math.abs(a.a) + Math.abs(a.b)).min().getAsInt());

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
        buildAndRun(D3_1_CrossedWires.class, System.getProperty("user.home") + "/Downloads", "unique_id_D3_1_CrossedWires_03.12.19_14:31_Advent of Code");
    }

    public D3_1_CrossedWires() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}