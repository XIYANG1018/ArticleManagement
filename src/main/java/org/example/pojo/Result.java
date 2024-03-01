package org.example.pojo;


// Unified Response Result

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; // status codes: 0 - Success, 1 - Failure
    private String message;
    private T data; //Response Data

    //Quickly return a successful response result (with response data).
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Success", data);
    }


    public static Result success() {
        return new Result(0, "Success", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
