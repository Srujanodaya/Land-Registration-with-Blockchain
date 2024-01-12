package com.megainfo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1102518187112957584L;
	private String product_id;
	private String product_name;
	private String product_category;
	private BigDecimal product_unit_price;
	private String product_description;
	private String product_registered_on;
	private String pin_code;
	private String is_product_ordered;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String product_id, String product_name, String product_category, BigDecimal product_unit_price,
			String product_description, String product_registered_on, String pin_code, String is_product_ordered) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_category = product_category;
		this.product_unit_price = product_unit_price;
		this.product_description = product_description;
		this.product_registered_on = product_registered_on;
		this.pin_code = pin_code;
		this.is_product_ordered = is_product_ordered;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public BigDecimal getProduct_unit_price() {
		return product_unit_price;
	}
	public void setProduct_unit_price(BigDecimal product_unit_price) {
		this.product_unit_price = product_unit_price;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_registered_on() {
		return product_registered_on;
	}
	public void setProduct_registered_on(String product_registered_on) {
		this.product_registered_on = product_registered_on;
	}
	public String getPin_code() {
		return pin_code;
	}
	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}
	
	public String getIs_product_ordered() {
		return is_product_ordered;
	}
	public void setIs_product_ordered(String is_product_ordered) {
		this.is_product_ordered = is_product_ordered;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_category == null) ? 0 : product_category.hashCode());
		result = prime * result + ((product_description == null) ? 0 : product_description.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		result = prime * result + ((product_registered_on == null) ? 0 : product_registered_on.hashCode());
		result = prime * result + ((product_unit_price == null) ? 0 : product_unit_price.hashCode());
		result = prime * result + ((pin_code == null) ? 0 : pin_code.hashCode());
		result = prime * result + ((is_product_ordered == null) ? 0 : is_product_ordered.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (product_category == null) {
			if (other.product_category != null)
				return false;
		} else if (!product_category.equals(other.product_category))
			return false;
		if (product_description == null) {
			if (other.product_description != null)
				return false;
		} else if (!product_description.equals(other.product_description))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		if (product_registered_on == null) {
			if (other.product_registered_on != null)
				return false;
		} else if (!product_registered_on.equals(other.product_registered_on))
			return false;
		if (product_unit_price == null) {
			if (other.product_unit_price != null)
				return false;
		} else if (!product_unit_price.equals(other.product_unit_price))
			return false;
		if (pin_code == null) {
			if (other.pin_code != null)
				return false;
		} else if (!pin_code.equals(other.pin_code))
			return false;
		if (is_product_ordered == null) {
			if (other.is_product_ordered != null)
				return false;
		} else if (!is_product_ordered.equals(other.is_product_ordered))
			return false;
		return true;
	}
	
	
}
