import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntArrayList;
import lib.generated.IntIntPair;
import lib.generated.IntList;
import lib.generated.ObjIntPair;
import lib.utils.QueueUtils;
import lib.utils.various.Ordered;

import java.util.*;

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
public class D18_2_ManyWorldsInterpretation extends AbstractSubmission {

    @Override
    public void testCase() {
        char[][] grid = sc.readAllNonEmptyLines().stream().map(String::toCharArray).toArray(char[][]::new);

        IntIntPair ogEntrance = null;
        List<IntIntPair> entrances = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@') {
                    ogEntrance = new IntIntPair(i, j);
                    entrances.add(new IntIntPair(i-1, j-1));
                    entrances.add(new IntIntPair(i+1, j-1));
                    entrances.add(new IntIntPair(i-1, j+1));
                    entrances.add(new IntIntPair(i+1, j+1));
                    grid[i][j] = '#';
                    grid[i-1][j] = '#';
                    grid[i+1][j] = '#';
                    grid[i][j-1] = '#';
                    grid[i][j+1] = '#';
                }
            }
        }

        int[] allKeysA = new int[4];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                 if (isLowerCase(grid[i][j])) {
                     int id = i < ogEntrance.a && j < ogEntrance.b ? 0
                            : i > ogEntrance.a && j < ogEntrance.b ? 1
                            : i < ogEntrance.a && j > ogEntrance.b ? 2
                            : 3;
                     allKeysA[id] |= 1 << (grid[i][j] - 'a');
                }
            }
        }

        int sum = 0;
        outer: for (int it = 0; it < 4; it++) {
            IntIntPair entrance = entrances.get(it);
            int allKeys = allKeysA[it];


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

                if (c == '#' || (isUpperCase(c) && (keys >> (c - 'A') & 1) == 0) && (allKeys >> (c - 'A') & 1) == 1) continue;
                if (distances.containsKey(el)) continue;
                distances.put(el, dist);

                if (isLowerCase(c)) {
                    keys |= 1 << (c - 'a');
                }

                if (keys == allKeys) {
                    debug.println("robot " + it + " at " + entrance + ": " + dist);
                    sum += dist;
                    continue outer;
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

        out.println(sum);


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
        buildAndRun(D18_2_ManyWorldsInterpretation.class, System.getProperty("user.home") + "/Downloads", "unique_id_D18_1_ManyWorldsInterpretation_18.12.19_16:22_Advent of Code");
    }

    public D18_2_ManyWorldsInterpretation() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}