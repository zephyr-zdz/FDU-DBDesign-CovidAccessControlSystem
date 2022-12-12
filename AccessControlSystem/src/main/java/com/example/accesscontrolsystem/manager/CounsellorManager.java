package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.CounsellorMapper;
import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("CounsellorManager")
public class CounsellorManager {
    private final CounsellorMapper counsellorMapper;
    @Autowired
    public CounsellorManager(CounsellorMapper counsellorMapper) {
        this.counsellorMapper = counsellorMapper;
    }
    public Optional<Counsellor> findCounsellorById(Integer counsellorId) {
        return counsellorMapper.findCounsellorById(counsellorId);
    }
    public List<Counsellor> getCounsellors() {
        return counsellorMapper.findAll();
    }
    public Counsellor addCounsellor(Counsellor counsellor) {
        return counsellorMapper.save(counsellor);
    }
}
