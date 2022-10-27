package com.flow.optional;

import com.flow.Streamdemo_01.Author;
import com.flow.Streamdemo_01.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * optional封装对象
 * */
public class optionalDemo_01 {
    public static void main(String[] args) {
        //使用ofNullable封装
        Optional<Author> optionalAuthor = getAthors();
        optionalAuthor.ifPresent(author1 -> System.out.println(author1.getName()));
        //使用of或empty封装
        Optional<Author> athors2 = getAthors2();
        athors2.ifPresent(author1 -> System.out.println(author1.getName()));
        //安全获取值
        Optional<Author> optionalAuthor1 = secureNum();
        Author author = optionalAuthor1.orElseGet(new Supplier<Author>() {
            @Override
            public Author get() {
                return new Author();
            }
        });
        System.out.println(author);
       /* //如果数据为空抛出异常
        Optional<Author> optionalAuthor2 = secureNum();
        try {
            optionalAuthor2.orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException("我有罪");
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }*/
        //获得不是15岁以上的对象
        Optional<Author> optionalAuthor3 = secureNum();
        Optional<Author> optionalAuthor4 = optionalAuthor3.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 18;
            }
        });
        optionalAuthor4.ifPresent(author1 -> System.out.println(author1));
        //判断数据是否存在
        Optional<Author> athors21 = getAthors2();
        if (athors21.isPresent()){
            System.out.println(athors21.get().getAge());
            System.out.println(athors21.get().getBookList());
        }
        //数据类型转换
        Optional<Author> athors22 = getAthors2();
        Optional<List<Book>> books = athors22.map(new Function<Author, List<Book>>() {
            @Override
            public List<Book> apply(Author author) {
                return author.getBookList();
            }
        });

        List<Author> authors = getAuthors();
//        打印作家年龄>17并且姓名长度>1的作家信息1
        printAuthors(authors);

    }

    private static void printAuthors(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17&&author.getName().length()>1;
                    }
                })
                .forEach(author -> System.out.println(author.getAge()+" "+author.getName()));
    }

    private static Optional<Author> secureNum() {
        return Optional.ofNullable(null);
    }

    private static  Optional<Author> getAthors2() {
        Author author1 = new Author(1L, "亚索", "my introduction 1", 33, null);
        return author1==null?Optional.empty():Optional.of(author1);
    }

    public static Optional<Author> getAthors() {
        Author author1 = new Author(1L, "亚索", "my introduction 1", 33, null);
        return Optional.ofNullable(author1);
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
