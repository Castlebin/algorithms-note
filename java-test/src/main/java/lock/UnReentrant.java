package lock;

public class UnReentrant {
    
    Lock lock = new Lock();
    
    public void outer() throws InterruptedException {
        lock.lock();
        inner();
        lock.unlock();
    }
    
    public void inner() throws InterruptedException {
        lock.lock();
        //do something
        lock.unlock();
    }
    
    public static void main(String[] args) throws InterruptedException {
        UnReentrant unReentrant = new UnReentrant();
        unReentrant.outer();
    }
    
}
