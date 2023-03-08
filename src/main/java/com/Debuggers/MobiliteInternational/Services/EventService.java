package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent();

    Event getEventById(Long id);
}
