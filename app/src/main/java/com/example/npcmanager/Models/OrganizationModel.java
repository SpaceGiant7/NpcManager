package com.example.npcmanager.Models;

import Constants.NpcConstants;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;

import java.util.List;
import java.util.Optional;

public class OrganizationModel extends BaseModel<Organization> {

    public OrganizationModel() {
        super();
        addOrganization( new Organization( NpcConstants.NONE, Location.of( NpcConstants.NONE ) ) );
    }

    public void addOrganization( Organization newOrganization ) {
        addItem( newOrganization );
    }

    public void removeOrganization( Organization organization ) {
        removeItem( organization );
    }

    public int getNumberOfOrganizations() {
        return getNumberOfItems();
    }

    public Optional<Organization> getOrganizationMaybe(String organizationName ) {
        return list.stream()
                .filter( o -> o.getOrganizationName()
                    .equals( organizationName ) )
                .findFirst();
    }

    public List<Organization> getAllOrganizations() {
        return list;
    }

    public boolean organizationExists( String organizationName ) {
        return getOrganizationMaybe( organizationName ).isPresent();
    }
}
