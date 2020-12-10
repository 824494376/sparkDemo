public class UserThreadLocal {
    private String str = "";
    public String getStr() {return str;}
    public void setStr(String j) {this.str = j;}
    public static void main(String[] args) {
        final UserThreadLocal userThreadLocal = new UserThreadLocal();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (UserThreadLocal.class) {
                        userThreadLocal.setStr(Thread.currentThread().getName() + "的数据");
                        System.out.println(Thread.currentThread().getName() + " 编号 " + userThreadLocal.getStr());
                    }
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}