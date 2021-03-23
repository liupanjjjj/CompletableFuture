import java.util.concurrent.CompletableFuture;

/**
 * WhenComplete用于异常回调处理，无返回值且无法恢复异常。
 * 比如上个stage是异常的那么whenComplete也是异常的，不会影响CompletableStage的状态。
 * 不管出不出异常都会进入
 */
public class WhenComplete {

    public static void main(String[] args) {
        test(0);
//        test(1);
    }

    private static void test(int tt) {
        CompletableFuture.supplyAsync(()->1).thenApply(i->i/tt).whenComplete((result, throwable) -> {
            if(throwable != null){
                System.out.println("exception occurred"+throwable);
            }else{
                System.out.println("no exception occurred");
            }
        }).exceptionally(ex->{
            //发生异常才会进
            return 2;
        }).thenAccept(t-> System.out.println(t));
    }
}
