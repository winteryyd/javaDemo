package com.deppon.demo.java.threadPool;
/**
 * 
 * @author 266372
 *
 */
public interface ThreadPool{
    // 执行一个Job，这个Job需要实现Runnable
    void execute(Job job);
    // 执行num个Job，这个Job需要实现Runnable
    void addJob(int num);
    // 关闭线程池
    void shutdown();
    // 增加工作者线程
    void addWorkers(int num);

    // 减少工作者线程
    void removeWorker(int num);

    // 得到正在等待执行的任务数量
    int getJobSize();
    // 得到 工作者线程的数量
    int getWorkerNum();
}
