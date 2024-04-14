package com.codecraftershub.telemedicine.services;

import com.codecraftershub.telemedicine.dtos.responses.lookups.LookupData;
import com.codecraftershub.telemedicine.enums.BloodGroup;
import com.codecraftershub.telemedicine.enums.Gender;
import com.codecraftershub.telemedicine.enums.doctors.Title;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupService {
    public List<LookupData> getBloodGroups() {
        List<LookupData> bloodGroups = new ArrayList<>();

        for (BloodGroup bloodGroup : BloodGroup.values()) {
            bloodGroups.add(LookupData.builder().name(bloodGroup.getDisplayValue()).code(bloodGroup.toString()).build());
        }

        return bloodGroups;
    }

    public List<LookupData> getGenders() {
        List<LookupData> genders = new ArrayList<>();

        for (Gender gender : Gender.values()) {
            genders.add(LookupData.builder().name(gender.getDisplayValue()).code(gender.toString()).build());
        }

        return genders;
    }

    public List<LookupData> getTitles() {
        List<LookupData> titles = new ArrayList<>();

        for (Title title : Title.values()) {
            titles.add(LookupData.builder().name(title.getDisplayValue()).code(title.toString()).build());
        }

        return titles;
    }
}
