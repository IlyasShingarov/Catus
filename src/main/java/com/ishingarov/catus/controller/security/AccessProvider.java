package com.ishingarov.catus.controller.security;

import org.springframework.stereotype.Service;

@Service
public interface AccessProvider {

    boolean checkId(Integer id);

    boolean checkIfCreateAllowed(Integer id);

    boolean checkIfUpdateAllowed(Integer id);

    boolean checkIfDeleteAllowed(Integer id);

    boolean checkIfReadAllowed(Integer id);

}
