## 队列同步器AbstractQueuedSynchronizer

### 独占式

- 获取独占锁
  - 首先根据tryAcquire线程安全获取独占锁
  - 获取失败，则通过addWaiter将该节点加入同步队列尾部
  - 调用acquireQueued，以死循环方式获取独占锁（获取不到则阻塞节点中线程，被阻塞的线程唤醒需依赖前驱节点出队货阻塞队列被中断）

```java
/**
 * Acquires in exclusive mode, ignoring interrupts.  Implemented
 * by invoking at least once {@link #tryAcquire},
 * returning on success.  Otherwise the thread is queued, possibly
 * repeatedly blocking and unblocking, invoking {@link
 * #tryAcquire} until success.  This method can be used
 * to implement method {@link Lock#lock}.
 *
 * @param arg the acquire argument.  This value is conveyed to
 *        {@link #tryAcquire} but is otherwise uninterpreted and
 *        can represent anything you like.
 */
@ReservedStackAccess
public final void acquire(int arg) {
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

- 独占式添加节点（快插：已有节点且一次插入成功）

```java
/**
 * Creates and enqueues node for current thread and given mode.
 *
 * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
 * @return the new node
 */
private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    enq(node);
    return node;
}
```

- 独占式入队
  - 构造了一个虚拟的头节点（该节点获取锁）
  - compareAndSetTail：以CAS方式添加节点（自旋+原子）

```java
/**
 * Inserts node into queue, initializing if necessary. See picture above.
 * @param node the node to insert
 * @return node's predecessor
 */
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        if (t == null) { // Must initialize
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

- 独占式获取独占锁（自旋）
  - 只有前去节点时头节点才能够获取
    - 头节点是成功获取独占锁的节点，头节点线程释放独占锁后，将唤醒后继节点，后继节点被唤醒需要检查自己前驱节点是否是头节点
    - 维护同步队列的FIFO原则

```java

/**
 * Acquires in exclusive uninterruptible mode for thread already in
 * queue. Used by condition wait methods as well as acquire.
 *
 * @param node the node
 * @param arg the acquire argument
 * @return {@code true} if interrupted while waiting
 */
@ReservedStackAccess
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            // 获取前驱节点
            final Node p = node.predecessor();
          	// 前驱节点是否为头节点（虚拟），尝试获取独占锁
            if (p == head && tryAcquire(arg)) {
              	// 当前节点置为头节点
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
          	// 独占锁获取失败后，node节点是否应阻塞（根据前驱节点的waitStatus判断）
            if (shouldParkAfterFailedAcquire(p, node) &&
                // 阻塞当前线程（前驱节点为Node.SIGNAL状态时）
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

- 节点状态
  - CANCELLED：当前线程已取消
  - SIGNAL：当前节点的后继节点线程需唤醒
  - CONDITION：当前线程正字等待condition
  - PROPAGATE：下一个acquireShared应无条件传播

```java
/** waitStatus value to indicate thread has cancelled */
static final int CANCELLED =  1;
/** waitStatus value to indicate successor's thread needs unparking */
static final int SIGNAL    = -1;
/** waitStatus value to indicate thread is waiting on condition */
static final int CONDITION = -2;
/**
 * waitStatus value to indicate the next acquireShared should
 * unconditionally propagate
 */
static final int PROPAGATE = -3;
```



- 判断当前节点所属线程是否应阻塞
  - 一般首次（0）将前驱节点设置成Node.SIGNAL，第二次自旋时才阻塞当前节点

```java
/**
 * Checks and updates status for a node that failed to acquire.
 * Returns true if thread should block. This is the main signal
 * control in all acquire loops.  Requires that pred == node.prev.
 *
 * @param pred node's predecessor holding status
 * @param node the node
 * @return {@code true} if thread should block
 */
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
  	// 前驱节点为Node.SIGNAL（-1），应阻塞
    if (ws == Node.SIGNAL)
        /*
         * This node has already set status asking a release
         * to signal it, so it can safely park.
         */
        return true;
  	// 前驱节点已取消，跳过前驱节点（知道前驱节点未取消）
    if (ws > 0) {
        /*
         * Predecessor was cancelled. Skip over predecessors and
         * indicate retry.
         */
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {	// 此时一定是0或PROPAGATE，表明需要传播（前驱节点值为Node.SIGNAL），但不需要阻塞
        /*
         * waitStatus must be 0 or PROPAGATE.  Indicate that we
         * need a signal, but don't park yet.  Caller will need to
         * retry to make sure it cannot acquire before parking.
         */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}

```

- 释放独占式锁

```java
/**
 * Releases in exclusive mode.  Implemented by unblocking one or
 * more threads if {@link #tryRelease} returns true.
 * This method can be used to implement method {@link Lock#unlock}.
 *
 * @param arg the release argument.  This value is conveyed to
 *        {@link #tryRelease} but is otherwise uninterpreted and
 *        can represent anything you like.
 * @return the value returned from {@link #tryRelease}
 */
@ReservedStackAccess
public final boolean release(int arg) {
    if (tryRelease(arg)) {
        Node h = head;
      	// h.waitStatus != 0:说明被其他线程修改过（被其后继节点修改成了Node.SIGNAL）
        if (h != null && h.waitStatus != 0)
          	// 唤醒其后继节点
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```



### 共享式

- 获取共享式共享锁

```java
/**
 * Acquires in shared uninterruptible mode.
 * @param arg the acquire argument
 */
private void doAcquireShared(int arg) {
    final Node node = addWaiter(Node.SHARED);
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
          	// 获取前驱节点
            final Node p = node.predecessor();
          	// 前驱节点为头节点
            if (p == head) {
              	// 尝试获取共享式共享锁
                int r = tryAcquireShared(arg);
              	// 获取共享式共享锁成功
                if (r >= 0) {
                  	// 设置头指针，继续唤醒后继节点（因为是共享锁，后继节点可能获取共享锁）
                    setHeadAndPropagate(node, r);
                    p.next = null; // help GC
                    if (interrupted)
                        selfInterrupt();
                    failed = false;
                    return;
                }
            }
          	// 可能因为获取不到共享锁而阻塞
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```



- 设置头指针，和节点状态
  - propagata
    - propagate > 0，还有剩余共享锁可以获取
    - propagate = 0，本次获取共享锁成功后，没有共享锁可以获取

```java
/**
 * Sets head of queue, and checks if successor may be waiting
 * in shared mode, if so propagating if either propagate > 0 or
 * PROPAGATE status was set.
 *
 * @param node the node
 * @param propagate the return value from a tryAcquireShared
 */
private void setHeadAndPropagate(Node node, int propagate) {
  	// 保留旧的头节点
    Node h = head; // Record old head for check below
  	// 设置当前节点为头节点
    setHead(node);
    /*
     * Try to signal next queued node if:
     *   Propagation was indicated by caller,
     *     or was recorded (as h.waitStatus either before
     *     or after setHead) by a previous operation
     *     (note: this uses sign-check of waitStatus because
     *      PROPAGATE status may transition to SIGNAL.)
     * and
     *   The next node is waiting in shared mode,
     *     or we don't know, because it appears null
     *
     * The conservatism in both of these checks may cause
     * unnecessary wake-ups, but only when there are multiple
     * racing acquires/releases, so most need signals now or soon
     * anyway.
     */
  	// propagate > 0 ： 本次获取共享锁成功后，还有剩余共享锁可以获取
  	// h.waitStatus < 0：旧头节点表明需要唤醒后继节点
  	// h.waitStatus < 0：新头节点表明需要唤醒后继节点
    if (propagate > 0 || h == null || h.waitStatus < 0 ||
        (h = head) == null || h.waitStatus < 0) {
        Node s = node.next;
        if (s == null || s.isShared())
            doReleaseShared();
    }
}
```

- 释放共享锁

```java
/**
 * Releases in shared mode.  Implemented by unblocking one or more
 * threads if {@link #tryReleaseShared} returns true.
 *
 * @param arg the release argument.  This value is conveyed to
 *        {@link #tryReleaseShared} but is otherwise uninterpreted
 *        and can represent anything you like.
 * @return the value returned from {@link #tryReleaseShared}
 */
@ReservedStackAccess
public final boolean releaseShared(int arg) {
  	// cas自旋释放共享锁
    if (tryReleaseShared(arg)) {
      	// cas自旋释放同步等待状态
        doReleaseShared();
        return true;
    }
    return false;
}
```

- cas自旋释放同步等待状态

```java
private void doReleaseShared() {
    /*
     * Ensure that a release propagates, even if there are other
     * in-progress acquires/releases.  This proceeds in the usual
     * way of trying to unparkSuccessor of head if it needs
     * signal. But if it does not, status is set to PROPAGATE to
     * ensure that upon release, propagation continues.
     * Additionally, we must loop in case a new node is added
     * while we are doing this. Also, unlike other uses of
     * unparkSuccessor, we need to know if CAS to reset status
     * fails, if so rechecking.
     */
    for (;;) {
        Node h = head;
      	// 头节点不为空，切不为最后节点（说明还存在等待的节点）
        if (h != null && h != tail) {
          	// 头节点状态
            int ws = h.waitStatus;
          	// Node.SIGNAL：需要唤醒后继节点
            if (ws == Node.SIGNAL) {
              	// 节点等待状态未cas成功，继续循环
                if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                    continue;            // loop to recheck cases
              	// 节点等待状态cas成功，唤醒后继节点
                unparkSuccessor(h);
            }
          	// 头节点状态为初始状态，则设置头节点状态为Node.PROPAGATE
            else if (ws == 0 &&   
                     !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                continue;                // loop on failed CAS
        }
      	// 不存在未节点，直接跳出循环
        if (h == head)                   // loop if head changed
            break;
    }
}
```

- 唤醒后继节点

```java
/**
 * Wakes up node's successor, if one exists.
 *
 * @param node the node
 */
private void unparkSuccessor(Node node) {
    /*
     * If status is negative (i.e., possibly needing signal) try
     * to clear in anticipation of signalling.  It is OK if this
     * fails or if status is changed by waiting thread.
     */
    int ws = node.waitStatus;
  	// 节点等待状态 < 0，尝试清除等待状态
    if (ws < 0)
        compareAndSetWaitStatus(node, ws, 0);

    /*
     * Thread to unpark is held in successor, which is normally
     * just the next node.  But if cancelled or apparently null,
     * traverse backwards from tail to find the actual
     * non-cancelled successor.
     */
  	// 后继节点为空或被取消（waitStatus > 0），则从队列尾部获取一个（waitStatus <= 0）的节点
    Node s = node.next;
    if (s == null || s.waitStatus > 0) {
        s = null;
        for (Node t = tail; t != null && t != node; t = t.prev)
            if (t.waitStatus <= 0)
                s = t;
    }
    if (s != null)
        LockSupport.unpark(s.thread);
}
```

### 独占式超时获取同步状态

- 独占式超时获取同步状态

```java

/**
 * Acquires in exclusive timed mode.
 *
 * @param arg the acquire argument
 * @param nanosTimeout max wait time
 * @return {@code true} if acquired
 */
private boolean doAcquireNanos(int arg, long nanosTimeout)
        throws InterruptedException {
    if (nanosTimeout <= 0L)
        return false;
  	// 超时的时间（多少纳秒的时候超时）
    final long deadline = System.nanoTime() + nanosTimeout;
    final Node node = addWaiter(Node.EXCLUSIVE);
    boolean failed = true;
    try {
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return true;
            }
          	// 超时剩余的时间（还有多少纳秒超时）
            nanosTimeout = deadline - System.nanoTime();
          	// 已超时
            if (nanosTimeout <= 0L)
                return false;
          	// 超时剩余的时间大于spinForTimeoutThreshold（1000）才阻塞：快速自旋，避免因阻塞导致不准确
            if (shouldParkAfterFailedAcquire(p, node) &&
                nanosTimeout > spinForTimeoutThreshold)
                LockSupport.parkNanos(this, nanosTimeout);
            if (Thread.interrupted())
                throw new InterruptedException();
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```



## ReentrantLock

### 可重入

- 可重入获取锁（非公平）

```java

/**
 * Performs non-fair tryLock.  tryAcquire is implemented in
 * subclasses, but both need nonfair try for trylock method.
 */
@ReservedStackAccess
final boolean nonfairTryAcquire(int acquires) {
  	// 当前线程
    final Thread current = Thread.currentThread();
    int c = getState();
  	// 未被线程获取同步状态
    if (c == 0) {
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
  	// 被线程获取了同步状态，且为当前线程
    else if (current == getExclusiveOwnerThread()) {
      	// 已有多少次重入
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

- 释放同步状态
  - 若锁被重入获取了n次，则前n-1次返回false，只有同步状态完成释放了，才能返回true

```java
@ReservedStackAccess
protected final boolean tryRelease(int releases) {
  	// 减少同步状态值
    int c = getState() - releases;
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
  	// 是否完全释放（不再有改线程持有）  
  	boolean free = false;
    if (c == 0) {
        free = true;
        setExclusiveOwnerThread(null);
    }
    setState(c);
    return free;
}
```

### 公平与非公平

- 公平获取锁

```java
/**
 * Fair version of tryAcquire.  Don't grant access unless
 * recursive call or no waiters or is first.
 */
@ReservedStackAccess
protected final boolean tryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
      	// 存在前驱节点，需要等待前驱节点获取并释放之后才能获取同步状态
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0)
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

- 同步队列中当前节点是否有前驱节点

```java
/**
 * Queries whether any threads have been waiting to acquire longer
 * than the current thread.
 *
 * <p>An invocation of this method is equivalent to (but may be
 * more efficient than):
 *  <pre> {@code
 * getFirstQueuedThread() != Thread.currentThread() &&
 * hasQueuedThreads()}</pre>
 *
 * <p>Note that because cancellations due to interrupts and
 * timeouts may occur at any time, a {@code true} return does not
 * guarantee that some other thread will acquire before the current
 * thread.  Likewise, it is possible for another thread to win a
 * race to enqueue after this method has returned {@code false},
 * due to the queue being empty.
 *
 * <p>This method is designed to be used by a fair synchronizer to
 * avoid <a href="AbstractQueuedSynchronizer#barging">barging</a>.
 * Such a synchronizer's {@link #tryAcquire} method should return
 * {@code false}, and its {@link #tryAcquireShared} method should
 * return a negative value, if this method returns {@code true}
 * (unless this is a reentrant acquire).  For example, the {@code
 * tryAcquire} method for a fair, reentrant, exclusive mode
 * synchronizer might look like this:
 *
 *  <pre> {@code
 * protected boolean tryAcquire(int arg) {
 *   if (isHeldExclusively()) {
 *     // A reentrant acquire; increment hold count
 *     return true;
 *   } else if (hasQueuedPredecessors()) {
 *     return false;
 *   } else {
 *     // try to acquire normally
 *   }
 * }}</pre>
 *
 * @return {@code true} if there is a queued thread preceding the
 *         current thread, and {@code false} if the current thread
 *         is at the head of the queue or the queue is empty
 * @since 1.7
 */
public final boolean hasQueuedPredecessors() {
    // The correctness of this depends on head being initialized
    // before tail and on head.next being accurate if the current
    // thread is first in queue.
    Node t = tail; // Read fields in reverse initialization order
    Node h = head;
    Node s;
  	// h != t：队列为空
  	// (s = h.next) == null：不存在节点
  	// s.thread != Thread.currentThread()：下一个运行节点的线程 与 当前获取同步状态不一致(添加节点时，把创建节点的线程保存在了节点中)，即下一个节点运行的线程与当前唤醒节点的线程不一致时，获取线程状态失败
    return h != t &&
        ((s = h.next) == null || s.thread != Thread.currentThread());
}
```

## ReentrantReadWriteLock

### 写锁获取与释放

```java
protected final boolean tryAcquire(int acquires) {
    /*
     * Walkthrough:
     * 1. If read count nonzero or write count nonzero
     *    and owner is a different thread, fail.
     * 2. If count would saturate, fail. (This can only
     *    happen if count is already nonzero.)
     * 3. Otherwise, this thread is eligible for lock if
     *    it is either a reentrant acquire or
     *    queue policy allows it. If so, update state
     *    and set owner.
     */
  	// 获取当前线程
    Thread current = Thread.currentThread();
  	// 获取同步状态值
    int c = getState();
  	// 获取读状态值（c的低十六位）
    int w = exclusiveCount(c);
    if (c != 0) {
        // (Note: if c != 0 and w == 0 then shared count != 0)
      	// 同步状态不为0，写状态为0（即有线程获取了读锁），或获取同步状态线程不为当前线程
        if (w == 0 || current != getExclusiveOwnerThread())
            return false;
      	// 此时写状态不为0（即有线程获取了写锁）且本线程获取了同步状态
        if (w + exclusiveCount(acquires) > MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
        // Reentrant acquire
      	// 写重入
        setState(c + acquires);
        return true;
    }
  	// 同步状态为0（没有线程获取同步状态：读写锁）
    if (writerShouldBlock() ||
        !compareAndSetState(c, c + acquires))	// cas设置同步状态成功
        return false;
  	// 当前线程获取同步状态
    setExclusiveOwnerThread(current);
    return true;
}
```

#### 公平锁

```java
/**
 * Fair version of Sync
 */
static final class FairSync extends Sync {
  	// 公平锁根据是否存在排队线程阻塞
    final boolean writerShouldBlock() {
        return hasQueuedPredecessors();
    }
}
```

#### 非公平锁

```java
/**
 * Nonfair version of Sync
 */
static final class NonfairSync extends Sync {
  	// 非公平锁直接不阻塞
    final boolean writerShouldBlock() {
        return false; // writers can always barge
    }
}
```



### 读锁获取与释放

```java
protected final int tryAcquireShared(int unused) {
    /*
     * Walkthrough:
     * 1. If write lock held by another thread, fail.
     * 2. Otherwise, this thread is eligible for
     *    lock wrt state, so ask if it should block
     *    because of queue policy. If not, try
     *    to grant by CASing state and updating count.
     *    Note that step does not check for reentrant
     *    acquires, which is postponed to full version
     *    to avoid having to check hold count in
     *    the more typical non-reentrant case.
     * 3. If step 2 fails either because thread
     *    apparently not eligible or CAS fails or count
     *    saturated, chain to version with full retry loop.
     */
  	// 当前线程
    Thread current = Thread.currentThread();
  	// 同步状态
    int c = getState();
  	// 写锁状态不为0，且获取写锁的线程非当前线程
    if (exclusiveCount(c) != 0 &&
        getExclusiveOwnerThread() != current)
        return -1;
  	// 此时写锁状态为0，或获取写锁的线程为当前线程
  	// 获取读锁状态（高十六位）
    int r = sharedCount(c);
    if (!readerShouldBlock() &&
        r < MAX_COUNT &&
        compareAndSetState(c, c + SHARED_UNIT)) {	// cas设置读状态
      	// 此时设置读状态成功（获取了同步状态）
      	// 若原读状态为0:即原先无线程获取读锁
        if (r == 0) {
          	// 记录第一个获取读锁的线程
            firstReader = current;
          	// firstReader持有计数（第一个获取读锁线程重入数？）
            firstReaderHoldCount = 1;
        } else if (firstReader == current) {
          	// 第一个线程重入数增加
            firstReaderHoldCount++;
        } else {	// 此时获取了读锁，但非第一个获取读锁线程
          	// 最后一个获取读锁线程计数（每个线程独有）
            HoldCounter rh = cachedHoldCounter;
          	// 最后一个获取读锁线程计数非空 或 最后一个获取读锁线程非本线程
            if (rh == null || rh.tid != getThreadId(current))
              	// 获取当前线程持有的可重入读锁数（readHolds：构造函数初始化）
                cachedHoldCounter = rh = readHolds.get();
            // 本线程为最后一个获取读锁线程
          	else if (rh.count == 0)
                readHolds.set(rh);
          	// 增加当前线程的可重入读锁数
            rh.count++;
        }
        return 1;
    }
  	//处理cas未命中或可重入读锁
    return fullTryAcquireShared(current);
}
```

#### 完整版获取读锁

```java
/**
 * Full version of acquire for reads, that handles CAS misses
 * and reentrant reads not dealt with in tryAcquireShared.
 */
final int fullTryAcquireShared(Thread current) {
    /*
     * This code is in part redundant with that in
     * tryAcquireShared but is simpler overall by not
     * complicating tryAcquireShared with interactions between
     * retries and lazily reading hold counts.
     */
    HoldCounter rh = null;
  	// cas自旋
    for (;;) {
      	// 同步状态
        int c = getState();
      	// 获取了写锁
        if (exclusiveCount(c) != 0) {
          	// 写锁非当前线程
            if (getExclusiveOwnerThread() != current)
                return -1;
            // else we hold the exclusive lock; blocking here
            // would cause deadlock.
          	// 否则当前线程获取了写锁
        // 此时没有线程获取写锁
        // 读锁是否应阻塞
        } else if (readerShouldBlock()) {
            // Make sure we're not acquiring read lock reentrantly
          	// 第一个获取获取读锁线程为当前线程
            if (firstReader == current) {
                // assert firstReaderHoldCount > 0;
            // 第一个获取获取读锁线程非当前线程，或没有线程获取读锁
            } else {
                if (rh == null) {
                  	// 最后一个获取读锁线程记录
                    rh = cachedHoldCounter;
                    if (rh == null || rh.tid != getThreadId(current)) {
                      	// 获取本线程读锁记录
                        rh = readHolds.get();
                        if (rh.count == 0)
                            readHolds.remove();
                    }
                }
              	// 当前线程重入次数为0
                if (rh.count == 0)
                    return -1;
            }
        }
      	// 读锁
        if (sharedCount(c) == MAX_COUNT)
            throw new Error("Maximum lock count exceeded");
      	// cas获取同步状态（获取读锁）
        if (compareAndSetState(c, c + SHARED_UNIT)) {
          	// 原同步状态为0
            if (sharedCount(c) == 0) {
              	// 记录当前线程为首次获取读锁线程
                firstReader = current;
                firstReaderHoldCount = 1;
            } else if (firstReader == current) {
                firstReaderHoldCount++;
            } else {
                if (rh == null)
                    rh = cachedHoldCounter;
                if (rh == null || rh.tid != getThreadId(current))
                    rh = readHolds.get();
                else if (rh.count == 0)
                    readHolds.set(rh);
                rh.count++;
                cachedHoldCounter = rh; // cache for release
            }
            return 1;
        }
    }
}
```

#### 公平锁

```java
/**
 * Fair version of Sync
 */
static final class FairSync extends Sync {
  	// 公平锁根据是否存在排队线程阻塞
    final boolean readerShouldBlock() {
        return hasQueuedPredecessors();
    }
}
```

#### 非公平锁

```java
/**
 * Nonfair version of Sync
 */  
static final class NonfairSync extends Sync {
    final boolean readerShouldBlock() {
        /* As a heuristic to avoid indefinite writer starvation,
         * block if the thread that momentarily appears to be head
         * of queue, if one exists, is a waiting writer.  This is
         * only a probabilistic effect since a new reader will not
         * block if there is a waiting writer behind other enabled
         * readers that have not yet drained from the queue.
         */
        return apparentlyFirstQueuedIsExclusive();
    }
}
```

##### 

```java

/**
 * Returns {@code true} if the apparent first queued thread, if one
 * exists, is waiting in exclusive mode.  If this method returns
 * {@code true}, and the current thread is attempting to acquire in
 * shared mode (that is, this method is invoked from {@link
 * #tryAcquireShared}) then it is guaranteed that the current thread
 * is not the first queued thread.  Used only as a heuristic in
 * ReentrantReadWriteLock.
 */
// 同步队列存在第一个线程以独占模式等待，为true
final boolean apparentlyFirstQueuedIsExclusive() {
    Node h, s;
    return (h = head) != null &&
        (s = h.next)  != null &&
        !s.isShared()         &&
        s.thread != null;
}
```

