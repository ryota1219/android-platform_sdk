/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.eclipse.org/org/documents/epl-v10.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.ide.common.layout;

/**
 * A Pair class is simply a 2-tuple for use in this package. We might want to
 * think about adding something like this to a more central utility place, or
 * replace it by a common tuple class if one exists, or even rewrite the layout
 * classes using this Pair by a more dedicated data structure (so we don't have
 * to pass around generic signatures as is currently done, though at least the
 * construction is helped a bit by the {@link #of} factory method.
 */
class Pair<S,T> {
    private final S mFirst;
    private final T mSecond;

    // Use {@link Pair#of} factory instead since it infers generic types
    private Pair(S first, T second) {
        this.mFirst = first;
        this.mSecond = second;
    }

    /** Return the first item in the pair */
    public S getFirst() {
        return mFirst;
    }

    /** Return the second item in the pair */
    public T getSecond() {
        return mSecond;
    }

    /** Constructs a new pair of the given two objects, inferring generic types. */
    public static <S,T> Pair<S,T> of(S a, T b) {
        return new Pair<S,T>(a,b);
    }

    @Override
    public String toString() {
        return "Pair [first=" + mFirst + ", second=" + mSecond + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mFirst == null) ? 0 : mFirst.hashCode());
        result = prime * result + ((mSecond == null) ? 0 : mSecond.hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (mFirst == null) {
            if (other.mFirst != null)
                return false;
        } else if (!mFirst.equals(other.mFirst))
            return false;
        if (mSecond == null) {
            if (other.mSecond != null)
                return false;
        } else if (!mSecond.equals(other.mSecond))
            return false;
        return true;
    }
}