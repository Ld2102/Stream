package com.flow.Streamdemo_01;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        //去重，打印小于18岁的作者和他的书名
        extracted(authors);
    System.out.println("-------------------------");
        //姓名长度等于2的做输出
        printName(authors);
    System.out.println("-------------------------");
        //类型转换
        changeOfClass(authors);
    System.out.println("-------------------------");
        //取出的年龄加10
        printAgeAddTen(authors);
    System.out.println("-------------------------");
        //打印作家信息，并去重
        printAuthorsDistinct(authors);
    System.out.println("-------------------------");
        //对作家信息排序，去重
        sortAuthorsDistinct(authors);
    System.out.println("-------------------------");
        //对作家信息降序，去重，得到两个最大年龄作家信息
        sortAuthorsDistinct_2(authors);
    System.out.println("-------------------------");
        //对作家信息降序，去重，非最大年龄的所有作者信息
        noMaxAuthorsprint(authors);
   System.out.println("-------------------------");
        //打印所有书籍，并去重
        printBooks(authors);
   System.out.println("-------------------------");
        //★打印所有书籍类型,如果一个书籍有两个类型如xx,xx 要拆开
        printBooksVategory(authors);
   System.out.println("-------------------------");
        //打印所有作家名字
        printAuthorsName(authors);
   System.out.println("-------------------------");
        //统计非重复书籍数目
        pringtBooksCounts(authors);
   System.out.println("-------------------------");
        //打赢作家所出的最高分和最低分(加点难度
        pringtScore(authors);
   System.out.println("-------------------------");
        //获取一个存放所有作者name的list
        getAllAuthoreName(authors);
   System.out.println("-------------------------");
        //获取一个存放所有书名放到set
        getAllBookToSet(authors);
   System.out.println("-------------------------");
        //获取一个map集合，key为作者名字，value为list<book>
        getAllAuthorsToMap(authors);
   System.out.println("-------------------------");
        //判断年龄是否有在29岁以上的作家
        judgeAge(authors);
   System.out.println("-------------------------");
        //判断年龄是否有>=18岁以上的作家
        judgeAge_02(authors);
   System.out.println("-------------------------");
        //判断年龄是否都没超过100岁的作家
        judgeAge_03(authors);
   System.out.println("-------------------------");
        //获取任意一个大于14的作者姓名
        findAnyAgeGt14(authors);
   System.out.println("-------------------------");
        //获取年龄最小的作家姓名 思路 升序 取第一个 可以用limit
        findFirstAuthorsMinAge(authors);
   System.out.println("-------------------------");
        //使用reduce求所有作者年龄的和
        subAuthorsAge(authors);
   System.out.println("-------------------------");
        //使用reduce打印年龄最大值,采用两个参数的重载方法
        MaxAuthorsAge(authors);
   System.out.println("-------------------------");
        //使用reduce打印年龄最小值,采用一个参数的重载方法
        MinAuthorsAge(authors);
        System.out.println("----------optional---------");

    }

    private static void MinAuthorsAge(List<Author> authors) {
        Optional<Integer> reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce( new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer rerult, Integer element) {
                        return rerult > element ? element : rerult;
                    }
                });
        reduce.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer rerult) {
                System.out.println(rerult);
            }
        });
    }

    private static void MaxAuthorsAge(List<Author> authors) {
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (rerult, element) -> {
                  /*  if (element > rerult) {
                        rerult = element;
                    }*/
                    //流元素反复与初始值比较，大于则赋值给初始值变量
                    return rerult<element?element:rerult;
                });
        System.out.println(reduce);
    }

    private static void subAuthorsAge(List<Author> authors) {
        Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer Rerult, Integer element) {
                        return Rerult + element;
                    }
                });
        System.out.println(reduce);
    }

    private static void findFirstAuthorsMinAge(List<Author> authors) {
        Optional<Author> first = authors.stream()
                .sorted((author1, author2) -> author2.getAge() - author1.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    private static void findAnyAgeGt14(List<Author> authors) {
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 10)
                .findAny();
//        如果没用元素，any.get()抛出NoSuchElementException: No value present
//        System.out.println(any.get().getName());
        any.ifPresent(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author.getName());
            }
        });
    }

    private static void judgeAge_03(List<Author> authors) {
        boolean b = authors.stream()
                .map(author -> author.getAge())
                .noneMatch(age -> age > 100);
        System.out.println(b);
    }

    private static void judgeAge_02(List<Author> authors) {
        boolean b = authors.stream()
                .map(author -> author.getAge())
                .allMatch(age -> age >= 18);
        System.out.println(b);
    }

    private static void judgeAge(List<Author> authors) {
        boolean b = authors.stream().map(author -> author.getAge())
                .anyMatch(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer > 29;
                    }
                });
        System.out.println(b);

    }

    private static void getAllAuthorsToMap(List<Author> authors) {
//        Map<String, List<Book>> collect = authors.stream()
//                .distinct()
//                .collect(Collectors.toMap(new Function<Author, String>() {
//                    @Override
//                    public String apply(Author author) {
//                        return author.getName();
//                    }
//                }, new Function<Author, List<Book>>() {
//                    @Override
//                    public List<Book> apply(Author author) {
//                        return author.getBookList();
//                    }
//                }));
        Map<String, List<Book>> collect = authors.stream()
                .distinct().collect(Collectors.toMap(author -> author.getName(), author -> author.getBookList()));

        for (String s : collect.keySet()) {
            System.out.println("key="+s+","+ "value="+collect.get(s));
        }
    }

    private static void getAllBookToSet(List<Author> authors) {
        Set<String> collect = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());

        System.out.println(collect);
    }

    private static void getAllAuthoreName(List<Author> authors) {
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .distinct()
                .sorted((s1,s2)->s1.compareTo(s2)
                        )
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void pringtScore(List<Author> authors) {
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book ->
                    book.getScore().intValue()
                )
                .max(Comparator.comparingInt(score -> score));

        System.out.println(max.get());
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book ->
                        book.getScore().intValue()
                )
                .min((s1,s2)->s1-s2);
        System.out.println(min.get());

    }

    private static void pringtBooksCounts(List<Author> authors) {
        long count = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .distinct()
                .count();
        System.out.println(count);

    }


    private static void printAuthorsName(List<Author> authors) {
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(System.out::println);
    }


    private static void printBooksVategory(List<Author> authors) {
        authors.stream()
                .flatMap(author->author.getBookList().stream())
                .distinct()
                //从这看相当于books.stream
//                .flatMap(new Function<Book, Stream<String>>() {
//                    @Override
//                /*String[] split = book.getCategory().split(",");所以返回值是字符串数组，
//                  Stream<String>stream = Arrays.Stream(split),其中String可以为?
//                  猜测估计是jdk提供的，而自定义的却会爆红
//                    */
//                    public Stream<String> apply(Book book) {
//                        return Arrays.stream(book.getCategory().split(","));
//                    }
//                })
                .map(new Function<Book, String>() {
                    @Override
                    public String apply(Book book) {
                        /*此步将字符串转数组,但需要将数组arrays.tostring，否则是地址
                        所以只能采取遍历元素得到
                         */
                        String[] split = book.getCategory().split(",");
                        for (String s : split) {
                         return s;
                        }
                        return book.getCategory();
                    }
                })
                .distinct()
                .forEach(System.out::println);
    }

    private static void printBooks(List<Author> authors) {
        authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .distinct()
                .forEach(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) {
                        System.out.println(book.getName());
                    }
                });
    }

    private static void noMaxAuthorsprint(List<Author> authors) {
        authors.stream().distinct()
                .sorted((o1, o2) -> o2.getAge()- o1.getAge())
                .skip(1)
                .forEach(System.out::println);
    }

    private static void sortAuthorsDistinct_2(List<Author> authors) {
        authors.stream().distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge()-o1.getAge();
                    }
                })
                .limit(2)
                .forEach(System.out::println);
    }

    private static void  sortAuthorsDistinct(List<Author> authors) {
        authors.stream().distinct()
                .sorted((o1, o2) -> o1.getAge()- o2.getAge())
                .forEach(System.out::println);
    }

    private static void printAuthorsDistinct(List<Author> authors) {
        authors.stream().distinct().forEach(System.out::println);
    }

    private static void printAgeAddTen(List<Author> authors) {
        authors.stream()
                .map(author -> author.getAge())
                .map(ages->ages+10)
                .forEach(System.out::println);
    }

    private static void changeOfClass(List<Author> authors) {
        authors.stream()
                .distinct()
                .map(author -> author.getName())
                .forEach(System.out::println);
    }

    private static void printName(List<Author> authors) {
        authors.stream()
             .filter(author -> author.getName().length()==2)
            .forEach(name-> System.out.println(name.getName()));
    }

    private static void extracted(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        Author author1 = new Author(1L, "亚索", "my introduction 1", 33, null);
        Author author2 = new Author(2L, "瑞兹", "my introduction 2", 34, null);
        Author author3 = new Author(3L, "莎弥拉", "my introduction 3", 15, null);
        Author author4 = new Author(3L, "莎弥拉", "my introduction 3", 15, null);
        Author author5 = new Author(3L, "马儿扎哈", "my introduction 4", 15, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "悲剧", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "雷剧", "书名2", 85D, "这是简介哦"));
        books1.add(new Book(3L, "惨剧", "书名3", 56D, "这是简介哦"));

        books2.add(new Book(3L, "惨剧", "书名3", 56D, "这是简介哦"));
        books2.add(new Book(4L, "好剧", "书名4", 56D, "这是简介哦"));

        books3.add(new Book(5L, "话剧,神剧", "书名5", 45D, "这是简介哦"));
        books3.add(new Book(6L, "喜剧,神剧", "书名6", 100D, "这是简介哦"));
        books3.add(new Book(6L, "喜剧,神剧", "书名6", 100D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books3);
        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4,author5));
    }


}