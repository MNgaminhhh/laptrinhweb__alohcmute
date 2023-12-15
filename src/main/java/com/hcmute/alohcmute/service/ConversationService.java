package com.hcmute.alohcmute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmute.alohcmute.entity.Conversation;
import com.hcmute.alohcmute.entity.User;
import com.hcmute.alohcmute.repository.ConversationRespository;
import com.hcmute.alohcmute.repository.UserRepository;

@Service
public class ConversationService {
    
    @Autowired
    private ConversationRespository conversationRespository;

    @Autowired
    private UserRepository userRepository;

    public List<Conversation> findAll() {
        return conversationRespository.findAll();
    }

    public Optional<Conversation> findConversationsOf(Long user1, Long user2) {
        return conversationRespository.findConversationsOf(user1, user2);
    }

    public Conversation addConversation(Long userId1, Long userId2, Conversation newConversation) {
        Optional<User> member1 = userRepository.findById(userId1);
        Optional<User> member2 = userRepository.findById(userId2);
        if (member1.isPresent() && member2.isPresent()) {
            Optional<Conversation> exist = conversationRespository.findConversationsOf(userId1, userId2);
            if (exist.isPresent()){
                return null;
            }
            newConversation.setMember1(member1.get());
            newConversation.setMember2(member2.get());
            return conversationRespository.save(newConversation);
        }
        else {
            return null;
        }
    }
}
