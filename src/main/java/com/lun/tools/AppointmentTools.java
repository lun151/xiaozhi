package com.lun.tools;

import com.lun.Server.AppointmentService;
import com.lun.enity.Appointment;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTools {

    @Autowired
    private AppointmentService appointmentService;

    @Tool(name = "预约挂号", value = "根据参数，先执行工具方法queryDepartment查询是否可预约，" +"并直接给用户回答是否可预约，并让用户确认所有预约信息，用户确认后再进行预约。" +
            "如果用户没有提供具体的医生姓名，请从向量存储中找到一位医生。")
    public String bookAppointment(Appointment appointment){  //查找数据库中是否包含对应的预约记录
         Appointment appointmentDB = appointmentService.getOne(appointment);
         if(appointmentDB == null){
             appointment.setId(null);//防止大模型幻觉设置了id
             if(appointmentService.save(appointment)){
                 return "预约成功，并返回预约详情";
            }else{
                 return "预约失败";
            }
         }
         return "您在相同的科室和时间已有预约";
    }
    @Tool(name="取消预约挂号", value = "根据参数，查询预约是否存在，如果存在则删除预约记录并返回取 消预约成功，否则返回取消预约失败")
    public String cancelAppointment(Appointment appointment){
        Appointment appointmentDB = appointmentService.getOne(appointment);
        if(appointmentDB != null){//删除预约记录
            if(appointmentService.removeById(appointmentDB.getId())){
                return "取消预约成功";
            }else{
                return "取消预约失败";
            }
        }//取消失败
        return "您没有预约记录，请核对预约科室和时间";
    }
    @Tool(name = "查询是否有号源", value="根据科室名称，日期，时间和医生查询是否有号源，并返回给用 户")
    public boolean queryDepartment(
            @P(value = "科室名称") String name,
            @P(value = "日期") String date,
            @P(value = "时间，可选值：上午、下午") String time,
            @P(value = "医生名称", required = false) String doctorName) {
        System.out.println("查询是否有号源");
        System.out.println("科室名称：" + name);
        System.out.println("日期：" + date);
        System.out.println("时间：" + time);
        System.out.println("医生名称：" + doctorName);
        // TODO: 查询医生排班信息，以下是伪代码逻辑说明

        if (doctorName == null || doctorName.isEmpty()) {
            // 未指定医生，查询该科室、日期、时间是否有可预约医生
            // return true if any doctor is available
            // 示例：return schedulingService.hasAvailableDoctor(name, date, time);
            return true; // 示例返回
        } else {
            // 指定了医生
            // 检查医生是否有排班
            boolean hasSchedule = true; // 示例：schedulingService.hasSchedule(doctorName, date, time);
            if (!hasSchedule) {
                return false;
            }

            // 检查排班是否已约满
            boolean isFull = false; // 示例：schedulingService.isFullyBooked(doctorName, date, time);
            return !isFull;
        }
    }
}
