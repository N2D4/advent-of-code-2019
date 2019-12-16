import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntPair;
import lib.generated.ObjIntPair;
import lib.utils.Arr;
import lib.utils.QueueUtils;
import lib.utils.Utils;
import lib.utils.function.BiCons;
import lib.utils.function.Func;
import lib.utils.tuples.Pair;
import lib.utils.various.Ordered;
import lib.utils.various.Range;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static java.math.BigInteger.*;


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
public class D15_1_OxygenSystem extends AbstractSubmission {

    private final static BigInteger MINUS_ONE = valueOf(-1);
    private final static BigInteger TWO = valueOf(2);
    private final static BigInteger THREE = valueOf(3);
    private final static BigInteger FOUR = valueOf(4);
    private final static BigInteger FIVE = valueOf(5);
    private final static BigInteger SIX = valueOf(6);
    private final static BigInteger SEVEN = valueOf(7);
    private final static BigInteger EIGHT = valueOf(8);
    private final static BigInteger NINE = valueOf(9);
    private final static BigInteger SENTINEL = new BigInteger("784562439875629783456192576203456938653464536");

    @Override
    public void testCase() {
        try {
            BigInteger[] code = Arr.stream(sc.nextLine().split(","))
                    .map(String::trim)
                    .map(BigInteger::new)
                    .toArray(BigInteger[]::new);

            Map<BigInteger, BigInteger> codeMap = new HashMap<>(new Range(0, code.length).stream().boxed().collect(Collectors.toMap(k -> valueOf(k), k -> code[k])));

            BlockingQueue<BigInteger> input = new LinkedBlockingQueue<>();
            BlockingQueue<BigInteger> output = new LinkedBlockingQueue<>();
            Thread intCodeComputer = new Thread(Utils.nonThrowing(() -> {
                comp(codeMap, input, output);
                output.add(SENTINEL);
            }));
            intCodeComputer.setDaemon(true);
            intCodeComputer.start();

            Queue<Pair<IntIntPair, IntIntPair>> queue = QueueUtils.createFIFO();
            queue.add(new Pair<>(new IntIntPair(0, 0), null));
            Map<IntIntPair, IntIntPair> prevs = new HashMap<>();

            while (!queue.isEmpty()) {
                Pair<IntIntPair, IntIntPair> popped = queue.remove();
                IntIntPair el = popped.a;
                IntIntPair prev = popped.b;
                debug.println(el + " from " + prev);

                if (prevs.containsKey(el)) continue;
                prevs.put(el, prev);

                List<BigInteger> directions = new ArrayList<>();
                IntIntPair iterloc = el;
                while (true) {
                    IntIntPair niterloc = prevs.get(iterloc);
                    if (niterloc == null) break;
                    if (niterloc.a - iterloc.a == 1) directions.add(THREE);
                    if (niterloc.a - iterloc.a == -1) directions.add(FOUR);
                    if (niterloc.b - iterloc.b == 1) directions.add(ONE);
                    if (niterloc.b - iterloc.b == -1) directions.add(TWO);
                    iterloc = niterloc;
                }
                Collections.reverse(directions);

                input.addAll(directions);
                for (int i = 0; i < directions.size() - 1; i++) {
                    assert output.take().equals(ONE);
                }

                debug.println(directions);

                try {
                    switch (directions.size() == 0 ? 1 : output.take().intValueExact()) {
                        case 0: {
                            debug.println("wall");
                            directions.remove(directions.size() - 1);
                            break;
                        }
                        case 1: {
                            debug.println("no wall");
                            for (int i = -1; i <= 1; i++) {
                                for (int j = -1; j <= 1; j++) {
                                    if (i * i + j * j != 1) continue;
                                    queue.add(new Pair<>(new IntIntPair(el.a + i, el.b + j), el));
                                }
                            }
                            break;
                        }
                        case 2: {
                            debug.println("goal!");
                            out.println(directions.size());
                            return;
                        }
                        default: {
                            throw new RuntimeException("unknown output code");
                        }
                    }
                } finally {
                    assert output.size() == 0;
                    for (int i = directions.size() - 1; i >= 0; i--) {
                        int dir = directions.get(i).intValueExact();
                        input.add(valueOf(dir == 1 ? 2 : dir == 2 ? 1 : dir == 3 ? 4 : 3));
                    }
                    for (int i = 0; i < directions.size(); i++) {
                        assert output.take().equals(ONE);
                    }
                    assert output.size() == 0;
                }
            }

            out.println("No path found!");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void comp(Map<BigInteger, BigInteger> mem, BlockingQueue<BigInteger> input, BlockingQueue<BigInteger> output) throws InterruptedException {
        BigInteger pc = ZERO;
        BigInteger rbase = ZERO;
        while (true) {
            BigInteger fpc = pc;
            BigInteger frbase = rbase;
            Func<Integer, BigInteger> aa = i -> Utils.let(mem.getOrDefault(fpc, ZERO).divide(TEN.pow(2 + i)).mod(TEN).intValueExact(), (Integer k) -> Utils.let(fpc.add(valueOf(i+1)), pos -> k == 0 ? mem.getOrDefault(pos, ZERO) : k == 1 ? pos : frbase.add(mem.getOrDefault(pos, ZERO))));
            Func<Integer, BigInteger> ra = i -> mem.getOrDefault(aa.apply(i), ZERO);
            BiCons<Integer, BigInteger> wa = (i, v) -> mem.put(aa.apply(i), v);
            switch (mem.getOrDefault(pc, ZERO).mod(valueOf(100)).intValueExact()) {
                case 1: {
                    // ADD
                    wa.accept(2, ra.apply(0).add(ra.apply(1)));
                    pc = pc.add(valueOf(4));
                    break;
                }
                case 2: {
                    // MUL
                    wa.accept(2, ra.apply(0).multiply(ra.apply(1)));
                    pc = pc.add(valueOf(4));
                    break;
                }
                case 3: {
                    // IN
                    BigInteger inp = input.take();
                    wa.accept(0, inp);
                    pc = pc.add(valueOf(2));
                    break;
                }
                case 4: {
                    // OUT
                    output.put(ra.apply(0));
                    pc = pc.add(valueOf(2));
                    break;
                }
                case 5: {
                    // JT
                    pc = !ra.apply(0).equals(ZERO) ? ra.apply(1) : pc.add(valueOf(3));
                    break;
                }
                case 6: {
                    // JF
                    pc = ra.apply(0).equals(ZERO) ? ra.apply(1) : pc.add(valueOf(3));
                    break;
                }
                case 7: {
                    // LT
                    wa.accept(2, ra.apply(0).compareTo(ra.apply(1)) < 0 ? ONE : ZERO);
                    pc = pc.add(valueOf(4));
                    break;
                }
                case 8: {
                    // EQ
                    wa.accept(2, ra.apply(0).equals(ra.apply(1)) ? ONE : ZERO);
                    pc = pc.add(valueOf(4));
                    break;
                }
                case 9: {
                    // ABASE
                    rbase = rbase.add(ra.apply(0));
                    pc = pc.add(valueOf(2));
                    break;
                }
                case 99: {
                    return;
                }
                default: {
                    throw new RuntimeException("Unknown instruction code! " + mem.getOrDefault(pc, ZERO) + " at mem[" + pc + "]");
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
        buildAndRun(D15_1_OxygenSystem.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D15_1_OxygenSystem() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}