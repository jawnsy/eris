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
 * Linear congruential generator implementation.
 *
 * <p>The Linear Congruential Generator (LCG) is a very simple algorithm for producing random
 * numbers.  It generates a stream of numbers using the recurrence relation:
 *
 * <p>X<sub>n+1</sub> = (aX<sub>n</sub> + C) mod m
 *
 * <p>where X is the sequence of pseudorandom values, and
 *
 * <ul><li>m, 0 &lt; m - the "modulus"
 * </li><li>a, 0 &lt; a &lt; m - the "multiplier"
 * </li><li>c, 0 &lt;= c &lt; m - the "increment"
 * </li></ul>
 *
 * <p>This algorithm is simple to implement, requires minimal state, and requires very few machine
 * instructions to operate.  Although the generated sequence appears to be random upon casual
 * inspection, it is trivial to derive the internal state by observing the algorithm's output; in
 * other words, the algorithm is <strong>not
 * <a href="https://en.wikipedia.org/wiki/Cryptographically_secure_pseudorandom_number_generator">
 * cryptographically secure</a></strong>.  On modern systems, <strong>there is almost never any
 * reason to choose this algorithm</strong>, except perhaps for compatibility with the built-in
 * generators available in other languages.
 *
 * <p>The <em>modulus</em> determines the maximum value output by the generator and thus limits the
 * number of usable bits in the output.  For example, if the modulus is 2<sup>32</sup>, then the
 * 32 high-order bits will always be zero for the lifetime of the generator.
 */
public final class LinearCongruentialGenerator implements RandomGenerator {
  /* These are the parameters devised by Donald Knuth for his MMIX processor */
  private static final long DEFAULT_MODULUS = 0xFFFFFFFF_FFFFFFFFL;
  private static final long DEFAULT_MULTIPLIER = 6364136223846793005L;
  private static final long DEFAULT_INCREMENT = 1442695040888963407L;

  /**
   * The modulus ({@code m}).  A larger value is typically better, since it results in more usable
   * bits of output.  Its value must be larger than both the multiplier ({@code a}) and
   * increment ({@code c}).
   */
  private final long modulus;

  /**
   * The multiplier ({@code a}).  Its value must be non-zero and less than the modulus.
   */
  private final long multiplier;

  /**
   * The increment ({@code c}).  Its value can be zero (a special case of linear congruential
   * generator called multiplicative congruential generator).  Its value must be less than the
   * modulus.
   */
  private final long increment;

  /**
   * The "seed" or "start value" is the next number in the sequence that will be returned.  It
   * represents the entire internal state of the generator.
   */
  private long seed;

  /**
   * Construct an instance with the given seed value.  The default parameters may change in
   * subsequent releases, hence the generated sequence is subject to change; however, the default
   * values are based on the generator devised by Donald Knuth for his MMIX processor.
   *
   * @param seed The "seed" or "start value", which is also the next number in the sequence that
   *             will be returned.  It represents the entire internal state of the generator.
   */
  public LinearCongruentialGenerator(final long seed) {
    this(DEFAULT_MODULUS, DEFAULT_MULTIPLIER, DEFAULT_INCREMENT, seed);
  }

  /**
   * Construct an instance with the given parameters and seed value.
   *
   * @param modulus The modulus ({@code m}).  A larger value is typically better, since it results
   *                in more usable bits of output. Its value must be larger than both the
   *                multiplier ({@code a}) and increment ({@code c}).
   * @param multiplier The multiplier ({@code a}).  Its value must be non-zero and less than the
   *                   modulus.
   * @param increment The increment ({@code c}).  Its value can be zero (a special case of linear
   *                  congruential generator called multiplicative congruential generator).  Its
   *                  value must be less than the modulus.
   * @param seed The "seed" or "start value", which is also the next number in the sequence that
   *             will be returned.  It represents the entire internal state of the generator.
   */
  public LinearCongruentialGenerator(final long modulus, final long multiplier,
      final long increment, final long seed)
  {
    this.modulus = modulus;
    this.multiplier = multiplier;
    this.increment = increment;
    this.seed = seed;
  }

  /**
   * Returns the next uniformly distributed {@code double} value in the sequence.  The general
   * contract is that all numbers between {@code 0.0} and {@code 1.0} should occur with equal
   * probability.
   *
   * <p>The number of usable bits depends on the chosen <em>modulus</em>.
   *
   * @return the next {@code long} value in the sequence.
   */
  @Override
  public long nextLong() {
    long result = seed;
    seed = (multiplier * seed + increment) % modulus;
    return result;
  }
}
