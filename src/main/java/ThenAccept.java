import java.util.concurrent.CompletableFuture;

/**
 * 接受上个CompletableFuture传入的参数但不返回新的CompletableFuture
 */
public class ThenAccept {

    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> 1).thenAccept(t -> System.out.println(t));
        future.join();
    }
}
