import lib.contest.AbstractSubmission;
import lib.contest.CacheVersion;
import lib.contest.ContestSubmission;
import lib.contest.ContestType;
import lib.utils.Utils;
import lib.utils.function.BiCons;
import lib.utils.function.Func;
import lib.utils.various.Range;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class D19_1_TractorBeam extends AbstractSubmission {

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
        BigInteger[] code = IntStream.of(109,424,203,1,21102,1,11,0,1106,0,282,21101,0,18,0,1106,0,259,1202,1,1,221,203,1,21101,0,31,0,1105,1,282,21102,38,1,0,1105,1,259,20102,1,23,2,21201,1,0,3,21102,1,1,1,21101,0,57,0,1105,1,303,2101,0,1,222,20102,1,221,3,21002,221,1,2,21101,0,259,1,21101,0,80,0,1106,0,225,21102,1,152,2,21101,91,0,0,1106,0,303,1201,1,0,223,21001,222,0,4,21101,0,259,3,21102,225,1,2,21101,0,225,1,21102,1,118,0,1105,1,225,20101,0,222,3,21102,61,1,2,21101,133,0,0,1106,0,303,21202,1,-1,1,22001,223,1,1,21102,148,1,0,1105,1,259,2101,0,1,223,21001,221,0,4,21001,222,0,3,21101,0,14,2,1001,132,-2,224,1002,224,2,224,1001,224,3,224,1002,132,-1,132,1,224,132,224,21001,224,1,1,21101,0,195,0,105,1,109,20207,1,223,2,20101,0,23,1,21102,-1,1,3,21102,214,1,0,1105,1,303,22101,1,1,1,204,1,99,0,0,0,0,109,5,2101,0,-4,249,21202,-3,1,1,21202,-2,1,2,21201,-1,0,3,21102,1,250,0,1106,0,225,22101,0,1,-4,109,-5,2106,0,0,109,3,22107,0,-2,-1,21202,-1,2,-1,21201,-1,-1,-1,22202,-1,-2,-2,109,-3,2105,1,0,109,3,21207,-2,0,-1,1206,-1,294,104,0,99,22102,1,-2,-2,109,-3,2105,1,0,109,5,22207,-3,-4,-1,1206,-1,346,22201,-4,-3,-4,21202,-3,-1,-1,22201,-4,-1,2,21202,2,-1,-1,22201,-4,-1,1,21202,-2,1,3,21101,343,0,0,1106,0,303,1105,1,415,22207,-2,-3,-1,1206,-1,387,22201,-3,-2,-3,21202,-2,-1,-1,22201,-3,-1,3,21202,3,-1,-1,22201,-3,-1,2,22101,0,-4,1,21101,0,384,0,1106,0,303,1105,1,415,21202,-4,-1,-4,22201,-4,-3,-4,22202,-3,-2,-2,22202,-2,-4,-4,22202,-3,-2,-3,21202,-4,-1,-2,22201,-3,-2,1,21201,1,0,-4,109,-5,2106,0,0)
                .mapToObj(BigInteger::valueOf)
                .toArray(BigInteger[]::new);




        try {
            int offsi = 1063;
            int offsj = 1708;
            int sz = 200;

            int res = 0;
            char[][] map = new char[sz][sz];
            for (int i = 0; i < map.length; i++) {
                Arrays.fill(map[i], '.');
            }
            for (int i = 0; i < sz; i++) {
                for (int j = 0; j < sz; j++) {
                    debug.println(i + " " + j);

                    Map<BigInteger, BigInteger> codeMap = new HashMap<>(new Range(0, code.length).stream().boxed().collect(Collectors.toMap(k -> valueOf(k), k -> code[k])));

                    BlockingQueue<BigInteger> input = new LinkedBlockingQueue<>();
                    BlockingQueue<BigInteger> output = new LinkedBlockingQueue<>();
                    Thread intCodeComputer = new Thread(Utils.nonThrowing(() -> {
                        comp(codeMap, input, output);
                        output.add(SENTINEL);
                    }));
                    intCodeComputer.setDaemon(true);
                    intCodeComputer.start();


                    input.add(valueOf(offsi + i));
                    input.add(valueOf(offsj + j));

                    BigInteger n = output.take();
                    debug.println("    " + n);
                    if (n.equals(ONE)) {
                        res++;
                        map[i][j] = '#';
                    }

                }
            }

            Utils.stream(map).forEachOrdered(a -> debug.println(new String(a)));

            out.println(res);


        } catch (InterruptedException e) {
            e.printStackTrace();
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
        buildAndRun(D19_1_TractorBeam.class, System.getProperty("user.home") + "/Downloads", "unique_id_D2_1_1202ProgramAlert_02.12.19_15:11_Advent of Code");
    }

    public D19_1_TractorBeam() {
        // we need this so reflection can do its thing
    }
    /* END-NO-BUNDLE */
}