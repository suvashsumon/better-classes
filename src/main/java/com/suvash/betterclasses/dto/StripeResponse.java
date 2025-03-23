package com.suvash.betterclasses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StripeResponse<T1, T2, T3>{
    private T1 first;
    private T2 second;
    private T3 third;
}
