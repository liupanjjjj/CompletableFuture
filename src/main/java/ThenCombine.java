import java.util.concurrent.CompletableFuture;

/**
 * 任务聚合
 * <p>注意点</p>
 * <ul>
 *     <li>thenCombineAsync操作是非阻塞的</li>
 *     <li>聚合操作时，内部任务合并操作是串行的</li>
 *     <li>由于其内部实现属于是串行的，该api在重io等阻塞严重的场景下慎用</li>
 *     <li>大量聚合(嵌套)操作的性能存在问题</li>
 *     <li>cpu密集型任务 该聚合方案也不是最优选择</li>
 * </ul>
 */
public class ThenCombine {

    public static void main1(String[] args) {
        //嵌套聚合 计算1+2+3的例子 能明显看出其串行执行的过程
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() ->  1);
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> 2).thenCombineAsync(first, (t, p) ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算 "+p+"+"+t);
            return t + p;
        } );
        CompletableFuture<Integer> last = CompletableFuture.supplyAsync(() -> 3).thenCombineAsync(second, (t, p) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算 "+p+"+"+t);
            return t + p;
        });

        long pointCut = System.currentTimeMillis();
        System.out.println(last.join());
        System.out.println("cost:"+(System.currentTimeMillis()-pointCut));

    }

}
