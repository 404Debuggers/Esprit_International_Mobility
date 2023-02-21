package com.Debuggers.MobiliteInternational.Services;

import com.Debuggers.MobiliteInternational.Entity.Post;

import java.util.List;

public interface PublicationService {
    public List<Post> getAllPublication();
    public Post getPublicationById(Long id);
    public void addPublication(Post post);
    public void updatePublication(Post post);
    public void deletePublication(Long id);

}
