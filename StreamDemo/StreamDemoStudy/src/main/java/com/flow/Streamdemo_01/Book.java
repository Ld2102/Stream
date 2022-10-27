/*
 * @author yjiewei
 * @date 2022/2/13 15:32
 */
package com.flow.Streamdemo_01;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String category;
    private String name;
    private Double score;
    private String introduction;

}
