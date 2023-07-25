package com.example.demo_java_8.controller;

import com.example.demo_java_8.model.Person;
import com.example.demo_java_8.model.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "demo/streamApi")
public class StreamApiController {
    @GetMapping
    public String index(Model model) {
        String content_1 = example_1();
        String content_2 = example_2();
        String content_3 = example_3();
        String content_4 = example_4();
        String content_5 = example_5();
        String content_6 = example_6();

        model.addAttribute("content_1",content_1);
        model.addAttribute("content_2",content_2);
        model.addAttribute("content_3",content_3);
        model.addAttribute("content_4",content_4);
        model.addAttribute("content_5",content_5);
        model.addAttribute("content_6",content_6);
        return"streamApi";
    }



    private String example_6()  {
        long firstNum = 1;
        long lastNum = 1_000_000;
        long actualTotal = 0L ;
        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        try {

             actualTotal = customThreadPool.submit(
                    () -> aList.parallelStream().reduce(0L, Long::sum)).get();
        }catch (ExecutionException | InterruptedException e ){
            e.printStackTrace();
        }finally {
            customThreadPool.shutdown();
        }


        //assertEquals((lastNum + firstNum) * lastNum / 2, actualTotal);

        // assertEquals((lastNum + firstNum) * lastNum / 2, actualTotal);

        System.out.println((lastNum + firstNum) * lastNum / 2);
        System.out.println(actualTotal);
        return "";
    }

    private String example_5() {

//        final int parallelism = 4;
//        ForkJoinPool forkJoinPool = null;
//
//        List<Integer> lists = Arrays.asList(2,3,4,5,6,13,23,55,32,22,11);
//        lists.stream().parallel().forEach(t->{
//            System.out.println(Thread.currentThread());
//            System.out.println(t);
//            System.out.println("--------------------------");
//        });
//        int[] array = IntStream.range(0, 1000000).toArray();
//
//        ForkJoinPool customThreadPool = new ForkJoinPool(4);
//        int sum = customThreadPool.submit(() -> {
//            return IntStream.of(array)
//                    .parallel()
//                    .sum();
//        }).join();
//
//        System.out.println(sum);

//        ForkJoinPool pool = new ForkJoinPool();
//
//        int[] array = new int[1000000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = i;
//        }
//
//        SumTask task = new SumTask(array, 0, array.length);
//        int result = pool.invoke(task);
//
//        System.out.println("Result: " + result);
//        System.out.println("Parallelism: " + pool.getParallelism());
//        System.out.println("Active threads: " + pool.getActiveThreadCount());
//        System.out.println("Queued tasks: " + pool.getQueuedTaskCount());
//        System.out.println("Tasks stolen: " + pool.getStealCount());
//
//        pool.awaitQuiescence(1, TimeUnit.SECONDS);
//        pool.shutdown();


//        WorkerThread  workerThread = new WorkerThread("Đông phương bất bại");
//        workerThread.run();

//      // part 2 demo
//
//        final int NUM_THREAD = 4 ;
//        final int INITIAL_DELAY  = 1 ;
//        final int DELAY  = 3 ;
////        ExecutorService executor = Executors.newSingleThreadExecutor();
////        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREAD);
////        ExecutorService executor = Executors.newCachedThreadPool();
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(NUM_THREAD);
//        for (int i = 0; i < 10; i++) {
//            Runnable worker = new WorkerThread(i+ "");
////            System.out.println("Thread current :  "+ Thread.currentThread());
////            executor.execute(worker);
//            executor.scheduleWithFixedDelay(worker,INITIAL_DELAY,DELAY,TimeUnit.SECONDS);
//        }
//        //waits for termination for 10s only
//        try {
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        executor.shutdown();
//
//        while (!executor.isTerminated()){
//
//        }
//        System.out.println("Finished all threads");
//
        return "";
    }



    private String example_4() {
        List<String> strings = Arrays.asList("hello", "world", "java", "stream", "optional","opl","");
        //convert all element with condition: uppercase
        List<Integer> strings_Leng = strings.stream().map(String::length).collect(Collectors.toList());
        List<String> strings_Upper = strings.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> string_notNull = strings.stream().filter(String::isEmpty).collect(Collectors.toList());

        ;

        return "Sử dụng method references với stream để viết in hoa các k tự trong chuỗi"+ strings_Upper.toString()+
                "\nSử dụng method references với stream để hiển thị độ dài của các phần tử trong mảng "+ strings_Leng.toString()+
                "\n Các chuỗi không phải null ở trong mảng  "+ string_notNull.toString()
                ;
    }

    private String example_3() {
        List<String> strings = Arrays.asList("hello", "world", "java", "stream", "optional","opl");
        // find string have the longest length into list
        Optional<String> longgestString = strings.stream().max((s1,s2)->Integer.compare(s1.length(),s2.length()));
        // use Optional to save all element is a string start with "z " of list
        Optional<String> string_startZ = strings.stream().filter(t-> t.startsWith("z")).findFirst();
        // use stream to check all element into list with conditions: start with o than check symmetry string and 4 characters


        return "Chuỗi dài nhất trong danh sách trên là: "+ (longgestString.isPresent()?longgestString.get():" Không có chuỗi dài nhất tồn tại")+
                "\nChuỗi đầu tiên trong dãy bắt đầu bằng z: "+ (string_startZ.isPresent()?string_startZ.get():" không có chuỗi dài nhất tồn tại")
                ;
//        return "Chuỗi dài nhất trong danh sách "+ longgestString.orElse("Không có chuỗi dài nhất tồn tại");

//          return "Chuỗi đầu tiên bắt đầu bằng kí tự Z " + string_startZ.orElseThrow();
//          return "Chuỗi đầu tiên bắt đầu bằng kí tự Z " + string_startZ.orElseThrow(()-> new RuntimeException("Value not found"));
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

        List<Person> list2 = new ArrayList<>();

//        return "Chuyển từ list object sang map có key và value đơn giản \n\n"+
//                map.toString()+
//                "\n\nChuyển từ list object sang map có key đơn giản và value là một object custom class \n\n"+
//                map2.toString()+
//                "\n\nChuyển từ list object sang map có key đơn giản và value là một list<String>  \n\n"+
//                map3.toString();
        map2.forEach((key, value)-> list2.add( new Person(key, value.getName(), value.getPhone(), value.getAddress(), value.getAge())) );



        return list2.toString();
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
    private static class SumTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 1000;
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = (start + end) / 2;
                SumTask left = new SumTask(array, start, mid);
                SumTask right = new SumTask(array, mid, end);
                left.fork();
                int rightResult = right.compute();
                int leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

    private boolean checkPrimeDigit(Integer t) {

        if(t ==2 ) return true;
        if(t <=1 ) return  false;
        for (int i = 2; i<= Math.sqrt(t); i ++  )
            if(t%i==0) return false;
        return true;
    }
    public static class WorkerThread implements Runnable {

        private String task;

        public WorkerThread(String s) {
            this.task = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Starting. Task = " + task);
            processCommand();
            System.out.println(Thread.currentThread().getName() + " Finished.");
        }

        private void processCommand() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " is rejected");
        }

    }
}
