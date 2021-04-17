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

public class Rational {
    public static final class Signs {
        public static final char DISPLAY_FRACTION = '/';
        public static final char CONV_ADD = '+';
        public static final char CONV_SUB = '-';
        public static final char CONV_MUL = '*';
        public static final char CONV_DIV = ':';
        public static final String REGEX_ANY_SIGN = "[+\\-*/:]";
    }

    private int numerator;
    private int denominator;

    @SuppressWarnings("unused")
    public int getNumerator() {
        return numerator;
    }

    @SuppressWarnings("unused")
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    @SuppressWarnings("unused")
    public int getDenominator() {
        return denominator;
    }

    @SuppressWarnings("unused")
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }
}
