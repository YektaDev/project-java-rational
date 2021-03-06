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

import static dev.yekta.rational.Rational.Signs.*;
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

    public void deleteOperator(int index) {
        char[] copy = new char[operators.length - 1];

        for (int i = 0, j = 0; i < operators.length; i++) {
            if (i != index)
                copy[j++] = operators[i];
        }

        operators = copy;
    }

    public Rational calculate() throws Exceptions.InternalException, Exceptions.UndefinedOperatorException {
        if (rationals == null)
            throw new NullPointerException("rationals[] Cannot Be Null!");

        if (rationals.length > 1)
            calculateMulDiv();
        if (operators.length > 0)
            calculateAddSub();

        if (rationals.length != 1)
            throw new Exceptions.InternalException("Calculation Failure of Operation: rational.length: %d, expected: 1", rationals.length);
        if (operators.length != 0)
            throw new Exceptions.InternalException("Calculation Failure of Operation: operators.length: %d, expected: 0", operators.length);

        return rationals[0];
    }

    private void calculateMulDiv() {
        for (int i = 0; i < this.operators.length; i++) {
            Rational tmp;
            switch (this.operators[i]) {
                case CONV_MUL:
                    tmp = this.rationals[i].mul(this.rationals[i + 1]);
                    this.rationals = insert(this.rationals, tmp, i);
                    deleteRational(i + 1);
                    deleteRational(i + 1);
                    deleteOperator(i);
                    i = -1; // Reset the counter
                    break;
                case CONV_DIV:
                    tmp = this.rationals[i].div(this.rationals[i + 1]);
                    this.rationals = insert(this.rationals, tmp, i);
                    deleteRational(i + 1);
                    deleteRational(i + 1);
                    deleteOperator(i);
                    i = -1; // Reset the counter
                    break;
            }
        }
    }

    private void calculateAddSub() throws Exceptions.UndefinedOperatorException {
        while (this.operators.length > 0) {
            Rational tmp;
            switch (this.operators[0]) {
                case CONV_ADD:
                    tmp = this.rationals[0].add(this.rationals[1]);
                    deleteRational(0);
                    deleteRational(0);
                    deleteOperator(0);
                    rationals = appendStart(this.rationals, tmp);
                    break;
                case CONV_SUB:
                    tmp = this.rationals[0].sub(this.rationals[1]);
                    deleteRational(0);
                    deleteRational(0);
                    deleteOperator(0);
                    rationals = appendStart(this.rationals, tmp);
                    break;
                default:
                    throw new Exceptions.UndefinedOperatorException(operators[0]);
            }
        }
    }

    private boolean isValid() {
        if (this.getOperators() != null && this.getRationals() != null)
            return this.getOperators().length + 1 == this.getRationals().length;
        return false;
    }
}