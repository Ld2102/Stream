/*
 * @author yjiewei
 * @date 2022/2/13 15:34
 */
package com.flow.Streamdemo_01;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Author{
    private Long id;
    private String name;
    private String introduction;
    private Integer age;
    private List<Book> bookList;

}
