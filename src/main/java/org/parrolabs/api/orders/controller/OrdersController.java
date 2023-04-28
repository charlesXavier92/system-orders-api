package org.parrolabs.api.orders.controller;

import org.parrolabs.api.model.orders.Orders;
import org.parrolabs.api.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    public static final String GET_ORDERS_ID = "/getById/{id}";
    public static final String SAVE_ORDER = "/save";
    public static final String DELETE_CUSTOMER = "/delete/{id}";

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Return the Order by Id", response = Orders.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(GET_ORDERS_ID)
    public ResponseEntity<Orders> getOrderById(
            @PathVariable("id") Long id) {
        Orders result = null;
        try {
            result = orderService.getOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Create the Order", response = Orders.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping (SAVE_ORDER)
    public ResponseEntity<Orders> saveOrder(
            @RequestBody Orders orders) {
        Orders result = null;
        try {
            result = orderService.saveOrder(orders);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete the Order", response = Orders.class)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping (DELETE_CUSTOMER)
    public ResponseEntity<Boolean> deleteOrder(
            @PathVariable("id") Long id) {
        Boolean result = false;
        try {
            result = orderService.deleteOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
