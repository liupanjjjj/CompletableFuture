import java.util.concurrent.CompletableFuture;

/**
 * 等待所有子线程完成任务
 */
public class AllOf {

    public static void main(String[] args) {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1 号任务执行完成");
            return "1";
        });
        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2 号任务执行完成");
            return "2";
        });
        CompletableFuture<String> third = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3 号任务执行完成");
            return "3";
        });
        //调用join方法主线程会在此处阻塞到所有子线程执行完毕
        CompletableFuture.allOf(first,second,third).join();

    }
}
