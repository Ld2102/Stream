视频教学来源b站up主三更草堂：https://www.bilibili.com/video/BV1Gh41187uR/?p=55&spm_id_from=pageDriver

# 概念

## 作用

- 大数量下处理集合效率高
- 代码可读性高
- 消灭嵌套地狱

![image-20221025170204791](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221025170204791.png)

解决

![image-20221025170321044](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221025170321044.png)

## 函数式编程思想

### 概念

- #### 主要关注数据对数据进行了什么操作

### 优点

- 代码简洁
- 提高开发效率
- 易于并发编程

## Lambda表达式

### 概述

- 对某县匿名内部类写法优化，函数式编程重要提现，不用关注什么是对象，专注于数据操作

### 核心原则

- 可推导，可省略

不在关注类名、方法名，只关注操作什么数据，做什么操作

### 格式

(参数列表)->{具体代码 }

调用前方法形参得是接口,lambda表达式就是重写接口方法做优化

### 示例1：

指定数组判断奇偶

```
public class Test01 {
    public static void main(String[] args) {
    //匿名内部类
printNum(new IntPredicate() {
    @Override
    public boolean test(int value) {
        return value%2==0;
    }
});
	//lambda表达式
printNum(value -> value%2==0?true:false);
    }
    public static void printNum(IntPredicate intPredicate){
        int arr[]={1,2,3,4,56,6};
        for (int i : arr) {
            if (intPredicate.test(i))
            System.out.println(i);
        }
    }
}
```

### 示例2：

String转Intger

```
public class Test02 {
    public static void main(String[] args) {
        Integer typeconver = typeconver(aa -> Integer.parseInt(aa));
        System.out.println(typeconver);
    }
    public static <R>R typeconver(Function<String,R>function){
        String str="12345";
        R apply = function.apply(str);
        return apply;
    }
}
```

## 优化规则

-  参数类型可省略

- 方法题只有一句大括号可省略，分号省略，是一条返回值时return省略

- 形参只有一个参数时可省略小括号

## Stream使用

- 首先创建流 .stream

- .distinct去重

- .filter设置过滤条件

- .foreach终结操作


## List创建Stream流

```
public static void main(String[] args) {
        List<Author> authors = getAuthors();
        //去重，打印小于18岁的作者和他的书名
//        extracted(authors);
    }

    private static void extracted(List<Author> authors) {
        authors
                .stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> out.println(author.getName()));
    }
```

## 数组创建Stream流

```
public class Test01 {
    public static void main(String[] args) {
        Integer[]ints={1,22,33,4566,6};
//        IntStream stream = Arrays.stream(ints);
        Stream<Integer> stream = Stream.of(ints);
        stream.forEach(integer-> System.out.println(integer));
    }
}
```

有两种方式

- Array.Stream（）；

- Stream.of（）；

## Map创建Stream流

```
public class mapStreamDemo {
    public static void main(String[] args) {
        Map<String,Integer>map=new HashMap<>();
        map.put("雪代巴", 18);
        map.put("慕南栀",25);
        map.put("怀庆",18);
        //map转为单列集合
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        //输出年龄小于20的所有信息
        entries.stream()
                .filter(new Predicate<Map.Entry<String, Integer>>() {
                    @Override
                    public boolean test(Map.Entry<String, Integer> stringIntegerEntry) {
                        return stringIntegerEntry.getValue()<20;
                    }
                })
                .forEach(new Consumer<Map.Entry<String, Integer>>() {
                    @Override
                    public void accept(Map.Entry<String, Integer> stringIntegerEntry) {
                        System.out.println(stringIntegerEntry.getKey()+"--"+stringIntegerEntry.getValue());
                    }
                });
    }
}
```

## 中间处理

### filter

进行过滤和筛选，符合过滤条件才能够保留在流当中

return Boolean使用

### map

对流元素进行类型转换，和相应的计算

#### 示例1

```
//输出作者姓名
private static void changeOfClass(List<Author> authors) {
    authors.stream()
            .distinct()
            .map(new Function<Author, String>() {
                @Override
                public String apply(Author author) {
                    return author.getName();
                }
            })
            .forEach(System.out::println);
}
```

#### 示例2

```
//作者年龄加10
private static void printAgeAddTen(List<Author> authors) {
    authors.stream()
            .map(author -> author.getAge())
            .map(ages->ages+10)
            .forEach(System.out::println);
}
```

### distinct

依赖的是object的equals方法判断是否相同来去重，重写了equals方法会用重写 的equals来判断

去重操作

### sorted

对流元素进行排序

#### 示例1

对作家进行排序并且不能重复

![image-20221026133702797](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221026133702797.png)

##### 有两个构造方法

###### 第一个构造方法 

​	实体类需要实现comparable接口，和treeset进行排序一样操作

###### 第二个构造方法（常用）

​	就是new一个comparator比较器，重写compare方法

​	ps:比较两两相减 O1在前为降序 O2在前为升序	O1-O2为true 说明第一个大于第二个

### limit

设置流最大长度限制，超出部分被抛弃

##### 示例1

```
//对作家信息降序，去重，得到两个最大年龄作家信息
private static void sortAuthorsDistinct_2(List<Author> authors) {
    authors.stream().distinct()
            .sorted()
            .limit(2)
            .forEach(System.out::println);
}
```

### skip

跳过流当中的前N个元素，返回剩下元素

##### 示例1

```
//对作家信息降序，去重，非最大年龄的所有作者信息
private static void noMaxAuthorsprint(List<Author> authors) {
    authors.stream().distinct()
            .sorted((o1, o2) -> o2.getAge()- o1.getAge())
            .skip(1)
            .forEach(System.out::println);
}
```

### flatmap（难）

map是把一个对象转换成另外一个对象，flatmap则是把一个对象转换为多个对象

##### 示例1

一个对想想包含集合，集合存放多个元素，把对象转换为集合创建流，所以是一个对像转换为多个对象

ps：测试books集合为空，会不会空指针异常 

结论：不会

```
//打印所有书籍名字，相同名字去重
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
```

##### 示例2

```
打印所有书籍类型,如果一个书籍有两个类型如xx,xx 要拆开
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
```

## 终结操作

### foreach

对流中的元素进行遍历操作，通过传入参数取指定对遍历到的元素进行什么具体操作

### count

获取流中元素个数 

#### 示例1

```
//统计非重复书籍数目
private static void pringtBooksCounts(List<Author> authors) {
    long count = authors.stream()
            .flatMap(author -> author.getBookList().stream())
            .distinct()
            .count();
    System.out.println(count);

}
```

### max&min

获取流中元素最大值和最小值

#### 示例1

```
//打赢作家所出的最高分和最低分(加点难度
rivate static void pringtScore(List<Author> authors) {
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
```

### Collect

把流中元素转换为集合

#### 示例1

```
//获取一个存放所有作者name的list
private static void getAllAuthoreName(List<Author> authors) {
    List<String> collect = authors.stream()
            .map(author -> author.getName())
            .distinct()
            .collect(Collectors.toList());
    System.out.println(collect);
}
```

#### 示例2

```
//获取一个存放所有书名放到set
private static void getAllBookToSet(List<Author> authors) {
    Set<String> collect = authors.stream()
            .flatMap(author -> author.getBookList().stream())
            .map(book -> book.getName())
            .collect(Collectors.toSet());

    System.out.println(collect);
}
```

#### 示例3

```
//获取一个map集合，key为作者名字，value为list<book>
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
```

### 查找与匹配

#### anymatch

判断流中元素是否符合，任意一个符合结果为true

总结：一个满足为true

##### 示例1

```
判断年龄是否有大于25的
private static void judgeAge(List<Author> authors) {
        boolean b = authors.stream().map(author -> author.getAge())
                .anyMatch(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer > 25;
                    }
                });
        System.out.println(b);

    }
```

#### AllMatch

判断流中所有元素都匹配则返回true

总结：全部满足为true

##### 示例1

```
//判断年龄是否有>=18岁以上的作家
private static void judgeAge_02(List<Author> authors) {
    boolean b = authors.stream()
            .map(author -> author.getAge())
            .allMatch(age -> age >= 18);
    System.out.println(b);
}
```

#### noneMatch

总结：都不满足为true

```
//判断年龄是否都没超过100岁的作家
private static void judgeAge_03(List<Author> authors) {
    boolean b = authors.stream()
            .map(author -> author.getAge())
            .noneMatch(age -> age > 100);
    System.out.println(b);
}
```

#### findany

获取流中任意一个与之匹配的元素

（测试结果:一直是同一个元素，所以应该是获取流中一个元素，但不是按流元素顺序获取，第一次获取无法确定为哪一个

##### 示例1

```
//获取任意一个大于14的作者姓名
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
```

#### findfirst

获取流中第一个元素

##### 示例1

```
//获取年龄最小的作家姓名 思路 升序 取第一个 可以用limit
 private static void findFirstAuthorsMinAge(List<Author> authors) {
        Optional<Author> first = authors.stream()
                        .sorted((author1,author2)->author2.getAge()- author1.getAge())
                                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }
```

#### reduce归并(难)

对流当中的元素按照指定方式得出一个结果（缩减操作）

![image-20221027013329990](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027013329990.png)

把Sream流中元素组合，传入一个初始值，然后根据从流中依次取值在初始化值的基础上进行计算，计算的结果在和后面的元素计算

1.首先指定一个初始化变量值 identity ，element=流元素

T result=（idengtity）；//result初始变量赋值

for（T e ：this stream）

result=自定义操作

return result

##### 示例1

```
//使用reduce求所有作者年龄的和
private static void subAuthorsAge(List<Author> authors) {
    Integer reduce = authors.stream()
            .map(author -> author.getAge())
            .distinct()
            .reduce(0, new BinaryOperator<Integer>() {
                @Override
                public Integer apply(Integer Rerult, Integer element) {
                    return Rerult + element;
                }
            });
    System.out.println(reduce);
}
```

##### 示例2

```
//使用reduce打印年龄最大值,采用两个参数的重载方法
private static void MaxAuthorsAge(List<Author> authors) {
    Integer reduce = authors.stream()
            .distinct()
            .map(author -> author.getAge())
            .reduce(0, (rerult, element) -> {
              /*  if (element > rerult) {
                    rerult = element;
                }*/
                //流元素反复与初始值比较，大于则赋值给初始值变量
                return rerult<element?element:rerult;
            });
    System.out.println(reduce);
}
```

##### 示例3

result一个参数的重载方法

​	首先这个for循环if条件 是拿流中第一个元素作为初始值使用，因为只执行一次 ，后续操作和有自定义初始值的操作类似

![image-20221027122207237](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027122207237.png)

```
//使用reduce打印年龄最小值,采用一个参数的重载方法
private static void MinAuthorsAge(List<Author> authors) {
    Optional<Integer> reduce = authors.stream()
            .distinct()
            .map(author -> author.getAge())
            .reduce(new BinaryOperator<Integer>() {
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
```

## stream注意事项

1.惰性求值 必须要有终结操作，否则中间操作无法执行

2.流是一次性的（一个流使用了一次终结操作就无法继续使用该流）

3.不会影响原数据(对流数据处理，不会影响集合的元素)

## optional

用来避免空指针异常，原本是采用if判断对象是否为null

optional相当于把数据封装成optional的一个属性，通过调用optional的避免空指针、

### ofNullable()（常用）

通过optional静态方法;ofNullable()封装对象; 

- 使用方式

类名.ofNullable(); 泛型为所指定的参数类型

#### 示例1

```
//使用optional封装对象
public class optionalDemo_01 {
    public static void main(String[] args) {

        Optional<Author> optionalAuthor = getAthors();
        optionalAuthor.ifPresent(author1 -> System.out.println(author1.getName()));
    }

    public static Optional<Author> getAthors() {
        Author author1 = new Author(1L, "亚索", "my introduction 1", 33, null);
        return Optional.ofNullable(author1);
    }
}
```

### of()(不常用)

如果确定一个对象不为null则用of(),为null 抛出空指针

### empty(不常用)

如果确定一个对象为null则用empty();

##### 示例1

```
//为null用empty，反之of
private static  Optional<Author> getAthors2() {
    Author author1 = new Author(1L, "亚索", "my introduction 1", 33, null);
    return author1==null?Optional.empty():Optional.of(author1);
}
```



### 安全消费（常用）：

ifpresent

### 获取值：

get（）；明确知道不会空指针则可以用

### 安全获取值：

#### ofElseGet

如果数据为空则返回设置的默认值，不为空则返回数据

##### 示例1

```
//安全获取值
Optional<Author> optionalAuthor1 = secureNum();
Author author = optionalAuthor1.orElseGet(new Supplier<Author>() {
    @Override
    public Author get() {
        return new Author();
    }
});
System.out.println(author);

private static Optional<Author> secureNum() {
        return Optional.ofNullable(null);
    }
```

#### ofElsethrow

如果数据为空抛出异常，不为空正常取出数据

### filter过滤

把filter过滤的不符合条件数据封装成新的optional对象

#### 示例1

```
//获得不是15岁以上的对象
Optional<Author> optionalAuthor3 = secureNum();
Optional<Author> optionalAuthor4 = optionalAuthor3.filter(new Predicate<Author>() {
    @Override
    public boolean test(Author author) {
        return author.getAge() > 18;
    }
});
optionalAuthor4.ifPresent(author1 -> System.out.println(author1));
```

### ispresent判断

对存在数据进行判断，存在为true

#### 示例1

```
//判断数据是否存在
Optional<Author> athors21 = getAthors2();
if (athors21.isPresent()){
    System.out.println(athors21.get().getAge());
    System.out.println(athors21.get().getBookList());
}
```

### map数据转换

对单个对象使用 

#### 示例1

```
//数据类型转换
Optional<Author> athors22 = getAthors2();
Optional<List<Book>> books = athors22.map(new Function<Author, List<Book>>() {
    @Override
    public List<Book> apply(Author author) {
        return author.getBookList();
    }
});
books.ifPresent(book-> System.out.println(book));
```

## 四大函数式接口

定义：只有一个抽象方法的接口，称函数式接口

```
@FunctionalInterface//可以通过此注解来表示是函数式接口
```

### consumer消费型接口 

- 接口方法

```
 void accept（T t）；
```

- 作用

重写此方法只能去消费t，比如做输出

### function计算型接口

- 接口方法

```
R apply(T t);
```

- 作用

可以对数据进行操作计算，做转换

### predicate判断型接口

- 接口方法

```
boolean test(T t);
```

### supplier生产型接口

- 接口方法

```
T get();
```

没有参数，需要自己创建个对象进行返回，所以称为生产



### ps：接口名称前面出现bi则是两个形参

![image-20221027163617115](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027163617115.png)

## 常用默认方法

### and

predice接口下的一个短路与&&操作

```
//源码
default Predicate<T> and(Predicate<? super T> other) {
    Objects.requireNonNull(other);
    return (t) -> test(t) && other.test(t);
}
```

#### 示例1

```
//打印作家年龄>17并且姓名长度>1的作家信息 （鸡肋不如直接写短路与
        printAuthors(authors);
    }

    private static void printAuthors(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17;
                    }
                }.and(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length()>1;
                    }
                }))
                .forEach(author -> System.out.println(author.getAge()+" "+author.getName()));
    }
```



#### 示例2

```
//等同于示例1 只是换了匿名内部类new的位置，使方法调用相对灵活
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
                    .filter(Predicate1.and(Predicate2))//只是让这看起来灵活
                    .forEach(System.out::println);

    }
```

### or

predice接口下的一个短路或||操作

```
//源码
default Predicate<T> or(Predicate<? super T> other) {
    Objects.requireNonNull(other);
    return (t) -> test(t) || other.test(t);
}
```

### negate

相当于！

## 默认方法总结

就相当于与或非，只不过，只有传递判断性接口的参数才能调用,传递参数可以是调方法时new，也可以是使用默认方法时候new

## 方法引用

进一步简化lambda表达式

- 大前提：方法体只有一行代码，代码只是某个方法的调用则可以使用

- 操作：类名或对象名 : :（方法引用符 )方法名

#### 示例1

```
//laambda表达式
.map(author -> author.getAge())
//方法引用
.map(Author::getAge)
```

### 引用类的静态方法

重写方法，方法体只有一行代码，该行代码是调用某个类的静态方法，并且把重写的抽象方法的参数传入该静态方法

#### 示例1

![image-20221027192934998](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027192934998.png)

使用方法引用

```
.map(String::valueof);
```

### 引用对象的实例方法1

重写方法，方法体只有一行代码，该行代码是调用某个类的成员方法，并且把重写的抽象方法的参数传入该成员方法

#### 示例1

![image-20221027195658849](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027195658849.png)

使用方法引用  局部变量调用的实例方法

```
.map(sb::append);对象名＋方法名
```

### 引用类的实例方法2

重写方法，方法体只有一行代码，该行代码是调用 第一个参数的成员方法，并且把重写的抽象方法的 所有剩余的参数 传入这成员方法中

#### 示例1

### ![image-20221027215041750](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027215041750.png)

由一个自定义接口+静态方法+调用静态方法匿名内部类重写接口方法

![image-20221027215905808](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027215905808.png)

第一个参数调用方法，剩下参数都得传入所调用的方法中

形参调用方法 则是类名

方法引用：：

调用方法

### 构造器引用

重写方法，方法体只有一行代码，该行代码是调用某个类的构造方法，并且把重写的抽象方法的参数传入该构造方法中

#### 示例1

![image-20221027222820379](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027222820379.png)

优化后

![image-20221027222943119](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027222943119.png)

构造器类+ ：：+new关键字

调用方法只能.一次所以第三个不行

打印语句 System.out  out是System一个静态变量  方法是println(是个重载方法 ，并且传入参数 str)所以是System.out : : print)

![image-20221027223545148](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027223545148.png)



## Stream流高级用法

Stream的方法都使用了泛型，而泛型得是引用类型，如integer 在做计算时就自动拆箱 如果返回值为integer又自动装箱，当数据量大的时候 就重复装箱拆箱

![image-20221027230201958](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027230201958.png)

## 并行流

串行流 一个线程完成了流所有操作

并行流针对大数据量操作，把数据交给多个线程处理最后结果做个汇总，组合

#### 示例1

![image-20221027232031045](C:\Users\LD18227837187\Desktop\Stream流\images\image-20221027232031045.png)

parallel() 帕瑞拉儿，把并行流转换为串行流

parallelStream（）直接得到并行流

peek 则是可以查看并行流线程信息