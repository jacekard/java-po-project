import java.util.concurrent.ThreadLocalRandom;

public class Util {
    public static int los(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b+1);
    }
}
