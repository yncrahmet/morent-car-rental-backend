package com.archisacademy.morent.ModelMappper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelMapperServiceImpl implements ModelMapperService {

    private final ModelMapper modelMapper;


    @Override
    public ModelMapper request() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setAmbiguityIgnored(true);

        return modelMapper;
    }//Request yeni veri eklerken bunu kullanıyoruz.

    @Override
    public ModelMapper response() {
        this.modelMapper.getConfiguration().
                setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);
        return null;
    }
}
