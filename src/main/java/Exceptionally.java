import java.util.concurrent.CompletableFuture;

/**
 * 只有CompletableFuture执行出现异常才会进入exceptionally，并返回一个自定义值
 */
public class Exceptionally {

    public static void main(String[] args) {
//        test(0);
        test(1);

    }

    private static void test(int tt) {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> 1).thenApply(i->i/tt).exceptionally(ex -> -1);
        System.out.println(exceptionally.join());
    }
}
