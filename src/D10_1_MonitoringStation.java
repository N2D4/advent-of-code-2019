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
public class D10_1_MonitoringStation extends AbstractSubmission {

    @Override
    public void testCase() {
        char[][] map = Utils.stream(sc.readAll().split("\n")).map(String::trim).filter(s -> !s.isEmpty()).map(String::toCharArray).toArray(char[][]::new);
        Map<Pair<Integer, Integer>, Integer> gcdTable = IntStream.range(-map.length, map.length + 1)
                .boxed()
                .flatMap(i -> IntStream.range(-map[0].length, map[0].length + 1).mapToObj(j -> new Pair<>(i, j)))
                .collect(Collectors.toMap(k -> k, k -> (int) ExtendedEuclid.gcd(Math.abs(k.a), Math.abs(k.b))));

        int max = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int asteroids = 0;
                for (int k = 0; k < map.length; k++) {
                    outer: for (int l = 0; l < map[k].length; l++) {
                        if ((k == i && l == j) || map[i][j] == '.' || map[k][l] == '.') continue;
                        int gcd = gcdTable.get(new Pair<>(k-i, l-j));
                        for (int m = 1; m < gcd; m++) {
                            System.err.println(i + " " + j + " " + (i+(k-i)/gcd*m) + " " + (j+(l-j)/gcd*m));
                            if (map[i+(k-i)/gcd*m][j+(l-j)/gcd*m] != '.') continue outer;
                        }
                        asteroids++;
                    }
                }
                max = Math.max(asteroids, max);
            }
        }

        out.println(max);
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
        buildAndRun(D10_1_MonitoringStation.class, System.getProperty("user.home") + "/Downloads", "unique_id_D10_1_MonitoringStation_10.12.19_20:03_Advent of Code");
    }

    public D10_1_MonitoringStation() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}