package com.tukaram.ordermanagement.controller;

import com.tukaram.ordermanagement.entity.Inventory;
import com.tukaram.ordermanagement.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(
            @RequestBody Inventory inventory) {

        Inventory savedInventory =
                inventoryService.createInventory(inventory);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedInventory);
    }
}
