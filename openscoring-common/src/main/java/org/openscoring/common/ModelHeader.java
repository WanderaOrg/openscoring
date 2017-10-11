package org.openscoring.common;

import java.util.List;
import java.util.Objects;

import org.dmg.pmml.Application;
import org.dmg.pmml.Header;
import org.dmg.pmml.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ModelHeader extends SimpleResponse {
	private static final long serialVersionUID = -3455004636779734011L;

	private String copyright;
	private String description;
	private String modelVersion;
	private String applicationName;
	private String applicationVersion;
	private String timestamp;

	public ModelHeader() {

	}

	public ModelHeader(Header pmmlHeader) {
		this();

		if (pmmlHeader != null) {
			copyright = pmmlHeader.getCopyright();
			description = pmmlHeader.getDescription();
			modelVersion = pmmlHeader.getModelVersion();
			setApplicationDetails(pmmlHeader);
			setTimestampDetails(pmmlHeader);
		}
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(copyright, description, modelVersion, applicationName, applicationVersion, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		ModelHeader other = (ModelHeader) obj;
		return Objects.equals(copyright, other.copyright)
				&& Objects.equals(description, other.description)
				&& Objects.equals(modelVersion, other.modelVersion)
				&& Objects.equals(applicationName, other.applicationName)
				&& Objects.equals(applicationVersion, other.applicationVersion)
				&& Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		String message = getMessage();
		if (message != null) {
			return super.toString();
		}

		ToStringHelper stringHelper = MoreObjects.toStringHelper(getClass()).add("copyright", getCopyright())
				.add("description", getDescription()).add("modelVersion", getModelVersion())
				.add("applicationName", getApplicationName()).add("applicationVersion", getApplicationVersion())
				.add("timestamp", getTimestamp());

		return stringHelper.toString();
	}

	private void setApplicationDetails(Header pmmlHeader) {
		Application application = pmmlHeader.getApplication();
		if (application != null) {
			applicationName = application.getName();
			applicationVersion = application.getVersion();
		}
	}

	private void setTimestampDetails(Header pmmlHeader) {
		Timestamp pmmlTimestamp = pmmlHeader.getTimestamp();
		if (pmmlTimestamp != null) {
			List<Object> content = pmmlTimestamp.getContent();
			if (content != null && content.size() > 0) {
				timestamp = content.get(0).toString();
			}
		}
	}
}
