package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;

//@Service
public interface DishService {

    /**
     * 新增菜品and relevant flavor
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
