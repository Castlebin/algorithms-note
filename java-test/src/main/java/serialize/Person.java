package serialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

// java 本身的序列化反序列化机制还有 hessian都要求要序列化的对象的类实现Serializable接口
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person implements Serializable {

    private String name;
    private int age;

}
