package com.eliasshallouf.examples.library_management_system.domain.model.exceptions;

public class RecordNotFoundException extends Exception {
    public <Entity> RecordNotFoundException(Class<Entity> clazz) {
        super("No " + clazz.getSimpleName() + " have found");
    }
}
