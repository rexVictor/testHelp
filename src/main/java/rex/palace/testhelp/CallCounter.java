/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This file is part of testHelp.
 *
 * TestHelp contains utilities to simplify writing unit tests.
 * Copyright (C) 2015 Matthias Johannes Reimchen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rex.palace.testhelp;

import java.util.concurrent.Callable;

/**
 * A {@link Callable} implementation, which counts how often
 * {@link #call()} is called or throws an Exception if any is set.
 */
public final class CallCounter implements Callable<Integer> {

    /**
     * How often {@link #call()} got called.
     */
    private int callCount = 0;

    /**
     * The Exception {@link #call()} shall throw.
     */
    private Exception exception;

    /**
     * Constructs a new CallCounter with initialCount 0.
     */
    public CallCounter() {
        this(0,null);
    }

    /**
     * Construct a new CallCounter, which will throw the specified Exception,
     * when {@link #call()} is called.
     *
     * @param exception the Exception {@code call()} will throw
     */
    public CallCounter(Exception exception) {
        this(0, exception);
    }

    /**
     * Constructs a new CallCounter with the specified initial call count.
     *
     * @param initialCallCount the initial call count
     */
    public CallCounter(int initialCallCount) {
        this(initialCallCount, null);
    }

    /**
     * Constructs a new CallCounter with the specified initial call count
     * and will throw the specified Exception, when {@link #call()} is called.
     *
     * @param initialCallCount the initial call count
     * @param exception the Exception {@code call()} will throw
     */
    private CallCounter(int initialCallCount, Exception exception) {
        this.callCount = initialCallCount;
        this.exception = exception;
    }

    /**
     * Increases the counter, how often this method got called and returns
     * it or throws an exception if any is set.
     *
     * @return how often this method has been called
     * @throws Exception if any has been set by
     *         {@link #CallCounter(Exception)} or
     *         {@link #setException(Exception)}
     */
    @Override
    public Integer call() throws Exception {
        if (exception != null) {
            throw exception;
        }
        return ++callCount;
    }

    /**
     * Sets the Exception following calls to {@link #call()} shall throw.
     *
     * <p>If null is specified, then {@code call()} will throw no Exception.
     *
     * @param exception the Exception {@code call()} shall throw or null if
     *                  none shall be thrown
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Returns how often {@link #call()} has been called.
     *
     * <p>If this instance got constructed via {@link #CallCounter(int)}, then
     * this method will return the specified value plus how often
     * {@code call()} got called.
     *
     * @return how often {@code call()} got called
     */
    public int getCallCount() {
        return callCount;
    }

}

/* vim:set shiftwidth=4 softtabstop=4 expandtab: */
