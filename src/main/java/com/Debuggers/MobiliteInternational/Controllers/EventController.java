package com.Debuggers.MobiliteInternational.Controllers;


import com.Debuggers.MobiliteInternational.Entity.Event;
import com.Debuggers.MobiliteInternational.Entity.Interview;
import com.Debuggers.MobiliteInternational.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventService eventService;
    @GetMapping("/affichertoutEvent")
    @ResponseBody
    public List<Event> getAllEvent(){return eventService.getAllEvent();}
}
