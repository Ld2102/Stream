package com.flow.optional;

import com.flow.Streamdemo_01.Author;
import com.flow.Streamdemo_01.Book;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * liudong
 * 2022/10/27
 **/
public class AndTest {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        //打印作家年龄>17并且姓名长度>1的作家信息2
        printAuthors2(authors,new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge()>17;
            }
        },new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getName().length()>1;
            }
        });
    }
    private static void printAuthors2(List<Author> authors,Predicate<Author> Predicate1,Predicate<Author> Predicate2) {
            authors.stream()
                    .distinct()
                    .filter(Predicate1.and(Predicate2))
                    .forEach(System.out::println);

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
