package com.zerobase.store.service;

import com.zerobase.member.domain.model.Member;
import com.zerobase.member.repository.MemberRepository;
import com.zerobase.store.domain.UploadStoreForm;
import com.zerobase.store.domain.model.Store;
import com.zerobase.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean uploadStore(UploadStoreForm form) {
        Optional<Store> optionalStore = storeRepository.findByStoreLocation(form.getStoreLocation());
        if (optionalStore.isPresent()) {
            return false;
        }
        Optional<Member> optionalMember = memberRepository.findByUsername(form.getUsername());
        if (optionalMember.isEmpty()) {
            return false;
        }
        Member member = memberRepository.getByUsername(form.getUsername());
        if (member.getStore() != null) {
            return false;
        }
        Store store = Store.from(form);
        store.setMember(member);
        member.setStore(store);
        storeRepository.save(store);
        memberRepository.save(member);
        return true;
    }

    @Override
    public List<Store> searchStore(String storeName) {
        Optional<List<Store>> optionalStore = storeRepository.findByStoreName(storeName);
        if (optionalStore.isEmpty()) {
            return null;
        }
        return storeRepository.getByStoreName(storeName);
    }
}