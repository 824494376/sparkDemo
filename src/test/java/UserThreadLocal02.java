public class UserThreadLocal02 {
    static ThreadLocal<String> str=new ThreadLocal<String>();
    public String getStr() {return str.get();}
    public void setStr(String j) {str.set(j);}
    public static void main(String[] args) {
        final UserThreadLocal02 userThreadLocal = new UserThreadLocal02();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                        userThreadLocal.setStr(Thread.currentThread().getName() + "的数据");
                        System.out.println(Thread.currentThread().getName() + " 编号 " + userThreadLocal.getStr());
                    }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
