package com.example.demo_java_8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
        return null;
    }

    private String example_4() {
        return null;
    }

    private String example_3() {
        return null;
    }

    private String example_2() {

        return null;
    }

    private String example_1() {
        LocalDate localDate = LocalDate.now();
        LocalDate dob = LocalDate.of(2001,12,5);
        String formattedDate_F = dob.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
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
                +"\n Formatter ngày theo định dạng FULL đầy đủ: "
                + formattedDate_F
                ;
    }
}
