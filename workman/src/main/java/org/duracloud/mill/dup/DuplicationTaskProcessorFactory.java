/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.mill.dup;

import org.duracloud.mill.credentials.CredentialRepo;
import org.duracloud.mill.domain.Task;
import org.duracloud.mill.workman.TaskProcessor;
import org.duracloud.mill.workman.TaskProcessorFactoryBase;

/**
 * This class is responsible for creating DuplicationTaskProcessors 
 * 
 * @author Daniel Bernstein
 * 
 */
public class DuplicationTaskProcessorFactory extends TaskProcessorFactoryBase {

    public DuplicationTaskProcessorFactory(CredentialRepo repo){
        super(repo);
    }
    
    @Override
    protected boolean isSupported(Task task) {
        return true;
    }

    @Override
    protected TaskProcessor createImpl(Task task) {
        // TODO Auto-generated method stub
        return null;
    }    
}