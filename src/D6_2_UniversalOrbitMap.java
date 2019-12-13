import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.graphs.UndirectedAdjacencyListGraph;
import lib.graphs.algorithms.GraphSearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


@ContestSubmission(ContestType.CODEFORCES)
@CacheVersion(0)
public class D6_2_UniversalOrbitMap extends AbstractSubmission {

    @Override
    public void testCase() {
        Map<String, Integer> names = new HashMap<>();
        List<String> lines = sc.readAllLines();
        UndirectedAdjacencyListGraph graph = new UndirectedAdjacencyListGraph(2 * lines.size());

        for (String line : lines) {
            String a = line.split("\\)")[1];
            String b = line.split("\\)")[0];
            names.putIfAbsent(a, names.size());
            names.putIfAbsent(b, names.size());
            graph.addEdge(names.get(a), names.get(b));
        }

        out.println(GraphSearch.getEdgeDistance(graph, names.get("YOU"), names.get("SAN")) - 2);
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
        buildAndRun(D6_2_UniversalOrbitMap.class, System.getProperty("user.home") + "/Downloads", "unique_id_D6_1_UniversalOrbitMap_06.12.19_12:56_Advent of Code");
    }

    public D6_2_UniversalOrbitMap() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}