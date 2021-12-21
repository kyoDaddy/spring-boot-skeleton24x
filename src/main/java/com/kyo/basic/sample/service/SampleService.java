package com.kyo.basic.sample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class SampleService {

    @Cacheable(cacheNames = "samples")
    public List<String> getSamples() {

        Random random = new Random();
        int nums = random.nextInt(10);
        List<String> samples = new ArrayList<>();
        for(int i=0; i<nums; i++) samples.add("K" + i);

        samples.forEach(log::info);

        return samples;
    }

}
