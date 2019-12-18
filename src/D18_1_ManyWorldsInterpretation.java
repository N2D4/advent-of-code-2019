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
import java.util.function.Function;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;


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
public class D18_1_ManyWorldsInterpretation extends AbstractSubmission {

    @Override
    public void testCase() {
        char[][] grid = sc.readAllNonEmptyLines().stream().map(String::toCharArray).toArray(char[][]::new);

        IntIntPair entrance = null;
        int allKeys = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@') {
                    grid[i][j] = '.';
                    entrance = new IntIntPair(i, j);
                } else if (isLowerCase(grid[i][j])) {
                    allKeys |= 1 << (grid[i][j] - 'a');
                }
            }
        }

        Queue<Ordered<ObjIntPair<IntIntPair>, Integer>> queue = QueueUtils.createFIFO();
        queue.add(new Ordered<>(new ObjIntPair<>(entrance, 0), 0));
        Map<ObjIntPair<IntIntPair>, Integer> distances = new HashMap<>();

        while (!queue.isEmpty()) {
            Ordered<ObjIntPair<IntIntPair>, Integer> popped = queue.remove();
            //debug.println(popped);
            ObjIntPair<IntIntPair> el = popped.get();
            IntIntPair pos = el.a;
            char c = grid[pos.a][pos.b];
            int keys = el.b;
            Integer dist = popped.getOrder();

            if (c == '#' || (isUpperCase(c) && (keys >> (c - 'A') & 1) == 0)) continue;
            if (distances.containsKey(el)) continue;
            distances.put(el, dist);

            if (isLowerCase(c)) {
                keys |= 1 << (c - 'a');
            }

            if (keys == allKeys) {
                out.println(dist);
                return;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i*i + j*j == 1) {
                        queue.add(new Ordered<>(new ObjIntPair<>(new IntIntPair(pos.a + i, pos.b + j), keys), dist + 1));
                    }
                }
            }

        }


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
        buildAndRun(D18_1_ManyWorldsInterpretation.class, System.getProperty("user.home") + "/Downloads", "unique_id_D18_1_ManyWorldsInterpretation_18.12.19_16:22_Advent of Code");
    }

    public D18_1_ManyWorldsInterpretation() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}