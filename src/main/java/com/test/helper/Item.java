package com.test.helper;

public class Item {

	private Integer id;

	private Integer category_id;

	private Integer product_id;

	private Integer size_id;

	private Integer color_id;

	private Integer unit_id;

	private String style;

	private Double quantity;

	private Double unit_price;

	private Double total_price;

	private boolean is_deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getSize_id() {
		return size_id;
	}

	public void setSize_id(Integer size_id) {
		this.size_id = size_id;
	}

	public Integer getColor_id() {
		return color_id;
	}

	public void setColor_id(Integer color_id) {
		this.color_id = color_id;
	}

	public Integer getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Integer unit_id) {
		this.unit_id = unit_id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", category_id=" + category_id + ", product_id=" + product_id + ", size_id=" + size_id
				+ ", color_id=" + color_id + ", unit_id=" + unit_id + ", style=" + style + ", quantity=" + quantity
				+ ", unit_price=" + unit_price + ", total_price=" + total_price + ", is_deleted=" + is_deleted + "]";
	}

}
