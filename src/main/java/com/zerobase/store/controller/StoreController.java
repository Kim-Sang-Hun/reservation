package com.zerobase.store.controller;

import com.zerobase.store.domain.UploadStoreForm;
import com.zerobase.store.service.StoreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreServiceImpl storeService;

    @GetMapping(value = "/upload")
    public String upload(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "store/upload";
    }

    @PostMapping(value = "/upload")
    public String uploadSubmit(UploadStoreForm uploadStoreForm) {
        if (!storeService.uploadStore(uploadStoreForm)) {
            return "store/upload_failure";
        }
        return "store/upload_complete";
    }

    @GetMapping(value = "/search")
    public String search() {
        return "store/search";
    }

    @PostMapping(value = "/search")
    public String searchSubmit(@RequestParam String storeName, Model model) {
        model.addAttribute("storeList", storeService.searchStore(storeName));
        if (model.getAttribute("storeList") == null) {
            return "store/search_failure";
        }
        return "store/search_success";
    }
}