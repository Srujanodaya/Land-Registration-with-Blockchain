package com.megainfo.model;

import java.io.Serializable;

public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5659212734323495740L;

	private int id;
	private String username;
	private String product_id;
	private String added_on;
	private String is_product_ordered;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int id, String username, String product_id, String added_on, String is_product_ordered) {
		super();
		this.id = id;
		this.username = username;
		this.product_id = product_id;
		this.added_on = added_on;
		this.is_product_ordered = is_product_ordered;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getAdded_on() {
		return added_on;
	}
	public void setAdded_on(String added_on) {
		this.added_on = added_on;
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
		result = prime * result + ((added_on == null) ? 0 : added_on.hashCode());
		result = prime * result + id;
		result = prime * result + ((is_product_ordered == null) ? 0 : is_product_ordered.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Cart other = (Cart) obj;
		if (added_on == null) {
			if (other.added_on != null)
				return false;
		} else if (!added_on.equals(other.added_on))
			return false;
		if (id != other.id)
			return false;
		if (is_product_ordered == null) {
			if (other.is_product_ordered != null)
				return false;
		} else if (!is_product_ordered.equals(other.is_product_ordered))
			return false;
		if (product_id == null) {
			if (other.product_id != null)
				return false;
		} else if (!product_id.equals(other.product_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
