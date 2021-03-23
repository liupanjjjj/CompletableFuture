import java.util.concurrent.CompletableFuture;

/**
 * 任务的聚合
 * <p>与thencombine的区别是聚合后没有返回值</p>
 */
public class ThenAcceptBoth {

    public static void main(String[] args) {
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() ->  1);
        CompletableFuture<Void> second = CompletableFuture.supplyAsync(() -> 2).thenAcceptBothAsync(first, (t, p) ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算 "+p+"+"+t);
        } );

        long pointCut = System.currentTimeMillis();
        second.join();
        System.out.println("cost:"+(System.currentTimeMillis()-pointCut));
    }

}
