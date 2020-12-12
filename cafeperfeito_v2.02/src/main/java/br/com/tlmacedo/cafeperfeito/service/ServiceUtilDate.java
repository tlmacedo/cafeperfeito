package br.com.tlmacedo.cafeperfeito.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class ServiceUtilDate {

    public static String getIntervaloData(LocalDate data) {
        return getIntervaloData(data, null);
    }


    public static String getIntervaloData(LocalDate data1, LocalDate data2) {
        if (data1 == null) return null;
        if (data2 == null) data2 = LocalDate.now();
        Period period = Period.between(data1, data2);
        StringBuilder stbPeriodo = new StringBuilder("");
        if (period.getYears() >= 1) {
            stbPeriodo.append(String.format("%d %s",
                    period.getYears(),
                    period.getYears() > 1 ? "anos" : "ano"));
        }
        if (period.getMonths() >= 1) {
            if (!stbPeriodo.toString().equals("")) stbPeriodo.append(" ");
            stbPeriodo.append(String.format("%d %s",
                    period.getMonths(),
                    period.getMonths() > 1 ? "meses" : "mês"));
        }
        if (period.getDays() >= 1) {
            if (!stbPeriodo.toString().equals("")) stbPeriodo.append(" ");
            stbPeriodo.append(String.format("%d %s",
                    period.getDays(),
                    period.getDays() > 1 ? "dias" : "dia"));
        }
        if (period.isZero() || stbPeriodo.toString().equals(""))
            stbPeriodo.append("hoje");
        return stbPeriodo.toString();
    }

    public static LocalDate getDataVencimento(LocalDate data, Integer dias, boolean diaUtil) {
        if (dias == null) return LocalDate.now();
        if (data == null)
            data = LocalDate.now();
        if (diaUtil) {
            for (int i = 0; i < dias; i++) {
                if (data.plusDays(i).getDayOfWeek() == DayOfWeek.SATURDAY
                        || data.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY)
                    dias = dias + 1;
            }
            if (data.plusDays(dias).getDayOfWeek() == DayOfWeek.SATURDAY)
                dias = dias + 2;
            if (data.plusDays(dias).getDayOfWeek() == DayOfWeek.SUNDAY)
                dias = dias + 1;
        }
        return data.plusDays(dias);
    }
}
