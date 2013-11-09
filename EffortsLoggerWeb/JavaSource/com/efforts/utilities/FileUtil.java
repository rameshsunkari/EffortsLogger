package com.efforts.utilities;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FileUtil {

	public static void export(byte[] info, String contentType, String extension)
			throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		String fileName = "" + System.currentTimeMillis() + extension;

		ec.responseReset();
		ec.setResponseContentType(contentType);
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		OutputStream output = ec.getResponseOutputStream();
		// Write the table back out
		output.write(info);
		output.close();
		fc.responseComplete();

	}

}
