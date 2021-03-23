import java.util.concurrent.CompletableFuture;

/**
 * 不接受上个CompletableFuture传入的参数也不返回新的CompletableFuture
 */
public class ThenRun {

    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 1).thenRun(() -> System.out.println(1));
        future.join();
    }
}
