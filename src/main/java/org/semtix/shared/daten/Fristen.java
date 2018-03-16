package org.semtix.shared.daten;


import java.util.Calendar;


/**
 * Created by Felix Schürgut on 24.01.18.
 *
 * Class to find whether we are in application period (01/01 - 03/15 // 06/01 - 08/15
 * Only compares runtime date to fixed time intervals
 * Can and probably be expanded for other deadlines if need be
 *
 */

/**
 * TODO: Use Joda time for this whole thing
 * -fx
 */


public class Fristen {

    public static boolean isAntragsFrist () {

        Calendar currentdate = Calendar.getInstance();
        // Calendar.MONTH is zero indexed! eg. January = 0 ...
        int currentmonth = currentdate.get(Calendar.MONTH);
        int currentdom = currentdate.get(Calendar.DAY_OF_MONTH);
        return (currentmonth == 0) || (currentmonth == 1) || ((currentmonth == 2) && (currentdom < 15)) || (currentmonth == 5) || (currentmonth == 6) || ((currentmonth == 7) && (currentdom < 15));
    }
}
