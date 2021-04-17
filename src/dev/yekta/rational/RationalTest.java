/*
 * Copyright © 2021 Ali Khaleqi Yekta, All Rights Reserved.
 *
 * Author: Ali Khaleqi Yekta [YektaDev]
 * Website: https://Yekta.Dev
 * Email: Me@Yekta.Dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.yekta.rational;

public class RationalTest {
    public static void main(String[] args) {
        System.out.println("[[[ Rational Class Test ]]]");

        // SAMPLE TESTS
        System.out.println("++ Sample Tests:");
        Rational x = new Rational(2, 5);
        Rational y = new Rational(4, 10);
        x.print(); // 2/5
        y.print(); // 2/5
        Rational z = x.mul(y);
        z.print(); // 4/25

        z = Rational.convertP("(5/3*3/5):(1/2+1/2)");
        z.print(); // 1/1
        z = x.add(Rational.convertP("(5/3*3/5):(6/4-1/2)"));
        z.print(); // 7/5

        // OTHER TESTS
        System.out.println("++ Other Tests:");
        Rational
                .convert("12*36+12/36+6/6*2/6")
                .print(); // 1/1
        Rational
                .convert("-1/2-2/1")
                .print(); // -5/2
        Rational.convert("0/1+2/4")
                .print(); // 1/2
        new Rational(8)
                .print(); // 8/1
        new Rational(1, 8)
                .print(); // 1/8
        new Rational(8)
                .div(new Rational(8))
                .sub(new Rational(16))
                .print(); // -15/1
        new Rational(2, 1)
                .reverse()
                .add(new Rational(8, 3))
                .print(); // 19/6
        new Rational(1, 8)
                .sub(new Rational(100, 8))
                .print(); // -99/8

        // POWER TESTS!
        System.out.println("++ Power Tests:");
        Rational.convertP("34/1*((((((6/1))):15/85)   + (((2555/34)*5/1)+(6/1*4/1))):(((  14747/1))))").print(); // 1/1
        Rational.convertP(" 5 /  3 * 3 / 5 :    ( 1/ 2 +1  /2)    ").print(); // 1/1
        Rational.convertP(" 0 / 0").print(); // UNDEFINED
    }
}
