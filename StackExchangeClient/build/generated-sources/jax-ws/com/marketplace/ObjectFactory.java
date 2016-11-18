
package com.marketplace;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.marketplace package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddProduct_QNAME = new QName("http://marketplace.com/", "addProduct");
    private final static QName _AddProductResponse_QNAME = new QName("http://marketplace.com/", "addProductResponse");
    private final static QName _ChangeLikeStatus_QNAME = new QName("http://marketplace.com/", "changeLikeStatus");
    private final static QName _ChangeLikeStatusResponse_QNAME = new QName("http://marketplace.com/", "changeLikeStatusResponse");
    private final static QName _ConfirmPurchase_QNAME = new QName("http://marketplace.com/", "confirmPurchase");
    private final static QName _ConfirmPurchaseResponse_QNAME = new QName("http://marketplace.com/", "confirmPurchaseResponse");
    private final static QName _DeleteProduct_QNAME = new QName("http://marketplace.com/", "deleteProduct");
    private final static QName _DeleteProductResponse_QNAME = new QName("http://marketplace.com/", "deleteProductResponse");
    private final static QName _EditProduct_QNAME = new QName("http://marketplace.com/", "editProduct");
    private final static QName _EditProductResponse_QNAME = new QName("http://marketplace.com/", "editProductResponse");
    private final static QName _GetAllProduct_QNAME = new QName("http://marketplace.com/", "getAllProduct");
    private final static QName _GetAllProductResponse_QNAME = new QName("http://marketplace.com/", "getAllProductResponse");
    private final static QName _GetLike_QNAME = new QName("http://marketplace.com/", "getLike");
    private final static QName _GetLikeResponse_QNAME = new QName("http://marketplace.com/", "getLikeResponse");
    private final static QName _GetLikeStatus_QNAME = new QName("http://marketplace.com/", "getLikeStatus");
    private final static QName _GetLikeStatusResponse_QNAME = new QName("http://marketplace.com/", "getLikeStatusResponse");
    private final static QName _GetPhoto_QNAME = new QName("http://marketplace.com/", "getPhoto");
    private final static QName _GetPhotoPurchase_QNAME = new QName("http://marketplace.com/", "getPhotoPurchase");
    private final static QName _GetPhotoPurchaseResponse_QNAME = new QName("http://marketplace.com/", "getPhotoPurchaseResponse");
    private final static QName _GetPhotoResponse_QNAME = new QName("http://marketplace.com/", "getPhotoResponse");
    private final static QName _GetProductPurchase_QNAME = new QName("http://marketplace.com/", "getProductPurchase");
    private final static QName _GetProductPurchaseResponse_QNAME = new QName("http://marketplace.com/", "getProductPurchaseResponse");
    private final static QName _GetProductSales_QNAME = new QName("http://marketplace.com/", "getProductSales");
    private final static QName _GetProductSalesResponse_QNAME = new QName("http://marketplace.com/", "getProductSalesResponse");
    private final static QName _GetTotalPurchase_QNAME = new QName("http://marketplace.com/", "getTotalPurchase");
    private final static QName _GetTotalPurchaseResponse_QNAME = new QName("http://marketplace.com/", "getTotalPurchaseResponse");
    private final static QName _GetYourProduct_QNAME = new QName("http://marketplace.com/", "getYourProduct");
    private final static QName _GetYourProductResponse_QNAME = new QName("http://marketplace.com/", "getYourProductResponse");
    private final static QName _Hello_QNAME = new QName("http://marketplace.com/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://marketplace.com/", "helloResponse");
    private final static QName _InsertLike_QNAME = new QName("http://marketplace.com/", "insertLike");
    private final static QName _InsertLikeResponse_QNAME = new QName("http://marketplace.com/", "insertLikeResponse");
    private final static QName _Product_QNAME = new QName("http://marketplace.com/", "product");
    private final static QName _Purchase_QNAME = new QName("http://marketplace.com/", "purchase");
    private final static QName _SearchProduct_QNAME = new QName("http://marketplace.com/", "searchProduct");
    private final static QName _SearchProductResponse_QNAME = new QName("http://marketplace.com/", "searchProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddProduct }
     * 
     */
    public AddProduct createAddProduct() {
        return new AddProduct();
    }

    /**
     * Create an instance of {@link AddProductResponse }
     * 
     */
    public AddProductResponse createAddProductResponse() {
        return new AddProductResponse();
    }

    /**
     * Create an instance of {@link ChangeLikeStatus }
     * 
     */
    public ChangeLikeStatus createChangeLikeStatus() {
        return new ChangeLikeStatus();
    }

    /**
     * Create an instance of {@link ChangeLikeStatusResponse }
     * 
     */
    public ChangeLikeStatusResponse createChangeLikeStatusResponse() {
        return new ChangeLikeStatusResponse();
    }

    /**
     * Create an instance of {@link ConfirmPurchase }
     * 
     */
    public ConfirmPurchase createConfirmPurchase() {
        return new ConfirmPurchase();
    }

    /**
     * Create an instance of {@link ConfirmPurchaseResponse }
     * 
     */
    public ConfirmPurchaseResponse createConfirmPurchaseResponse() {
        return new ConfirmPurchaseResponse();
    }

    /**
     * Create an instance of {@link DeleteProduct }
     * 
     */
    public DeleteProduct createDeleteProduct() {
        return new DeleteProduct();
    }

    /**
     * Create an instance of {@link DeleteProductResponse }
     * 
     */
    public DeleteProductResponse createDeleteProductResponse() {
        return new DeleteProductResponse();
    }

    /**
     * Create an instance of {@link EditProduct }
     * 
     */
    public EditProduct createEditProduct() {
        return new EditProduct();
    }

    /**
     * Create an instance of {@link EditProductResponse }
     * 
     */
    public EditProductResponse createEditProductResponse() {
        return new EditProductResponse();
    }

    /**
     * Create an instance of {@link GetAllProduct }
     * 
     */
    public GetAllProduct createGetAllProduct() {
        return new GetAllProduct();
    }

    /**
     * Create an instance of {@link GetAllProductResponse }
     * 
     */
    public GetAllProductResponse createGetAllProductResponse() {
        return new GetAllProductResponse();
    }

    /**
     * Create an instance of {@link GetLike }
     * 
     */
    public GetLike createGetLike() {
        return new GetLike();
    }

    /**
     * Create an instance of {@link GetLikeResponse }
     * 
     */
    public GetLikeResponse createGetLikeResponse() {
        return new GetLikeResponse();
    }

    /**
     * Create an instance of {@link GetLikeStatus }
     * 
     */
    public GetLikeStatus createGetLikeStatus() {
        return new GetLikeStatus();
    }

    /**
     * Create an instance of {@link GetLikeStatusResponse }
     * 
     */
    public GetLikeStatusResponse createGetLikeStatusResponse() {
        return new GetLikeStatusResponse();
    }

    /**
     * Create an instance of {@link GetPhoto }
     * 
     */
    public GetPhoto createGetPhoto() {
        return new GetPhoto();
    }

    /**
     * Create an instance of {@link GetPhotoPurchase }
     * 
     */
    public GetPhotoPurchase createGetPhotoPurchase() {
        return new GetPhotoPurchase();
    }

    /**
     * Create an instance of {@link GetPhotoPurchaseResponse }
     * 
     */
    public GetPhotoPurchaseResponse createGetPhotoPurchaseResponse() {
        return new GetPhotoPurchaseResponse();
    }

    /**
     * Create an instance of {@link GetPhotoResponse }
     * 
     */
    public GetPhotoResponse createGetPhotoResponse() {
        return new GetPhotoResponse();
    }

    /**
     * Create an instance of {@link GetProductPurchase }
     * 
     */
    public GetProductPurchase createGetProductPurchase() {
        return new GetProductPurchase();
    }

    /**
     * Create an instance of {@link GetProductPurchaseResponse }
     * 
     */
    public GetProductPurchaseResponse createGetProductPurchaseResponse() {
        return new GetProductPurchaseResponse();
    }

    /**
     * Create an instance of {@link GetProductSales }
     * 
     */
    public GetProductSales createGetProductSales() {
        return new GetProductSales();
    }

    /**
     * Create an instance of {@link GetProductSalesResponse }
     * 
     */
    public GetProductSalesResponse createGetProductSalesResponse() {
        return new GetProductSalesResponse();
    }

    /**
     * Create an instance of {@link GetTotalPurchase }
     * 
     */
    public GetTotalPurchase createGetTotalPurchase() {
        return new GetTotalPurchase();
    }

    /**
     * Create an instance of {@link GetTotalPurchaseResponse }
     * 
     */
    public GetTotalPurchaseResponse createGetTotalPurchaseResponse() {
        return new GetTotalPurchaseResponse();
    }

    /**
     * Create an instance of {@link GetYourProduct }
     * 
     */
    public GetYourProduct createGetYourProduct() {
        return new GetYourProduct();
    }

    /**
     * Create an instance of {@link GetYourProductResponse }
     * 
     */
    public GetYourProductResponse createGetYourProductResponse() {
        return new GetYourProductResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link InsertLike }
     * 
     */
    public InsertLike createInsertLike() {
        return new InsertLike();
    }

    /**
     * Create an instance of {@link InsertLikeResponse }
     * 
     */
    public InsertLikeResponse createInsertLikeResponse() {
        return new InsertLikeResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Purchase }
     * 
     */
    public Purchase createPurchase() {
        return new Purchase();
    }

    /**
     * Create an instance of {@link SearchProduct }
     * 
     */
    public SearchProduct createSearchProduct() {
        return new SearchProduct();
    }

    /**
     * Create an instance of {@link SearchProductResponse }
     * 
     */
    public SearchProductResponse createSearchProductResponse() {
        return new SearchProductResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "addProduct")
    public JAXBElement<AddProduct> createAddProduct(AddProduct value) {
        return new JAXBElement<AddProduct>(_AddProduct_QNAME, AddProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "addProductResponse")
    public JAXBElement<AddProductResponse> createAddProductResponse(AddProductResponse value) {
        return new JAXBElement<AddProductResponse>(_AddProductResponse_QNAME, AddProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeLikeStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "changeLikeStatus")
    public JAXBElement<ChangeLikeStatus> createChangeLikeStatus(ChangeLikeStatus value) {
        return new JAXBElement<ChangeLikeStatus>(_ChangeLikeStatus_QNAME, ChangeLikeStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeLikeStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "changeLikeStatusResponse")
    public JAXBElement<ChangeLikeStatusResponse> createChangeLikeStatusResponse(ChangeLikeStatusResponse value) {
        return new JAXBElement<ChangeLikeStatusResponse>(_ChangeLikeStatusResponse_QNAME, ChangeLikeStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "confirmPurchase")
    public JAXBElement<ConfirmPurchase> createConfirmPurchase(ConfirmPurchase value) {
        return new JAXBElement<ConfirmPurchase>(_ConfirmPurchase_QNAME, ConfirmPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "confirmPurchaseResponse")
    public JAXBElement<ConfirmPurchaseResponse> createConfirmPurchaseResponse(ConfirmPurchaseResponse value) {
        return new JAXBElement<ConfirmPurchaseResponse>(_ConfirmPurchaseResponse_QNAME, ConfirmPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "deleteProduct")
    public JAXBElement<DeleteProduct> createDeleteProduct(DeleteProduct value) {
        return new JAXBElement<DeleteProduct>(_DeleteProduct_QNAME, DeleteProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "deleteProductResponse")
    public JAXBElement<DeleteProductResponse> createDeleteProductResponse(DeleteProductResponse value) {
        return new JAXBElement<DeleteProductResponse>(_DeleteProductResponse_QNAME, DeleteProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "editProduct")
    public JAXBElement<EditProduct> createEditProduct(EditProduct value) {
        return new JAXBElement<EditProduct>(_EditProduct_QNAME, EditProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "editProductResponse")
    public JAXBElement<EditProductResponse> createEditProductResponse(EditProductResponse value) {
        return new JAXBElement<EditProductResponse>(_EditProductResponse_QNAME, EditProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getAllProduct")
    public JAXBElement<GetAllProduct> createGetAllProduct(GetAllProduct value) {
        return new JAXBElement<GetAllProduct>(_GetAllProduct_QNAME, GetAllProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getAllProductResponse")
    public JAXBElement<GetAllProductResponse> createGetAllProductResponse(GetAllProductResponse value) {
        return new JAXBElement<GetAllProductResponse>(_GetAllProductResponse_QNAME, GetAllProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLike }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getLike")
    public JAXBElement<GetLike> createGetLike(GetLike value) {
        return new JAXBElement<GetLike>(_GetLike_QNAME, GetLike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLikeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getLikeResponse")
    public JAXBElement<GetLikeResponse> createGetLikeResponse(GetLikeResponse value) {
        return new JAXBElement<GetLikeResponse>(_GetLikeResponse_QNAME, GetLikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLikeStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getLikeStatus")
    public JAXBElement<GetLikeStatus> createGetLikeStatus(GetLikeStatus value) {
        return new JAXBElement<GetLikeStatus>(_GetLikeStatus_QNAME, GetLikeStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLikeStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getLikeStatusResponse")
    public JAXBElement<GetLikeStatusResponse> createGetLikeStatusResponse(GetLikeStatusResponse value) {
        return new JAXBElement<GetLikeStatusResponse>(_GetLikeStatusResponse_QNAME, GetLikeStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPhoto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getPhoto")
    public JAXBElement<GetPhoto> createGetPhoto(GetPhoto value) {
        return new JAXBElement<GetPhoto>(_GetPhoto_QNAME, GetPhoto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPhotoPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getPhotoPurchase")
    public JAXBElement<GetPhotoPurchase> createGetPhotoPurchase(GetPhotoPurchase value) {
        return new JAXBElement<GetPhotoPurchase>(_GetPhotoPurchase_QNAME, GetPhotoPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPhotoPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getPhotoPurchaseResponse")
    public JAXBElement<GetPhotoPurchaseResponse> createGetPhotoPurchaseResponse(GetPhotoPurchaseResponse value) {
        return new JAXBElement<GetPhotoPurchaseResponse>(_GetPhotoPurchaseResponse_QNAME, GetPhotoPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPhotoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getPhotoResponse")
    public JAXBElement<GetPhotoResponse> createGetPhotoResponse(GetPhotoResponse value) {
        return new JAXBElement<GetPhotoResponse>(_GetPhotoResponse_QNAME, GetPhotoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getProductPurchase")
    public JAXBElement<GetProductPurchase> createGetProductPurchase(GetProductPurchase value) {
        return new JAXBElement<GetProductPurchase>(_GetProductPurchase_QNAME, GetProductPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getProductPurchaseResponse")
    public JAXBElement<GetProductPurchaseResponse> createGetProductPurchaseResponse(GetProductPurchaseResponse value) {
        return new JAXBElement<GetProductPurchaseResponse>(_GetProductPurchaseResponse_QNAME, GetProductPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getProductSales")
    public JAXBElement<GetProductSales> createGetProductSales(GetProductSales value) {
        return new JAXBElement<GetProductSales>(_GetProductSales_QNAME, GetProductSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getProductSalesResponse")
    public JAXBElement<GetProductSalesResponse> createGetProductSalesResponse(GetProductSalesResponse value) {
        return new JAXBElement<GetProductSalesResponse>(_GetProductSalesResponse_QNAME, GetProductSalesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTotalPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getTotalPurchase")
    public JAXBElement<GetTotalPurchase> createGetTotalPurchase(GetTotalPurchase value) {
        return new JAXBElement<GetTotalPurchase>(_GetTotalPurchase_QNAME, GetTotalPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTotalPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getTotalPurchaseResponse")
    public JAXBElement<GetTotalPurchaseResponse> createGetTotalPurchaseResponse(GetTotalPurchaseResponse value) {
        return new JAXBElement<GetTotalPurchaseResponse>(_GetTotalPurchaseResponse_QNAME, GetTotalPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetYourProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getYourProduct")
    public JAXBElement<GetYourProduct> createGetYourProduct(GetYourProduct value) {
        return new JAXBElement<GetYourProduct>(_GetYourProduct_QNAME, GetYourProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetYourProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "getYourProductResponse")
    public JAXBElement<GetYourProductResponse> createGetYourProductResponse(GetYourProductResponse value) {
        return new JAXBElement<GetYourProductResponse>(_GetYourProductResponse_QNAME, GetYourProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertLike }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "insertLike")
    public JAXBElement<InsertLike> createInsertLike(InsertLike value) {
        return new JAXBElement<InsertLike>(_InsertLike_QNAME, InsertLike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertLikeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "insertLikeResponse")
    public JAXBElement<InsertLikeResponse> createInsertLikeResponse(InsertLikeResponse value) {
        return new JAXBElement<InsertLikeResponse>(_InsertLikeResponse_QNAME, InsertLikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Product }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "product")
    public JAXBElement<Product> createProduct(Product value) {
        return new JAXBElement<Product>(_Product_QNAME, Product.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Purchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "purchase")
    public JAXBElement<Purchase> createPurchase(Purchase value) {
        return new JAXBElement<Purchase>(_Purchase_QNAME, Purchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "searchProduct")
    public JAXBElement<SearchProduct> createSearchProduct(SearchProduct value) {
        return new JAXBElement<SearchProduct>(_SearchProduct_QNAME, SearchProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace.com/", name = "searchProductResponse")
    public JAXBElement<SearchProductResponse> createSearchProductResponse(SearchProductResponse value) {
        return new JAXBElement<SearchProductResponse>(_SearchProductResponse_QNAME, SearchProductResponse.class, null, value);
    }

}
