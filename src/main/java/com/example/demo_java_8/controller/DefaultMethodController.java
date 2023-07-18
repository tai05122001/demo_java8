package com.example.demo_java_8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping(path = "demo/defaultMethod")
public class DefaultMethodController {
    @GetMapping
    public String index(Model model){
        String content_1 = example_1();
        String content_2 = example_2();
        String content_3 = example_3();
        model.addAttribute("content_1",content_1);
        model.addAttribute("content_2",content_2);
        model.addAttribute("content_3",content_3);

        return "defaultMethod";
    }

    private String example_3() {
        return "";
    }

    private String example_2() {
        Collection<Integer> numbers = Arrays.asList(700, 32,21,131,21,3311,655);
        // count method is default method of Stream use to count elements in list
        long count = numbers.stream()
                .filter(n -> n > 500)
                .count();

        return "private String example_2() {\n" +
                "        Collection<Integer> numbers = Arrays.asList(700, 32,21,131,21,3311,655);\n" +
                "\n" +
                "        long count = numbers.stream()\n" +
                "                .filter(n -> n > 500)\n" +
                "                .count();\n" +
                "        // count method is default method of Stream use to count elements in list \n" +
                "\n" +
                "        return \"\";\n" +
                "    }\n"+
                "Số phần tử lớn hơn 500 trong danh sách:\n"+count
                ;
    }

    private String example_1() {
        Imath imath = new math();
        int add_value = imath.add(3, 5 );
        int sub_value = imath.substract(4,3);
        int multiply = imath.multiply(3,3);
        int divide = imath.divide(4,2);


        return "private String example_1() {\n" +
                "        Imath imath = new math();\n" +
                "        int add_value = imath.add(3, 5 );\n" +
                "        int sub_value = imath.substract(4,3);\n" +
                "        int multiply = imath.multiply(3,3);\n" +
                "        int divide = imath.divide(4,2); \n" +
                "        \n" +
                "\n" +
                "        return \"\";\n" +
                "    }\n"+
                "Kết quả của các phép toán trên là\n "+
                "Add: "+add_value+
                "\nSubstract: "+sub_value+
                "\nMultiply: "+multiply+
                "\nDivide: "+divide
                ;
    }
    public interface Imath{
        int add(int a,  int b);
        default int substract(int a, int b ){
            return add(a, -b );
        }
        default int multiply(int a, int b ){
            return a*b;
        }
        default int divide(int a, int b ){
           if(b==0){
               throw new IllegalArgumentException("Cann't divide by 0");
           }
           return a/b;
        }
    }
    public class math implements Imath{

        @Override
        public int add(int a, int b) {
            return a+b;
        }
    }
}
