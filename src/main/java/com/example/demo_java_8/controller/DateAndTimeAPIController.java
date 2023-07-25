package com.example.demo_java_8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;

@Controller
@RequestMapping(path = "demo/dateAndTimeAPI")
public class DateAndTimeAPIController {
    @GetMapping
    public String index(Model model){
        String content_1 = example_1();
        String content_2 = example_2();
        String content_3 = example_3();
        String content_4 = example_4();
        String content_5 = example_5();
        model.addAttribute("content_1",content_1);
        model.addAttribute("content_2",content_2);
        model.addAttribute("content_3",content_3);
        model.addAttribute("content_4",content_4);
        model.addAttribute("content_5",content_5);
        return "dateAndTimeApi";
    }

    private String example_5() {
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nowLocal = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");
        String formatted = nowLocal.format(formatter);
        ZonedDateTime newYorkTime = nowLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        return "Thời gian hiện tại(UTC): "+ nowUTC +
                "\nThời gian hiện tại(Local): "+ nowLocal+
                "\nĐịnh dạng lại thời gian: "+ formatted+
                "\nChuyển giờ sang múi giờ America/NewYork"+ newYorkTime
                ;
    }

    private String example_4() {
        Instant now = Instant.now();

        Instant later = now.plus(1, ChronoUnit.HOURS);
        long seconds = now.until(later, ChronoUnit.SECONDS);


        long epochSeconds = now.getEpochSecond();
        Instant fromEpochSeconds = Instant.ofEpochSecond(epochSeconds);


        Instant earlier = now.minus(1, ChronoUnit.HOURS);
        boolean isBefore = earlier.isBefore(now);
        boolean isAfter = later.isAfter(now);

        return "Thời gian hiện tại "+ now +
                "\nKhoảng cách thời gian so với trước một tiếng của hiện tại" +seconds +
                "\nSố giây tính từ Epoch"+ epochSeconds +
                "\nSố giây"+ fromEpochSeconds +
                "\n Mốc thời gian 1 tiếng sau hiện tại có sau hiện tại không?"+ isAfter+
                "\n Mốc thời gian 1 tiếng trước hiện tại có sau hiện tại không?"+ isBefore;

    }

    private String example_3() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String stringFormat_F = localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        String stringFormat_S = localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String stringFormat_Custom = localDateTime.format(dateTimeFormatter);




        return "Ngày và giờ hiện tại là " + localDateTime +
                "\nNgày và giờ hiện tại theo định dạng FULL là "+ stringFormat_F +
                "\nNgày và giờ hiện tại theo định dạng SHORT là "+ stringFormat_S +
                "\nNgày và giờ hiện tại theo định dạng tự custom là "+ stringFormat_Custom+
                "\n1g 30p 25s trước Ngày và giờ hiện tại là "+ localDateTime.minusHours(1).minusMinutes(30).minusSeconds(25)
                ;
    }

    private String example_2() {
        LocalTime localTime = LocalTime.now();
        LocalTime timePivot = LocalTime.of(12,59,20);
        LocalTime parsedTime = LocalTime.parse("14:30:45.333333");
        ValueRange hourRange = parsedTime.range(ChronoField.HOUR_OF_DAY);

        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));

        return "Bây giờ là: "+ localTime
                +"\nMốc thời gian khai báo là: "+ timePivot
                +"\n30p trước mốc thời gian khai báo là: "+ timePivot.minusMinutes(30)
                +"\n1g 30p trước mốc thời gian khai báo là: "+ timePivot.minusMinutes(30).minusHours(1)
                +"\nThời gian khai bằng cách paste String là: "+ parsedTime
                +"\nGiới hạn của trường giờ là: "+ hourRange
                +"\nGiá trị tối thiểu của trường giờ là: "+ hourRange.getMinimum()
                +"\nGiá trị tối đa của trường giờ là: "+ hourRange.getMaximum()
                +"\nBiến đổi trường phút của mốc thời gian khai báo thành 45 : "+ timePivot.with(ChronoField.MINUTE_OF_HOUR,45)
                ;
    }

    private String example_1() {
        LocalDate localDate = LocalDate.now();
        LocalDate dob = LocalDate.of(2001,12,5);
        String formattedDate_F = dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        String formattedDate_S = dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String formattedDate_M = dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        String formattedDate_L = dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        String formattedDate_Custom = dob.format(dateTimeFormatter);  //17-02-2022
//        LocalDate tomorrow = localDate.date

        return "Ngày hôm nay là ngày: "+ localDate +
                "\n Ngày sinh của tôi là ngày "+ dob+
                "\n Truy xuất thông tin ngày, Tháng , Năm của ngày sinh"+
                "\nNgày: "+dob.getDayOfMonth()+
                "\nTháng: " + dob.getMonthValue()+
                "\nNăm: " + dob.getYear()+
                "\nSo sánh ngày sinh với ngày hôm nay: "+ (dob.compareTo(localDate)>1?" Ngày sinh lớn hơn":" Ngày sinh bé hơn")+
                "\nNăm sinh của bản thân tôi có phải năm nhuận không? "+(dob.isLeapYear()?" Có ":" Không ")+
                "\n Ngày sinh sau 20 năm của tôi là ngày: "+ dob.plusYears(20)+
                "\n Trước tôi một tháng 2 ngày là ngày "+ dob.minusMonths(1).minusDays(2)+
                "\n Có bao nhiêu ngày diễn ra từ ngày sinh tôi đến nay "
                + dob.until(localDate).getYears()+" năm "
                + dob.until(localDate).getMonths()+" tháng "
                + dob.until(localDate).getDays()+" ngày "
                +"\n Formatter ngày theo định dạng FULL: "
                + formattedDate_F
                +"\n Formatter ngày theo định dạng SHORT: "
                + formattedDate_S
                +"\n Formatter ngày theo định dạng MEDIUM: "
                + formattedDate_M
                +"\n Formatter ngày theo định dạng LONG: "
                + formattedDate_L
                +"\n Formatter ngày theo định dạng tự custom (dd/MMMM/YYYY): "
                + formattedDate_Custom
                ;
    }
}
