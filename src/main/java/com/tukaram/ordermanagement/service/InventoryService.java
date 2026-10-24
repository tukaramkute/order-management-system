package com.tukaram.ordermanagement.service;

import com.tukaram.ordermanagement.entity.Inventory;
import com.tukaram.ordermanagement.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        if (inventory.getSku() == null
                || inventory.getSku().isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }

        if (inventory.getProductName() == null
                || inventory.getProductName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (inventory.getAvailableQuantity() == null
                || inventory.getAvailableQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Quantity must be zero or greater");
        }

        if (inventoryRepository.existsBySku(inventory.getSku())) {
            throw new IllegalArgumentException("SKU already exists");
        }

        return inventoryRepository.save(inventory);
    }
}
