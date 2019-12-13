import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.utils.Utils;
import lib.utils.various.Range;

import java.util.Comparator;
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
public class D8_2_SpaceImageFormat extends AbstractSubmission {

    @Override
    public void testCase() {
        String s = sc.nextLine();
        out.println(new Range(0, 6).stream().mapToObj(i -> new Range(0, 25).stream().mapToObj(j -> new Range(0, s.length() / 150 + 1).stream().map(a -> s.charAt(150 * a + 25 * i + j)).filter(a -> a != '2').findFirst().getAsInt() == '0' ? "  " : "XX").collect(Collectors.joining())).collect(Collectors.joining("\n")));
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
        buildAndRun(D8_2_SpaceImageFormat.class, System.getProperty("user.home") + "/Downloads", "unique_id_D8_1_SpaceImageFormat_08.12.19_21:39_Advent of Code");
    }

    public D8_2_SpaceImageFormat() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}