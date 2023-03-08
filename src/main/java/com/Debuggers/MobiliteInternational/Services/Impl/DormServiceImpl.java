package com.Debuggers.MobiliteInternational.Services.Impl;
import com.Debuggers.MobiliteInternational.Entity.Dormitories;
import com.Debuggers.MobiliteInternational.Repository.DormRepository;
import com.Debuggers.MobiliteInternational.Services.DormService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DormServiceImpl implements DormService {

    DormRepository dormitoriesRepository;

    @Override
    public List<Dormitories> getAllDorm() {
        return dormitoriesRepository.findAll();
    }

    @Override
    public Dormitories getDormById(long id) {
        return dormitoriesRepository.findById(id).orElse(null);
    }

    @Override
    public Dormitories addDorm(Dormitories d) {
        return dormitoriesRepository.save(d);
    }

    @Override
    public Dormitories UpdateDorm(Dormitories d) {
        return dormitoriesRepository.save(d);
    }

    @Override
    public void deleteDorm(long id) {
        dormitoriesRepository.deleteById(id);
    }

}
