import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.generated.IntIntPair;
import lib.utils.Arr;
import lib.utils.Utils;
import lib.utils.function.BiCons;
import lib.utils.function.Func;
import lib.utils.tuples.Pair;
import lib.utils.various.Range;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
public class D13_2_CarePackage extends AbstractSubmission {

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

            BlockingQueue<BigInteger> input = new LinkedBlockingQueue<>(1);
            BlockingQueue<BigInteger> output = new LinkedBlockingQueue<>();
            new Thread(Utils.nonThrowing(() -> {
                comp(codeMap, input, output);
                output.add(SENTINEL);
            })).start();

            Map<Pair<BigInteger, BigInteger>, BigInteger> blocks = Collections.synchronizedMap(new HashMap<>());
            blocks.put(new Pair<>(ZERO, ZERO), ONE);
            BigInteger[] score = {ZERO};

            new Thread(Utils.nonThrowing(() -> {
                Thread.sleep(500);
                while ("".isEmpty()) {
                    Thread.sleep(50);
                    synchronized (blocks) {
                        int minX = blocks.keySet().stream().mapToInt(a -> a.a.intValueExact()).min().getAsInt();
                        int maxX = blocks.keySet().stream().mapToInt(a -> a.a.intValueExact()).max().getAsInt();
                        int minY = blocks.keySet().stream().mapToInt(a -> a.b.intValueExact()).min().getAsInt();
                        int maxY = blocks.keySet().stream().mapToInt(a -> a.b.intValueExact()).max().getAsInt();

                        for (int j = minY; j <= maxY; j++) {
                            for (int i = minX; i <= maxX; i++) {
                                out.print((new char[]{' ', '#', '0', '-', 'O'})[blocks.getOrDefault(new Pair<>(valueOf(i), valueOf(j)), ZERO).intValueExact()]);
                            }
                            out.println();
                        }

                        out.println("Score: " + score[0]);

                        input.put(valueOf(blocks.entrySet().stream().filter(a -> a.getValue().equals(FOUR)).findAny().get().getKey().a.subtract(blocks.entrySet().stream().filter(a -> a.getValue().equals(THREE)).findAny().get().getKey().a).signum()));
                    }
                }
            })).start();

            while (true) {
                BigInteger next = output.take();
                synchronized (blocks) {
                    if (next == SENTINEL) break;
                    Pair<BigInteger, BigInteger> pos = new Pair<>(next, output.take());
                    if (pos.equals(new Pair<>(MINUS_ONE, ZERO))) score[0] = output.take();
                    else blocks.put(pos, output.take());
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void comp(Map<BigInteger, BigInteger> mem, BlockingQueue<BigInteger> input, BlockingQueue<BigInteger> output) throws InterruptedException {
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
                    wa.accept(0, input.take());
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
        buildAndRun(D13_2_CarePackage.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D13_2_CarePackage() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}