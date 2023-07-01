package com.zerobase.store.service;

import com.zerobase.member.domain.model.Member;
import com.zerobase.member.repository.MemberRepository;
import com.zerobase.store.domain.UploadForm;
import com.zerobase.store.domain.model.Store;
import com.zerobase.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean Upload(UploadForm form) {
        Optional<Store> optionalStore = storeRepository.findByStoreLocation(form.getStoreLocation());
        if (optionalStore.isPresent()) {
            return false;
        }
        Optional<Member> optionalMember = memberRepository.findByUsername(form.getUsername());
        if (optionalMember.isEmpty()) {
            return false;
        }
        Store store = Store.from(form);
        store.setMember(memberRepository.getByUsername(form.getUsername()));
        storeRepository.save(store);
        return true;
    }
}