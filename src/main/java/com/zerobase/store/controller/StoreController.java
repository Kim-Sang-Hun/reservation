package com.zerobase.store.controller;

import com.zerobase.store.domain.UploadStoreForm;
import com.zerobase.store.service.StoreServiceImpl;
import lombok.RequiredArgsConstructor;
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
    public String upload() {
        return "store/upload";
    }

    @PostMapping(value = "/upload")
    public String uploadSubmit(UploadStoreForm uploadStoreForm) {
        if (!storeService.uploadStore(uploadStoreForm)) {
            return "store/upload_failure";
        }
        // 업로드 했는데 유저아이디에 스토어아이디가 없는 문제 발생.
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