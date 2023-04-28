package org.parrolabs.api.orders.service;

import java.util.List;
import java.util.Optional;

import org.parrolabs.api.model.customers.Customers;
import org.parrolabs.api.model.orders.Orders;
import org.parrolabs.api.model.products.Products;
import org.parrolabs.api.repository.customers.CustomersRepository;
import org.parrolabs.api.repository.orders.OrdersRepository;
import org.parrolabs.api.repository.products.ProductsRepository;
import org.parrolabs.api.repository.shippingaddress.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ShippingRepository shippingRepository;


    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id).get();
    }

    public Orders saveOrder(Orders orders) {
        if (!customersRepository.existsById(orders.getCustomer().getId())) {
            orders.setCustomer(customersRepository.save(
                    orders.getCustomer()
            ));
        }

        if (!shippingRepository.existsById(orders.getShippingAddress().getId())) {
            orders.setShippingAddress(shippingRepository.save(
                    orders.getShippingAddress()
            ));
        }

        Orders newOrder = new Orders(-1L,
                orders.getDate(),
                orders.getCustomer(),
                orders.getShippingAddress(),
                orders.getPaymentType(),
                orders.getListOrdersProducts(),
                orders.getTotalValue());

        return ordersRepository.save(newOrder);
    }

    public Boolean deleteOrder(Long id) {

        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

