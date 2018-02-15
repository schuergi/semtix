package org.semtix.shared.daten;


import java.util.Calendar;


/**
 * Created by Felix Schürgut on 24.01.18.
 *
 * Class to find whether we are in application period (01/01 - 02/28 // 06/01 - 07/31
 * Only compares runtime date to fixed time intervals
 * Can and probably be expanded for other deadlines if need be
 *
 */
public class Fristen {

    public static boolean isAntragsFrist () {

        Calendar currentdate = Calendar.getInstance();
        int currentmonth = currentdate.get(Calendar.MONTH);

        return (currentmonth == 1) || (currentmonth == 2) || (currentmonth == 6) || (currentmonth == 7);
    }
}
