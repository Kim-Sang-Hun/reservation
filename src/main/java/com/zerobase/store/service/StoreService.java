package com.zerobase.store.service;

import com.zerobase.store.domain.ReservationForm;
import com.zerobase.store.domain.UploadStoreForm;
import com.zerobase.store.domain.model.Store;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    default boolean uploadStore(UploadStoreForm form) {return false;}
    default List<Store> searchStore(String storeName) {return null;}
    default boolean uploadReservation(ReservationForm form) {return false;}
}