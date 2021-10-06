package org.primefaces.showcase.mobile;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

/**
 * required to activate CDI
 *
 * @author Marco Janc
 */
@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class FacesConfigBean implements Serializable
{
	private static final long serialVersionUID = 8089566558727801685L;
}
