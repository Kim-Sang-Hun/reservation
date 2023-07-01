package com.zerobase.store.repository;

import com.zerobase.store.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreLocation(String storeLocation);
    Optional<List<Store>> findByStoreName(String storeName);
    List<Store> getByStoreName(String storeName);
}