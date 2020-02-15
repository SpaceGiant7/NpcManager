package com.example.npcmanager.Models;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseModel<E> {
    private static final Logger LOGGER = Logger.getLogger( BaseModel.class.getName() );
    List<E> list;

    public BaseModel() {
        list = new ArrayList<>();
    }

    public List getList(){
        return list;
    }

    protected void addItem( E newItem ){
        if ( !itemExists( newItem ) ) {
            list.add( newItem );
            logAdd( newItem );
        }
        else {
            logFailedAdd( newItem );
        }

    }

    protected void removeItem( E item ) {
        if (itemExists( item ) ) {
            list.remove( item );
            logRemove( item );
        }
        else {
            logFailedRemove( item );
        }
    }

    protected Boolean itemExists( E item ) {
        return list.contains( item );
    }

    protected void replaceList( List<E> newItems ){
        list = new ArrayList<>( newItems );
    }

    protected int getNumberOfItems() {
        return list.size();
    }

    private void logAdd( E item ) {
        LOGGER.log( Level.INFO, String.format( "Added %s to model: %s", item.getClass(), item ) );
    }

    private void logRemove( E item ) {
        LOGGER.log( Level.INFO, String.format( "Removed %s from model: %s", item.getClass(), item ) );
    }

    private void logFailedAdd( E item ) {
        LOGGER.log( Level.INFO, String.format( "Failed to add %s to model: %s", item.getClass(), item ) );
    }

    private void logFailedRemove( E item ) {
        LOGGER.log( Level.INFO, String.format( "Failed to remove %s from model: %s", item.getClass(), item ) );
    }
}
