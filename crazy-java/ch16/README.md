# 第16章 多线程
## 16.1 线程概述
## 16.2 线程的创建和启动
## 16.3 线程的生命周期
    线程有 新建、就绪、运行、阻塞、死亡 五种状态
    
## 16.4 控制线程
## 16.5 线程同步
## 16.6 线程通信
    重点、难点，此知识点 比较难消化 属于稍高级内容
    - 其实也只是稍微难一点点而已，并没什么
    - 其实挺简单的啊！
    
    1. 使用wait()和notfify()/notifyAll()来实现线程通信
    2. 使用Condition控制线程通信
      使用Lock和Condition，使用Condition类的await()和sign()/signAll()方法
      类似传统方式中同步监视器, wait()和notify()/notifyAll()方法
      使用方式完全类似
    3. 使用阻塞队列（BlockingQueue）控制线程通信 (太方便了！)
      BlockingQueue主要用来做线程通信使用，提供两个阻塞方法，分别是put()和take()
      put(E e): 尝试将元素放入BlockingQueue中，如果该队列已满，则线程阻塞
      take(): 尝试从BlockingQueue中取出元素，如果该队列为空，则线程阻塞

## 16.7 线程组和未处理的异常
    简单
    
    - ThreadGroup的使用方法
    - 为Thread注册异常处理Handler

## 16.8 线程池
