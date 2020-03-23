package thread;

class MyData{

    int number=0;

    public void addNumber(){
        this.number = 60;
    }
}

/**
 * 1验证volatile可见性
 * 1.1 假如int age = 0,number变量之前根本没有添加volatile关键字修饰,没有可见性,注意现在高等级版本的jdk1.8不加volatile也有可见性
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addNumber();
            System.out.println(Thread.currentThread().getName()+"\t update value:"+myData.number);

        },"aaa").start();

        //第二个线程
        while (myData.number == 0){
        }
        System.out.println(Thread.currentThread().getName()+"\t over:"+myData.number);
    }
}
