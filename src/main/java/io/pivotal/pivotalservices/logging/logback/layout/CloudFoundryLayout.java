package io.pivotal.pivotalservices.logging.logback.layout;

import io.pivotal.pivotalservices.logging.model.VcapApplication;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CloudFoundryLayout extends LayoutBase<ILoggingEvent>{

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final VcapApplication vcapApplication;

	public CloudFoundryLayout() {
		final String vcapAppEnvVar = System.getenv("VCAP_APPLICATION");		
		System.out.println("VCAP_APPLICATION=" + vcapAppEnvVar);

		try {
			vcapApplication = objectMapper.readValue(vcapAppEnvVar, VcapApplication.class);
		} catch (Exception e) {
			throw new IllegalStateException("Unble to parse VCAP_APPLICATION environment variable; VCAP_APPLICATION=" + vcapAppEnvVar, e);
		}
	}

	public String doLayout(ILoggingEvent event) {
	    StringBuffer sbuf = new StringBuffer(128);
	    sbuf.append("[").append(vcapApplication.getSpaceName()).append(":").append(vcapApplication.getApplicationName()).append("] ");
	    sbuf.append(event.getLevel());
	    sbuf.append(" [");
	    sbuf.append(event.getThreadName());
	    sbuf.append("] ");
	    sbuf.append(event.getLoggerName());
	    sbuf.append(" - ");
	    sbuf.append(event.getFormattedMessage());
	    sbuf.append(CoreConstants.LINE_SEPARATOR);
	    return sbuf.toString();
	  }

}
