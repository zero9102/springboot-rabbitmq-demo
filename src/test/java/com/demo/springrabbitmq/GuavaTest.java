package com.demo.springrabbitmq;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 *  guava executor test..
 */
public class GuavaTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService listeningExecutorService = MoreExecutors.newDirectExecutorService();
        listeningExecutorService.submit(() -> {
            System.out.printf("hello, test...");
        });


        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);

        ExecutorService moreExecutor = MoreExecutors.getExitingExecutorService(executor, 100,
                TimeUnit.MILLISECONDS);

        moreExecutor.submit(() -> {
            while (true) {

            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService listeningExecutorService1 = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> future1 = listeningExecutorService1.submit(() -> "simple");
        ListenableFuture<String> future2 = listeningExecutorService1.submit(() -> "complex2");

        String ret = Futures.allAsList(future1, future2).get().stream().collect(Collectors.joining(""));
        System.out.printf(ret);

        ConcurrentHashMap<String, LongAdder> curMap = new ConcurrentHashMap();
        curMap.computeIfAbsent("1", k -> new LongAdder()).increment();

    }
}
