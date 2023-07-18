package com.example.demo_java_8.controller;

import com.example.demo_java_8.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Controller
@RequestMapping(path = "demo/functionalinterface")
public class FunctionalInterfaceController {
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

        return "functionalInterface";
    }

    private String example_5() {
        Random r = new Random();
        Supplier<Double> random = () -> r.nextDouble(250.0);
        Double result = random.get();
        List<Double> double_ls = new ArrayList<>();
        for (int i = 0 ;i<5; i++ ){
            double_ls.add(random.get());
        }
        return "private String example_5() {\n" +
                "        Random r = new Random();\n" +
                "        Supplier<Double> random = () -> r.nextDouble(250.0);\n" +
                "        Double result = random.get();\n" +
                "        List<Double> double_ls = new ArrayList<>();\n" +
                "        for (int i = 0 ;i<5; i++ ){\n" +
                "            double_ls.add(random.get());\n" +
                "        }\n" +
                "        return \"\"\n" +
                "                double_ls.toString();\n" +
                "    }\n"+
                "Kết quả của bài toán: " +double_ls.toString();
    }

    private String example_4() {
        Predicate<Person> isAdult = p -> p.getAge() > 18;
        List<Person> persons = Arrays.asList(
                new Person(1, "Trần Tấn Tài", "000000000", "Long An" ,18 ),
                new Person(2, "Trần Văn B ", "020000000", "Long An" ,16 ),
                new Person(3, "Nguyễn Thị C", "020000000", "Long An" ,24 )

        );
        List<Boolean> arr = new ArrayList<>();
        int i = 0;
        persons.forEach(t-> {
            arr.add(isAdult.test(t));

        });

        return " private String example_4() {\n" +
                "        Predicate<Person> isAdult = p -> p.getAge() > 18;\n" +
                "        List<Person> persons = Arrays.asList(\n" +
                "                new Person(1, \"Trần Tấn Tài\", \"000000000\", \"Long An\" ,18 ),\n" +
                "                new Person(2, \"Trần Văn B \", \"020000000\", \"Long An\" ,16 ),\n" +
                "                new Person(3, \"Nguyễn Thị C\", \"020000000\", \"Long An\" ,24 )\n" +
                "\n" +
                "        );\n" +
                "        List<Boolean> arr = new ArrayList<>();\n" +
                "        int i = 0;\n" +
                "        persons.forEach(t-> {\n" +
                "            arr.add(isAdult.test(t));\n" +
                "\n" +
                "        });\n" +
                "        \n" +
                "        return \"\"\n" +
                "Kết quả: Duyệt danh sách và đưa ra người > 18 tuổi \n"+
                arr.toString()
                ;


    }

    private String example_3() {
        //create object function use to convert from Person obj to String obj
        Function<Person, String > convertString =  s -> s.getName() +"\n"+s.getAddress();
        // create object Person
        Person a = new Person(1, "Trần Tấn Tài", "000000000", "Long An" ,18 );
        // use String variable to save value
        String result = convertString.apply(a);
        //return view example
        return "private String example_3() {\n" +
                "        //create object function use to convert from Person obj to String obj \n" +
                "        Function<Person, String > convertString =  s -> s.getName() +\"\\n\"+s.getAddress();\n" +
                "        // create object Person \n" +
                "        Person a = new Person(1, \"Trần Tấn Tài\", \"000000000\", \"Long An\");\n" +
                "        // use String variable to save value \n" +
                "        String result = convertString.apply(a);\n" +
                "        //return view example \n" +
                "        return 'view of example ';\n" +
                "    }\n"+
                "Kết quả: result ="+ result;
    }


    private String example_2() {
        List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice");
        // use StringBuilder to save result
        StringBuilder sb = new StringBuilder();
        // Consumer use to save one method not return and here is append string
        Consumer<String> printName = name -> sb.append(name+"  ");
        // Read and append each element into List
        names.forEach(printName);
        // return view of example
        return "private String example_2() {\n" +
                "List<String> names = Arrays.asList(\"John\", \"Jane\", \"Bob\", \"Alice\");\n" +
                "        // use StringBuilder to save result\n" +
                "        StringBuilder sb = new StringBuilder();\n" +
                "        // Consumer use to save one method not return and here is append string\n" +
                "        Consumer<String> printName = name -> sb.append(name+\"  \");\n" +
                "        // Read and append each element into List \n" +
                "        names.forEach(printName);\n" +
                "        // return view of example \n"+
                "        return 'view of example' \n" +
                "}"+
                "Kết quả: Chuỗi sb ="+ sb.toString();
    }

    private String example_1() {
        // definition for 2 interfaces plus and sub
        Imath plus = ((x, y) -> x+y);
        Imath sub = ((x, y) -> x-y);

        // use 2 variables to save value of 2 interface above
        int plus_to = plus.declare(4,5);
        int sub_to = sub.declare(9, 5 );

        return "@FunctionalInterface\n" +
                "interface Imath{\n" +
                "int declare(int x , int y);\n" +
                "}\n" +
                "private String example_1() {\n" +
                "        // definition for 2 interfaces plus and sub \n" +
                "        Imath plus = ((x, y) -> x+y);\n" +
                "        Imath sub = ((x, y) -> x-y);\n" +
                "        \n" +
                "        // use 2 variables to save value of 2 interface above \n" +
                "        int plus_to = plus.declare(4,5);\n" +
                "        int sub_to = sub.declare(9, 5 );\n" +
                "        \n" +
                "        return 'view of ex' "+
                " }\n"+
                "Kết quả: plus_to = "+plus_to+"; sub_to = "+ sub_to;

    }

    @FunctionalInterface
    interface Imath{
        int declare(int x , int y);
    }


}
