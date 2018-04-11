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

import android.support.annotation.NonNull;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Request> UseCase input Params
 * @param <Result>  UseCase Result OK output
 * @param <Err>     UseCase Result ERROR output
 */
public abstract class UseCase<Request, Result, Err extends StringErrorOutput> {

	private Request mRequestValues;

	public String getTag() {
		return getClass().getSimpleName();
	}

	void setRequestValues(Request requestValues) {
		mRequestValues = requestValues;
	}

	UseCaseResponse<Result, Err> run() {
		return executeUseCase(mRequestValues);
	}

	@NonNull
	protected abstract UseCaseResponse<Result, Err> executeUseCase(Request requestValues);
}
