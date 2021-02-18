/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package training.my.setup;

import static training.my.constants.MyextensionConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import training.my.constants.MyextensionConstants;
import training.my.service.MyextensionService;


@SystemSetup(extension = MyextensionConstants.EXTENSIONNAME)
public class MyextensionSystemSetup
{
	private final MyextensionService myextensionService;

	public MyextensionSystemSetup(final MyextensionService myextensionService)
	{
		this.myextensionService = myextensionService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		myextensionService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return MyextensionSystemSetup.class.getResourceAsStream("/myextension/sap-hybris-platform.png");
	}
}
