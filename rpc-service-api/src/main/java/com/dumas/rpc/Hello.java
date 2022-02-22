package com.dumas.rpc;

import lombok.*;

import java.io.Serializable;

/**
 * @author dumas
 * @date 2022/02/16 1:55 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    private String message;
    private String description;
}
