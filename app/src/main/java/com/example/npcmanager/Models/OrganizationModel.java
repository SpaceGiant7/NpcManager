package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;

import java.util.List;
import java.util.Optional;

public class OrganizationModel extends BaseModel{

    public OrganizationModel() {
        super();
        addOrganization(Organization.None());
    }

    public void addOrganization(Organization newOrganization) {
        addItem(newOrganization);
    }

    public void removeOrganizationIfExists(String organizationName) {
        findFirstOrganizationMaybe(organizationName).ifPresent(this::removeItem);
    }

    public Optional<Organization> findFirstOrganizationMaybe(String organizationName) {
        return getAllOrganizations()
                .stream()
                .filter(o -> o.getName()
                        .equals(organizationName))
                .findFirst();
    }

    public List<Organization> getAllOrganizations() {
        return getList();
    }

    public boolean organizationExists(String organizationName) {
        return findFirstOrganizationMaybe(organizationName).isPresent();
    }

    public void replaceLocation(String oldLocationName, Location newLocation) {
        getAllOrganizations()
                .stream()
                .filter(o -> o.getLocation().getName().equals(oldLocationName))
                .forEach(o -> o.setLocation(newLocation));
    }
}
