package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.ReponseReport;

import javax.mail.MessagingException;
import java.util.List;

public interface ReponseReportService {
    List<ReponseReport> getAllReponse();
    public ReponseReport getReponseById(long id);
    public ReponseReport addReponse(ReponseReport r,long reportId) throws MessagingException;
    public ReponseReport updateReponse(ReponseReport r , Long reportId);



}
