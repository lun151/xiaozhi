package com.lun.Server.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lun.Server.AppointmentService;
import com.lun.enity.Appointment;
import com.lun.mapper.AppointmentMapper;
import kotlin.jvm.internal.Lambda;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper,Appointment>
        implements AppointmentService {
    /**
     * 获取预约信息
     * @param appointment
     * @return
     */

    @Override
    public Appointment getOne(Appointment appointment) {
        LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Appointment::getUsername,appointment.getUsername());
        queryWrapper.eq(Appointment::getIdCard,appointment.getIdCard());
        queryWrapper.eq(Appointment::getDepartment,appointment.getDepartment());
        queryWrapper.eq(Appointment::getDate,appointment.getDate());
        queryWrapper.eq(Appointment::getTime,appointment.getTime());

        Appointment appointmentDB = baseMapper.selectOne(queryWrapper);

        return appointmentDB;
    }
}
