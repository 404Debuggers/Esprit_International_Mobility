package com.Debuggers.MobiliteInternational.Services.Impl;


import com.Debuggers.MobiliteInternational.Entity.Event;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;

@Component
public class ReminderSchedulerImpl {
    public void scheduleReminder(Event event, Interview interview, Integer duration) {
        // Calculate the time at which the reminder should be scheduled
        LocalDateTime reminderTime = event.getStart().minusDays(duration);
        System.out.println(reminderTime);
        Date localDateTime = java.util.Date.from(reminderTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(localDateTime);



        TaskScheduler taskScheduler = new ConcurrentTaskScheduler(); // Create a new TaskScheduler


        taskScheduler.schedule(new ReminderEventImpl(event,interview), localDateTime);
    }
}
