/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.android.layoutlib.api;

/**
 * Scene result class.
 */
public class SceneResult {

    private final SceneStatus mStatus;
    private final String mErrorMessage;
    private final Throwable mThrowable;

    public enum SceneStatus {
        SUCCESS,
        NOT_IMPLEMENTED,
        ERROR_TIMEOUT,
        ERROR_LOCK_INTERRUPTED,
        ERROR_UNKNOWN;

        /**
         * Returns a {@link SceneResult} object with this status.
         * @return an instance of SceneResult;
         */
        public SceneResult getResult() {
            // don't want to get generic error that way.
            assert this != ERROR_UNKNOWN;

            if (this == SUCCESS) {
                return SceneResult.SUCCESS;
            }

            return new SceneResult(this);
        }
    }

    /**
     * Singleton SUCCESS {@link SceneResult} object.
     */
    public static final SceneResult SUCCESS = new SceneResult(SceneStatus.SUCCESS);

    /**
     * Creates a {@link SceneResult} object, with {@link SceneStatus#ERROR_UNKNOWN} status, and
     * the given message.
     */
    public SceneResult(String errorMessage) {
        mStatus = SceneStatus.ERROR_UNKNOWN;
        mErrorMessage = errorMessage;
        mThrowable = null;
    }

    /**
     * Creates a {@link SceneResult} object, with {@link SceneStatus#ERROR_UNKNOWN} status, and
     * the given message and {@link Throwable}
     */
    public SceneResult(String errorMessage, Throwable t) {
        mStatus = SceneStatus.ERROR_UNKNOWN;
        mErrorMessage = errorMessage;
        mThrowable = t;
    }

    /**
     * Creates a{@link SceneResult} object with the given SceneStatus.
     *
     * @param status the status
     */
    private SceneResult(SceneStatus status) {
        mStatus = status;
        mErrorMessage = null;
        mThrowable = null;
    }

    /**
     * Returns the status. This is never null.
     */
    public SceneStatus getStatus() {
        return mStatus;
    }

    /**
     * Returns the error message. This is only non-null when {@link #getStatus()} returns
     * {@link SceneStatus#ERROR_UNKNOWN}
     */
    public String getErrorMessage() {
        return mErrorMessage;
    }

    /**
     * Returns the exception. This is only non-null when {@link #getStatus()} returns
     * {@link SceneStatus#ERROR_UNKNOWN}
     */
    public Throwable getException() {
        return mThrowable;
    }
}