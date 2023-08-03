package com.dsecurity.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private Long quantity;
}
