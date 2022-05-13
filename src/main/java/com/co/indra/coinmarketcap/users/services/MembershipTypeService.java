package com.co.indra.coinmarketcap.users.services;

import com.co.indra.coinmarketcap.users.repositories.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembershipTypeService {
    @Autowired
    private MembershipTypeRepository membershipTypeRepository;
}
