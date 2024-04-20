package appli;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testDate {
    public static void main(String[] args) {
        String date = "2023-08-28"; // Remplacez ceci par la date que vous souhaitez tester
        System.out.println(jouurr(date));
    }

    public static String jouurr(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsedDate = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);

            int jours = calendar.get(Calendar.DAY_OF_WEEK);

            switch (jours) {
                case Calendar.SUNDAY:
                    return "DIMANCHE";
                case Calendar.MONDAY:
                    return "LUNDI";
                case Calendar.TUESDAY:
                    return "MARDI";
                case Calendar.WEDNESDAY:
                    return "MERCREDI";
                case Calendar.THURSDAY:
                    return "JEUDI";
                case Calendar.FRIDAY:
                    return "VENDREDI";
                case Calendar.SATURDAY:
                    return "SAMEDI";
                default:
                    return "Jour inconnu";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la conversion de la date";
        }
    }
}