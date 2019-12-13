import lib.contest.*;
import lib.utils.*;


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
public class D1_1_TheTyrannyOfTheRocketEquation extends AbstractSubmission {

    @Override
    public void testCase() {
        out.println(Arr.stream(sc.readAll().split("\n")).filter(a -> !a.trim().equals("")).mapToLong(Long::parseLong).map(a -> a / 3 - 2).sum());
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
        buildAndRun(D1_1_TheTyrannyOfTheRocketEquation.class, System.getProperty("user.home") + "/Downloads", "unique_id_D1_TheTyrannyOfTheRocketEquation_2019-12-02_00:36_Advent of Code");
    }

    public D1_1_TheTyrannyOfTheRocketEquation() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}