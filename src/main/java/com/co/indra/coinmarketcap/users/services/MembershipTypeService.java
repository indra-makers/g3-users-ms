package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.config.ErrorCodes;
import com.co.indra.coinmarketcap.users.exceptions.BusinessExceptions;
import com.co.indra.coinmarketcap.users.model.MembershipType;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.repositories.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipTypeService {
    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    public List<MembershipType> getMembership() {
       return  membershipTypeRepository.findAll();
    }
}
