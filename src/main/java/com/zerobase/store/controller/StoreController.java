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

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

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

    @RequestMapping(value = "/reservation")
    public String reservation() {
        return "store/reservation";
    }

    @GetMapping(value = "/reservation")
    public String reservation(HttpSession session, Model model) {
        String storeId = (String) session.getAttribute("storeId");
        model.addAttribute("storeId", storeId);
        return "store/reservation";
    }

    @PostMapping(value = "/reservation")
    public String reservationSubmit(
            @RequestParam(value = "datetime") LocalDateTime dateTime,
            @RequestParam(value = "storeId") Long storeId
    ) {
        return null;
    }
}