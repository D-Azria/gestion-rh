package fr.doandgo.gestionrh.utils;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtils {

    public boolean isFirstDateBeforeOrSameSecondDate(Date firstDate, Date dateAfterOrSame){
        return !dateAfterOrSame.before(firstDate);
    }

    public boolean dateIsPresentOrFuture(Date date){
        Date today = new Date();
        return date.after(today) || date.equals(today);
    }
}
