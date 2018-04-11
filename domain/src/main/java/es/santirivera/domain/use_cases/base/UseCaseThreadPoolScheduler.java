/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.santirivera.domain.use_cases.base;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class UseCaseThreadPoolScheduler implements UseCaseScheduler {

	private static final int POOL_SIZE = 2;
	private static final int MAX_POOL_SIZE = 10;
	private static final int MAX_QUEUE_SIZE = 150;
	private static final int TIMEOUT = 600;
	private final Handler mHandler;
	private ThreadPoolExecutor mThreadPoolExecutor;

	@Inject
	public UseCaseThreadPoolScheduler() {
		this(new Handler(Looper.getMainLooper()));
	}

	public UseCaseThreadPoolScheduler(Handler handler) {
		mHandler = handler;
		mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE));
	}

	@Override
	public Future execute(Runnable runnable) {
		return mThreadPoolExecutor.submit(runnable);
	}

	@Override
	public <Result> void onResponseSuccess(final String tag, final Result response, final UseCaseTaggedCallback<Result, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onSuccess(tag, response);
			}
		});
	}

	@Override
	public <Err extends StringErrorOutput> void onError(final String tag, final Err error, final UseCaseTaggedCallback<?, Err> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onError(tag, error);
			}
		});
	}

	@Override
	public void onGenericError(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onGenericError(tag);
			}
		});
	}

	@Override
	public void onNetworkUnavailable(final String tag, final UseCaseTaggedCallback<?, ?> useCaseCallback) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				useCaseCallback.onNetworkUnavailable(tag);
			}
		});
	}
}
