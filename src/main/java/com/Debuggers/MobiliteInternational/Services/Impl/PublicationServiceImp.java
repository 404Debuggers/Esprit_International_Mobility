package com.Debuggers.MobiliteInternational.Services.Impl;

import com.Debuggers.MobiliteInternational.Entity.Post;
import com.Debuggers.MobiliteInternational.Repository.PublicationRepository;
import com.Debuggers.MobiliteInternational.Services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublicationServiceImp implements PublicationService {
    private final PublicationRepository publicationRepository;

    @Override
    public List<Post> getAllPublication() {
        return publicationRepository.findAll();
    }

    @Override
    public Post getPublicationById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public void addPublication(Post post) {
        publicationRepository.save(post);
    }

    @Override
    public void updatePublication(Post post) {
        publicationRepository.save(post);
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
