package cn.ffcs.thread;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by MәӧωρaЯsε on 2017/5/8.
 *
 */
class EventSubscriber implements DisposableBean, Runnable {

    private Thread thread;
    private volatile boolean someCondition;

    EventSubscriber(){
        this.thread = new Thread(this);
    }

    @Override
    public void run(){
        while(someCondition){

            System.out.println("线程");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy(){
        someCondition = false;
    }

}
