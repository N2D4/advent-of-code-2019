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
public class D20_1_DonutMaze extends AbstractSubmission {

    @Override
    public void testCase() {
        List<String> lines = sc.readAllNonEmptyLines();
        int[][] map = new int[lines.size() + 4][];
        map[0] = map[1] = map[map.length - 1] = map[map.length - 2] = IntStream.range(0, lines.get(0).length() + 4).mapToObj(a -> "#").collect(Collectors.joining()).codePoints().toArray();
        for (int i = 0; i < lines.size(); i++) {
            map[i + 2] = ("##" + lines.get(i) + "##").codePoints().toArray();
        }

        for (int i = 2; i < map.length - 2; i++) {
            outer: for (int j = 2; j < map[i].length - 2; j++) {
                if (Character.isUpperCase(map[i][j])) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (k*k + l*l != 1) continue;
                            if (map[i+k][j+l] == '.') {
                                map[i+k][j+l] = -1;
                                map[i+k][j+l] &= ~(1 << (map[i][j] - 'A'));
                                map[i][j] = ' ';
                                continue outer;
                            }
                        }
                    }
                    //throw new RuntimeException(i + " " + j + " " + map[i][j]);
                }
            }
        }

        for (int i = 2; i < map.length - 2; i++) {
            outer: for (int j = 2; j < map[i].length - 2; j++) {
                if (Character.isUpperCase(map[i][j])) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (k*k + l*l != 1) continue;
                            if (map[i+k+k][j+l+l] < 0) {
                                map[i+k+k][j+l+l] &= ~(1 << (map[i][j] - 'A'));
                                continue outer;
                            }
                        }
                    }
                    throw new RuntimeException(i + " " + j + " " + map[i][j]);
                }
            }
        }

        debug.println(Arr.stream(map).map(a -> new String(Arr.stream(a).map(b -> b < 0 ? 'x' : b).toArray(), 0, a.length)).collect(Collectors.joining("\n")));

        Map<Integer, List<IntIntPair>> occs = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] < 0) {
                    occs.putIfAbsent(map[i][j], new ArrayList<>());
                    occs.get(map[i][j]).add(new IntIntPair(i, j));
                    if (occs.get(map[i][j]).size() > 2) {
                        throw new RuntimeException("Three-way warp " + map[i][j] + " found at " + i + ", " + j + "; please note that AB and BA are considered the same warps (filter them out manually)");
                    }
                    debug.println("   " + Integer.toBinaryString(map[i][j]) + " " + i + " " + j + " " + occs.get(map[i][j]));
                }
            }
        }

        Queue<Ordered<IntIntPair, Integer>> queue = QueueUtils.createFIFO();
        queue.add(new Ordered<>(occs.get(-1 & ~(1 << ('A' - 'A'))).get(0), 0));
        Map<IntIntPair, Integer> distances = new HashMap<>();
        
        while (!queue.isEmpty()) {
            Ordered<IntIntPair, Integer> popped = queue.remove();
            IntIntPair el = popped.get();
            Integer dist = popped.getOrder();
            
            if (map[el.a][el.b] != '.' && map[el.a][el.b] >= 0) continue;
            if (map[el.a][el.b] == (-1 & ~(1 << ('Z' - 'A')))) {
                out.println(dist);
                return;
            }
            if (distances.containsKey(el)) continue;
            distances.put(el, dist);

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i*i + j*j == 1) {
                        queue.add(new Ordered<>(new IntIntPair(el.a+i, el.b+j), dist + 1));
                    }
                }
            }

            if (map[el.a][el.b] < 0) {
                for (IntIntPair pair : occs.get(map[el.a][el.b])) {
                    debug.println(map[el.a][el.b] + " " + pair);
                    queue.add(new Ordered<>(pair, dist + 1));
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
        buildAndRun(D20_1_DonutMaze.class, System.getProperty("user.home") + "/Downloads", "unique_id_D20_1_DonutMaze_20.12.19_17:20_Advent of Code");
    }

    public D20_1_DonutMaze() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}