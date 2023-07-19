package com.example.demo_java_8.controller;

import com.example.demo_java_8.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "demo/lambdaexpression")
public class LambdaExpressionController {

    @GetMapping
    public String index( Model model){

        String content_1 = example_1();
        String content_2 = example_2();
        String content_3 = example_3();
        model.addAttribute("content_1",content_1);
        model.addAttribute("content_2",content_2);
        model.addAttribute("content_3",content_3);

        return "lambdaexpression";
    }

    private String example_5() {
        return null;
    }

    private String example_4() {
        return null;
    }

    private String example_3() {
       List<String> lst = Arrays.asList("ATC" , "CTS ", "DAE", "BTZ", "CCC" ,"ATT", "XtX");
       // Sort list follow with A-Z
       lst.sort((a,b)-> a.compareTo(b));
       // Filter list create to sublist contain all element start with "A" prefix
       List<String> lst_start_a = lst.stream().filter(t->t.startsWith("A")).collect(Collectors.toList());
       // Filter list create to sublist contain all element end with "c" prefix and find first element.
       Optional<String> firstMultipleOfThree= lst.stream().filter(t -> t.endsWith("C")).findFirst();
       String result = firstMultipleOfThree.get();
       // Filter list create to sublist contain all element have
        List<String> lst_string = lst.stream().filter(
                t-> t.equals(new StringBuilder(t).reverse().toString())
        ).collect(Collectors.toList());

       return
               "Danh sách đã được sắp xếp A-Z :\n"+
               lst.toString()+
               "\nDanh sách lọc các kí tự bắt đầu bằng kí tự A\n"+
               lst_start_a.toString()+
               "\nPhần tử đầu tiên có kí tự cuối cùng là C\n"+
               firstMultipleOfThree.get()+
               "\nDanh sách các chuỗi đối xứng\n"+
               lst_string.toString()

               ;

    }

    private boolean checkPrimeDigit(int a ){
        if(a == 2) return true;
        if(a <= 1) return false;
        for (int i = 2; i<= (int)Math.sqrt(a); i++){
            if(a%2== i ){
                return false;
            }
        }
        return true;
    }
    private String example_2() {
        List<Integer> list =  Arrays.asList(1,8,3,9,5,6 ,1 ,2,2);
        Collections.sort(list, (a,b)-> b-a);

        int sum = list.stream().reduce(0,(integer, integer2) -> integer+ integer2);

        List<Integer> list_even = list.stream().filter(integer -> integer%2==0).collect(Collectors.toList());
        //create list integer contains all element only meet 1 times and odd prime digit
        List<Integer> list_unique = list.stream().filter(t-> checkPrimeDigit(t)&& t%2==1).map(Integer::intValue).distinct().collect(Collectors.toList());

        return
                "Kết quả của sắp xếp danh sách các số bằng lambda expression\n"+
                list.toString()+
                "\nTổng các số  trong dãy là \n"+
                sum+
                "\nCác số chẵn trong dãy là \n"+
                list_even.toString()+
                "\nCác số duy nhất trong dãy và là số lẻ nguyên tố  \n"+
                list_unique.toString()
                ;
    }


    private String example_1() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Trần Tấn Tài", "000000000", "Long An" ,18 ),
                new Person(2, "Trần Văn B ", "020000000", "Long An" ,16 ),
                new Person(3, "Nguyễn Thị C", "020000000", "Long An" ,24 ),
                new Person(4, "Nguyễn Thị D", "020000000", "Nam Định" ,32 ),
                new Person(5, "Nguyễn Thị Thành ", "020000000", "Long An" ,27 )

        );
        // use lambda expression to sort list persons follow age
        persons.sort(Comparator.comparingInt(Person::getAge));
        // result of sort
        List<String> result = new ArrayList<>();
        persons.forEach(t-> result.add(t.getName()));
        // check list and show all person have age attribute > 18
        List<Person> per_18 = persons.stream().filter(Person::getAgeGraterThan18).collect(Collectors.toList());
        List<String> result2 = new ArrayList<>();
        per_18.forEach(t-> result2.add(t.getName()));
        // sort list follow func compare of Person
        persons.sort((a,b)-> a.comparePerson(b));
        // sort list follow attribute name
        List<Person> lst_copy = new ArrayList<>(persons);

        lst_copy.sort((a,b)->
                a.getName().split(" ")[a.getName().split(" ").length -1 ].compareTo(
                b.getName().split(" ")[b.getName().split(" ").length -1 ]
        ));
        // filter follow home-town of all person export sublist contains home-town unique



        List<String> uniqueHomeTown = persons.stream()
                .map(Person::getAddress)
                .distinct()
                .collect(Collectors.toList());

        return "Danh sách person được sắp xếp theo tuổi\n" +
                        result.toString() +
                        "\nDanh sách person được lọc theo tuổi là > 18 tuổi\n" +
                        result2.toString() +
                        "\nDanh sách các person được sắp xếp dựa theo hàm compare của hàm person\n" +
                        persons.toString()+
                        "\nDanh sách các person được sắp xếp theo tên trong họ và tên\n"+
                        lst_copy.toString()+
                        "\nDanh sách các quê quán duy nhất \n"+
                        uniqueHomeTown.toString()
                ;

    }

}
