package com.example.demo_java_8.controller;

import com.example.demo_java_8.model.Person;
import com.example.demo_java_8.model.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "demo/streamApi")
public class StreamApiController {
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
        return"streamApi";
    }

    private String example_5() {
        return null;
    }

    private String example_4() {
        return null;
    }

    private String example_3() {
        return "";
    }

    private String example_2() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Trần Tấn Tài", "000000000", "Long An" ,18 ),
                new Person(2, "Trần Văn B ", "020000000", "Long An" ,16 ),
                new Person(3, "Nguyễn Thị C", "020000000", "Long An" ,24 ),
                new Person(4, "Nguyễn Thị D", "020000000", "Nam Định" ,32 ),
                new Person(5, "Nguyễn Thị Thành ", "020000000", "Long An" ,27 )

        );

        // convert list object to map and one key
        Map<Integer , String > map = persons.stream().collect(Collectors.toMap(Person::getId,Person::getName, (oldValue, newValue) -> newValue));
        // convert list object to map have one key and arr value
        Map<Integer , PersonInfo> map2 = persons.stream().collect(Collectors.toMap(Person::getId
                        , p-> new PersonInfo(p.getName()
                        , p.getPhone(), p.getAddress()
                        , p.getAge()),(oldValue, newValue)->newValue));
        // convert list object to map have one key and arr value second way
        Map<Integer, List<String> > map3 = persons.stream().collect(Collectors.toMap(
                Person::getId, p-> addList(p.getName(), p.getPhone(),String.valueOf(p.getAge()),p.getAddress()),(oldValue, newValue)-> newValue));

        return "Chuyển từ list object sang map có key và value đơn giản \n\n"+
                map.toString()+
                "\n\nChuyển từ list object sang map có key đơn giản và value là một object custom class \n\n"+
                map2.toString()+
                "\n\nChuyển từ list object sang map có key đơn giản và value là một list<String>  \n\n"+
                map3.toString();
    }
    public List<String> addList(String a1 , String a2, String a3 ,String a4 ){
        return  Arrays.asList(a1,a2,a3,a4);

    }
    private String example_1() {
        List<Integer> list = Arrays.asList(1,2,7,6,4,9,10,16,12,2,4,10);

        return "Sử dụng stream().sort() sắp xếp danh sách\n "+

                //Use stream().sort() sort list
                list.stream().sorted().collect(Collectors.toList()).toString()+
                "\nSử dụng stream().filter() để lọc ra danh sách các số chẵn \n"+

                //Use stream().filter() to filter all even element
                list.stream().filter(t->t%2==0).collect(Collectors.toList()).toString() +

                "\nSử dụng stream().filter() để lọc ra danh sách các số chẵn và là duy nhất trong mảng \n"+
                //Use stream().filter()  and district to filter all even element and only appear 1 times in list
                list.stream().filter(t->t%2==0).distinct().collect(Collectors.toList()).toString()+
                "\nSử dụng stream().reduce() và district để tổng các số chẵn và chỉ xuất hiện một lần trong mảng \n"+
                //Use stream().filter() and district(), reduce()  to sum all even element and only appear 1 times in list
                list.stream().filter(t->t%2==0).distinct().reduce( (a,b)-> a+b).get()+
                "\n Sử dụng stream().findFirst() để tìm kiếm giá trị đầu tiên thõa điều kiện " +
                "một số lẻ là số nguyên số và chỉ xuất hiện duy nhất 1 lần trong mảng "+
                // use stream().findFirst() to find first element fit conditions: odd, prime, unique in list
                list.stream().filter(t-> checkPrimeDigit(t) && t%2==1).distinct().collect(Collectors.toList()).toString();



    }

    private boolean checkPrimeDigit(Integer t) {

        if(t ==2 ) return true;
        if(t <=1 ) return  false;
        for (int i = 2; i<= Math.sqrt(t); i ++  )
            if(t%i==0) return false;
        return true;
    }
}
