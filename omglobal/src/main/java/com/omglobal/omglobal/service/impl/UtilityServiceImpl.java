package com.omglobal.omglobal.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omglobal.omglobal.model.entities.Role;
import com.omglobal.omglobal.model.entities.Store;
import com.omglobal.omglobal.model.entities.User;
import com.omglobal.omglobal.model.entities.Warehouse;
import com.omglobal.omglobal.model.request.RoleRequest;
import com.omglobal.omglobal.model.request.UserLoginRequest;
import com.omglobal.omglobal.model.request.UserRequest;
import com.omglobal.omglobal.model.response.Response;
import com.omglobal.omglobal.model.response.UserResponse;
import com.omglobal.omglobal.repository.RoleRepo;
import com.omglobal.omglobal.repository.StoreRepo;
import com.omglobal.omglobal.repository.UserRepo;
import com.omglobal.omglobal.repository.WarehouseRepo;
import com.omglobal.omglobal.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service(value = "userService")
public class UtilityServiceImpl implements UtilityService, UserDetailsService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StoreRepo storeRepo;

//    public Response createRoles() {
//        Response response=null;
//        try {
//
//            Role role1 = new Role();
//            role1.setRoleName("ADMIN");
//            Role role2 = new Role();
//            role2.setRoleName("CUSTOMER");
//            Role role3 = new Role();
//            role3.setRoleName("SUPPLIER");
//            Set<Role> roleSet = new HashSet<>();
//            roleSet.add(role1);
//            roleSet.add(role2);
//            roleSet.add(role3);
//            User user = new User();
//            user.setRoleSet(roleSet);
//            User userRole = userRepo.save(user);
//       response=new Response("Success","200","UserRole set successfully",userRole);
//        } catch (Exception exception) {
//            response = new Response("Failure", "500", exception.getMessage(), null);
//
//        }
//        return response;
//    }

    @Override
    public Response createRole(RoleRequest roleRequest) {
        Response response = null;
        try {
            Role role = null;
            if (roleRequest.getId() != null) {
                Optional<Role> optionalRole = roleRepo.findById(roleRequest.getId());
                if (optionalRole.isPresent()) {
                    role = optionalRole.get();
                    role.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    role = new Role();
                    role.setCreatedAt(LocalDateTime.now().toString());

                }
            } else {
                role = new Role();
                role.setCreatedAt(LocalDateTime.now().toString());

            }
            Optional<Store> optionalStore = storeRepo.findById(roleRequest.getStoreId());
            if (optionalStore.isPresent()) {
                Store store = optionalStore.get();
                role.setStore(store);
            } else {
                throw new Exception("store not found");
            }

            role.setRoleName(roleRequest.getRoleName());
            role.setDescription(roleRequest.getDescription());
            role.setStatus(roleRequest.getStatus());
            Role savedRole = roleRepo.save(role);

            response = new Response("Success", "200", "saved role successfully", savedRole);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Response createUser(UserRequest userRequest) {
        Response response = null;
        try {
            User user = null;
            if (userRequest.getId() != null) {
                Optional<User> optionalUser = userRepo.findById(userRequest.getId());
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    user.setUpdatedAt(LocalDateTime.now().toString());
                } else {
                    user = new User();
                    user.setCreatedAt(LocalDateTime.now().toString());

                }
            } else {
                user = new User();
                user.setCreatedAt(LocalDateTime.now().toString());
            }

            //setting store details
            Optional<Store> optionalStore = storeRepo.findById(userRequest.getStoreId());
            if (optionalStore.isPresent()) {
                Store store = optionalStore.get();
                user.setStore(store);
            } else {
                throw new Exception("store not found");
            }

            //setting warehouses
            List<Warehouse> warehouseList = warehouseRepo.findAllById(userRequest.getWarehouseIds());
            user.setWarehouses(new HashSet<Warehouse>(warehouseList));

            //setting role
            Optional<Role> optionalRole = roleRepo.findById(userRequest.getRoleId());
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                user.setRole(role);
            } else {
               throw new Exception("Role not found");
            }

            //setting other details
            user.setUserName(userRequest.getUserName());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPassword(encoder.encode(userRequest.getPassword()));
            user.setDateOfBirth(userRequest.getDateOfBirth());
            user.setAddress(userRequest.getAddress());
            user.setStatus(userRequest.getStatus());
            user.setCountry(userRequest.getCountry());
            user.setCity(userRequest.getCity());
            user.setState(userRequest.getState());
            user.setGender(userRequest.getGender());
            user.setPostCode(userRequest.getPostCode());
            user.setCreatedBy(userRequest.getCreatedBy());

            //saving details in DB
            User savedUser = userRepo.save(user);
            savedUser.setPassword(null);
            //sending response back
            response = new Response("Success", "200", "saved role successfully", savedUser);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);

        }
        return response;
    }

    @Override
    public Response deleteUser(Long id) {
        Response response=null;
        try {
            userRepo.deleteById(id);

       response=new Response("Success","200","user deleted successfully",null);
        }catch (Exception exception){
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response userLogin(UserLoginRequest userLoginRequest) {
        Response response=null;
        try {
            Optional<User>optionalUser=userRepo.findByUserName(userLoginRequest.getUserName());
            if (optionalUser.isPresent()){
                User user=optionalUser.get();

            }

        }catch (Exception exception){
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByUserName(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(userOptional.get().getUserName(), userOptional.get().getPassword(), getAuthority(userOptional.get()));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getRoleName()));
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findOne(String username) {
        Optional<User> user = userRepo.findByUserName(username);
        return user.orElse(null);
    }
}