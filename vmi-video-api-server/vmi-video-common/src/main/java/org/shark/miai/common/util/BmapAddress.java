package org.shark.miai.common.util;

public class BmapAddress {
	
	/**
	 * 地址
	 */
	private String formatted_address;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 区
	 */
	private String district;
	
	/**
	 * 街道
	 */
	private String street;
	
	/**
	 * 街道牌号
	 */
	private String street_number;
	
	/**
	 * 行政区划代码
	 */
	private String admin_area_code;
	
	/**
	 * 国家代码
	 */
	private String country_code;
	
	/**
	 * 提示
	 */
	private String recommended_location_description;
	
	/**
	 * 经度
	 */
	private double lng;
	
	/**
	 * 纬度
	 */
	private double lat;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet_number() {
		return street_number;
	}

	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	public String getRecommended_location_description() {
		return recommended_location_description;
	}

	public void setRecommended_location_description(
			String recommended_location_description) {
		this.recommended_location_description = recommended_location_description;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getAdmin_area_code() {
		return admin_area_code;
	}

	public void setAdmin_area_code(String admin_area_code) {
		this.admin_area_code = admin_area_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BmapAddress [formatted_address=");
		builder.append(formatted_address);
		builder.append(", country=");
		builder.append(country);
		builder.append(", province=");
		builder.append(province);
		builder.append(", city=");
		builder.append(city);
		builder.append(", district=");
		builder.append(district);
		builder.append(", street=");
		builder.append(street);
		builder.append(", street_number=");
		builder.append(street_number);
		builder.append(", admin_area_code=");
		builder.append(admin_area_code);
		builder.append(", country_code=");
		builder.append(country_code);
		builder.append(", recommended_location_description=");
		builder.append(recommended_location_description);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", lat=");
		builder.append(lat);
		builder.append("]");
		return builder.toString();
	}

}
