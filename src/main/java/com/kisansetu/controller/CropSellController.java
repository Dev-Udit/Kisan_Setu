package com.kisansetu.controller;

import com.kisansetu.entity.CropSellRequest;
import com.kisansetu.service.CropSellService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sell")
public class CropSellController {

    private final CropSellService sellService;

    public CropSellController(CropSellService sellService) {
        this.sellService = sellService;
    }

    @PostMapping("/crop/{cropId}")
    public CropSellRequest sellCrop(@PathVariable Long cropId,
                                    @RequestParam String mode) {
        return sellService.sellCrop(cropId, mode);
    }
}
