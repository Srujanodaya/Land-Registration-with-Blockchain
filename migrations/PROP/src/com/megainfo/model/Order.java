package com.megainfo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3899518850153007202L;
	private int order_id;
	private String ordered_product_id;
	private int ordered_product_units;
	private BigDecimal total_order_amount;
	private String ordered_on;
	private String ordered_by;
	private String order_status;
	private String order_status_updated_on;
	private Boolean is_order_delivered;
	private String order_delivered_on;
	private Boolean is_order_cancelled;
	private Boolean order_cancelled_on;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int order_id, String ordered_product_id, int ordered_product_units, BigDecimal total_order_amount,
			String ordered_on, String ordered_by, String order_status, String order_status_updated_on,
			Boolean is_order_delivered, String order_delivered_on, Boolean is_order_cancelled,
			Boolean order_cancelled_on) {
		super();
		this.order_id = order_id;
		this.ordered_product_id = ordered_product_id;
		this.ordered_product_units = ordered_product_units;
		this.total_order_amount = total_order_amount;
		this.ordered_on = ordered_on;
		this.ordered_by = ordered_by;
		this.order_status = order_status;
		this.order_status_updated_on = order_status_updated_on;
		this.is_order_delivered = is_order_delivered;
		this.order_delivered_on = order_delivered_on;
		this.is_order_cancelled = is_order_cancelled;
		this.order_cancelled_on = order_cancelled_on;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrdered_product_id() {
		return ordered_product_id;
	}
	public void setOrdered_product_id(String ordered_product_id) {
		this.ordered_product_id = ordered_product_id;
	}
	public int getOrdered_product_units() {
		return ordered_product_units;
	}
	public void setOrdered_product_units(int ordered_product_units) {
		this.ordered_product_units = ordered_product_units;
	}
	public BigDecimal getTotal_order_amount() {
		return total_order_amount;
	}
	public void setTotal_order_amount(BigDecimal total_order_amount) {
		this.total_order_amount = total_order_amount;
	}
	public String getOrdered_on() {
		return ordered_on;
	}
	public void setOrdered_on(String ordered_on) {
		this.ordered_on = ordered_on;
	}
	public String getOrdered_by() {
		return ordered_by;
	}
	public void setOrdered_by(String ordered_by) {
		this.ordered_by = ordered_by;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getOrder_status_updated_on() {
		return order_status_updated_on;
	}
	public void setOrder_status_updated_on(String order_status_updated_on) {
		this.order_status_updated_on = order_status_updated_on;
	}
	public Boolean getIs_order_delivered() {
		return is_order_delivered;
	}
	public void setIs_order_delivered(Boolean is_order_delivered) {
		this.is_order_delivered = is_order_delivered;
	}
	public String getOrder_delivered_on() {
		return order_delivered_on;
	}
	public void setOrder_delivered_on(String order_delivered_on) {
		this.order_delivered_on = order_delivered_on;
	}
	public Boolean getIs_order_cancelled() {
		return is_order_cancelled;
	}
	public void setIs_order_cancelled(Boolean is_order_cancelled) {
		this.is_order_cancelled = is_order_cancelled;
	}
	public Boolean getOrder_cancelled_on() {
		return order_cancelled_on;
	}
	public void setOrder_cancelled_on(Boolean order_cancelled_on) {
		this.order_cancelled_on = order_cancelled_on;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((is_order_cancelled == null) ? 0 : is_order_cancelled.hashCode());
		result = prime * result + ((is_order_delivered == null) ? 0 : is_order_delivered.hashCode());
		result = prime * result + ((order_cancelled_on == null) ? 0 : order_cancelled_on.hashCode());
		result = prime * result + ((order_delivered_on == null) ? 0 : order_delivered_on.hashCode());
		result = prime * result + order_id;
		result = prime * result + ((order_status == null) ? 0 : order_status.hashCode());
		result = prime * result + ((order_status_updated_on == null) ? 0 : order_status_updated_on.hashCode());
		result = prime * result + ((ordered_by == null) ? 0 : ordered_by.hashCode());
		result = prime * result + ((ordered_on == null) ? 0 : ordered_on.hashCode());
		result = prime * result + ((ordered_product_id == null) ? 0 : ordered_product_id.hashCode());
		result = prime * result + ordered_product_units;
		result = prime * result + ((total_order_amount == null) ? 0 : total_order_amount.hashCode());
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
		Order other = (Order) obj;
		if (is_order_cancelled == null) {
			if (other.is_order_cancelled != null)
				return false;
		} else if (!is_order_cancelled.equals(other.is_order_cancelled))
			return false;
		if (is_order_delivered == null) {
			if (other.is_order_delivered != null)
				return false;
		} else if (!is_order_delivered.equals(other.is_order_delivered))
			return false;
		if (order_cancelled_on == null) {
			if (other.order_cancelled_on != null)
				return false;
		} else if (!order_cancelled_on.equals(other.order_cancelled_on))
			return false;
		if (order_delivered_on == null) {
			if (other.order_delivered_on != null)
				return false;
		} else if (!order_delivered_on.equals(other.order_delivered_on))
			return false;
		if (order_id != other.order_id)
			return false;
		if (order_status == null) {
			if (other.order_status != null)
				return false;
		} else if (!order_status.equals(other.order_status))
			return false;
		if (order_status_updated_on == null) {
			if (other.order_status_updated_on != null)
				return false;
		} else if (!order_status_updated_on.equals(other.order_status_updated_on))
			return false;
		if (ordered_by == null) {
			if (other.ordered_by != null)
				return false;
		} else if (!ordered_by.equals(other.ordered_by))
			return false;
		if (ordered_on == null) {
			if (other.ordered_on != null)
				return false;
		} else if (!ordered_on.equals(other.ordered_on))
			return false;
		if (ordered_product_id == null) {
			if (other.ordered_product_id != null)
				return false;
		} else if (!ordered_product_id.equals(other.ordered_product_id))
			return false;
		if (ordered_product_units != other.ordered_product_units)
			return false;
		if (total_order_amount == null) {
			if (other.total_order_amount != null)
				return false;
		} else if (!total_order_amount.equals(other.total_order_amount))
			return false;
		return true;
	}
	
	
	
}
