package com.test.telegram.service;

import com.test.telegram.data.Domain;
import com.test.telegram.repo.DomainRepo;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    private final DomainRepo domainRepo;

    public DomainService(DomainRepo domainRepo) {
        this.domainRepo = domainRepo;
    }

    public Domain findById(int id) {
        return domainRepo.findById(id).orElseThrow();
    }

    public long count() {
        return domainRepo.count();
    }

    public Domain save(Domain domain) {
        return domainRepo.save(domain);
    }

    public Iterable<Domain> saveAll(Iterable<Domain> domains) {
        return domainRepo.saveAll(domains);
    }

    public void deleteAll() {
        domainRepo.deleteAll();
    }
}
