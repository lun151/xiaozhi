package com.lun.java.ai.langchain4j;


import com.lun.Server.AppointmentService;
import com.lun.enity.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("内科");
        appointment.setDate("2024-04-14");
        appointment.setTime("上午");
        appointment.setDoctorName("张医生");
        Appointment one = appointmentService.getOne(appointment);
        System.out.println(one);
    }

    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("123456789012345678");
        appointment.setDepartment("内科");
        appointment.setDate("2024-04-14");
        appointment.setTime("上午");
        boolean save = appointmentService.save(appointment);
        System.out.println(save);
    }

    @Test
    void testRemove() {
        appointmentService.removeById(1L);
    }
}
