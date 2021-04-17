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

import static dev.yekta.rational.Util.*;

final class Operation {
    private Rational[] rationals;
    private char[] operators;

    public Rational[] getRationals() {
        return rationals;
    }

    public char[] getOperators() {
        return operators;
    }

    public Operation(String str) {
        this();

        String currentStr = str.replaceAll(" ", "");
        while (true) {
            if (currentStr.length() == 0) break;

            Rational r = collectRational(currentStr);
            add(r);
            currentStr = deleteStartCharsBySizeOf(currentStr, r);

            if (currentStr.length() == 0) break;

            char ch = collectOperator(currentStr);
            add(ch);
            currentStr = deleteStartCharsByOne(currentStr);
        }

        if (!isValid())
            System.out.println("Error: Invalid Operation!");
    }

    public Operation(Rational[] rationals, char[] operators) {
        this.rationals = rationals;
        this.operators = operators;
    }

    public Operation() {
        this(new Rational[0], new char[0]);
    }

    public void add(char operator) {
        operators = addElement(operators, operator);
    }

    public void add(Rational rational) {
        rationals = addElement(rationals, rational);
    }

    private static Rational[] insert(Rational[] a, Rational key, int index) {
        Rational[] result = new Rational[a.length + 1];

        System.arraycopy(a, 0, result, 0, index);
        result[index] = key;
        System.arraycopy(a, index, result, index + 1, a.length - index);

        return result;
    }

    public void deleteRational(int index) {
        Rational[] copy = new Rational[rationals.length - 1];

        for (int i = 0, j = 0; i < rationals.length; i++) {
            if (i != index)
                copy[j++] = rationals[i];
        }

        rationals = copy;
    }
}