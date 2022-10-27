# lombok

## 注解

```
@Data//自动创建set get方法 hash equas tosting等等
@NoArgsConstructor //生成有参构造
@AllArgsConstructor//生成无参构造
@EqualsAndHashCode//用于去重，注在类上，提供对应的 equals 和 hashCode 方法
```

## 依赖

```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.16</version>
</dependency>
```