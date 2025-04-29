package kh.Stoko;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StokoMain {
	public static String menuTitle;
	public static String _menuTitle;

	public static void main(String[] args) throws IOException {
		// 1. 프로세스 관리 클래스
		StokoProcess sp = StokoProcess.getInstance();
		// 2. 파일에 있는 데이터를 저장하기 위한 컬렉션
		ArrayList<Stoko> productList = new ArrayList<Stoko>();
		ArrayList<Stoko2> salesList = new ArrayList<Stoko2>();
		inputProductList(productList);
		inputSalesList(salesList);
		boolean stopFlag = false;
		while (!stopFlag) {
			// 사용자 선택
			System.out.println("╔═════════════════════ MODE 선택 ═════════════════════╗");
			System.out.println("║                                                    ║");
			System.out.println("║   1. 관리자 모드        　 *　　　　　　　　　　　　　　　     ║");
			System.out.println("║   2. 사용자 모드        　 *　　　　　　　　　　　　　　　     ║");
			System.out.println("║   3. 프로그램 종료         *　  　 　　　                ║");
			System.out.println("║                                                    ║");
			System.out.println("╚════════════════════════════════════════════════════╝");
			System.out.print("▶ 모드를 선택하세요: ");

			Scanner scan = new Scanner(System.in);
			int no = Integer.parseInt(scan.nextLine());
			switch (no) {
			case 1: {
				System.out.println("╔═════════════════════ ADMIN MODE ═════════════════════╗");
				System.out.println("║                                                      ║");
				System.out.println("║       1. 재고 입고    2. 재고 현황    3. 재고 검색         ║");
				System.out.println("║       4. 재고 정렬    5. 매출 현황    6. 종료             ║");
				System.out.println("║                                                      ║");
				System.out.println("╚══════════════════════════════════════════════════════╝");
				System.out.print("▶ 메뉴 번호를 선택하세요: ");
				int _no = Integer.parseInt(scan.nextLine());
				switch (_no) {
				case 1: // 재고 입고
					// 상품 입력
					System.out.print("상품명>");
					String productName = scan.nextLine();
					// 수량 입력
					System.out.print("상품 수량>");
					int quantity = Integer.parseInt(scan.nextLine());
					// 가격 입력
					System.out.print("상품 가격>");
					int price = Integer.parseInt(scan.nextLine());
					// 입고 일자 입력
					System.out.print("입고 일자>");
					String stockInDate = scan.nextLine();
					Stoko inPd = new Stoko(productName, quantity, price, stockInDate);
					inPd.setProductName(productName);
					inPd.setQuantity(quantity);
					inPd.setStockInDate(stockInDate);
					productList.add(inPd);
					System.out.printf("재고에 %s가 추가되었습니다.", productName);
					break;

				case 2:
					// 재고 현황
					int page = 1;
					while (true) {
						int totalPage = productList.size() / 5;
						int remainValue = productList.size() % 5;
						if (remainValue != 0) {
							totalPage += 1;
						}
						// 해당되는페이지 시작위치, 끝위치
						int start = 5 * (page - 1);
						int stop = 5 * (page - 1) + 5;

						// 마지막페이지일때 나머지값이 있을때 끝위치 1~4증가
						if (page == totalPage && remainValue != 0) {
							stop = 5 * (page - 1) + remainValue;
						}
						System.out.printf("전체 %d페이지 / 현재 %d페이지 \n", totalPage, page);
						for (int i = start; i < stop; i++) {
							System.out.println(productList.get(i).toString());
						}
						System.out.printf("page[1-%d] (-1 : exit) 페이지선택>", totalPage);
						page = Integer.parseInt(scan.nextLine());
						if (page == -1) {
							break;
						}
					}
					break;
				case 3:
					// 재고 검색
					System.out.print("검색할 재고명>");
					String name = scan.nextLine();
					boolean searchFlag = false;
					for (Stoko data : productList) {
						if (data.getProductName().equals(name)) {
							System.out.println(data);
							searchFlag = true;
						}
					}
					if (searchFlag == false) {
						System.out.printf("%s(이)라는 제품은 없습니다.\n", name);
					}
					break;
				case 4: {
					// 재고 정렬
					System.out.println("╔════════════════════ 정렬 옵션 선택 ═════════════════════╗");
					System.out.println("║                                                      ║");
					System.out.println("║          1. 오름차순 정렬       2. 내림차순 정렬           ║");
					System.out.println("║                                                      ║");
					System.out.println("╚══════════════════════════════════════════════════════╝");
					System.out.print("▶ 정렬 방식을 선택하세요: ");
					int __no = Integer.parseInt(scan.nextLine());
					switch (__no) {
					case 1:
						// 오름 차순
						Collections.sort(productList);
						System.out.println("오름 차순 정렬 완료");
						break;
					case 2:
						// 내림 차순
						Collections.sort(productList, Collections.reverseOrder());
						System.out.println("내림 차순 정렬 완료");
						break;
					}
				}
					break;
				case 5:
					// 매출 현황
					int _page = 1;
					while (true) {
						int totalPage = salesList.size() / 5;
						int remainValue = salesList.size() % 5;
						if (remainValue != 0) {
							totalPage += 1;
						}
						// 해당되는페이지 시작위치, 끝위치
						int start = 5 * (_page - 1);
						int stop = 5 * (_page - 1) + 5;

						// 마지막페이지일때 나머지값이 있을때 끝위치 1~4증가
						if (_page == totalPage && remainValue != 0) {
							stop = 5 * (_page - 1) + remainValue;
						}
						System.out.printf("전체 %d페이지 / 현재 %d페이지 \n", totalPage, _page);
						for (int i = start; i < stop; i++) {
							System.out.println(salesList.get(i).toString());
						}
						System.out.printf("page[1-%d] (-1 : exit) 페이지선택>", totalPage);
						_page = Integer.parseInt(scan.nextLine());
						if (_page == -1) {
							break;
						}
					}
					break;
				case 6:
					// 종료
					System.out.println("종료");
					break;
				}// end of switch(main case 2)
				break;// ★main★ case 1 break
			} // end of ★main★ case 1
			case 2: {
				System.out.println("╔════════════════════ CUSTOMER MENU ═════════════════════╗");
				System.out.println("║                                                        ║");
				System.out.println("║              1. 구매              2. 영수증 요청  　　  　　 ║");
				System.out.println("║              3. 환불              4. 종료                ║");
				System.out.println("║                                                        ║");
				System.out.println("╚════════════════════════════════════════════════════════╝");
				System.out.print("▶ 메뉴 번호를 선택하세요: ");
				int _no = Integer.parseInt(scan.nextLine());
				switch (_no) {
				case 1:
					// 구매
					for (Stoko i : productList) {
						System.out.println(i.toString());
					}
					System.out.print("구매할 상품명>");
					String productName = scan.nextLine();
					Stoko findProductData = null;
					for (Stoko sto : productList) {
						if (sto.getProductName().equals(productName)) {
							findProductData = sto;
							break;
						}
					}
					if (findProductData == null) {
						System.out.printf("%s 제품을 찾지 못했습니다.\n", productName);
						return;
					}
					System.out.printf("%s 제품 재고 %s\n", productName, findProductData.toString());
					System.out.printf("현재 제품 갯수: %d => 구입할 갯수> ", findProductData.getQuantity());
					int qty = Integer.parseInt(scan.nextLine());
					findProductData.setQuantity(findProductData.getQuantity() - qty);
					System.out.printf("%s 제품 구입 완료\n", productName);
					System.out.printf("구매 후 제품 갯수 : %d\n ", findProductData.getQuantity());
					////////////
					int _totalPrice = qty * findProductData.getPrice();
					Stoko2 data = new Stoko2(salesList.size() + 1, findProductData.getProductName(), qty, _totalPrice,
							findProductData.getStockInDate());
					salesList.add(data);
					//////////////
					// 파일에 저장할 내용을 추가해서 저장한다.
					FileOutputStream fo = new FileOutputStream("Stock/Product.txt");
					PrintStream out = new PrintStream(fo);
					// 구매 후
					// 저장할 내용을 추가해서 저장한다.
					FileOutputStream _fo = new FileOutputStream("Stock/Sales.txt");
					PrintStream _out = new PrintStream(_fo);

					out.print(StokoMain.menuTitle);
					_out.print(StokoMain._menuTitle);
					for (Stoko2 _sto : salesList) {
						System.out.println(_sto.toString());
					}
					// 실제 데이터 저장
					for (Stoko sto : productList) {
						out.printf("\n%s,%d,%d,%s", sto.getProductName(), sto.getQuantity(), sto.getPrice(),
								sto.getStockInDate());
					}
					// 구매 후 실제 데이터 저장
					for (Stoko2 _sto : salesList) {
						_out.printf("\n%d,%s,%d,%d,%s", _sto.getReceiptNo(), _sto.get_productName(),
								_sto.get_quantity(), _sto.get_totalPrice(), _sto.get_stockInDate());
					}
					_out.close();
					_fo.close();

					out.close();
					fo.close();
					// 종료
					System.out.println("구매 후 재고/영수증 반영 완료");
					break;
				case 2:
					// 영수증 출력
					// PK Num에 따른 영수증 출력
					System.out.print("출력할 영수증 번호 입력>");
					int receiptNo = Integer.parseInt(scan.nextLine());
					boolean searchFlag = false;
					for (Stoko2 _data : salesList) {
						if (_data.getReceiptNo() == (receiptNo)) {
							System.out.println(_data);
							searchFlag = true;
						}
					}
					if (searchFlag == false) {
						System.out.printf("%s 로 검색된 이름은 없습니다.\n", receiptNo);
					}

					break;
				case 3:
					// 환불
					FileOutputStream __fo = new FileOutputStream("Stock/Sales.txt");
					PrintStream __out = new PrintStream(__fo);

					System.out.println("삭제할 영수증 번호>");
					int receiptData = Integer.parseInt(scan.nextLine());
					Stoko2 removeReceiptNo = null;
					for (Stoko2 sto : salesList) {
						if (sto.getReceiptNo() == receiptData) {
							removeReceiptNo = sto;
							break;
						}
					}
					if (removeReceiptNo != null) {
						System.out.printf("%s\n삭제가 완료되었습니다.\n", removeReceiptNo.toString());
						salesList.remove(removeReceiptNo);
					} else {
						System.out.printf("%s?? 해당 영수증 번호는 없습니다.\n", receiptData);
					}
					__out.print(StokoMain._menuTitle);
					for (Stoko2 _sto : salesList) {
					}
					// 구매 후 실제 데이터 저장
					for (Stoko2 _sto : salesList) {
						__out.printf("\n%d,%s,%d,%d,%s", _sto.getReceiptNo(), _sto.get_productName(),
								_sto.get_quantity(), _sto.get_totalPrice(), _sto.get_stockInDate());
					}
					__out.close();
					__fo.close();
					break;
				case 4:
					// 종료
					System.out.println("종료 완료(라임 쩔었다)");
					break;
				}// end of switch(main case 2)
				break;// ★main★ case 2 break
			} // end of ★main★ case 2
			case 3:
				stopFlag = true;
				System.out.println("찾아주셔서 감사합니다.");
				break;
			}// end of ★main★ switch

		} // end of while

	}

	public static void inputProductList(ArrayList<Stoko> productList) {
		// 파일에서 가져온다. 보조스트림정의한다.(Scanner)
		FileInputStream fi = null;
		try {
			fi = new FileInputStream("Stock/Product.txt");
			Scanner scan = new Scanner(fi);
			// 첫라인없앤다.
			if (scan.hasNextLine()) {
				// 첫 줄을 받아서 메뉴 타이틀 양식으로? 저장한다.
				StokoMain.menuTitle = scan.nextLine();// 이거 왜 있는건지 모르겠음 이게 첫 줄이래
//				return;
			}

			// 반복문을 한라인씩을 가져와서 =>String tokens => 형변환시켜서 => StudentData 객체 =>ArrayList 입력
			while (true) {
				if (!scan.hasNextLine()) {
					break;
				}
				String data = scan.nextLine();
				String[] tokens = data.split(",");

				String productName = tokens[0];
				int quantity = Integer.parseInt(tokens[1]);
				int price = Integer.parseInt(tokens[2]);
				String stockInDate = tokens[3];

				Stoko sto = new Stoko(productName, quantity, price, stockInDate);

				productList.add(sto);
			}

			System.out.println("파일에서 ProductList 로드가 완료되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("로드가 문제가 있어서 완료하지 못했습니다. 점검바랍니다.");
		}

		finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void inputSalesList(ArrayList<Stoko2> salesList) {
		FileInputStream fi = null;
		try {
			fi = new FileInputStream("Stock/Sales.txt");
			Scanner _scan = new Scanner(fi);
			// 첫라인없앤다.
			if (!_scan.hasNextLine()) {
				System.out.println("파일 정보가 없습니다.");
				return;

			}
			StokoMain._menuTitle = _scan.nextLine();// 이거 왜 있는건지 모르겠음 이게 첫 줄이래

			// 반복문을 한라인씩을 가져와서 =>String tokens => 형변환시켜서 => StudentData 객체 =>ArrayList 입력
			while (true) {
				if (!_scan.hasNextLine()) {
					break;
				}
				String data = _scan.nextLine();
				String[] tokens = data.split(",");

				int receiptNo = Integer.parseInt(tokens[0]);
				String _productName = tokens[1];
				int _quantity = Integer.parseInt(tokens[2]);
				int _totalPrice = Integer.parseInt(tokens[3]);
				String _stockInDate = tokens[4];

				Stoko2 sto = new Stoko2(receiptNo, _productName, _quantity, _totalPrice, _stockInDate);

				salesList.add(sto);
			}

			System.out.println("파일에서 SalesList 로드가 완료되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("로드가 문제가 있어서 완료하지 못했습니다. 점검바랍니다.");
		} finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
