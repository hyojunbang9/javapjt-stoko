package kh.Stoko;

public class Stoko implements Comparable {
	private String productName;
	private int quantity;
	private int price;
	private String stockInDate;
	private int totalPrice;
	private int sales;

	public Stoko() {
		this(null, 0, 0, null);
	}

	public Stoko(String productName, int quantity, int price, String stockInDate) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.stockInDate = stockInDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStockInDate() {
		return stockInDate;
	}

	public void setStockInDate(String stockInDate) {
		this.stockInDate = stockInDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Stoko [productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", stockInDate="
				+ stockInDate + "]";
	}

	@Override
	public int compareTo(Object object) {
		// Object 속에 StudentData가 있는지 점검한다.
		Stoko pro = null;
		if (object instanceof Stoko) {
			pro = (Stoko) object;
		}
		// 비교
		if (this.getQuantity() > pro.getQuantity()) {
			return 1;
		} else if (this.getQuantity() < pro.getQuantity()) {
			return -1;
		} else {
			return 0;
		}
	}
}
