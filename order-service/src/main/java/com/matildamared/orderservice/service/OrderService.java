package com.matildamared.orderservice.service;

import com.matildamared.orderservice.dto.OrderLineItemDto;
import com.matildamared.orderservice.dto.OrderRequest;
import com.matildamared.orderservice.model.Order;
import com.matildamared.orderservice.model.OrderLineItems;
import com.matildamared.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(this::mapToOrderLineItem)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItem(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItems;
    }
}
