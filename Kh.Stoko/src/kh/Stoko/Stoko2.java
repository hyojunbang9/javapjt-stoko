package kh.Stoko;

public class Stoko2 {
	private int receiptNo;
	private String _productName;
	private int _quantity;
	private int _totalPrice;
	private String _stockInDate;

	public Stoko2() {
		this(0, null, 0, 0, null);
	}

	public Stoko2(int receiptNo, String _productName, int _quantity, int _totalPrice, String _stockInDate) {
		super();
		this.receiptNo = receiptNo;
		this._productName = _productName;
		this._quantity = _quantity;
		this._totalPrice = _totalPrice;
		this._stockInDate = _stockInDate;
	}

	public int getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String get_productName() {
		return _productName;
	}

	public void set_productName(String _productName) {
		this._productName = _productName;
	}

	public int get_quantity() {
		return _quantity;
	}

	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}

	public int get_totalPrice() {
		return _totalPrice;
	}

	public void set_totalPrice(int _totalPrice) {
		this._totalPrice = _totalPrice;
	}

	public String get_stockInDate() {
		return _stockInDate;
	}

	public void set_stockInDate(String _stockInDate) {
		this._stockInDate = _stockInDate;
	}

	@Override
	public String toString() {
		return "Stoko2 [receiptNo=" + receiptNo + ", _productName=" + _productName + ", _quantity=" + _quantity
				+ ", _totalPrice=" + _totalPrice + ", _stockInDate=" + _stockInDate + "]";
	}

}
