package com.spacifii.konstruct.explore.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This service class is used for controlling asynchronous task
 */
//@Service
public class ExploreExecutorService {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void doTask(Runnable runnable){
        executorService.execute(runnable);
    }

}
