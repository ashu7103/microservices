package com.programmingtechie.orderservice.controller;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @CircuitBreaker(name="inventory",fallbackMethod = "fallbackMethod")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
    public String fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return "OOps, Something went wrong, TRY AGAIN LATER";
    }
//-----------------------------------------------------------------------------------------------------------
    @PostMapping("/testing")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory1", fallbackMethod = "fallbackMethod1")
    @TimeLimiter(name="inventory1")
//    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder1(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()->orderService.placeOrder1(orderRequest));
    }

    public CompletableFuture<String> fallbackMethod1(RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()-> "Oops! Something went wrong, please try again later!");
    }
}
