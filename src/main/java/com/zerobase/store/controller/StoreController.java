package com.zerobase.store.controller;

import com.zerobase.store.domain.UploadForm;
import com.zerobase.store.service.StoreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreServiceImpl storeService;

    @GetMapping(value = "/upload")
    public String upload() {

        return "store/upload";
    }

    @PostMapping(value = "/upload")
    public String uploadSubmit(UploadForm uploadForm) {
        if (!storeService.Upload(uploadForm)) {
            return "store/upload_failure";
        }
        return "store/upload_complete";
    }


}