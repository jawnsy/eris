/*
 * Copyright 2015 by Jonathan Yu and other contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package ca.imaginary.eris;

/**
 * A service that generates a sequence of unpredictable (random or pseudo-random) numbers.
 *
 * <p>Random numbers are useful in a variety of contexts including cryptography, simulations (such
 * as the Monte Carlo method), and statistics (for selecting random samples).  This interface makes
 * no guarantees as to the thread safety, performance, origin, or quality of random numbers
 * returned. Users are encouraged to review the documentation for each available implementation to
 * determine suitability for a given application.
 *
 * <p><strong>Implementation Notes</strong>:
 *
 * <ul><li>All implementing classes <strong><em>must</em></strong> implement <em>at least one</em>
 * of the {@link #nextInt()} or {@link #nextLong()} methods.  For convenience, appropriate default
 * implementations are supplied for all other methods.
 * </li></ul>
 *
 * @see java.util.Random Random
 * @see java.security.SecureRandom SecureRandom
 */
public interface RandomGenerator {
  /**
   * Returns the next uniformly-distributed {@code boolean} value in the sequence.  The general
   * contract is that {@code true} and {@code false} should occur with (approximately) equal
   * probability.
   *
   * <p><strong>Implementation Note</strong>: The default implementation uses the value of the
   * lowest-order bit returned by a call to {@link #nextInt()}.
   *
   * @return the next {@code boolean} value in the sequence.
   */
  public default boolean nextBoolean() {
    return ((nextInt() & 0x1) == 0x1);
  }

  /**
   * Returns the next uniformly-distributed {@code int} value in the sequence. The general contract
   * is that all numbers between {@link Integer#MIN_VALUE} and {@link Integer#MAX_VALUE} should
   * occur with equal probability.
   *
   * <p><strong>Implementation Note</strong>: The default implementation returns the lower 32 bits
   * returned by a call to {@link #nextLong()}.
   *
   * @return the next {@code int} value in the sequence.
   */
  public default int nextInt() {
    return (int) (nextLong() >>> Integer.SIZE);
  }

  /**
   * Returns the next uniformly-distributed {@code long} value in the sequence.  The general
   * contract is that all numbers between {@link Long#MIN_VALUE} and {@link Long#MAX_VALUE} should
   * occur with equal probability.
   *
   * <p><strong>Implementation Note</strong>: The default implementation combines the result of two
   * invocations to {@link #nextInt()}.
   *
   * @return the next {@code long} value in the sequence.
   */
  public default long nextLong() {
    return (nextInt() << Integer.SIZE) | nextInt();
  }

  /**
   * Returns the next uniformly distributed {@code double} value in the sequence.  The general
   * contract is that all numbers between {@code 0.0} and {@code 1.0} should occur with equal
   * probability.
   *
   * <p><strong>Implementation Note</strong>: The default implementation is implemented by dividing
   * the absolute value returned by a call to {@link #nextLong()} by {@link Long#MAX_VALUE}, as if
   * by: {@code Math.abs(nextLong()) / (double) Long.MAX_VALUE}.
   *
   * @return the next {@code double} value in the sequence.
   */
  public default double nextDouble() {
    return Math.abs(nextLong()) / (double) Long.MAX_VALUE;
  }
}
