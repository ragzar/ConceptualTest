package com.company.openbank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

	@JsonProperty("public_alias")
	private String publicAlias;

	@JsonProperty("private_alias")
	private String privateAlias;

	@JsonProperty("more_info")
	private String moreInfo;

	@JsonProperty("URL")
	private String url;

	@JsonProperty("image_URL")
	private String imageUrl;

	@JsonProperty("open_corporates_URL")
	private String openCorporatesUrl;

	public String getPublicAlias() {
		return publicAlias;
	}

	public void setPublicAlias(String publicAlias) {
		this.publicAlias = publicAlias;
	}

	public String getPrivateAlias() {
		return privateAlias;
	}

	public void setPrivateAlias(String privateAlias) {
		this.privateAlias = privateAlias;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getOpenCorporatesUrl() {
		return openCorporatesUrl;
	}

	public void setOpenCorporatesUrl(String openCorporatesUrl) {
		this.openCorporatesUrl = openCorporatesUrl;
	}

	@Override
	public String toString() {
		return " [ publicAlias=" + publicAlias + ", privateAlias=" + privateAlias + ", moreInfo=" + moreInfo + ", url="
				+ url + ", imageUrl=" + imageUrl + ", openCorporatesUrl=" + openCorporatesUrl;
	}

}