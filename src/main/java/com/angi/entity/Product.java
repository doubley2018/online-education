package com.angi.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author angi
 * @date 2021/1/22 18:38
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
