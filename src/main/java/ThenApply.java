import java.util.concurrent.CompletableFuture;

/**
 * 改变CompletableFuture返回的类型
 */
public class ThenApply {

    public static void main(String[] args) {
        //Integer -> String
        CompletableFuture<String> applyAsync = CompletableFuture.supplyAsync(() -> 1).thenApplyAsync((t)-> t+"str");
        System.out.println(applyAsync.join());
    }


}
