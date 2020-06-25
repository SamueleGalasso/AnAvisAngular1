package com.anavis.service.impl;

import com.anavis.domain.Bloodcount;
import com.anavis.repository.BloodcountRepository;
import com.anavis.service.BloodcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodcountServiceImpl implements BloodcountService {
    @Autowired
    private BloodcountRepository bloodcountRepository;


    @Override
    public Bloodcount getBloodcount() {
        List<Bloodcount> bloodcountList = (List<Bloodcount>) bloodcountRepository.findAll();
        System.out.println(bloodcountList);
        if(bloodcountList.size() == 0){
            return null;
        }
        return bloodcountList.get(0);
    }

    @Override
    public Bloodcount save(Bloodcount bloodcount) {
        return bloodcountRepository.save(bloodcount);
    }
}
