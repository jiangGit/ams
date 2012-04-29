/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.scau;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 *
 * @author Administrator
 */
public class AmgSetup implements Setup{

    public void init(NutConfig nc) {
       // ProcessEngine processEngine = Configuration.getProcessEngine();
	//RepositoryService repositoryService = processEngine.getRepositoryService();
	//repositoryService.createDeployment().addResourceFromClasspath("apply.jbpl.xml").deploy();
    }

    public void destroy(NutConfig nc) {
        
    }
    
}
