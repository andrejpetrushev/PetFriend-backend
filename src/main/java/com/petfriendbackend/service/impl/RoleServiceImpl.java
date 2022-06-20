package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Role;
import com.petfriendbackend.repository.RoleRepository;
import com.petfriendbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

   private final RoleRepository roleRepository;

   @Override
   public Role getRole(Long id) {
      return this.roleRepository.findById(id).orElse(null);
   }

   @Override
   public Role createRole(String name) {
      Role role = new Role(name);
      return this.roleRepository.save(role);
   }

   @Override
   public Role getRoleByName(String name) {
      return this.roleRepository.findByName(name).orElse(null);
   }
}
