import java.util.concurrent.CompletableFuture;

/**
 * 用于异常回调处理，返回一个CompletableFuture，并且将管道的异常状态恢复，不管出不出异常都会进入
 */
public class Handle {

    public static void main(String[] args) {
        test(0);
//        test(1);
    }

    private static void test(int tt) {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> 1).thenApply(t -> t /tt).handle((t, ex) -> {
            if (ex != null) {
                System.out.println("exception occurred "+ex.getMessage());
                return 100;
            }
            return t + 1;
        }).exceptionally(ex-> -100);
        System.out.println(handle.join());
    }
}
