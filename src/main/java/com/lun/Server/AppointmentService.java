package com.lun.Server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lun.enity.Appointment;



public interface AppointmentService extends IService<Appointment> {

    Appointment getOne(Appointment appointment);
}
