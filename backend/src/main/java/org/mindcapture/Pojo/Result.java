package org.mindcapture.Pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <E> Result<E> success(E data){
        return new Result<E>(0, "success", data);
    }

    public static Result success(){
        return new Result<>(0, "success", null);
    }

    public static Result error(String message){
        return new Result<>(1, message, null);
    }

}
