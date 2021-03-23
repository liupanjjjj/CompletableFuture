import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * complete方法返回CompletableFuture是否执行完毕，若执行完毕则get返回执行结果，否则返回complete方法设置的默认值，一般用于Async方法
 */
public class Complete {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1).thenApplyAsync(t -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t;
        });
        //join等待future执行完毕，若注释此行程序运行结果为100
        future.join();
        future.complete(100);
        System.out.println(future.get());

    }
}
