import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntPair;
import lib.utils.tuples.Pair;

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
public class D3_2_CrossedWires extends AbstractSubmission {

    @Override
    public void testCase() {
        Map<IntIntPair, Integer>[] sets = new Map[2];
        for (int it = 0; it < sets.length; it++) {
            Map<IntIntPair, Integer> set = sets[it] = new HashMap<>();
            String[] cmds = sc.nextLine().split(",");
            IntIntPair pos = new IntIntPair(0, 0);
            int dst = 0;
            for (String cmd : cmds) {
                char c = cmd.charAt(0);
                int xdir = c == 'L' ? -1 : c == 'R' ? 1 : 0;
                int ydir = c == 'U' ? -1 : c == 'D' ? 1 : 0;
                int dist = Integer.parseInt(cmd.substring(1));
                for (int i = 0; i < dist; i++) {
                    dst++;
                    pos = new IntIntPair(pos.a + xdir, pos.b + ydir);
                    if (!set.containsKey(pos)) set.put(pos, dst);
                }
            }
        }

        out.println(sets[0].keySet().stream().filter(a -> sets[1].containsKey(a)).mapToInt(a -> sets[0].get(a) + sets[1].get(a)).min().getAsInt());

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
        buildAndRun(D3_2_CrossedWires.class, System.getProperty("user.home") + "/Downloads", "unique_id_D3_1_CrossedWires_03.12.19_14:31_Advent of Code");
    }

    public D3_2_CrossedWires() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}