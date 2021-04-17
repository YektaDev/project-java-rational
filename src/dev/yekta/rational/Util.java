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

import static dev.yekta.rational.Rational.Signs.REGEX_ANY_SIGN;

final class Util {
    static Rational collectRational(String str) {
        String[] sp = str.split(REGEX_ANY_SIGN);
        int numerator;
        int denominator;

        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            numerator = Integer.parseInt(sp[1]);
            numerator *= str.charAt(0) == '+' ? 1 : -1;
            denominator = Integer.parseInt(sp[2]);
        } else {
            numerator = Integer.parseInt(sp[0]);
            denominator = Integer.parseInt(sp[1]);
        }

        return new Rational(numerator, denominator, false); //IT'S IMPORTANT TO BE FALSE
    }

    static char collectOperator(String str) {
        return str.charAt(0);
    }

    static String deleteStartCharsBySizeOf(String str, Rational rational) {
        return str.substring(rational.strLength());
    }
}