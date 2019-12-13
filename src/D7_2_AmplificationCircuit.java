import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntFunc;
import lib.utils.Arr;
import lib.utils.MathUtils;
import lib.utils.Utils;
import lib.utils.various.PermutationIterable;
import lib.utils.various.Range;
import sun.jvm.hotspot.opto.Block;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
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
public class D7_2_AmplificationCircuit extends AbstractSubmission {

    @Override
    public void testCase() {
        int[] code = Arr.stream(sc.readAll().split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();


        int max = Integer.MIN_VALUE;
        for (List<Integer> list : new PermutationIterable<>(Arr.asList(5, 6, 7, 8, 9))) {
            BlockingQueue<Integer>[] q = IntStream.range(0, list.size()).mapToObj(i -> new ArrayBlockingQueue(100, false, Arr.asList(list.get(i)))).toArray(BlockingQueue[]::new);
            q[0].add(0);
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                final int fi = i;
                Thread t = new Thread(Utils.nonThrowing(() -> comp(Arrays.copyOf(code, code.length), q[fi], q[(fi+1)%5])));
                threads.add(t);
                t.start();
            }
            threads.forEach(t -> {
                try {
                    t.join(0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            max = Math.max(max, q[0].remove());
        }
        out.println(max);
    }

    public static void comp(int[] mem, BlockingQueue<Integer> input, BlockingQueue<Integer> output) throws InterruptedException {
        int pc = 0;
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
                    mem[mem[pc+1]] = input.take();
                    pc += 2;
                    break;
                }
                case 4: {
                    output.put(args.apply(0));
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
                    return;
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
        buildAndRun(D7_2_AmplificationCircuit.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D7_2_AmplificationCircuit() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}