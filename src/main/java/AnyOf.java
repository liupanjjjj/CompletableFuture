import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 多个线程取执行最快的执行下一步
 *
 * <p>注意点：</p>
 * <ul>
 *     <li>线程池参数缺省时默认使用ForkJoinPool.commonPool()</li>
 *     <li>线程池参数缺省时，任务以守护线程的形式运行，要改变这点可以手动指定其他线程池</li>
 * </ul>
 *
 *
 */
public class AnyOf {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1 号任务执行完成\t"+Thread.currentThread().isDaemon());
            return "1";
        });
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2 号任务执行完成\t"+Thread.currentThread().isDaemon());
            return "2";
        });
        CompletableFuture<String> third = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3 号任务执行完成\t"+Thread.currentThread().isDaemon());
            return "3";
        });
        CompletableFuture.anyOf(first, second, third).join();

        //主线程的结束会导致守护线程结束，即子线程强制结束，2，3任务并不会完成
    }

}
