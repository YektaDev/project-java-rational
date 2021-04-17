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

public final class Exceptions {
    private static abstract class RationalException extends Exception {
        public RationalException(String errorMsg, Object arg) {
            super(String.format(errorMsg, arg));
        }
    }

    public static final class UndefinedOperatorException extends RationalException {
        public final static String errorMsg = "Undefined Operator: %s";

        public UndefinedOperatorException(char operator) {
            super(errorMsg, operator);
        }
    }
}
