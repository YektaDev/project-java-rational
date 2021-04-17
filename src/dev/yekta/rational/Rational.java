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

import java.util.regex.Pattern;

import static dev.yekta.rational.Rational.Signs.DISPLAY_FRACTION;

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

    public Rational(int numerator, int denominator, boolean doSimplification) {
        if (doSimplification)
            setSimplifiedRational(numerator, denominator);
        else
            setRational(numerator, denominator);
    }

    public Rational(int numerator, int denominator) {
        setSimplifiedRational(numerator, denominator);
    }

    public Rational(int numerator) {
        setRational(numerator, 1);
    }

    public Rational() {
        setRational(0, 1);
    }

    public Rational add(Rational num) {
        int n1 = this.numerator, d1 = this.denominator, n2 = num.numerator, d2 = num.denominator;
        return new Rational((n1 * d2) + (n2 * d1), d1 * d2);
    }

    public Rational sub(Rational num) {
        return add(new Rational(-num.numerator, num.denominator));
    }

    public Rational mul(Rational num) {
        return new Rational(this.numerator * num.numerator, this.denominator * num.denominator);
    }

    public Rational div(Rational num) {
        return mul(new Rational(num.denominator, num.numerator));
    }

    public Rational reverse() {
        return new Rational(denominator, numerator);
    }

    @Override
    public String toString() {
        return (denominator != 0) ? numerator + String.valueOf(DISPLAY_FRACTION) + denominator : "UNDEFINED";
    }

    public int strLength() {
        return (String.valueOf(numerator) + denominator).length() + 1;
    }

    public void print() {
        System.out.println(this);
    }

    public static Rational convert(String str) {
        if (str == null) {
            System.out.println("Rational.convert(): str Cannot Be Empty!");
            return new Rational();
        }

        try {
            return new Operation(str).calculate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Rational();
        }
    }

    public static Rational convertP(String str) {
        if (str == null) {
            System.out.println("Rational.convertP(): str Cannot Be Empty!");
            return new Rational();
        }

        int lastPOpenIndex = str.lastIndexOf('(');

        if (lastPOpenIndex == -1)
            return convert(str);

        String neededStr = str.substring(lastPOpenIndex);
        int lastPCloseIndex = neededStr.indexOf(')');
        neededStr = neededStr.substring(0, lastPCloseIndex + 1);
        String operationStr = neededStr.substring(1, neededStr.length() - 1);

        return convertP(str.replaceAll(Pattern.quote(neededStr), convert(operationStr).toString()));
    }

    private void setRational(int numerator, int denominator) {
        if (denominator < 0) {
            this.numerator = -1 * numerator;
            this.denominator = -1 * denominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
}
