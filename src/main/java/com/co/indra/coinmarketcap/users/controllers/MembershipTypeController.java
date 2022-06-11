package com.co.indra.coinmarketcap.users.controllers;

import com.co.indra.coinmarketcap.users.config.Routes;
import com.co.indra.coinmarketcap.users.model.MembershipType;
import com.co.indra.coinmarketcap.users.model.User;
import com.co.indra.coinmarketcap.users.services.MembershipTypeService;
import com.co.indra.coinmarketcap.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.MEMBERSHIP_PATH)
public class MembershipTypeController {
   @Autowired
   private MembershipTypeService membershipTypeService;

   @GetMapping
   public List<MembershipType> getMembershipByUser() {
      return membershipTypeService.getMembership();
   }
}
