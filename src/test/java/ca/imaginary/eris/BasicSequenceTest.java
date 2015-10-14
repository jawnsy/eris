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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Very simplistic checks verifying that the first few numbers emitted by the generator look
 * correct.
 */
public final class BasicSequenceTest
{
  private BasicSequenceTest() {
    // Class instantiated via TestNG using reflection
  }

  @DataProvider(name = "intSequences", parallel = true)
  private Object[][] generators() {
    return new Object[][] {
      /* These three test sequences come from the Wikipedia topic on LCGs */
      {
        new LinearCongruentialGenerator(9, 2, 0, 1),
        new int[] { 1, 2, 4, 8, 7, 5, 1 }
      }, {
        new LinearCongruentialGenerator(9, 2, 0, 3),
        new int[] { 3, 6, 3, 6, 3, 6, 3, 6, 3, 6 }
      }, {
        new LinearCongruentialGenerator(9, 4, 1, 0),
        new int[] { 0, 1, 5, 3, 4, 8, 6, 7, 2, 0 }
      }
    };
  }

  @Test(dataProvider = "intSequences")
  public void testIntegers(final RandomGenerator generator, final int[] expected) {
    for (int i = 0; i < expected.length; i++) {
      assertEquals(generator.nextInt(), expected[i], "Incorrect value at index = " + i);
    }
  }
}
