package com.anavis.service;

import com.anavis.domain.Bloodcount;

public interface BloodcountService {
    Bloodcount getBloodcount();
    Bloodcount save(Bloodcount bloodcount);
}
