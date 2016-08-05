/*
 * Semtix Semesterticketbüroverwaltungssoftware entwickelt für das
 *        Semesterticketbüro der Humboldt-Universität Berlin
 *
 * Copyright (c) 2015. Michael Mertins (MichaelMertins@gmail.com)
 * 2011-2014 Jürgen Schmelzle (j.schmelzle@web.de)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.semtix.shared.daten.enums;

import java.text.DecimalFormat;

/**
 * Created by Felix Schuergut <schuergf@student.hu-berlin.de> on 03.08.16.
 *
 * Wandelt die gegebene Zahl in deutsche Zahlworte um.
 *
 */
public class GermanNumberToWords {

        private static final String[] tensNames = {
                "",
                " zehn",
                " zwanzig",
                " dreißig",
                " vierzig",
                " fünfzig",
                " sechzig",
                " siebzig",
                " achtzig",
                " neunzig"
        };

        private static final String[] numNames = {
                "",
                " ein",
                " zwei",
                " drei",
                " vier",
                " fünf",
                " sechs",
                " sieben",
                " acht",
                " neun",
                " zehn",
                " elf",
                " zwölf",
                " dreizehn",
                " vierzehn",
                " fünfzehn",
                " sechzehn",
                " siebzehn",
                " achtzehn",
                " neunzehn"
        };

        public GermanNumberToWords() {
        }

        public static String convertLessThanOneThousand(int number) {
                String soFar;

                if (number % 100 < 20) {
                        soFar = numNames[number % 100];
                        number /= 100;
                } else {
                        soFar = numNames[number % 10];
                        number /= 10;

                        soFar = new StringBuilder().append(soFar).append(" und").append(tensNames[number % 10]).toString();
                        number /= 10;
                }
                if (number == 0) return soFar;
                return numNames[number] + " hundert" + soFar;
        }


        public static String convert(long number) {
                // 0 to 999 999 999 999
                if (number == 0) {
                        return "null";
                }

                String snumber = Long.toString(number);

                // pad with "0"
                String mask = "000000000000";
                DecimalFormat df = new DecimalFormat(mask);
                snumber = df.format(number);

                // XXXnnnnnnnnn
                int billions = Integer.parseInt(snumber.substring(0, 3));
                // nnnXXXnnnnnn
                int millions = Integer.parseInt(snumber.substring(3, 6));
                // nnnnnnXXXnnn
                int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
                // nnnnnnnnnXXX
                int thousands = Integer.parseInt(snumber.substring(9, 12));

                String tradBillions;
                switch (billions) {
                        case 0:
                                tradBillions = "";
                                break;
                        case 1:
                                tradBillions = convertLessThanOneThousand(billions)
                                        + " milliarden ";
                                break;
                        default:
                                tradBillions = convertLessThanOneThousand(billions)
                                        + " billion ";
                }
                String result = tradBillions;

                String tradMillions;
                switch (millions) {
                        case 0:
                                tradMillions = "";
                                break;
                        case 1:
                                tradMillions = convertLessThanOneThousand(millions)
                                        + " million ";
                                break;
                        default:
                                tradMillions = convertLessThanOneThousand(millions)
                                        + " million ";
                }
                result = result + tradMillions;

                String tradHundredThousands;
                switch (hundredThousands) {
                        case 0:
                                tradHundredThousands = "";
                                break;
                        case 1:
                                tradHundredThousands = "ein tausend ";
                                break;
                        default:
                                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                                        + " tausend ";
                }
                result = result + tradHundredThousands;

                String tradThousand;
                tradThousand = convertLessThanOneThousand(thousands);
                result = result + tradThousand;

                // remove extra spaces!
                return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
        }


}