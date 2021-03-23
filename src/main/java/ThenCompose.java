import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * 也可以改变CompletableFuture返回类型，但返回一个新的CompletableFuture。与thenApply的区别是，thenCompose使得调用链扁平化，方便我们调用链使用封装（第三方）的异步方法（见代码注释）
 */
public class ThenCompose {

    public static void main(String[] args) {
        CompletableFuture<String> compose = CompletableFuture.supplyAsync(() -> 1).thenCompose(t -> CompletableFuture.supplyAsync(() -> t+"str"));
        //thenCompose 直接调用composeTest方法返回一个扁平化的CompletableFuture
        CompletableFuture<String> thenCompose = CompletableFuture.supplyAsync(() -> 1).thenCompose(ThenCompose::composeTest);
        //我们使用thenApply测试 发现返回了一个嵌套的结果，这既不美观也不符合我们的预期
        CompletableFuture<CompletionStage<String>> thenApply = CompletableFuture.supplyAsync(() -> 1).thenApply(ThenCompose::composeTest);
        //若要使用thenApply达到thenCompose相同的效果我们需要对方法进行修改
        CompletableFuture<String> thenApply2 = CompletableFuture.supplyAsync(()->1).thenApply(ThenCompose::applyTest);
    }

    /**
     * 同步方法
     * @param t
     * @return
     */
    private static String applyTest(Integer t) {
        return t+"str";
    }

    /**
     * 异步方法
     * @param t
     * @return
     */
    private static CompletionStage<String> composeTest(Integer t) {
        return CompletableFuture.supplyAsync(()->t+"str");
    }

}
