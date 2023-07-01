package com.zerobase.store.service;

import com.zerobase.store.domain.UploadForm;
import org.springframework.stereotype.Service;

@Service
public interface StoreService {
    default boolean Upload(UploadForm form) {return false;}
}