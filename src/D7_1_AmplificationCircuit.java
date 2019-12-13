import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntFunc;
import lib.utils.Arr;
import lib.utils.MathUtils;
import lib.utils.Utils;
import lib.utils.various.BruteForceIterable;
import lib.utils.various.PermutationIterable;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


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
public class D7_1_AmplificationCircuit extends AbstractSubmission {

    @Override
    public void testCase() {
        int[] code = Arr.stream(sc.readAll().split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        int max = Integer.MIN_VALUE;
        for (List<Integer> list : new PermutationIterable<>(Arr.asList(0, 1, 2, 3, 4))) {
            int val = 0;
            for (int i = 0; i < list.size(); i++) {
                val = comp(code, new ArrayDeque<>(Arr.asList(list.get(i), val)));
            }
            max = Math.max(val, max);
        }
        out.println(max);
    }

    public static int comp(int[] code, Queue<Integer> input) {
        int[] mem = Arrays.copyOf(code, code.length);
        int pc = 0;
        int output = -1;
        while (true) {
            int fpc = pc;
            IntIntFunc args = i -> Utils.let(mem[fpc+1+i], j -> mem[fpc] / 100 / MathUtils.pow(10, i) % 10 == 0 ? mem[j] : j);
            switch (mem[pc] % 100) {
                case 1: {
                    mem[mem[pc+3]] = args.apply(0) + args.apply(1);
                    pc += 4;
                    break;
                }
                case 2: {
                    mem[mem[pc+3]] = args.apply(0) * args.apply(1);
                    pc += 4;
                    break;
                }
                case 3: {
                    mem[mem[pc+1]] = input.remove();
                    pc += 2;
                    break;
                }
                case 4: {
                    output = args.apply(0);
                    pc += 2;
                    break;
                }
                case 5: {
                    pc = args.apply(0) != 0 ? args.apply(1) : pc + 3;
                    break;
                }
                case 6: {
                    pc = args.apply(0) == 0 ? args.apply(1) : pc + 3;
                    break;
                }
                case 7: {
                    mem[mem[pc+3]] = args.apply(0) < args.apply(1) ? 1 : 0;
                    pc += 4;
                    break;
                }
                case 8: {
                    mem[mem[pc+3]] = args.apply(0) == args.apply(1) ? 1 : 0;
                    pc += 4;
                    break;
                }
                case 99: {
                    return output;
                }
                default: {
                    throw new RuntimeException("Unknown instruction code! " + mem[pc]);
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
        buildAndRun(D7_1_AmplificationCircuit.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D7_1_AmplificationCircuit() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}