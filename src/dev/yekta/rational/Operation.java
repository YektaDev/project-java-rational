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
}